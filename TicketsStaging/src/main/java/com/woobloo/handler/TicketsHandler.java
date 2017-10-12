package com.woobloo.handler;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.woobloo.action.handler.TicketsHttpHandler;
import com.woobloo.action.handler.WooblooHttpMethods;
import com.woobloo.apigatewayproxy.model.ApiGatewayProxyResponse;
import com.woobloo.apigatewayproxy.model.ApiGatewayRequest;
import com.woobloo.exception.AppException;
import com.woobloo.utility.Utility;

public class TicketsHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayProxyResponse> {

	private static Map<String, WooblooHttpMethods> lookupPath = new HashMap<String, WooblooHttpMethods>();
	static {
		lookupPath.put("tickets", new TicketsHttpHandler());
	}

	@Override
	public ApiGatewayProxyResponse handleRequest(ApiGatewayRequest request, Context context) {
		ApiGatewayProxyResponse apiGatewayProxyResponse = null;
		context.getLogger().log("starting line .......");
		context.getLogger().log("path is  ......." + request.getPath());
		context.getLogger().log("resource is  ......." + request.getResource());

		try {

			return lookupPath.get(request.getPath().split("/")[1]).execute(request, context.getLogger());

		} catch (Exception e) {
			Utility.logExceptionStack(e, context.getLogger());
			try {
				return Utility.getErrorResponse(new AppException("FAILURE", 403, e.getMessage()));
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				Utility.logExceptionStack(ex, context.getLogger());
			}

		}

		return apiGatewayProxyResponse;

	}

}
