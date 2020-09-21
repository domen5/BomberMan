package it.bomberman.entity.creature;

import java.awt.Color;
import java.awt.Graphics;

import com.google.errorprone.annotations.DoNotCall;

import it.bomberman.collisions.Body;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Rectangle;
import it.bomberman.collisions.Shape;
import it.bomberman.collisions.Vector2;

public class WallFactoryImpl implements WallFactory {

	@Override
	public Wall mapLimitWall(int x, int y, EntityController controller) {
		class mapLimitWall extends Wall{
			Wall inner = simpleWall(x, y, controller);
			private EntityController contr = controller;

			public mapLimitWall(int x, int y) {
				super(x, y);
				// TODO Auto-generated constructor stub
			}

			@Override
			public Vector2 getPosition() {
				return inner.getPosition();
			}

			@Override
			public Body getBody() {
				return inner.getBody();
			}

			@Override
			public boolean shouldCollide(ICollidable collidable) {
				return collidable instanceof Player;
			}

			@Override
			public void collision(ICollidable collidable) {
				// DO NOTHING
			}

			@Override
			public void tick() {
				// DO NOTHING
			}

			@Override
			public void render(Graphics g) {
				this.inner.getBody().render(g, Color.DARK_GRAY);
				
			}

			@Override
			public void dispose() {
				this.contr.notifyDisposal(this);
			}
			
		}
		return new mapLimitWall(x, y);
	}

	@Override
	public Wall simpleWall(int x, int y, EntityController controller) {
		return new Wall(x, y) {
			private Body body = new Body(new Rectangle(x, y, width, height));
			private EntityController contr = controller;
			
			@Override
			public Vector2 getPosition() {
				return Vector2.unmodifiableVector2(new Vector2(this.x, this.y));
			}

			@Override
			public boolean shouldCollide(ICollidable collidable) {
				if (collidable instanceof Player) {
					return true;
				}
				if (collidable instanceof Explosion) {
					return true;
				}
				return false;
			}

			@Override
			public void collision(ICollidable collidable) {
				if(collidable instanceof Explosion){
					this.collision((Explosion) collidable);
				}

			}

			public void collision(Explosion explosion) {
				// this.dispose();
				// ovvero: Rimozione da vari Observer, Listerner
				this.dispose();
			}

			@Override
			public void tick() {
				// DO NOTHING
				// E' un muro, non fa niente
			}

			@Override
			public void render(Graphics g) {
				this.body.render(g, Color.GRAY);
			}

			@Override
			public Body getBody() {
				// restituire versione non modificabile?
				return this.body;
			}

			@Override
			public void dispose() {
				this.contr.notifyDisposal(this);
			}
		};
	}

	// non finito
	@Override
	public Wall deathWall(int x, int y, EntityController contr) {
		return null;
//		return new Wall(x, y) {
//			Wall simpleWall = simpleWall(x, y);
//
//			@Override
//			public Vector2 getPosition() {
//				return simpleWall.getPosition();
//			}
//
//			@Override
//			public Body getBody() {
//				return simpleWall.getBody();
//			}
//
//			@Override
//			public boolean shouldCollide(ICollidable collidable) {
//				return simpleWall.shouldCollide(collidable);
//			}
//
//			@Override
//			public void collision(ICollidable collidable) {
//				if(collidable instanceof Player) {
//					this.collision((Player) collidable);
//				}
//			}
//			
//			public void collision(Player player) {
//				//player.die();
//			}
//
//			@Override
//			public void tick() {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void render(Graphics g) {
//				// TODO Auto-generated method stub
//
//			}
//
//		};
	}

}
