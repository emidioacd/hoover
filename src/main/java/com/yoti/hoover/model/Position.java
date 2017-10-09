package com.yoti.hoover.model;

public class Position {

	private int x;
	private int y;

	public Position(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public void move() {

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Position) {
			Position other = (Position) obj;

			return (x == other.getX()) && (y == other.getY());
		}

		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		long bits = java.lang.Double.doubleToLongBits(getX());
		bits ^= java.lang.Double.doubleToLongBits(getY()) * 31;
		return (((int) bits) ^ ((int) (bits >> 32)));
	}

}
