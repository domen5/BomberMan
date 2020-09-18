package it.bomberman.entity.creature;

import java.awt.Color;
import java.awt.Graphics;

import it.bomberman.collisions.Body;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Rectangle;
import it.bomberman.collisions.Shape;
import it.bomberman.collisions.Vector2;

public class WallFactoryImpl implements WallFactory{

	@Override
	public Wall MapLimitWall(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Wall SimpleWall(int x, int y) {
		return new Wall(x, y, Wall.DEFAULT_WALL_WIDTH, Wall.DEFAULT_WALL_WIDTH) {	
			
			private Body body = new Body(new Rectangle(x, y, width, height));
			
			@Override
			public Vector2 getPosition() {
				return new Vector2(this.x, this.y);
			}

			@Override
			public boolean shouldCollide(ICollidable collidable) {
				return true;
			}

			@Override
			public void collision(ICollidable collidable) {
				// TODO Auto-generated method stub
				
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
			public void collision(Wall wall) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void collision(Explosion explosion) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void tick() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void render(Graphics g) {
				this.body.boundingShapes.stream().forEach(e -> e.render(g));
//			    g.drawRect(this.x,this.y,this.width,this.height);  
//			    g.setColor(Color.RED);  
//			    g.fillRect(this.x,this.y,this.width,this.height);  
			}

			@Override
			public Body getBody() {
				return this.body;
			}
		};
	}

	@Override
	public Wall DeathWall(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

}
