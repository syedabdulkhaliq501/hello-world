package com.woobloo.response.model;

import java.util.ArrayList;

public class Package {
	private String type;

	private String package_id;

	private String package_description;

	private String minimum_distance;

	private String distance_unit;

	private String minimum_time;

	private String time_unit;

	private String base_fare;

	private String minimum_fare;

	private String cost_per_distance;

	private String waiting_cost_per_hour;

	private String ride_cost_per_hour;

	private ArrayList<Object> surcharge;

	private boolean rates_higher_than_usual;

	private boolean rates_lower_than_usual;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPackage_id() {
		return package_id;
	}

	public void setPackage_id(String package_id) {
		this.package_id = package_id;
	}

	public String getPackage_description() {
		return package_description;
	}

	public void setPackage_description(String package_description) {
		this.package_description = package_description;
	}

	public String getMinimum_distance() {
		return minimum_distance;
	}

	public void setMinimum_distance(String minimum_distance) {
		this.minimum_distance = minimum_distance;
	}

	public String getDistance_unit() {
		return distance_unit;
	}

	public void setDistance_unit(String distance_unit) {
		this.distance_unit = distance_unit;
	}

	public String getMinimum_time() {
		return minimum_time;
	}

	public void setMinimum_time(String minimum_time) {
		this.minimum_time = minimum_time;
	}

	public String getTime_unit() {
		return time_unit;
	}

	public void setTime_unit(String time_unit) {
		this.time_unit = time_unit;
	}

	public String getBase_fare() {
		return base_fare;
	}

	public void setBase_fare(String base_fare) {
		this.base_fare = base_fare;
	}

	public String getMinimum_fare() {
		return minimum_fare;
	}

	public void setMinimum_fare(String minimum_fare) {
		this.minimum_fare = minimum_fare;
	}

	public String getCost_per_distance() {
		return cost_per_distance;
	}

	public void setCost_per_distance(String cost_per_distance) {
		this.cost_per_distance = cost_per_distance;
	}

	public String getWaiting_cost_per_hour() {
		return waiting_cost_per_hour;
	}

	public void setWaiting_cost_per_hour(String waiting_cost_per_hour) {
		this.waiting_cost_per_hour = waiting_cost_per_hour;
	}

	public String getRide_cost_per_hour() {
		return ride_cost_per_hour;
	}

	public void setRide_cost_per_hour(String ride_cost_per_hour) {
		this.ride_cost_per_hour = ride_cost_per_hour;
	}

	public ArrayList<Object> getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(ArrayList<Object> surcharge) {
		this.surcharge = surcharge;
	}

	public boolean isRates_higher_than_usual() {
		return rates_higher_than_usual;
	}

	public void setRates_higher_than_usual(boolean rates_higher_than_usual) {
		this.rates_higher_than_usual = rates_higher_than_usual;
	}

	public boolean isRates_lower_than_usual() {
		return rates_lower_than_usual;
	}

	public void setRates_lower_than_usual(boolean rates_lower_than_usual) {
		this.rates_lower_than_usual = rates_lower_than_usual;
	}
}