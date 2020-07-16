package it.bomberman.entity.creature;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.bomberman.display.DisplayController;
import it.bomberman.gfx.*;

public class Player extends Creature {

	private Animation animDown, animUp, animLeft, animRight;
	// AGGIUNGI Game game,
	DisplayController c;
	public Player(DisplayController c, float x, float y) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		//this.game = game;
		this.c=c;
		animDown = new Animation(100, Assets.player_d);
		animUp = new Animation(100, Assets.player_u);
		animLeft = new Animation(100, Assets.player_l);
		animRight = new Animation(100, Assets.player_r);
	}

	public void getInput() {
		xMove = 0;
		yMove = 0;
//
//		if (game.getKeyManager().up)
//			yMove -= speed;
//		if (game.getKeyManager().down)
//			yMove = speed;
//		if (game.getKeyManager().left)
//			xMove -= speed;
//		if (game.getKeyManager().right)
//			xMove = speed;
	}

	@Override
	public void tick() {
		animDown.tick();
		animLeft.tick();
		animRight.tick();
		animUp.tick();
		getInput();
		move();

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);

	}

	private BufferedImage getCurrentAnimationFrame() {
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
