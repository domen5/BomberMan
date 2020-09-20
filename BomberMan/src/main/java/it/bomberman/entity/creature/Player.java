package it.bomberman.entity.creature;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import it.bomberman.collisions.Body;
import it.bomberman.collisions.CollisionManager;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Rectangle;
import it.bomberman.collisions.Vector2;
import it.bomberman.gfx.*;
import it.bomberman.input.KeyManager;

public class Player extends Creature implements ICollidable {

	private Animation animDown, animUp, animLeft, animRight, animBomb;
	// AGGIUNGI Game game,
	private KeyManager keyManager;
	private int playerNumb;
	private Body body;
	private CollisionManager collisionMan;
	private final int cropOffset = 82;

	public Player(int x, int y, int n, KeyManager keyManager, CollisionManager collisionMan) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		// this.game = game;
		// this.c = c;
		this.playerNumb = n;
		this.keyManager = keyManager;
		this.body = new Body();
		this.body.add(new Rectangle(this.x + cropOffset, this.y + cropOffset, 60, 135));
		this.collisionMan = collisionMan;
		this.collisionMan.register(this);;
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// Creare una classe esterna che gestisce i player per animazioni!!

		if (playerNumb == 1) {
			animDown = new Animation(150, Assets.player_d);
			animUp = new Animation(150, Assets.player_u);
			animLeft = new Animation(150, Assets.player_l);
			animRight = new Animation(150, Assets.player_r);
			animBomb = new Animation(200, Assets.player_bomb);
		}
		if (playerNumb == 2) {
			animDown = new Animation(150, Assets.player_d2);
			animUp = new Animation(150, Assets.player_u2);
			animLeft = new Animation(150, Assets.player_l2);
			animRight = new Animation(150, Assets.player_r2);
			animBomb = new Animation(200, Assets.player_bomb2);
		}

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
			new Bomb(this.x + cropOffset, this.y+cropOffset, this.collisionMan);
		}
		
		if (this.keyManager.drop && playerNumb == 2) {
			
		}
	}

	@Override
	public void tick() {
		animDown.tick();
		animLeft.tick();
		animRight.tick();
		animUp.tick();
		animBomb.tick();

		getInput();
		int oldX = this.x;
		int oldY = this.y;
		move();
		this.body.move(this.x + cropOffset, this.y + cropOffset);
		if (this.collisionMan.verifyCollision(this)) {
			int xBounce = 0;
			int yBounce = 0;
//			if (xMove > 0) {
//				xBounce = -8;
//			} else if (xMove < 0) {
//				xBounce = 8;
//			}
//			if (yMove > 0) {
//				yBounce = -8;
//			} else if (yMove < 0) {
//				yBounce = 8;
//			}
			this.x = oldX + xBounce;
			this.y = oldY + yBounce;
			this.xMove = 0;
			this.yMove = 0;
			this.body.move(this.x + cropOffset, this.y + cropOffset);
		}
//		this.xMove = 0;
//		this.yMove = 0;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), x, y, width, height, null);

		// debug only
		 this.body.render(g);
	}

	private BufferedImage getCurrentAnimationFrame() {
//		super.move();
		if (this.keyManager.drop && playerNumb == 1)
			return animBomb.getCurrentFrame();
		if (this.keyManager.drop2 && playerNumb == 2)
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
		boolean out = false;
		if (collidable instanceof Explosion) {
			out = true;
		}
		if (collidable instanceof Wall)
			out = true;
		return out;
	}

	@Override
	public void collision(ICollidable collidable) {
		// Do nothing
	}

	public void collision(Player player) {
		// Do nothing
	}

	public void collision(Bomb bomb) {
		// Do nothing
	}

	public void collision(Explosion exp) {
		// Muori
		// Notifica eventuali listener del fatto che sei morto
	}

	public void collision(Wall wall) {
		// Do Nothing
		// Die if deathWall
	}
	
	@Override
	public void dispose() {
		//this.collisionMan.remove(this);
	}
}
