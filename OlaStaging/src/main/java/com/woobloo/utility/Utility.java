package com.woobloo.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woobloo.apigatewayproxy.model.ApiGatewayProxyResponse;
import com.woobloo.constants.OConst;
import com.woobloo.constants.OlaConstants;
import com.woobloo.database.DBManager;
import com.woobloo.exception.AppException;

public class Utility {

	public static <T> T jsonToObjcet(String json, Class<T> t) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// JSON from String to Object
		return t.cast(mapper.readValue(json, t));

	}

	public static String prepareUriParams(Map<String, String> requestparams) {
		StringBuffer buffer = new StringBuffer("?");
		requestparams.forEach((param, value) -> {

			buffer.append(param + "=" + value + "&");
		});

		return buffer.substring(0, buffer.length() - 1);
	}

	public static String objectToJsonString(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		// Object to JSON in String
		return mapper.writeValueAsString(object);

	}

	public static void logExceptionStack(Exception exception, LambdaLogger lambdaLogger) {

		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		exception.printStackTrace(printWriter);
		lambdaLogger.log("Exception occured" + stringWriter.toString());

	}

	public static String getAccessTokenDb() {
		DBManager dbManager = new DBManager();
		Connection connection = dbManager.getDBConnection();
		String token = "";
		ResultSet rs = null;
		try {
			rs = connection.createStatement()
					.executeQuery("select access_token from OLA_TOKENS ORDER BY DT_CREATED desc limit 1");
			if (rs.next()) {
				token = rs.getString("access_token");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBManager.endSQLActivity(rs, null, connection);
		return token;
	}

	public static Map<String, String> httpPostRequestHeaders() {

		Map<String, String> requestHeaders = new HashMap<String, String>();

		requestHeaders.put(OlaConstants.AUTHORIZATION, "Bearer " + Utility.getAccessTokenDb().trim());
		requestHeaders.put(OlaConstants.X_APP_TOKEN, OlaConstants.X_APP_TOKEN_VALUE);
		requestHeaders.put(OlaConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		return requestHeaders;
	}

	public static Map<String, String> httpGetRequestHeaders() {

		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put(OlaConstants.X_APP_TOKEN, OlaConstants.X_APP_TOKEN_VALUE);
		requestHeaders.put(OlaConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		return requestHeaders;
	}

	public static Map<String, String> httpGetRequestHeadersWithAuthorization() {

		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put(OlaConstants.AUTHORIZATION, "Bearer " + Utility.getAccessTokenDb().trim());
		requestHeaders.put(OlaConstants.X_APP_TOKEN, OlaConstants.X_APP_TOKEN_VALUE);
		requestHeaders.put(OlaConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		return requestHeaders;
	}

	public static boolean isNotNullOrEmpty(String text) {

		return text != null && !text.isEmpty();
	}
	
	public static ApiGatewayProxyResponse getErrorResponse(AppException appException) throws Exception{
		
	return	new ApiGatewayProxyResponse(appException.getCode(), OConst.responseHeaders, Utility.objectToJsonString(appException));
		
	}

}
