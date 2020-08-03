package it.bomberman.collisions;

import java.util.Optional;
import java.util.Set;
//import javax.naming.OperationNotSupportedException;

public class Circle extends Shape {

	public Circle(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Optional<Set<Vector2>> getVertices() {
		//throw new OperationNotSupportedException(); //forse più appropriato?
		return Optional.empty();
	}

	@Override
	public <S extends Shape> boolean intersects(S shape) {
		// TODO Auto-generated method stub
		return false;
	}

}
