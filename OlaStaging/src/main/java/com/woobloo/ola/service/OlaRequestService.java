package com.woobloo.ola.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woobloo.apigatewayproxy.model.ApiGatewayProxyResponse;
import com.woobloo.apigatewayproxy.model.ApiGatewayRequest;
import com.woobloo.constants.OConst;
import com.woobloo.constants.OlaConstants;
import com.woobloo.database.DBManager;
import com.woobloo.exception.AppException;
import com.woobloo.ola.model.AvailabeCabs;
import com.woobloo.ola.model.CabInfo;
import com.woobloo.ola.model.OlaApigatewayRequest;
import com.woobloo.ola.model.RideAvailableCab;
import com.woobloo.response.model.CabBookRootReponse;
import com.woobloo.response.model.RideEstimate;
import com.woobloo.response.model.RideEstimateRootResponse;
import com.woobloo.response.model.RideEstimateUiResponse;
import com.woobloo.response.model.RideEstimateUiResponseList;
import com.woobloo.response.model.ShowCabsRootResponse;
import com.woobloo.response.model.TrackCab;
import com.woobloo.restclient.JerseyClient;
import com.woobloo.utility.Utility;

public class OlaRequestService {

	public static ApiGatewayProxyResponse ridirectForAccessToken() {
		String body = OlaConstants.REDIRECT_FOR_TOKEN;

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "text/html");

