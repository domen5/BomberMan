package it.bomberman.entity.creature;

import java.awt.Graphics;

import it.bomberman.collisions.Body;
import it.bomberman.collisions.ICollidable;
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
	
	public Explosion(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean shouldCollide(ICollidable collidable) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void collision(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collision(Bomb bomb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collision(ICollidable collidable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collision(Wall wall) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collision(Explosion explosion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}

}
