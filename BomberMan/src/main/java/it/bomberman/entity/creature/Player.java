package it.bomberman.entity.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import it.bomberman.collisions.Body;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Rectangle;
import it.bomberman.collisions.Vector2;
import it.bomberman.entity.creature.PowerUp.PowerUpType;
import it.bomberman.gfx.*;
import it.bomberman.input.KeyManager;

public class Player extends AbstractEntity {

	public static final long DEFAULT_DROP_COOL_DOWN = (long) 5e8; // 1/2 s
	public static final int DEFAULT_PLAYER_WIDTH = 128;
	public static final int DEFAULT_PLAYER_HEIGHT = 100;
	private Animation animDown, animUp, animLeft, animRight, animBomb;
	private KeyManager keyManager;
	private int playerNumb;
	private Body body;
	private final int cropOffsetX = 17;
	private final int cropOffsetY = 22;
	private int xMove;
	private int yMove;

	private int health = 1;
	private int speed = 12;
	private int nBombs = 3;
	private int bombExtension = 1;
	private long lastBombDroppedTime = 0;
	private long bombDroppedCoolDown = DEFAULT_DROP_COOL_DOWN;
	public Set<Bomb> bombs;

	protected Player(int x, int y, int width, int height, EntityController controller) {
		super(x, y, width, height, controller);
	}

	public Player(int x, int y, int n, KeyManager keyManager, EntityController controller) {
		this(x, y, DEFAULT_PLAYER_WIDTH, DEFAULT_PLAYER_HEIGHT, controller);
		this.playerNumb = n;
		this.keyManager = keyManager;

		this.bombs = new HashSet<Bomb>();
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// Creare una classe esterna che gestisce i player per animazioni!!

		if (playerNumb == 1) {
			animDown = new Animation(150, Assets.player_d);
			animUp = new Animation(150, Assets.player_u);
			animLeft = new Animation(150, Assets.player_l);
			animRight = new Animation(150, Assets.player_r);
			animBomb = new Animation(150, Assets.player_bomb);
		}
		if (playerNumb == 2) {
			animDown = new Animation(150, Assets.player_d2);
			animUp = new Animation(150, Assets.player_u2);
			animLeft = new Animation(150, Assets.player_l2);
			animRight = new Animation(150, Assets.player_r2);
			animBomb = new Animation(200, Assets.player_bomb2);
		}
	}

	@Override
	protected void initBody() {
		this.body = new Body();
		this.body.add(new Rectangle(this.x + cropOffsetX, this.y + cropOffsetY, 40, 70));
	}

	public void getInput() {
		xMove = 0;
		yMove = 0;
		if (this.playerNumb == 1) {
			if (this.keyManager.up)
				yMove -= speed;
			if (this.keyManager.down)
				yMove = speed;
			if (this.keyManager.left)
				xMove -= speed;
			if (this.keyManager.right)
				xMove = speed;
		}

		if (this.playerNumb == 2) {
			if (this.keyManager.up2)
				yMove -= speed;
			if (this.keyManager.down2)
				yMove = speed;
			if (this.keyManager.left2)
				xMove -= speed;
			if (this.keyManager.right2)
				xMove = speed;
		}
		if (this.keyManager.drop && playerNumb == 1) {
			dropBomb();
		}

		if (this.keyManager.drop2 && playerNumb == 2) {
			dropBomb();
		}
	}

	@Override
	public void tick() {
		// rimuove il riferimento ad ogni bomba gi� esplosa
		this.bombs.removeIf(Bomb::hasFinished);
		animDown.tick();
		animLeft.tick();
		animRight.tick();
		animUp.tick();
		animBomb.tick();

		getInput();
		int oldX = this.x;
		int oldY = this.y;
		this.x += xMove;
		this.y += yMove;
		this.body.move(this.x + cropOffsetX, this.y + cropOffsetY);
		if (this.controller.verifyCollision(this)) {
			this.x = oldX;
			this.y = oldY;
			this.xMove = 0;
			this.yMove = 0;
			this.body.move(this.x + cropOffsetX, this.y + cropOffsetY);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), x, y, width, height, null);

		// debug only
		this.body.render(g, Color.RED);
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (this.keyManager.drop && playerNumb == 1 && canDropBomb()) {
			return animBomb.getCurrentFrame();
		}
		if (this.keyManager.drop2 && playerNumb == 2 && canDropBomb())
			return animBomb.getCurrentFrame();

		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else {
			return animDown.getCurrentFrame();
		}
	}

	@Override
	public Vector2 getPosition() {
		return new Vector2(this.x, this.y);
	}

	@Override
	public Body getBody() {
		// versione non modificabile per preservare l'incapsulamento
		return this.body;
	}

	@Override
	public boolean shouldCollide(ICollidable collidable) {
		if (collidable instanceof Wall) {
			return true;
		}
		return false;
	}

	@Override
	public void collision(ICollidable collidable) {
		if (collidable instanceof Explosion) {
			this.collision((Explosion) collidable);
		}
		if (collidable instanceof PowerUp) {
			collision((PowerUp) collidable);
		}
	}

	public void collision(Explosion exp) {
		// Muori
		// Notifica eventuali listener del fatto che sei morto
		die();
	}

	public void collision(Wall wall) {
		// Do Nothing
		// Die if deathWall

		// Qui andrebbe implementata la dinamica di rollback

	}

	public void collision(PowerUp up) {
		if (up.getType() == PowerUpType.BOMB_NUM) {
			this.nBombs++;
		}

		if (up.getType() == PowerUpType.LIFE) {

		}
	}

	public void dropBomb() {
		if (this.lastBombDroppedTime == 0) {
			this.lastBombDroppedTime = System.nanoTime();
		}

		if (canDropBomb()) {
			Bomb b = new Bomb(this.x + cropOffsetX, this.y + cropOffsetY, this.bombExtension, this.controller);
			this.bombs.add(b);
			this.controller.register(b);
			this.lastBombDroppedTime = System.nanoTime();
		}
	}

	@Override
	public void dispose() {
		this.controller.notifyDisposal(this);
	}

	public void die() {
		this.dispose();
	}

	private boolean canDropBomb() {
		// se non ci sono bombe sul campo
		// oppure
		// ci sono meno bombe del massi & � passato abbastanza tempo dall'ultima bomba
		return (this.bombs.size() == 0) || ((this.bombs.size() < this.nBombs)
				&& (System.nanoTime() - this.lastBombDroppedTime > this.bombDroppedCoolDown));
	}

	public int getHealth() {
		return this.health;
	}

	public int getSpeed() {
		return this.speed;
	}
	public int getPlayerNumb() {
		return this.playerNumb;
	}
	
}
