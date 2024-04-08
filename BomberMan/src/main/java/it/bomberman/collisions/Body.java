package it.bomberman.collisions;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Body {

	public List<Shape> boundingShapes;

	public Body() {
		this.boundingShapes = new ArrayList<>();
	}

	public Body(Shape s) {
		this();
		this.add(s);
	}

	public void add(Shape s) {
		this.boundingShapes.add(s);
	}

	public boolean hasBoundingShapes() {
		return this.boundingShapes.isEmpty();
	}

	public boolean checkCollision(Body other) {
		if (this.equals(other))
			return false;
		return this.checkCollision(other.getBoundingShapes());
	}

	public boolean checkCollision(List<Shape> otherBoundingShapes) {
		if (this.boundingShapes.isEmpty() || otherBoundingShapes.isEmpty()) {
			return false;
		}

		for (Shape s : this.boundingShapes) {
			for (Shape otherS : otherBoundingShapes) {
				if (s.intersects(otherS)) {
					return true; // Collision detected, return true immediately
				}
			}
		}
		return false; // No collision detected
	}

	public List<Shape> getBoundingShapes() {
		return Collections.unmodifiableList(this.boundingShapes);
	}

	public void move(int x, int y) {
		boundingShapes.forEach(s -> s.move(x, y));
	}

	public void render(Graphics g, Color color) {
		boundingShapes.forEach(s -> s.render(g, color));
	}
}
