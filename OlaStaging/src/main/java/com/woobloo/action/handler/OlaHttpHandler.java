package com.woobloo.action.handler;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.woobloo.apigatewayproxy.model.ApiGatewayProxyResponse;
import com.woobloo.apigatewayproxy.model.ApiGatewayRequest;
import com.woobloo.constants.OlaConstants;
import com.woobloo.enums.OlaActionContants;
import com.woobloo.ola.service.OlaRequestService;

public class OlaHttpHandler extends WooblooHttpMethods {

	@Override
	ApiGatewayProxyResponse httpGet(ApiGatewayRequest request, LambdaLogger lambdaLogger) throws Exception {
		// TODO Auto-generated method stub

		if (request.getQueryStringParameters() == null) {
			return OlaRequestService.ridirectForAccessToken();

		}

		if (!request.getQueryStringParameters().getOrDefault(OlaConstants.ACCESS_TOKEN, "N").equals("N")) {
			return OlaRequestService.storeAccessToken(request.getQueryStringParameters(), lambdaLogger);
		}

		return null;
	}

	@Override
	ApiGatewayProxyResponse httpPost(ApiGatewayRequest request, LambdaLogger lambdaLogger) throws Exception {
		// TODO Auto-generated method stub

		OlaActionContants actionContants = OlaActionContants
				.valueOf(request.getPathParameters().get(OlaConstants.REQUEST_ACTION));

		switch (actionContants) {
		case showcabs:
			return OlaRequestService.getCabsOnLocation(request, lambdaLogger);
		case book:
			return OlaRequestService.bookCab(request, lambdaLogger);
		case rideestimate:
			return OlaRequestService.rideEstimate(request, lambdaLogger);

		case track:
			return OlaRequestService.trackCab(request, lambdaLogger);
		case cancelride:
			return OlaRequestService.cancelRide(request, lambdaLogger);

		case cancelreasons:
			return OlaRequestService.listCancellationReasons(request, lambdaLogger);
		}

		return null;
	}

	@Override
	ApiGatewayProxyResponse httpPut(ApiGatewayRequest request, LambdaLogger lambdaLogger) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	ApiGatewayProxyResponse httpDelete(ApiGatewayRequest request, LambdaLogger lambdaLogger) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
