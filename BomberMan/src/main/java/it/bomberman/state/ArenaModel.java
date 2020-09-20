package it.bomberman.state;

import java.util.List;

import it.bomberman.entity.creature.Entity;
import it.bomberman.entity.creature.Player;

public interface ArenaModel {
	public void update();
	public void register(Entity entity);
	public List<Entity> getDrawables();
	public Player getP1();
	public Player getP2();
}
