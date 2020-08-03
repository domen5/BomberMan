package it.bomberman.collisions;

import java.util.Optional;
import java.util.Set;

import it.bomberman.entity.creature.*;

public interface ICollidable {
	// s extends shape //?
	public Optional<Set<Shape>> getBoundings();
	public Vector2 getPosition();
	//servono diversi offset per ogni Shape? forse una classe ad hoc 
	//invece di un Set<Shae>?
	
	public void collision(Player player);
	public void collision(Bomb bomb);
	//public void collision(Wall wall);
	//public void collision(Explosion explosion);
	//public void collision(DeathWall dw);
	//public void collision(HardWall hw);
	
}
