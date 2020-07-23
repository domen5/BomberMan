package it.bomberman.state;

import java.awt.Graphics;

import it.bomberman.display.DisplayController;
import it.bomberman.entity.*;
import it.bomberman.entity.creature.Player;

public class GameState extends State{

	private Player player;
	private int ca=0;
	public GameState(DisplayController c) { //(Game game)
		//super(game);
		super(c);
		ca++;
		//player= new Player(c, 100, 100, ca);
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
