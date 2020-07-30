package it.bomberman.entity.creature;

public abstract class Creature extends Entity {
	
	public static final int DEFAULT_CREATURE_WIDTH = 228;
	public static final int DEFAULT_CREATURE_HEIGHT =228;
	public static final int DEFAULT_HEALTH = 2;
	public static final float DEFAULT_SPEED = 8.0f;
	protected int health;
	protected float speed;
	protected float xMove, yMove;

	public Creature(float x, float y, int width, int height) {
		super(x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		yMove = 0;
		xMove = 0;
	}

	public void move() {
		x += xMove;
		y += yMove;
	}

	// GETTER SETTER
		
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
}