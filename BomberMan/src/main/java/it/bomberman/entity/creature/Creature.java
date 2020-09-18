package it.bomberman.entity.creature;

public abstract class Creature extends Entity {
	
	public static final int DEFAULT_CREATURE_WIDTH = 228;
	public static final int DEFAULT_CREATURE_HEIGHT =228;
	public static final int DEFAULT_HEALTH = 2;
	public static final int DEFAULT_SPEED = 12;
	protected int health;
	protected int speed;
	protected int xMove, yMove;

	public Creature(int x, int y, int width, int height) {
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

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getxMove() {
		return xMove;
	}

	public void setxMove(int xMove) {
		this.xMove = xMove;
	}

	public int getyMove() {
		return yMove;
	}

	public void setyMove(int yMove) {
		this.yMove = yMove;
	}
}