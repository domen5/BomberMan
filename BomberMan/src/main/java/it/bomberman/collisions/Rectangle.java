package it.bomberman.collisions;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Rectangle extends Shape {
	protected Set<Vector2> vertices;

	public Rectangle(int x, int y) {
		super(x, y);
		this.vertices = new HashSet<Vector2>();
	}

	@Override
	public Optional<Set<Vector2>> getVertices() {
		return Optional.ofNullable(this.vertices);
	}

	@Override
	public <S extends Shape> boolean intersects(S shape) {
		// TODO Auto-generated method stub
		return false;
	}

}
