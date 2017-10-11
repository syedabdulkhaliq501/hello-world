package com.woobloo.response.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackCab {
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequest_type() {
		return request_type;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

	public String getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
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

	public CabDetails getCab_details() {
		return cab_details;
	}

	public void setCab_details(CabDetails cab_details) {
		this.cab_details = cab_details;
	}

	public String getDriver_image_url() {
		return driver_image_url;
	}

	public void setDriver_image_url(String driver_image_url) {
		this.driver_image_url = driver_image_url;
	}

	public double getDriver_rating() {
		return driver_rating;
	}

	public void setDriver_rating(double driver_rating) {
		this.driver_rating = driver_rating;
	}

	public double getPickup_lat() {
		return pickup_lat;
	}

	public void setPickup_lat(double pickup_lat) {
		this.pickup_lat = pickup_lat;
	}

	public double getPickup_lng() {
		return pickup_lng;
	}

	public void setPickup_lng(double pickup_lng) {
		this.pickup_lng = pickup_lng;
	}

	public int getDrop_lat() {
		return drop_lat;
	}

	public void setDrop_lat(int drop_lat) {
		this.drop_lat = drop_lat;
	}

	public int getDrop_lng() {
		return drop_lng;
	}

	public void setDrop_lng(int drop_lng) {
		this.drop_lng = drop_lng;
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

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public String getBooking_status() {
		return booking_status;
	}

	public void setBooking_status(String booking_status) {
		this.booking_status = booking_status;
	}

	public String getShare_ride_url() {
		return share_ride_url;
	}

	public void setShare_ride_url(String share_ride_url) {
		this.share_ride_url = share_ride_url;
	}

	private String request_type;

	private String booking_id;

	private String driver_name;

	private String driver_number;

	private CabDetails cab_details;

	private String driver_image_url;

	private double driver_rating;

	private double pickup_lat;

	private double pickup_lng;

	private int drop_lat;

	private int drop_lng;

	private double driver_lat;

	private double driver_lng;

	private Duration duration;

	private Distance distance;

	private String booking_status;

	private String share_ride_url;
}
