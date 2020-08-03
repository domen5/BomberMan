package it.bomberman.entity.creature;

import java.awt.Graphics;

import org.dyn4j.geometry.Vector2;

public class Bomb extends Entity {

	public static long DEFAULT_TIMER_LENGTH = (long) 5e+9; // ?
	public long startTime;
	public final long timerLength;
	public boolean exploded;

	public Bomb(float x, float y, int width, int height) {
		this(x, y, width, height, DEFAULT_TIMER_LENGTH);
	}

	public Bomb(float x, float y, int width, int height, long timerLength) {
		super(x, y, width, height);
		this.timerLength = timerLength;
		this.startTime = System.nanoTime();
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
		if(exploded)
			return; //provvisorio
		//se la bomba fosse esplosa non dovrebbe essere più aggiornata
		
		if (this.startTime - System.nanoTime() > this.timerLength) {
			this.explode();
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

	public void explode() {
		if (this.exploded)
			return;

		this.exploded = true;
		
		//width e length non sono esatte! 
		//vanno determinate in base alla posizione e i limiti della mappa?
		Explosion ex = new Explosion(this.x, this.y, width, height);
		// *****************************************************************
		// TODO: ex va registrata in collisionManager, Model per gli update,
		// View per essere renderizzata
		
		// {...}
	}

}
