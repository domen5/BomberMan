package it.bomberman.entity.creature;

import java.awt.Color;
import java.awt.Graphics;

import it.bomberman.collisions.Body;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Rectangle;
import it.bomberman.collisions.Shape;
import it.bomberman.collisions.Vector2;
import it.bomberman.gfx.Assets;

public class Explosion extends Entity implements ICollidable {

	/**
	 * Un esplosione si occupa di gestire due Shape Rettangolari e dell collisioni
	 * con Player, Wall e Bomb
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */

	public static final long DEFAULT_TIMER_LENGTH = (long) 1e9; // 1s espressi in nano secondi
	public final static int DEFAULT_EXPLOSION_EXTENSION = 1;
	public final static int UNIT = Wall.DEFAULT_WALL_WIDTH;

	private Body body;
	private final int explExtension;
	private final long startTime;
	private final long timerLength;
	private final EntityController controller;

	public Explosion(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_EXPLOSION_EXTENSION, null);
	}

	public Explosion(int x, int y, int width, int height, int explExtension, EntityController controller) {
		super(x, y, width, height);

		this.explExtension = explExtension;
		this.timerLength = DEFAULT_TIMER_LENGTH;

		this.controller = controller;

		this.body = new Body();
		this.body.add(new Rectangle(x - UNIT * explExtension, y, (1 + 2 * explExtension) * UNIT, UNIT));
		this.body.add(new Rectangle(x, y - UNIT * explExtension, UNIT, (1 + 2 * explExtension) * UNIT));

		this.startTime = System.nanoTime();
	}

	@Override
	public void tick() {
		if (System.nanoTime() - this.startTime > this.timerLength) {
			this.end();
		}
		this.controller.checkAndResolveCollisions(this);
	}

	@Override
	public void render(Graphics g) {
		//this.body.render(g, Color.YELLOW);
		g.drawImage(Assets.explosion[0], this.x, this.y, this.width, this.height, null);
		//UP|DOWN
		g.drawImage(Assets.explosion[1], this.x-45, this.y+16, this.width, this.height, null);
		g.drawImage(Assets.explosion[1], this.x+58, this.y+16, this.width, this.height, null);
		//LEFT|RIGHT
		g.drawImage(Assets.explosion[2], this.x+12, this.y+60, this.width, this.height, null);
		g.drawImage(Assets.explosion[2], this.x+12, this.y-45, this.width, this.height, null);
		
	}

	@Override
	public Vector2 getPosition() {
		return Vector2.unmodifiableVector2(new Vector2(this.x, this.y));
	}

	@Override
	public boolean shouldCollide(ICollidable collidable) {
		if (collidable instanceof Player) {
			return true;
		}
		if (collidable instanceof Wall) {
			return true;
		}
		if (collidable instanceof Bomb) {
			return true;
		}
		return false;
	}

	public void collision(Player player) {
		player.collision(this);
	}

	public void collision(Bomb bomb) {
		// TODO Auto-generated method stub

	}

	public void collision(ICollidable collidable) {
		if (collidable instanceof Wall) {
			collision((Wall) collidable);
		} else if (collidable instanceof Player) {
			collision((Player) collidable);
		} else if (collidable instanceof Bomb) {
			collision((Bomb) collidable);
		}
	}

	public void collision(Wall wall) {
		// Explosion serve solo a innescare collisioni in altre Entity
		// per questo le consguenze delle collisioni sono gestite in Wall
		wall.collision(this);
	}

	@Override
	public Body getBody() {
		return this.body;
	}

	public void end() {
		this.controller.notifyDisposal(this);
	}

	@Override
	public void dispose() {
		this.controller.notifyDisposal(this);
	}
}
