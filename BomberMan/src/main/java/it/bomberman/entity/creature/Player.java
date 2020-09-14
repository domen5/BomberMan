package it.bomberman.entity.creature;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import it.bomberman.display.DisplayController;
import it.bomberman.gfx.*;

public class Player extends Creature {
	
	
	private Animation animDown, animUp, animLeft, animRight, animBomb;
	// AGGIUNGI Game game,
	DisplayController c;
	private int playerNumb;
	public Player(float x, float y, int n) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		// this.game = game;
		//this.c = c;
		this.playerNumb=n;
		
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//Creare una classe esterna che gestisce i player per animazioni!!
		
		if(playerNumb==1) {
			animDown = new Animation(100, Assets.player_d);
			animUp = new Animation(100, Assets.player_u);
			animLeft = new Animation(100, Assets.player_l);
			animRight = new Animation(100, Assets.player_r);
			animBomb= new Animation(200, Assets.player_bomb);
			}
		if(playerNumb==2) {
			animDown = new Animation(150, Assets.player_d2);
			animUp = new Animation(150, Assets.player_u2);
			animLeft = new Animation(150, Assets.player_l2);
			animRight = new Animation(150, Assets.player_r2);
			animBomb= new Animation(200, Assets.player_bomb2);
			}
			
	}

	public void getInput() {
//		xMove = 0;
//		yMove = 0;
//		if(this.playerNumb==1) {
//			if (c.getKeyManager().up)
//				yMove -= speed;
//			if (c.getKeyManager().down)
//				yMove = speed;
//			if (c.getKeyManager().left)
//				xMove -= speed;
//			if (c.getKeyManager().right)
//				xMove = speed;	
//		}
//				
//		if(this.playerNumb==2) {
//			if (c.getKeyManager().up2)
//				yMove -= speed;
//			if (c.getKeyManager().down2)
//				yMove = speed;
//			if (c.getKeyManager().left2)
//				xMove -= speed;
//			if (c.getKeyManager().right2)
//				xMove = speed;
//			}
	}
	
	public void keyPressed(KeyEvent k) {
		if(this.playerNumb==1) {
			if (k.getKeyCode() == KeyEvent.VK_W)
				yMove -= speed;
			if (k.getKeyCode() == KeyEvent.VK_S)
				yMove = speed;
			if (k.getKeyCode() == KeyEvent.VK_A)
				xMove -= speed;
			if (k.getKeyCode() == KeyEvent.VK_D)
				xMove = speed;	
		}
		
		if(this.playerNumb==2) {
			if (k.getKeyCode() == KeyEvent.VK_UP)
				yMove -= speed;
			if (k.getKeyCode() == KeyEvent.VK_DOWN)
				yMove = speed;
			if (k.getKeyCode() == KeyEvent.VK_LEFT)
				xMove -= speed;
			if (k.getKeyCode() == KeyEvent.VK_RIGHT)
				xMove = speed;
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
		move();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
	}

	private BufferedImage getCurrentAnimationFrame() {
		super.move();
//		if (c.getKeyManager().drop)
//			return animBomb.getCurrentFrame();
//		if (c.getKeyManager().drop2)
//			return animBomb.getCurrentFrame();
				
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
}
