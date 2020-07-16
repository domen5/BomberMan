package it.bomberman.state;

import java.awt.Graphics;

import it.bomberman.display.DisplayController;
import it.bomberman.entity.*;
import it.bomberman.entity.creature.Player;

public class GameState extends State{

	private Player player;
	
	public GameState(DisplayController c) { //(Game game)
		//super(game);
		super(c);
		player= new Player(c, 100, 100);
	}
	
	@Override
	public void tick() {
		player.tick();
	}
	
	@Override
	public void render(Graphics g) {
		player.render(g);
	}
	
}
