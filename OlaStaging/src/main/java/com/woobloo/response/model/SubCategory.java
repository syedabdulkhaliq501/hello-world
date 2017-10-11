package com.woobloo.response.model;

import java.util.ArrayList;

public class SubCategory {
	private String id;

	private String display_name;

	private String currency;

	private String distance_unit;

	private String time_unit;

	private int eta;

	private Object distance;

	private ArrayList<Package> packages;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDistance_unit() {
		return distance_unit;
	}

	public void setDistance_unit(String distance_unit) {
		this.distance_unit = distance_unit;
	}

	public String getTime_unit() {
		return time_unit;
	}

	public void setTime_unit(String time_unit) {
		this.time_unit = time_unit;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public Object getDistance() {
		return distance;
	}

	public void setDistance(Object distance) {
		this.distance = distance;
	}

	public ArrayList<Package> getPackages() {
		return packages;
	}

	public void setPackages(ArrayList<Package> packages) {
		this.packages = packages;
	}
}