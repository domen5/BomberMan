package it.bomberman.entity.creature;

import java.awt.Color;
import java.awt.Graphics;

import it.bomberman.collisions.Body;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Rectangle;
import it.bomberman.collisions.Vector2;

public class PowerUp extends Entity {
	private final static int DEFAULT_WIDTH = 50;
	private Body body;
	private EntityController controller;
	private PowerUpType type;
	private int value;

	public enum PowerUpType {
		LIFE, SPEED, BOMB_NUM, BOMB_EXTENSION
	}

	public PowerUp(int x, int y, EntityController controller, PowerUpType type, int value) {
		this(x, y, DEFAULT_WIDTH, DEFAULT_WIDTH, type, controller);
	}

	private PowerUp(int x, int y, int width, int height, PowerUpType type,  EntityController controller) {
		this(x, y, width, height);
		this.controller = controller;
		this.body = new Body();
		this.body.add(new Rectangle(x, y, width, height));
		this.type = type;
	}

	public PowerUp(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public Vector2 getPosition() {
		return Vector2.unmodifiableVector2(new Vector2(this.x, this.y));
	}

	@Override
	public Body getBody() {
		return this.body;
	}

	@Override
	public boolean shouldCollide(ICollidable collidable) {
		return collidable instanceof Player;
	}

	@Override
	public void collision(ICollidable collidable) {
		collidable.collision(this);
		dispose();
	}

	@Override
	public void dispose() {
		this.controller.notifyDisposal(this);
	}

	@Override
	public void tick() {
		this.controller.checkAndResolveCollisions(this);
	}

	@Override
	public void render(Graphics g) {
		this.body.render(g, Color.yellow);
	}

	public int getValue() {
		return value;
	}

	public PowerUpType getType() {
		return type;
	}

	public static class PowerUpBuilder {
		private int x;
		private int y;
		private EntityController controller;
		private PowerUpType type;
		private int value;

		private PowerUpBuilder() {

		}

		public PowerUpBuilder setX(int x) {
			this.x = x;
			return this;
		}

		public PowerUpBuilder setY(int y) {
			this.y = y;
			return this;
		}

		public PowerUpBuilder setController(EntityController controller) {
			this.controller = controller;
			return this;
		}

		public PowerUpBuilder setType(PowerUpType type) {
			this.type = type;
			return this;
		}
		/**
		 * 
		 * @param value deve essere < di 6 e > 0
		 * @return
		 */
		public PowerUpBuilder setValue(int value) {
			if(!checkValue(value)) {
				throw new IllegalArgumentException("Value di PowerUp deve essere 0 < v < 6");
			}
			this.value = value;
			return this;
		}
		
		private boolean checkValue(int value) {
			return value > 0 && value < 6;
		}

		public PowerUp build() {
			if(this.controller == null || this.type == null ||  !checkValue(this.value)){
				throw new IllegalStateException("Un dato ha valore inaccettabile!");
			}
			return new PowerUp(this.x, this.y, this.controller, this.type, this.value);
		}
		
		public static PowerUpBuilder newBuilder() {
			return new PowerUpBuilder();
		}
	}
}
