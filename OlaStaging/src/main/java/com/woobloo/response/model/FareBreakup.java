package com.woobloo.response.model;

import java.util.ArrayList;

public class FareBreakup {
	private Object type;

	public Object getType() {
		return this.type;
	}

	public void setType(Object type) {
		this.type = type;
	}

	private Object fare;

	public Object getFare() {
		return this.fare;
	}

	public void setFare(Object fare) {
		this.fare = fare;
	}

	private ArrayList<Object> surcharge;

	public ArrayList<Object> getSurcharge() {
		return this.surcharge;
	}

	public void setSurcharge(ArrayList<Object> surcharge) {
		this.surcharge = surcharge;
	}

	private Integer minimum_distance;

	public Integer getMinimum_distance() {
		return this.minimum_distance;
	}

	public void setMinimum_distance(Integer minimum_distance) {
		this.minimum_distance = minimum_distance;
	}

	private Integer minimum_time;

	public Integer getMinimum_time() {
		return this.minimum_time;
	}

	public void setMinimum_time(Integer minimum_time) {
		this.minimum_time = minimum_time;
	}

	private Object base_fare;

	public Object getBase_fare() {
		return this.base_fare;
	}

	public void setBase_fare(Object base_fare) {
		this.base_fare = base_fare;
	}

	private Object minimum_fare;

	public Object getMinimum_fare() {
		return this.minimum_fare;
	}

	public void setMinimum_fare(Object minimum_fare) {
		this.minimum_fare = minimum_fare;
	}

	private Object cost_per_distance;

	public Object getCost_per_distance() {
		return this.cost_per_distance;
	}

	public void setCost_per_distance(Object cost_per_distance) {
		this.cost_per_distance = cost_per_distance;
	}

	private Integer waiting_cost_per_minute;

	public Integer getWaiting_cost_per_minute() {
		return this.waiting_cost_per_minute;
	}

	public void setWaiting_cost_per_minute(Integer waiting_cost_per_minute) {
		this.waiting_cost_per_minute = waiting_cost_per_minute;
	}

	private Object ride_cost_per_minute;

	public Object getRide_cost_per_minute() {
		return this.ride_cost_per_minute;
	}

	public void setRide_cost_per_minute(Object ride_cost_per_minute) {
		this.ride_cost_per_minute = ride_cost_per_minute;
	}

	private Boolean rates_lower_than_usual;

	public Boolean getRates_lower_than_usual() {
		return this.rates_lower_than_usual;
	}

	public void setRates_lower_than_usual(Boolean rates_lower_than_usual) {
		this.rates_lower_than_usual = rates_lower_than_usual;
	}

	private Boolean rates_higher_than_usual;

	public Boolean geTrates_higher_than_usual() {
		return this.rates_higher_than_usual;
	}

	public void setRates_higher_than_usual(Boolean rates_higher_than_usual) {
		this.rates_higher_than_usual = rates_higher_than_usual;
	}
}
