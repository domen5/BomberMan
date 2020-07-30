package it.bomberman.entity.creature;

import java.awt.Graphics;
import java.awt.Graphics2D;

import org.dyn4j.dynamics.Body;

public abstract class Entity2 extends Body {
	protected Body body;
	
	public abstract void update(double delta);
	
	public abstract void render(Graphics2D g);
	
}
