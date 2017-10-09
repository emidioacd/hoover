package com.yoti.hoover.web.model;

import com.yoti.hoover.model.Position;

public class HooverResponse implements IHooverResponse {

	private static final long serialVersionUID = 3260859788840886089L;

	private Position coords;

	private int patches;

	public HooverResponse(Position coords, int patches) {
		this.coords = coords;
		this.patches = patches;
	}

	public int[] getCoords() {
		return new int[] { coords.getX(), coords.getY() };
	}

	public void setCoords(Position coords) {
		this.coords = coords;
	}

	public int getPatches() {
		return patches;
	}

	public void setPatches(int patches) {
		this.patches = patches;
	}
}
