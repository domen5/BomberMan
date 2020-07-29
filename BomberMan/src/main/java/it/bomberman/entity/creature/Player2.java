package it.bomberman.entity.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
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
		
		this.addFixture(Geometry.createRectangle(0.5, 1.5), 1, 0.2, 0.2);
		BodyFixture bf2 = this.addFixture(Geometry.createEquilateralTriangle(0.5), 1, 0.2, 0.2);
		bf2.getShape().translate(0, 0.9);
		this.translate(0.0, 2.0);
		this.setMass(MassType.NORMAL);
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
	
	/*
	 * Esempio: Thrust dal pacco dyn4j-samples
	 * 
	 * protected void update(Graphics2D g, double elapsedTime) {
		super.update(g, elapsedTime);
		
		final double scale = this.scale;
		final double force = 1000 * elapsedTime;
		
        final Vector2 r = new Vector2(ship.getTransform().getRotationAngle() + Math.PI * 0.5);
        final Vector2 c = ship.getWorldCenter();
		
		// apply thrust
        if (this.forwardThrustOn.get()) {
        	Vector2 f = r.product(force);
        	Vector2 p = c.sum(r.product(-0.9));
        	
        	ship.applyForce(f);
        	
        	g.setColor(Color.ORANGE);
        	g.draw(new Line2D.Double(p.x * scale, p.y * scale, (p.x - f.x) * scale, (p.y - f.y) * scale));
        } 
        if (this.reverseThrustOn.get()) {
        	Vector2 f = r.product(-force);
        	Vector2 p = c.sum(r.product(0.9));
        	
        	ship.applyForce(f);
        	
        	g.setColor(Color.ORANGE);
        	g.draw(new Line2D.Double(p.x * scale, p.y * scale, (p.x - f.x) * scale, (p.y - f.y) * scale));
        }
        if (this.leftThrustOn.get()) {
        	Vector2 f1 = r.product(force * 0.1).right();
        	Vector2 f2 = r.product(force * 0.1).left();
        	Vector2 p1 = c.sum(r.product(0.9));
        	Vector2 p2 = c.sum(r.product(-0.9));
        	
        	// apply a force to the top going left
        	ship.applyForce(f1, p1);
        	// apply a force to the bottom going right
        	ship.applyForce(f2, p2);
        	
        	g.setColor(Color.RED);
        	g.draw(new Line2D.Double(p1.x * scale, p1.y * scale, (p1.x - f1.x) * scale, (p1.y - f1.y) * scale));
        	g.draw(new Line2D.Double(p2.x * scale, p2.y * scale, (p2.x - f2.x) * scale, (p2.y - f2.y) * scale));
        }
        if (this.rightThrustOn.get()) {
        	Vector2 f1 = r.product(force * 0.1).left();
        	Vector2 f2 = r.product(force * 0.1).right();
        	Vector2 p1 = c.sum(r.product(0.9));
        	Vector2 p2 = c.sum(r.product(-0.9));
        	
        	// apply a force to the top going left
        	ship.applyForce(f1, p1);
        	// apply a force to the bottom going right
        	ship.applyForce(f2, p2);
        	
        	g.setColor(Color.RED);
        	g.draw(new Line2D.Double(p1.x * scale, p1.y * scale, (p1.x - f1.x) * scale, (p1.y - f1.y) * scale));
        	g.draw(new Line2D.Double(p2.x * scale, p2.y * scale, (p2.x - f2.x) * scale, (p2.y - f2.y) * scale));
        }
	}
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public void getInput() {
		if (this.playerNumb == 1 || this.playerNumb == 3) {
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
