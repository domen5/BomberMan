package it.bomberman.entity.creature;

import java.awt.Graphics;
import java.util.Optional;

import it.bomberman.collisions.Body;
import it.bomberman.collisions.CollisionManager;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Rectangle;
import it.bomberman.collisions.Vector2;

//import org.dyn4j.geometry.Vector2;

public class Bomb extends Entity implements ICollidable {

	public static final long DEFAULT_TIMER_LENGTH = (long) 3e9; // 3s espressi in nano secondi
	public static final int DEFAULT_WIDTH = 100;
	public static final int DEFAULT_EXPLOSION_EXTENTION = 1;

	private final CollisionManager collMan;
	private final long startTime;
	private final long timerLength;
	private int exlposionExtention;
	private boolean exploded = false;
	private boolean animationOver = false;
	private Body body;
	private Optional<Explosion> ex;

	public Bomb(int x, int y, CollisionManager collMan) {
		this(x, y, DEFAULT_WIDTH, DEFAULT_WIDTH, collMan);
	}

	public Bomb(int x, int y, int width, int height, CollisionManager collMan) {
		this(x, y, width, height, DEFAULT_TIMER_LENGTH, collMan);
	}

	public Bomb(int x, int y, int width, int height, long timerLength, CollisionManager collMan) {
		super(x, y, width, height);
		this.collMan = new CollisionManager();

		this.body = new Body();
		this.body.add(new Rectangle(x, y, width, height));
		this.ex = Optional.empty();
		this.exploded = false;
		this.timerLength = timerLength;
		this.startTime = System.nanoTime();
	}

	@Override
	public void tick() {
		if (exploded)
			return; // provvisorio
		// se la bomba fosse esplosa non dovrebbe essere più aggiornata

		if (this.startTime - System.nanoTime() > this.timerLength && (!this.exploded)) {
			this.explode();
		}
		this.ex.ifPresent(e -> e.tick());
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		this.ex.ifPresent(e -> e.render(g));
		this.body.render(g);
	}

	public void explode() {
		if (this.exploded)
			return;

		this.exploded = true;

		// width e length non sono esatte!
		// vanno determinate in base alla posizione e i limiti della mappa?
		this.ex = Optional.of(new Explosion(this.x, this.y, this.width, this.height, this.exlposionExtention));
		this.collMan.register(ex.get());
		// *****************************************************************
		// TODO: ex va registrata in collisionManager, Model per gli update,
		// View per essere renderizzata

		// {...}
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
		// TODO Auto-generated method stub

	}

}
