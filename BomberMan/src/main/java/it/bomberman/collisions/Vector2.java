package it.bomberman.collisions;

public class Vector2 {
	/**
	 * Vettore di due interi
	 */
	
	private int x;
	private int y;

	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
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

	public double length() {
		return (double) Math.sqrt(Math.abs(Math.pow(this.x, 2) + Math.pow(this.y, 2)));
	}

}
