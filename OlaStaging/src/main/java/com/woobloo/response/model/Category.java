package com.woobloo.response.model;

import java.util.ArrayList;

public class Category {
	private String id;

	private String display_name;

	private String currency;

	private String distance_unit;

	private String time_unit;

	private int eta;

	private Object distance;

	private String ride_later_enabled;

	private String image;

	private CancellationPolicy cancellation_policy;

	private ArrayList<FareBreakup> fare_breakup;

	private ArrayList<SubCategory> sub_categories;

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

	public String getRide_later_enabled() {
		return ride_later_enabled;
	}

	public void setRide_later_enabled(String ride_later_enabled) {
		this.ride_later_enabled = ride_later_enabled;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public CancellationPolicy getCancellation_policy() {
		return cancellation_policy;
	}

	public void setCancellation_policy(CancellationPolicy cancellation_policy) {
		this.cancellation_policy = cancellation_policy;
	}

	public ArrayList<FareBreakup> getFare_breakup() {
		return fare_breakup;
	}

	public void setFare_breakup(ArrayList<FareBreakup> fare_breakup) {
		this.fare_breakup = fare_breakup;
	}

	public ArrayList<SubCategory> getSub_categories() {
		return sub_categories;
	}

	public void setSub_categories(ArrayList<SubCategory> sub_categories) {
		this.sub_categories = sub_categories;
	}
}