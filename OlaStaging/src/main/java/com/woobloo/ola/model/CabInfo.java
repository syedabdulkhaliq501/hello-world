package com.woobloo.ola.model;

import com.woobloo.utility.Utility;

public class CabInfo {
	private String booking_id;
	private String pickup_lat;
	private String pickup_lng;
	private String drop_lat = "0";
	private String drop_lng = "0";
	private String pickup_mode;
	private String category;
	private String cancelReson;
	private String seats ;
	
	
	public String getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}
	public String getPickup_lat() {
		return pickup_lat;
	}
	public void setPickup_lat(String pickup_lat) {
		this.pickup_lat = pickup_lat;
	}
	public String getPickup_lng() {
		return pickup_lng;
	}
	public void setPickup_lng(String pickup_lng) {
		this.pickup_lng = pickup_lng;
	}
	public String getDrop_lat() {
		return drop_lat;
	}
	public void setDrop_lat(String drop_lat) {
		this.drop_lat = drop_lat;
	}
	public String getDrop_lng() {
		return drop_lng;
	}
	public void setDrop_lng(String drop_lng) {
		this.drop_lng = drop_lng;
	}
	public String getPickup_mode() {
		return pickup_mode;
	}
	public void setPickup_mode(String pickup_mode) {
		this.pickup_mode = pickup_mode;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCancelReson() {
		return cancelReson;
	}
	public void setCancelReson(String cancelReson) {
		this.cancelReson = cancelReson;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	
	public String isValid(){
		if(!Utility.isNotNullOrEmpty(this.pickup_lat)){
			return "pickup_lat can not be empty";
			
		}
		if(!Utility.isNotNullOrEmpty(this.pickup_lng)){
			return "pickup_lng can not be empty";
			
		}
		if(!Utility.isNotNullOrEmpty(this.drop_lat)){
			return "drop_lat can not be empty";
			
		}
		if(!Utility.isNotNullOrEmpty(this.drop_lng)){
			return "drop_lng can not be empty";
			
		}
		if(!Utility.isNotNullOrEmpty(this.pickup_mode)){
			return "pickup_mode can not be empty";
			
		}
		if(!Utility.isNotNullOrEmpty(this.category)){
			return "category can not be empty";
			
		}
		
		return "";
		
		
	}
	
}
