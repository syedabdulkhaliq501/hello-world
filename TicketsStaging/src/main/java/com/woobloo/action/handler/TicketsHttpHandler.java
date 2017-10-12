package com.woobloo.action.handler;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.woobloo.apigatewayproxy.model.ApiGatewayProxyResponse;
import com.woobloo.apigatewayproxy.model.ApiGatewayRequest;
import com.woobloo.services.AgentTicketServices;

public class TicketsHttpHandler extends WooblooHttpMethods {

	@Override
	public ApiGatewayProxyResponse httpGet(ApiGatewayRequest request, LambdaLogger lambdaLogger) throws Exception {
		// TODO Auto-generated method stub
		return AgentTicketServices.getNewTicketsDetails(lambdaLogger, request.getPathParameters().get("ticketType"));
	}

	@Override
	public ApiGatewayProxyResponse httpPost(ApiGatewayRequest request, LambdaLogger lambdaLogger) throws Exception {
		// TODO Auto-generated method stub

		return AgentTicketServices.createNewTicket(lambdaLogger, request);

	}

	@Override
	public ApiGatewayProxyResponse httpPut(ApiGatewayRequest request, LambdaLogger lambdaLogger) throws Exception {
		// TODO Auto-generated method stub
		return AgentTicketServices.updateTicketStatus(lambdaLogger, request);
	}

	@Override
	public ApiGatewayProxyResponse httpDelete(ApiGatewayRequest request, LambdaLogger lambdaLogger) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
