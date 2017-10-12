package com.woobloo.action.handler;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.woobloo.apigatewayproxy.model.ApiGatewayProxyResponse;
import com.woobloo.apigatewayproxy.model.ApiGatewayRequest;
import com.woobloo.enums.HttpMethodsEnum;

public abstract class WooblooHttpMethods {

	public ApiGatewayProxyResponse execute(ApiGatewayRequest request, LambdaLogger lambdaLogger) throws Exception {
		// TODO Auto-generated method stub
		HttpMethodsEnum methodsEnum = HttpMethodsEnum.valueOf(request.getHttpMethod());

		switch (methodsEnum) {
		case GET:
			return httpGet(request, lambdaLogger);
		case POST:
			return httpPost(request, lambdaLogger);
		case PUT:
			return httpPut(request, lambdaLogger);
		case DELETE:
			return httpDelete(request, lambdaLogger);

		}

		return null;

	}
	
	public abstract ApiGatewayProxyResponse httpGet(ApiGatewayRequest request,LambdaLogger lambdaLogger) throws Exception;
	public abstract ApiGatewayProxyResponse httpPost(ApiGatewayRequest request,LambdaLogger lambdaLogger)throws Exception;
	public abstract ApiGatewayProxyResponse httpPut(ApiGatewayRequest request,LambdaLogger lambdaLogger)throws Exception;
	public abstract ApiGatewayProxyResponse httpDelete(ApiGatewayRequest request,LambdaLogger lambdaLogger)throws Exception;
	
	
}
