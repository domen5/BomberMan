package it.bomberman.collisions;

import java.util.HashSet;
import java.util.Set;

public class CollisionManager {
	private Set<ICollidable> collidables;

	public CollisionManager() {
		this.collidables = new HashSet<ICollidable>();
	}

	public void register(ICollidable coll) {
		this.collidables.add(coll);
	}

	public boolean verifyCollision(ICollidable col) {
		/**
		 * Verifica se l'oggetto collide con qualcosa
		 */
//		int count = 0;
//		for(ICollidable c : this.collidables) {
//			if(col.shouldCollide(c)) {
//				boolean b = col.getBody().checkCollision(c.getBody());
//				if(b) {
//					count++;
//				}
//			}
//		}
//		return count > 0;
		
		return this.collidables.stream()
				.filter(c -> c.shouldCollide(col))
				.filter(c -> c.getBody().checkCollision(col.getBody()))
				.count() > 0;
	}

	public void checkAndResolveCollisions(ICollidable col) {
		this.collidables.stream()
				.filter(c -> c.shouldCollide(col))
				.filter(c -> c.getBody().checkCollision(col.getBody()))
				.forEach(c -> c.collision(col));
	}
}
