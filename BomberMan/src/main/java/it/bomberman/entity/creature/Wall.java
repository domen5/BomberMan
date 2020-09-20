package it.bomberman.entity.creature;

import it.bomberman.collisions.ICollidable;

public abstract class Wall extends Entity implements ICollidable {

	public Wall(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public Wall(int x, int y) {
		super(x, y, DEFAULT_WALL_WIDTH, DEFAULT_WALL_WIDTH);
	}

	public static final int DEFAULT_WALL_WIDTH = 100;
}
