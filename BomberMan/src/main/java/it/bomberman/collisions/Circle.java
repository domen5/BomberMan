package it.bomberman.collisions;

import java.util.ArrayList;
import java.util.List;
//import javax.naming.OperationNotSupportedException;

public class Circle extends Shape {
	private int r;
	
	public Circle(int x, int y, int r) {
		super(x, y);
		this.r = r;
	}

	@Override
	public List<Vector2> getVertices() {
		//throw new OperationNotSupportedException(); //forse più appropriato?
		return new ArrayList();
	}

	@Override
	public <S extends Shape> boolean intersects(S shape) {
		return false;
	}
	
	public boolean intersects(Circle circle) {
		double distance = this.position.distance(circle.getPosition());
		double radius = (double) r;
		
		if(distance <= radius) {
			return true;
		}
		return false;
	}
	
	public boolean intersects(Rectangle rectangle) {
		return false;
	}
	
	

}
