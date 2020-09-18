package it.bomberman.collisions;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class Shape {

	// dovrebbe essere il centro o un angolo?
	protected Vector2 position; //per ora si opta per l'angolo in alto a sinistra
	// (0,0)------->x
	// |
	// |
	// v
	// y
	
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

	// abstarct? e' una peculiarita di determinate Shape...
	public abstract List<Vector2> getVertices();
		//return Optional.fromNullable(this.vertices);
	
	
	// T extends Shape ?
	public abstract <S extends Shape> boolean intersects(S shape);
	
	public boolean intersectsAny(Set<Shape> otherBoundingShapes) {
		return otherBoundingShapes.stream()
				.filter(s -> this.intersects(s)) //filtra tutte le shapes che intersecano this
				.count() > 0;	// se ne trova più di una allora restituisce true
	}
	
	public abstract <S extends Shape> S cloneAtPosition(int x, int y);
	
	public abstract void render(Graphics g);
	
	public abstract void move(int x, int y);

}
