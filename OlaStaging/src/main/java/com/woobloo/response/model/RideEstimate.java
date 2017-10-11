package com.woobloo.response.model;

import java.util.ArrayList;

public class RideEstimate {
	private String category;

	private double distance;

	private int travel_time_in_minutes;

	private int amount_min;

	private int amount_max;

	private Discounts discounts;
	private Integer travel_time_min;

	private Integer travel_time_max;

	private String travel_time_unit;

	private String promotional_message;

	private ArrayList<Fare> fares;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getTravel_time_in_minutes() {
		return travel_time_in_minutes;
	}

	public void setTravel_time_in_minutes(int travel_time_in_minutes) {
		this.travel_time_in_minutes = travel_time_in_minutes;
	}

	public int getAmount_min() {
		return amount_min;
	}

	public void setAmount_min(int amount_min) {
		this.amount_min = amount_min;
	}

	public int getAmount_max() {
		return amount_max;
	}

	public void setAmount_max(int amount_max) {
		this.amount_max = amount_max;
	}

	public Discounts getDiscounts() {
		return discounts;
	}

	public void setDiscounts(Discounts discounts) {
		this.discounts = discounts;
	}

	public Integer getTravel_time_min() {
		return travel_time_min;
	}

	public void setTravel_time_min(Integer travel_time_min) {
		this.travel_time_min = travel_time_min;
	}

	public Integer getTravel_time_max() {
		return travel_time_max;
	}

	public void setTravel_time_max(Integer travel_time_max) {
		this.travel_time_max = travel_time_max;
	}

	public String getTravel_time_unit() {
		return travel_time_unit;
	}

	public void setTravel_time_unit(String travel_time_unit) {
		this.travel_time_unit = travel_time_unit;
	}

	public String getPromotional_message() {
		return promotional_message;
	}

	public void setPromotional_message(String promotional_message) {
		this.promotional_message = promotional_message;
	}

	public ArrayList<Fare> getFares() {
		return fares;
	}

	public void setFares(ArrayList<Fare> fares) {
		this.fares = fares;
	}

}
