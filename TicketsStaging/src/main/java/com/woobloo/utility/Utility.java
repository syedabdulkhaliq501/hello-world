package com.woobloo.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woobloo.apigatewayproxy.model.ApiGatewayProxyResponse;
import com.woobloo.contants.UtilityConstants;
import com.woobloo.exception.AppException;

public class Utility {

	public static <T> T jsonToObjcet(String json, Class<T> t) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// JSON from String to Object
		return t.cast(mapper.readValue(json, t));

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

	public static boolean isNotNullOrEmpty(String text) {

		return text != null && !text.isEmpty();
	}

	public static ApiGatewayProxyResponse getErrorResponse(AppException appException) throws Exception {

		return new ApiGatewayProxyResponse(appException.getCode(), UtilityConstants.responseHeaders,
				Utility.objectToJsonString(appException));

	}

	public static java.sql.Date getSqlDate(String date) throws Exception {
		java.util.Date apptDay = null;
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		apptDay = (java.util.Date) df.parse(date);
		return new java.sql.Date(apptDay.getTime());

	}

}
