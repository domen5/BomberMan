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
	//public 
}
