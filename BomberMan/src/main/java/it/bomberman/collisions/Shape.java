package it.bomberman.collisions;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class Shape {

	// dovrebbe essere il centro o un angolo?
	protected Vector2 position;
	
	//abstract?
	//protected Set<Vector2> vertices;

	public Shape(int x, int y) {
		this.position = new Vector2(x, y);
		//this.vertices = new HashSet<Vector2>();
	}

	public Vector2 getPosition() {
		return this.position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void setPosition(int x, int y) {
		this.position.setX(x);
		this.position.setY(y);

	}

	// abstarct?
	public abstract Optional<Set<Vector2>> getVertices();
		//return Optional.fromNullable(this.vertices);
	
	
	// T extends Shape ?
	public abstract <S extends Shape> boolean intersects(S shape);

}
