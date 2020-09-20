package it.bomberman.entity.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Optional;

import it.bomberman.collisions.Body;
import it.bomberman.collisions.CollisionManager;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Rectangle;
import it.bomberman.collisions.Vector2;

//import org.dyn4j.geometry.Vector2;

public class Bomb extends Entity implements ICollidable {

	public static final long DEFAULT_TIMER_LENGTH = (long) 3e+9; // 3s espressi in nano secondi
	public static final int DEFAULT_WIDTH = 100;
	public static final int DEFAULT_EXPLOSION_EXTENTION = 1;

	private final EntityController controller;
	private final long startTime;
	private final long timerLength;
	private int exlposionExtention;
	private boolean exploded = false;
	private boolean animationOver = false;
	private Body body;
	private Optional<Explosion> ex;

	public Bomb(int x, int y, EntityController controller) {
		this(x, y, DEFAULT_WIDTH, DEFAULT_WIDTH, controller);
	}

	public Bomb(int x, int y, int width, int height, EntityController controller) {
		this(x, y, width, height, DEFAULT_TIMER_LENGTH, controller);
	}

	public Bomb(int x, int y, int width, int height, long timerLength, EntityController controller) {
		super(x, y, width, height);
		this.controller = controller;
		this.body = new Body();
		this.body.add(new Rectangle(x, y, width, height));
		this.ex = Optional.empty();
		this.exploded = false;
		this.timerLength = timerLength;
		this.exlposionExtention = DEFAULT_EXPLOSION_EXTENTION;
		this.startTime = System.nanoTime();
	}

	@Override
	public void tick() {
		long now = System.nanoTime();
		if (now - this.startTime> this.timerLength) {
			this.explode();
		}
		// this.ex.ifPresent(Explosion::tick);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		// this.ex.ifPresent(e -> e.render(g));
		this.body.render(g, Color.BLUE);
	}

	public void explode() {
		this.exploded = true;

		this.controller.register(
				new Explosion(this.x, this.y, this.width, this.height,
						this.exlposionExtention, this.controller));
		this.dispose();
	}

	@Override
	public Vector2 getPosition() {
		return Vector2.unmodifiableVector2(new Vector2(this.x, this.y));
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean shouldCollide(ICollidable collidable) {
		return collidable instanceof Explosion;
	}

	@Override
	public void collision(ICollidable collidable) {
		if (collidable instanceof Explosion) {
			this.collision((Explosion) collidable);
		}
	}

	public void collision(Explosion explosion) {
		this.explode();
	}

	@Override
	public void dispose() {
		this.controller.notifyDisposal(this);
	}

}
