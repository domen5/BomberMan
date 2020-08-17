package it.bomberman.collisions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Rectangle extends Shape {
	//quale struttura dati utilizzare?
	//sono sicuro che saranno sempre 4 punti.
	//è imortante l'ordine dei punti
	protected final List<Vector2> vertices;

	public Rectangle(int x, int y, int width, int height) {
		this(x, y, 
				new Vector2(x, y),
				new Vector2(x+width, y),
				new Vector2(x+width, y+height),
				new Vector2(x, y+height));		
	}
	
	//private? 
	private Rectangle(int x, int y, Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4) {
		super(x, y);
		//verifiche del caso? p1.x == p3.x, ...
		this.vertices = new ArrayList<Vector2>(4);
		this.vertices.add(p1);
		this.vertices.add(p2);
		this.vertices.add(p3);
		this.vertices.add(p4);
	}

	@Override
	public List<Vector2> getVertices() {
		//sbagliato perche' vertices sara' sempre definito,
		//e' piuttosto il contenuto che potra essere vuoto
		return this.vertices;
	}

	@Override
	public <S extends Shape> boolean intersects(S shape) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean intersects(Circle c) {
		// TODO 
		return false;
	}
	
	public boolean intersects(Rectangle other) {
		if(this.getBottomRight().getX() < other.getTopLeft().getX()
				|| this.getTopLeft().getX() > other.getBottomRight().getX()) {
			return false;
		}
		if(this.getBottomRight().getY() < other.getTopLeft().getY()
				|| this.getTopLeft().getY() > other.getBottomRight().getY()) {
			return false;
		}
		
		return true;
	}
	
	// getTopLeft dovrebber cambiare se i punti del triangolo fossero dati in ordine sparso
	public Vector2 getTopLeft() {
		return getVertices().get(0);
	}
	
	public Vector2 getBottomRight() {
		return getVertices().get(2);
	}
	
	public boolean pointInsideRectangle(Vector2 point){
		/*
		 * 0 <=  AP·AB <=  AB·AB and 0 <=  AP·AD <=  AD·AD
		double ap =  this.vertices.get(0).distance(point);
		double ab = this.vertices.get(0).distance(this.vertices.get(1));
		double ad = this.vertices.get(0).distance(this.vertices.get(3));
		*/
		//if(0 <= AP*AB)
		
		if(getTopLeft().getX() <= point.getX()
				&& getTopLeft().getY() <= point .getY()
				&& getBottomRight().getX() >= point.getX()
				&& getBottomRight().getY() >= point.getY()) {
			return true;
		}
		
		return false;
		
	}

}
