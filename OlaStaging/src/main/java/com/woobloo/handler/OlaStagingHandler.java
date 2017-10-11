package com.woobloo.handler;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.woobloo.action.handler.OlaHttpHandler;
import com.woobloo.action.handler.WooblooHttpMethods;
import com.woobloo.apigatewayproxy.model.ApiGatewayProxyResponse;
import com.woobloo.apigatewayproxy.model.ApiGatewayRequest;
import com.woobloo.exception.AppException;
import com.woobloo.utility.Utility;

public class OlaStagingHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayProxyResponse> {
	private static Map<String, WooblooHttpMethods> lookupPath = new HashMap<String, WooblooHttpMethods>();
	static {

		lookupPath.put("ola", new OlaHttpHandler());
	}

	public ApiGatewayProxyResponse handleRequest(ApiGatewayRequest request, Context context) {
		ApiGatewayProxyResponse response = null;
		try {
			return lookupPath.get(request.getPath().split("/")[1]).execute(request, context.getLogger());

		} catch (Exception ex) {
			Utility.logExceptionStack(ex, context.getLogger());
			try {
				return Utility.getErrorResponse(new AppException("FAILURE", 403, ex.getMessage()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Utility.logExceptionStack(ex, context.getLogger());
			}
		}

		return response;
	}
}