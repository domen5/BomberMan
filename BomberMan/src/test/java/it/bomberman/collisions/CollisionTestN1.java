package it.bomberman.collisions;

import static org.junit.Assert.*;
import java.util.List;

public class CollisionTestN1 {
	
	@org.junit.Before
	public void init() {
		
	}
	
	@org.junit.Test
	public void simpleShapeInstersectionTest() {
		Vector2 origin = new Vector2(0, 0);
		int width = 5, height = 10, offset = 1;
		
		Rectangle r1 = new Rectangle(origin.getX(), origin.getY(), width, height);
		Rectangle r2 = new Rectangle(origin.getX() + offset,
				origin.getY() +offset,
				width, height);
		Rectangle r3 = new Rectangle(origin.getX() + width + offset,
				origin.getY() + height + offset,
				width, height);
		
		assertTrue(r1.intersects(r2)); //r1 e r2 sono uguali ma r2 è traslato di (1,1)
		assertTrue(r2.intersects(r1)); //prova inversa
		assertFalse(r1.intersects(r3)); //r1 e r3 non hanno punti in comune
		assertFalse(r3.intersects(r1)); 
		assertTrue(r2.intersects(r3)); // r2 e r3 hanno solo un vertice in comune
		assertTrue(r3.intersects(r2)); 
	}
}
