package com.yoti.hoover.model;

import java.util.HashSet;
import java.util.Set;

public class Room {

	private Set<Position> dirtyPatches;

	private int width;

	private int height;

	public Room(int width, int height) {
		this.width = width;
		this.height = height;
		dirtyPatches = new HashSet<>();
	}

	public void setDirtyPatch(Position position) {
		dirtyPatches.add(position);
	}

	public int clean(Position position) {
		return dirtyPatches.remove(position) ? 1 : 0;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
