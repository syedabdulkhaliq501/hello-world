package com.woobloo.response.model;

import java.util.ArrayList;

public class ShowCabsRootResponse {
	private ArrayList<Category> categories;

	private RideEstimate ride_estimate;
private String message;
	
	private String code;
	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public RideEstimate getRide_estimate() {
		return ride_estimate;
	}

	public void setRide_estimate(RideEstimate ride_estimate) {
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