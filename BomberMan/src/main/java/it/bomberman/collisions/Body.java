package it.bomberman.collisions;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Body {
	public Set<Shape> boundingShapes;

	public Body() {
		this.boundingShapes = new HashSet<Shape>();
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
		if(this.equals(other))
			return false;
		return this.checkCollision(other.getBoundingShapes());
	}

	public boolean checkCollision(Set<Shape> otherBoundingShapes) {
		if (this.boundingShapes.isEmpty() || otherBoundingShapes.isEmpty()) {
			return false;
			// oppure throw new Exception?
		}

//		prova con lambda functions
//		return this.boundingShapes.stream()
//				.filter(s -> s.intersectsAny(otherBoundingShapes))
//				.count() > 0;

		boolean out = false;
		long count = 0;
		// filtrare ogni shape per sapere se almeno una collide con almeno una delle shape dell'otherBody 
		for(var s : this.boundingShapes) {
			for(var otherS : otherBoundingShapes) {
				if(s.intersects(otherS)) {
					out = true;
					count++;
				}
			}
		}
		return out;
	}

	public Set<Shape> getBoundingShapes() {
		// restituisco una versione protetta di bounding shape per mantenere
		// l'incapsulamento
		return Collections.unmodifiableSet(this.boundingShapes);
	}
	
	public void move(int x, int y) {
		
		for(Shape s : this.boundingShapes) {
			s.move(x, y);			
		}
	}
	
	public void render(Graphics g) {
		// debug only
		for(Shape s : this.boundingShapes) {
			s.render(g);
		}
	}
}
