package it.bomberman.entity.creature;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

import org.dyn4j.geometry.Vector2;

import it.bomberman.display.DisplayController;
import it.bomberman.gfx.Animation;
import it.bomberman.gfx.Assets;

public class Player2 extends Creature2 {
	/**
	 * Questa classe è un work in progress per cambiare il sistema di movimento
	 * Invece di applicare un cambiamento alla posizione si applica una forza
	 * al body. In questo modo il body si accorge autonomamente se va a sbattere
	 * contro un muro.
	 * 
	 */
	
	private AtomicBoolean upOn = new AtomicBoolean(false);
	private AtomicBoolean downOn = new AtomicBoolean(false);
	private AtomicBoolean rightOn = new AtomicBoolean(false);
	private AtomicBoolean leftOn = new AtomicBoolean(false);

	private Animation animDown, animUp, animLeft, animRight;
	// AGGIUNGI Game game,
	DisplayController c;
	private int playerNumb;

	public Player2(DisplayController c, float x, float y, int n) {

		this.c = c;
		this.playerNumb = n;

		if (playerNumb == 1 || playerNumb == 3) {
			animDown = new Animation(100, Assets.player_d);
			animUp = new Animation(100, Assets.player_u);
			animLeft = new Animation(100, Assets.player_l);
			animRight = new Animation(100, Assets.player_r);
		}
		if (playerNumb == 2) {
			animDown = new Animation(100, Assets.player_d2);
			animUp = new Animation(100, Assets.player_u2);
			animLeft = new Animation(100, Assets.player_l2);
			animRight = new Animation(100, Assets.player_r2);
		}
	}

	@Override
	public void update(double delta) {
		animDown.tick();
		animLeft.tick();
		animRight.tick();
		animUp.tick();
		getInput();

		final double scale = 1;
		final double f = 1000 * delta;

		final Vector2 c = this.getWorldCenter();

		if (this.upOn.get()) {
			this.applyForce(new Vector2(0, f));
		}

		if (this.downOn.get()) {
			this.applyForce(new Vector2(0, -f));
		}
		if (this.rightOn.get()) {
			this.applyForce(new Vector2(f, 0));
		}

		if (this.downOn.get()) {
			this.applyForce(new Vector2(-f, 0));
		}

	}

	public void getInput() {
		if (this.playerNumb == 1) {
			this.upOn.set(c.getKeyManager().up);
			this.downOn.set(c.getKeyManager().down);
			this.rightOn.set(c.getKeyManager().left);
			this.leftOn.set(c.getKeyManager().right);
		}
/*		if (this.playerNumb == 2) {
			if (c.getKeyManager().up2)
				yMove -= speed;
			if (c.getKeyManager().down2)
				yMove = speed;
			if (c.getKeyManager().left2)
				xMove -= speed;
			if (c.getKeyManager().right2)
				xMove = speed;
		}
*/
	}

	@Override
	public void render(Graphics2D g) {
		double scale = 1;
		final int pr = 4;
		
		// save the original transform
		AffineTransform ot = g.getTransform();

		// transform the coordinate system from world coordinates to local coordinates
		AffineTransform lt = new AffineTransform();
		lt.translate(this.transform.getTranslationX() * scale, this.transform.getTranslationY() * scale);
		g.transform(lt);
		
		
		
		double x = this.getLocalCenter().x * scale - pr * 0.5;
		double y = this.getLocalCenter().y * scale - pr * 0.5;
		
		g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT, null);
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (this.leftOn.get()) {
			return animLeft.getCurrentFrame();
		} else if (this.rightOn.get()) {
			return animRight.getCurrentFrame();
		} else if (this.upOn.get()) {
			return animUp.getCurrentFrame();
		} else {
			return animDown.getCurrentFrame();
		}
	}

}
