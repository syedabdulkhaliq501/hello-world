package com.woobloo.ola.model;

import java.util.ArrayList;

public class AvailabeCabs {

	public ArrayList<RideAvailableCab> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<RideAvailableCab> categories) {
		this.categories = categories;
	}

	private ArrayList<RideAvailableCab> categories;
}
