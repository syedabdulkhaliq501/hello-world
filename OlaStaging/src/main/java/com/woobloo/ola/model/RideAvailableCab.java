package com.woobloo.ola.model;

public class RideAvailableCab {
	private String id;
	private String display_name;
	private int eta;
	private Object cost_per_distance;
	private String image;
	private Double totalCost;

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

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public Object getCost_per_distance() {
		return cost_per_distance;
	}

	public void setCost_per_distance(Object cost_per_distance) {
		this.cost_per_distance = cost_per_distance;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

}
