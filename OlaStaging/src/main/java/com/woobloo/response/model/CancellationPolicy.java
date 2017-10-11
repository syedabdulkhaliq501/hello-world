package com.woobloo.response.model;

public class CancellationPolicy {
	private int cancellation_charge;

	private String currency;

	private int cancellation_charge_applies_after_time;

	private String time_unit;

	public int getCancellation_charge() {
		return cancellation_charge;
	}

	public void setCancellation_charge(int cancellation_charge) {
		this.cancellation_charge = cancellation_charge;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getCancellation_charge_applies_after_time() {
		return cancellation_charge_applies_after_time;
	}

	public void setCancellation_charge_applies_after_time(int cancellation_charge_applies_after_time) {
		this.cancellation_charge_applies_after_time = cancellation_charge_applies_after_time;
	}

	public String getTime_unit() {
		return time_unit;
	}

	public void setTime_unit(String time_unit) {
		this.time_unit = time_unit;
	}

}
