package it.bomberman.collisions;

import java.util.ArrayList;
import java.util.List;
//import javax.naming.OperationNotSupportedException;

public class Circle extends Shape {

	public Circle(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Vector2> getVertices() {
		//throw new OperationNotSupportedException(); //forse più appropriato?
		return new ArrayList();
	}

	@Override
	public <S extends Shape> boolean intersects(S shape) {
		// TODO Auto-generated method stub
		return false;
	}

}
