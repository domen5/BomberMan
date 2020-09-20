package it.bomberman.entity.creature;

import java.awt.Graphics;

import it.bomberman.collisions.Body;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Rectangle;
import it.bomberman.collisions.Shape;
import it.bomberman.collisions.Vector2;

public class Explosion extends Entity implements ICollidable{
	
	/**
	 * Un esplosione si occupa di gestire due Shape Rettangolari
	 * e dell collisioni con Player, Wall e Bomb
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
	
	public Explosion(int x, int y, int width, int height) {
		this(x,y,width, height, DEFAULT_EXPLOSION_EXTENSION); 
	}
	
	public Explosion(int x, int y, int width, int height, int explExtension) {
		super(x, y, width, height);
		this.explExtension = explExtension;
		this.timerLength = DEFAULT_TIMER_LENGTH;
		this.startTime = System.nanoTime();
		this.body = new Body();
		this.body.add(new Rectangle(x - UNIT*explExtension, y, 1 + 2*explExtension*UNIT, UNIT));
		this.body.add(new Rectangle(x, y- UNIT*explExtension, UNIT, 1 + 2*explExtension*UNIT));
	}
	
	

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if (this.startTime - System.nanoTime() > this.timerLength) {
			this.end();
		}
	}

	@Override
	public void render(Graphics g) {
		this.body.render(g);
	}

	@Override
	public Vector2 getPosition() {
		return Vector2.unmodifiableVector2(new Vector2(this.x, this.y));
	}

	@Override
	public boolean shouldCollide(ICollidable collidable) {
		if(collidable instanceof Player) {
			return true;
		}
		if(collidable instanceof Wall) {
			return true;
		}
		if(collidable instanceof Bomb) {
			return true;
		}
		return false;
	}

	public void collision(Player player) {
		// TODO Auto-generated method stub
		
	}
	
	public void collision(Bomb bomb) {
		// TODO Auto-generated method stub
		
	}
	
	public void collision(ICollidable collidable) {
		// TODO Auto-generated method stub
		
	}
	
	public void collision(Wall wall) {
		// TODO Auto-generated method stub
		
	}

	public void collision(Explosion explosion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Body getBody() {
		return this.body;
	}
	
	public void end() {
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
