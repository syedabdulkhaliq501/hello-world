package com.woobloo.response.model;

public class CabBookRootReponse {
	private String status;

	private String booking_id;

	private String crn;

	private String driver_name;

	private String driver_number;

	private String cab_type;

	private String cab_number;

	private String car_model;

	private String car_color;

	private int eta;

	private double driver_lat;

	private double driver_lng;

	private String share_ride_url;

	private Otp otp;
	private String message;
	private String code;

	private String surcharge_value;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}

	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	public String getDriver_number() {
		return driver_number;
	}

	public void setDriver_number(String driver_number) {
		this.driver_number = driver_number;
	}

	public String getCab_type() {
		return cab_type;
	}

	public void setCab_type(String cab_type) {
		this.cab_type = cab_type;
	}

	public String getCab_number() {
		return cab_number;
	}

	public void setCab_number(String cab_number) {
		this.cab_number = cab_number;
	}

	public String getCar_model() {
		return car_model;
	}

	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}

	public String getCar_color() {
		return car_color;
	}

	public void setCar_color(String car_color) {
		this.car_color = car_color;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public double getDriver_lat() {
		return driver_lat;
	}

	public void setDriver_lat(double driver_lat) {
		this.driver_lat = driver_lat;
	}

	public double getDriver_lng() {
		return driver_lng;
	}

	public void setDriver_lng(double driver_lng) {
		this.driver_lng = driver_lng;
	}

	public String getShare_ride_url() {
		return share_ride_url;
	}

	public void setShare_ride_url(String share_ride_url) {
		this.share_ride_url = share_ride_url;
	}

	public Otp getOtp() {
		return otp;
	}

	public void setOtp(Otp otp) {
		this.otp = otp;
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

	public String getSurcharge_value() {
		return surcharge_value;
	}

	public void setSurcharge_value(String surcharge_value) {
		this.surcharge_value = surcharge_value;
	}

}
