package it.bomberman.map;

import java.util.List;

import it.bomberman.collisions.*;
import it.bomberman.entity.creature.*;

public class MapSpot extends Shape {

	private Entity entityInside;
	private boolean isPlayerInside;
	private boolean isExplosionInside;
	
	public MapSpot(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Vector2> getVertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Shape> boolean intersects(S shape) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
