package it.bomberman.entities;

import it.bomberman.collisions.Body;
import it.bomberman.collisions.Vector2;

public abstract class AbstractEntity implements Entity {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected EntityController controller;
	protected Body body;

	protected AbstractEntity(int x, int y, int width, int height, EntityController controller) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.controller = controller;
		initBody();
	}

	protected abstract void initBody();

	@Override
	public Body getBody() {
		return this.body;
	}

	@Override
	public Vector2 getPosition() {
		return Vector2.unmodifiableVector2(new Vector2(x, y));
	}
}