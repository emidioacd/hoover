package com.yoti.hoover.model;

import java.util.List;

public class Robot {

	private Position position;

	private List<Character> instructions;

	public Robot(int x, int y) {
		position = new Position(x, y);
	}

	public Robot(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public List<Character> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Character> instructions) {
		this.instructions = instructions;
	}

	public void goNorth() {
		position.setY(position.getY() + 1);
	}

	public void goSouth() {
		position.setY(position.getY() - 1);
	}

	public void goEast() {
		position.setX(position.getX() + 1);
	}

	public void goWest() {
		position.setX(position.getX() - 1);
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
	}
}
