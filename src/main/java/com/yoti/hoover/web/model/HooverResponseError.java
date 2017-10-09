package com.yoti.hoover.web.model;

public class HooverResponseError implements IHooverResponse {

	private static final long serialVersionUID = 3260859788840886089L;

	private String error;

	public HooverResponseError(String error) {
		this.setError(error);
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
