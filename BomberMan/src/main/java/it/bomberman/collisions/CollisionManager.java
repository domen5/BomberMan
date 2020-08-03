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
		/*
		 * for (ICollidable c : collidables) {
		 
			
		}
		*/
		
		return false;
	}
	
	public void checkAndResolveCollisions(ICollidable coll) {
		/*
		 * collidedBodies
		 * 
		 * for (ICollidable c : collidables) {
		 * 	if() {
		 * 		collidedBodies.add(c);
		 * }
		 * 
		 * for(ICollidable c : collidedBodies) {
		 * 		c.collide(col);
		 * }
		 * 
			
		}
		*/
	}
}
