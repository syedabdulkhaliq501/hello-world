package com.woobloo.response.model;

import java.util.ArrayList;

public class RideEstimateRootResponse {
	private ArrayList<Category> categories;

	private ArrayList<RideEstimate> ride_estimate;
	
	private String message;
	
	private String code;

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public ArrayList<RideEstimate> getRide_estimate() {
		return ride_estimate;
	}

	public void setRide_estimate(ArrayList<RideEstimate> ride_estimate) {
		this.ride_estimate = ride_estimate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
