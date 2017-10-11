package com.woobloo.response.model;

public class Fare {
	private int seats;

	private int actual_cost;

	private Double cost;

	private int savings;

	private String peak_time_charge;

	private String peak_time_text;

	private String discount_string;

	private String fare_string;

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getActual_cost() {
		return actual_cost;
	}

	public void setActual_cost(int actual_cost) {
		this.actual_cost = actual_cost;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public int getSavings() {
		return savings;
	}

	public void setSavings(int savings) {
		this.savings = savings;
	}

	public String getPeak_time_charge() {
		return peak_time_charge;
	}

	public void setPeak_time_charge(String peak_time_charge) {
		this.peak_time_charge = peak_time_charge;
	}

	public String getPeak_time_text() {
		return peak_time_text;
	}

	public void setPeak_time_text(String peak_time_text) {
		this.peak_time_text = peak_time_text;
	}

	public String getDiscount_string() {
		return discount_string;
	}

	public void setDiscount_string(String discount_string) {
		this.discount_string = discount_string;
	}

	public String getFare_string() {
		return fare_string;
	}

	public void setFare_string(String fare_string) {
		this.fare_string = fare_string;
	}

}
