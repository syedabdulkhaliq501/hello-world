package com.woobloo.restclient;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.woobloo.apigatewayproxy.model.ApiGatewayProxyResponse;
import com.woobloo.constants.OlaConstants;
import com.woobloo.ola.model.OlaApigatewayRequest;
import com.woobloo.utility.Utility;

public class JerseyClient {

	public static ApiGatewayProxyResponse restClient(OlaApigatewayRequest request, LambdaLogger lambdaLogger)
			throws Exception, JsonMappingException, IOException {
		Client client = Client.create();
		ClientResponse response = null;
		WebResource webResource = client.resource(request.getEndpointUrl());
		WebResource.Builder builder = webResource.accept(MediaType.APPLICATION_JSON);
		builder.type(MediaType.APPLICATION_JSON);

		request.getRequestheaders().forEach((headerName, headerValue) -> {
			builder.header(headerName, headerValue);
		});

		if (request.getHttpMethod().equals(OlaConstants.HTTP_GET)) {
			response = builder.get(ClientResponse.class);
		}

		else {
			if (Utility.isNotNullOrEmpty(request.getBody())) {
				response = builder.post(ClientResponse.class, request.getBody());
			} else {
				response = builder.post(ClientResponse.class);
			}

		}

		String body = response.getEntity(String.class);
		lambdaLogger.log("jersey  status cabs  ........." + body);

		return new ApiGatewayProxyResponse(response.getStatus(), request.getResponseheaders(), body);
	}

}