		return new ApiGatewayProxyResponse(200, headers, body);
	}

	public static ApiGatewayProxyResponse storeAccessToken(Map<String, String> request, LambdaLogger logger) {

		PreparedStatement preparedStatement = null;
		Connection con = null;

		DBManager dbManager = null;

		try {
			dbManager = new DBManager();
			con = dbManager.getDBConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(
					"INSERT INTO OLA_TOKENS (access_token, scope,token_type,state,expires_in,dt_created) VALUES (?, ?, ?, ?, ?,CURRENT_TIMESTAMP)");
			preparedStatement = con.prepareStatement(sql.toString());
			preparedStatement.setString(1, request.get(OlaConstants.ACCESS_TOKEN));
			preparedStatement.setString(2, request.get(OlaConstants.SCOPE));
			preparedStatement.setString(3, request.get(OlaConstants.TOKEN_TYPE));
			preparedStatement.setString(4, request.get(OlaConstants.State));
			preparedStatement.setInt(5, Integer.parseInt(request.get(OlaConstants.EXPIRES_IN)));

			preparedStatement.executeUpdate();

		}

		catch (Exception ex) {
			Utility.logExceptionStack(ex, logger);
		} finally {
			DBManager.endSQLActivity(null, preparedStatement, con);
			logger.log("processing ...............end");
		}

		return new ApiGatewayProxyResponse(200, null, "");
	}

	/*
	 * final CatService catService = LambdaInvokerFactory.builder()
	 * .lambdaClient(AWSLambdaClientBuilder.defaultClient())
	 * .build(CatService.class);
	 * 
	 * String re=catService.countCats("hiiii");
	 * context.getLogger().log("2nd lambda reply is: "+re);
	 * 
	 * HashMap<String , String> hashMap =new HashMap <String,String>();
	 * hashMap.put("Content-Type", "text/html"); BookCab bookCab=new BookCab();
	 * 
	 * bookCab.setPickup_lat(13.007046f); bookCab.setPickup_lng(77.688839f);
	 * bookCab.setCategory("mini"); bookCab.setPickup_mode("NOW"); // response =
	 * new ApiGatewayProxyResponse(200, hashMap,
	 * olaRequestService.getCabsOnLocation()); response = new
	 * ApiGatewayProxyResponse(200, hashMap,
	 * olaRequestService.bookCab(bookCab));
	 * 
	 * 
	 * return null; }
	 */

	static public ApiGatewayProxyResponse getCabsOnLocation(ApiGatewayRequest apiGatewayRequest,
			LambdaLogger lambdaLogger) throws Exception {
		// .resource(OlaConstants.OLA_URL+"bookings/create?pickup_lat=13.007046&pickup_lng=77.688839&pickup_mode=NOW&category=mini");

		// .resource(OlaConstants.OLA_URL+"bookings/create?pickup_lat=13.007046&pickup_lng=77.688839&pickup_mode=NOW&category=mini");
		CabInfo cab = Utility.jsonToObjcet(apiGatewayRequest.getBody(), CabInfo.class);
		HashMap<String, String> uriParams = new HashMap<>();
		uriParams.put(OlaConstants.PICKUP_LAT, cab.getPickup_lat());
		uriParams.put(OlaConstants.PICKUP_LNG, cab.getPickup_lng());

		String endpointUrl = OlaConstants.OLA_URL + OlaConstants.GETALLCABS_URI + Utility.prepareUriParams(uriParams);

		OlaApigatewayRequest olaApigatewayRequest = new OlaApigatewayRequest();
		olaApigatewayRequest.setEndpointUrl(endpointUrl);
		olaApigatewayRequest.setHttpMethod(OlaConstants.HTTP_GET);
		olaApigatewayRequest.setRequestheaders(Utility.httpGetRequestHeaders());
		olaApigatewayRequest.setResponseheaders(OConst.responseHeaders);

		ApiGatewayProxyResponse apiGatewayProxyResponse = JerseyClient.restClient(olaApigatewayRequest, lambdaLogger);
		ObjectMapper mapper = new ObjectMapper();
		ShowCabsRootResponse myPojo = mapper.readValue(apiGatewayProxyResponse.getBody(), ShowCabsRootResponse.class);
		if (Utility.isNotNullOrEmpty(myPojo.getMessage())) {
			return Utility.getErrorResponse(new AppException("FAILURE", 400, myPojo.getMessage()));
		}
		
		AvailabeCabs availabeCabs = new AvailabeCabs();
		ArrayList<RideAvailableCab> cabs = new ArrayList<>();
		myPojo.getCategories().forEach(item -> {

			if (!(item.getId().equalsIgnoreCase("rental") || item.getId().equalsIgnoreCase("outstation"))) {
				RideAvailableCab rideAvailableCab = new RideAvailableCab();
				rideAvailableCab.setId(item.getId());
				rideAvailableCab.setDisplay_name(item.getDisplay_name());
				rideAvailableCab.setEta(item.getEta());
				item.getFare_breakup().forEach(action -> {
					if (action.getType().toString().equalsIgnoreCase("flat_rate")) {
						rideAvailableCab.setCost_per_distance(action.getCost_per_distance());
						/*
						 * rideAvailableCab.setBase_fare(action.getBase_fare());
						 * rideAvailableCab.setRide_cost_per_minute(action.
						 * getRide_cost_per_minute());
						 */

						Double base = Double.parseDouble(action.getBase_fare().toString());

						rideAvailableCab.setTotalCost(base);
					}

					if (item.getId().equalsIgnoreCase("share")) {
						rideAvailableCab.setTotalCost(54.0);

					}
				});

				rideAvailableCab.setImage(item.getImage());
				cabs.add(rideAvailableCab);
			}
		});
		availabeCabs.setCategories(cabs);
		apiGatewayProxyResponse.setBody(Utility.objectToJsonString(availabeCabs));
		return apiGatewayProxyResponse;

	}

	static public ApiGatewayProxyResponse rideEstimate(ApiGatewayRequest apiGatewayRequest, LambdaLogger lambdaLogger)
			throws Exception {
		// .resource(OlaConstants.OLA_URL+"bookings/create?pickup_lat=13.007046&pickup_lng=77.688839&pickup_mode=NOW&category=mini");

		// .resource(OlaConstants.OLA_URL+"bookings/create?pickup_lat=13.007046&pickup_lng=77.688839&pickup_mode=NOW&category=mini");
		CabInfo cab = Utility.jsonToObjcet(apiGatewayRequest.getBody(), CabInfo.class);
		HashMap<String, String> uriParams = new HashMap<>();
		uriParams.put(OlaConstants.PICKUP_LAT, cab.getPickup_lat());
		uriParams.put(OlaConstants.PICKUP_LNG, cab.getPickup_lng());
		uriParams.put(OlaConstants.DROP_LAT, cab.getDrop_lat());
		uriParams.put(OlaConstants.DROP_LNG, cab.getDrop_lng());
		lambdaLogger.log(" body is + " + apiGatewayRequest.getBody());
		String endpointUrl = OlaConstants.OLA_URL + OlaConstants.GETALLCABS_URI + Utility.prepareUriParams(uriParams);

		OlaApigatewayRequest olaApigatewayRequest = new OlaApigatewayRequest();
		olaApigatewayRequest.setEndpointUrl(endpointUrl);
		olaApigatewayRequest.setHttpMethod(OlaConstants.HTTP_GET);
		olaApigatewayRequest.setRequestheaders(Utility.httpGetRequestHeaders());
		olaApigatewayRequest.setResponseheaders(OConst.responseHeaders);

		ApiGatewayProxyResponse apiGatewayProxyResponse = JerseyClient.restClient(olaApigatewayRequest, lambdaLogger);

		RideEstimateRootResponse rideEstimateRootResponse = Utility.jsonToObjcet(apiGatewayProxyResponse.getBody(),
				RideEstimateRootResponse.class);
		RideEstimateUiResponseList rideEstimateUiResponseList = new RideEstimateUiResponseList();
		ArrayList<RideEstimateUiResponse> cabs = new ArrayList<>();
		if (Utility.isNotNullOrEmpty(rideEstimateRootResponse.getMessage())) {
			return Utility.getErrorResponse(new AppException("FAILURE", 400, rideEstimateRootResponse.getMessage()));
		}
		if (rideEstimateRootResponse.getRide_estimate() != null) {
			Map<String, RideEstimate> rideEstimateMap = rideEstimateRootResponse.getRide_estimate().stream()
					.collect(Collectors.toMap(RideEstimate::getCategory, Function.identity()));
			rideEstimateRootResponse.getCategories().forEach(item -> {

				if (!(item.getId().equalsIgnoreCase("rental") || item.getId().equalsIgnoreCase("outstation"))) {
					RideEstimateUiResponse rideEstimateUIResponse = new RideEstimateUiResponse();
					rideEstimateUIResponse.setId(item.getId());
					rideEstimateUIResponse.setDisplay_name(item.getDisplay_name());
					rideEstimateUIResponse.setEta(item.getEta());

					item.getFare_breakup().forEach(action -> {
						if (action.getType().toString().equalsIgnoreCase("flat_rate")) {
							rideEstimateUIResponse.setCost_per_distance(action.getCost_per_distance());
							rideEstimateUIResponse.setTotalCost(Double.parseDouble(action.getBase_fare().toString()));
						}
					});
					if (rideEstimateMap.containsKey((item.getId().trim()))) {
						RideEstimate estimate = rideEstimateMap.get(item.getId());

						/*
						 * rideEstimateUIResponse.setDistance(estimate.
						 * getDistance() );
						 * rideEstimateUIResponse.setTravel_time_in_minutes(
						 * estimate .getTravel_time_in_minutes());
						 * rideEstimateUIResponse.setAmount_max(estimate.
						 * getAmount_max());
						 * 
						 * rideEstimateUIResponse.setAmount_min(estimate.
						 * getAmount_min());
						 */
						if (item.getId().equalsIgnoreCase("share")) {
							/*
							 * rideEstimateUIResponse.setTravel_time_in_minutes(
							 * estimate.getTravel_time_min());
							 * rideEstimateUIResponse.setAmount_min(estimate.
							 * getFares().get(0).getCost());
							 */
							rideEstimateUIResponse.setTotalCost(estimate.getFares().get(0).getCost());
						}

						else {

							rideEstimateUIResponse
									.setTotalCost(rideEstimateUIResponse.getTotalCost() + estimate.getAmount_max());
						}
					}
					rideEstimateUIResponse.setImage(item.getImage());
					cabs.add(rideEstimateUIResponse);
				}
			});

			rideEstimateUiResponseList.setCategories(cabs);
			apiGatewayProxyResponse.setBody(Utility.objectToJsonString(rideEstimateUiResponseList));
		}

		return apiGatewayProxyResponse;

	}

	static public ApiGatewayProxyResponse bookCab(ApiGatewayRequest request, LambdaLogger lambdaLogger) {
		ApiGatewayProxyResponse apiGatewayProxyResponse = null;
		try {
			// .resource(OlaConstants.OLA_URL+"bookings/create?pickup_lat=13.007046&pickup_lng=77.688839&pickup_mode=NOW&category=mini");

			// .resource(OlaConstants.OLA_URL+"bookings/create?pickup_lat=13.007046&pickup_lng=77.688839&pickup_mode=NOW&category=mini");

			CabInfo cab = Utility.jsonToObjcet(request.getBody(), CabInfo.class);
			HashMap<String, String> uriParams = new HashMap<>();
			uriParams.put(OlaConstants.PICKUP_LAT, cab.getPickup_lat());
			uriParams.put(OlaConstants.PICKUP_LNG, cab.getPickup_lng());
			uriParams.put(OlaConstants.DROP_LAT, cab.getDrop_lat());
			uriParams.put(OlaConstants.DROP_LNG, cab.getDrop_lng());
			uriParams.put(OlaConstants.PICKUP_MODE, cab.getPickup_mode());
			uriParams.put(OlaConstants.CATEGORY, cab.getCategory());
			if (cab.getCategory().equalsIgnoreCase("share"))
				uriParams.put(OlaConstants.SEATS, cab.getSeats());

			String endpointUrl = OlaConstants.OLA_URL + OlaConstants.BOOK_URI + Utility.prepareUriParams(uriParams);

			OlaApigatewayRequest olaApigatewayRequest = new OlaApigatewayRequest();
			olaApigatewayRequest.setEndpointUrl(endpointUrl);
			olaApigatewayRequest.setHttpMethod(OlaConstants.HTTP_POST);
			olaApigatewayRequest.setRequestheaders(Utility.httpPostRequestHeaders());
			olaApigatewayRequest.setResponseheaders(OConst.responseHeaders);

			apiGatewayProxyResponse = JerseyClient.restClient(olaApigatewayRequest, lambdaLogger);
			ObjectMapper mapper = new ObjectMapper();
			CabBookRootReponse cabBookReponse = mapper.readValue(apiGatewayProxyResponse.getBody(),
					CabBookRootReponse.class);
			apiGatewayProxyResponse.setBody(Utility.objectToJsonString(cabBookReponse));
			return apiGatewayProxyResponse;
		} catch (Exception ex) {

			Utility.logExceptionStack(ex, lambdaLogger);
		}
		return apiGatewayProxyResponse;
	}

	static public ApiGatewayProxyResponse cancelRide(ApiGatewayRequest request, LambdaLogger lambdaLogger) {
		ApiGatewayProxyResponse apiGatewayProxyResponse = null;
		try {
			// .resource(OlaConstants.OLA_URL+"bookings/create?pickup_lat=13.007046&pickup_lng=77.688839&pickup_mode=NOW&category=mini");

			// .resource(OlaConstants.OLA_URL+"bookings/create?pickup_lat=13.007046&pickup_lng=77.688839&pickup_mode=NOW&category=mini");

			HashMap<String, String> uriParams = new HashMap<>();

			CabInfo cab = Utility.jsonToObjcet(request.getBody(), CabInfo.class);

			String requestBody = "{\"booking_id\":\"" + cab.getBooking_id() + "\",\"reason\":\"" + cab.getCancelReson()
					+ "\"}";

			String endpointUrl = OlaConstants.OLA_URL + OlaConstants.CANCEL_RIDE_URI
					+ Utility.prepareUriParams(uriParams);

			OlaApigatewayRequest olaApigatewayRequest = new OlaApigatewayRequest();
			olaApigatewayRequest.setEndpointUrl(endpointUrl);
			olaApigatewayRequest.setHttpMethod(OlaConstants.HTTP_POST);
			olaApigatewayRequest.setBody(requestBody);
			olaApigatewayRequest.setRequestheaders(Utility.httpPostRequestHeaders());
			olaApigatewayRequest.setResponseheaders(OConst.responseHeaders);

			apiGatewayProxyResponse = JerseyClient.restClient(olaApigatewayRequest, lambdaLogger);

		} catch (Exception ex) {

			Utility.logExceptionStack(ex, lambdaLogger);
		}
		return apiGatewayProxyResponse;
	}

	static public ApiGatewayProxyResponse trackCab(ApiGatewayRequest request, LambdaLogger lambdaLogger) {
		ApiGatewayProxyResponse apiGatewayProxyResponse = null;
		try {

			// .resource(OlaConstants.OLA_URL+"bookings/create?pickup_lat=13.007046&pickup_lng=77.688839&pickup_mode=NOW&category=mini");
			CabInfo cab = Utility.jsonToObjcet(request.getBody(), CabInfo.class);
			HashMap<String, String> uriParams = new HashMap<>();
			uriParams.put(OlaConstants.BOOKING_ID, cab.getBooking_id());
			String endpointUrl = OlaConstants.OLA_URL + OlaConstants.TRACK_CAB_URI
					+ Utility.prepareUriParams(uriParams);

			OlaApigatewayRequest olaApigatewayRequest = new OlaApigatewayRequest();
			olaApigatewayRequest.setEndpointUrl(endpointUrl);
			olaApigatewayRequest.setHttpMethod(OlaConstants.HTTP_GET);
			olaApigatewayRequest.setRequestheaders(Utility.httpGetRequestHeadersWithAuthorization());
			olaApigatewayRequest.setResponseheaders(OConst.responseHeaders);

			apiGatewayProxyResponse = JerseyClient.restClient(olaApigatewayRequest, lambdaLogger);

			ObjectMapper mapper = new ObjectMapper();
			TrackCab trackCab = mapper.readValue(apiGatewayProxyResponse.getBody(), TrackCab.class);
			apiGatewayProxyResponse.setBody(Utility.objectToJsonString(trackCab));
			return apiGatewayProxyResponse;

		} catch (Exception ex) {

			Utility.logExceptionStack(ex, lambdaLogger);
		}
		return apiGatewayProxyResponse;
	}

	static public ApiGatewayProxyResponse listCancellationReasons(ApiGatewayRequest request, LambdaLogger lambdaLogger)
			throws Exception {
		ApiGatewayProxyResponse apiGatewayProxyResponse = null;
		try {

			// .resource(OlaConstants.OLA_URL+"bookings/create?pickup_lat=13.007046&pickup_lng=77.688839&pickup_mode=NOW&category=mini");
			CabInfo cab = Utility.jsonToObjcet(request.getBody(), CabInfo.class);
			HashMap<String, String> uriParams = new HashMap<>();
			uriParams.put(OlaConstants.CATEGORY, cab.getCategory());
			uriParams.put(OlaConstants.PICKUP_LAT, cab.getPickup_lat());
			uriParams.put(OlaConstants.PICKUP_LNG, cab.getPickup_lng());
			String endpointUrl = OlaConstants.OLA_URL + OlaConstants.CANCELLATION_REASONS_URI
					+ Utility.prepareUriParams(uriParams);

			OlaApigatewayRequest olaApigatewayRequest = new OlaApigatewayRequest();
			olaApigatewayRequest.setEndpointUrl(endpointUrl);
			olaApigatewayRequest.setHttpMethod(OlaConstants.HTTP_GET);
			olaApigatewayRequest.setRequestheaders(Utility.httpGetRequestHeadersWithAuthorization());
			olaApigatewayRequest.setResponseheaders(OConst.responseHeaders);

			apiGatewayProxyResponse = JerseyClient.restClient(olaApigatewayRequest, lambdaLogger);

		} catch (Exception ex) {

			Utility.logExceptionStack(ex, lambdaLogger);

		}
		return apiGatewayProxyResponse;
	}

}
