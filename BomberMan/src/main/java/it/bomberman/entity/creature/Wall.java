package it.bomberman.entity.creature;

import it.bomberman.collisions.ICollidable;

public abstract class Wall extends Entity implements ICollidable {

	public Wall(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public static final int DEFAULT_WALL_WIDTH = 100;
}
