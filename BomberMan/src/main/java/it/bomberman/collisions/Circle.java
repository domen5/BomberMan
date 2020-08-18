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
		// throw new OperationNotSupportedException(); //forse più appropriato?
		return new ArrayList();
	}

	@Override
	public <S extends Shape> boolean intersects(S shape) {
		return false;
	}

	public boolean intersects(Circle circle) {
		double distance = this.position.distance(circle.getPosition());
		double radius = (double) r;

		if (distance <= radius) {
			return true;
		}
		return false;
	}

	public boolean intersects(Rectangle rectangle) {
		// variabili temporanee per testare i lati
		double cx = this.position.getX();
		double cy = this.position.getY();
		double rx = rectangle.getPosition().getX();
		double ry = rectangle.getPosition().getY();
		double rw = rectangle.getWidth();
		double rh = rectangle.getHeight();
		double radius = this.r;
		
		double testX = cx;
		double testY = cy;

		// serie di if per capire quale lato e' il piu vicino
		if (cx < rx)
			testX = rx; // prova lato sinistro
		else if (cx > rx + rw)
			testX = rx + rw; // lato destro
		if (cy < ry)
			testY = ry; // lato piu' alto
		else if (cy > ry + rh)
			testY = ry + rh; // lato piu' basso

		// calcola distanza dal lato piu vicino
		double distX = cx - testX;
		double distY = cy - testY;
		double distance = Math.sqrt((distX * distX) + (distY * distY));

		// se la distanza e' minore del raggio abbiamo una collisione
		if (distance <= radius) {
			return true;
		}
		return false;
	}

}
