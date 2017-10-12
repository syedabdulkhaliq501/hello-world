package com.woobloo.response.model;


public class Ticket {

	private int ticketId;
	String subscriberId;
	String subscriberName;
	String category;
	String pickup_lat;
	String pickup_lng;
	String categoryDescription;
	String timeLapse;
	
	public String getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}
	public String getSubscriberName() {
		return subscriberName;
	}
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public String getTimeLapse() {
		return timeLapse;
	}
	public void setTimeLapse(String timeLapse) {
		this.timeLapse = timeLapse;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
}
