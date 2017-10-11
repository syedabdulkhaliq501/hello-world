package com.woobloo.ola.model;

import java.util.Map;

public class OlaApigatewayRequest {

	private String endpointUrl;
	private String httpMethod;
	private Map<String, String> requestheaders;
	private Map<String, String> responseheaders;
	private String body;

	public String getEndpointUrl() {
		return endpointUrl;
	}

	public void setEndpointUrl(String endpointUrl) {
		this.endpointUrl = endpointUrl;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public Map<String, String> getRequestheaders() {
		return requestheaders;
	}

	public void setRequestheaders(Map<String, String> requestheaders) {
		this.requestheaders = requestheaders;
	}

	public Map<String, String> getResponseheaders() {
		return responseheaders;
	}

	public void setResponseheaders(Map<String, String> responseheaders) {
		this.responseheaders = responseheaders;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
