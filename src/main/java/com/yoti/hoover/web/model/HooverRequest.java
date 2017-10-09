package com.yoti.hoover.web.model;

import java.io.Serializable;
import java.util.List;

public class HooverRequest implements Serializable {

	private static final long serialVersionUID = 3634041971669861702L;

	private int[] roomSize;

	private int[] coords;

	private List<int[]> patches;

	private String instructions;

	public int[] getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(int[] roomSize) {
		this.roomSize = roomSize;
	}

	public int[] getCoords() {
		return coords;
	}

	public void setCoords(int[] coords) {
		this.coords = coords;
	}

	public List<int[]> getPatches() {
		return patches;
	}

	public void setPatches(List<int[]> patches) {
		this.patches = patches;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

}
