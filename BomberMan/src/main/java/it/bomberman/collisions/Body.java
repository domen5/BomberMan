package it.bomberman.collisions;

import java.util.HashMap;
import java.util.Map;

public class Body {
	public Map<Shape, Vector2> boundingShapes;
	
	public Body() {
		this.boundingShapes = new HashMap<Shape, Vector2>();
		
	}
	
	public void add(Shape s, Vector2 position) {
		this.boundingShapes.put(s, position);
	}
	
	public boolean hasBoundingShapes() {
		return this.boundingShapes.isEmpty();
	}	
	
	public boolean checkCollision(Body other) {
		return other.checkCollision(this.boundingShapes);
	}
	
	public boolean checkCollision(Map<Shape, Vector2> otherBoundingShapes) {
		if(!this.hasBoundingShapes() || otherBoundingShapes.isEmpty()) {
			return false;
			// oppure throw new Exception?
		}
		/*
		 * filtrare ogni shape per sapere se almeno una collide con almeno una delle shape dell'otherBody
		 * 
		this.boundingShapes.entrySet().stream()
			.filter(entry -> entry.getKey().intersects(shape))
		*/
		return false;
	}
}
