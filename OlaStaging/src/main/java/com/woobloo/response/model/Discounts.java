package com.woobloo.response.model;

public class Discounts {
	private Object discount_type;

	private Object discount_code;

	private Object discount_mode;

	public Object getDiscount_type() {
		return discount_type;
	}

	public void setDiscount_type(Object discount_type) {
		this.discount_type = discount_type;
	}

	public Object getDiscount_code() {
		return discount_code;
	}

	public void setDiscount_code(Object discount_code) {
		this.discount_code = discount_code;
	}

	public Object getDiscount_mode() {
		return discount_mode;
	}

	public void setDiscount_mode(Object discount_mode) {
		this.discount_mode = discount_mode;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getCashback() {
		return cashback;
	}

	public void setCashback(int cashback) {
		this.cashback = cashback;
	}

	private int discount;

	private int cashback;

}