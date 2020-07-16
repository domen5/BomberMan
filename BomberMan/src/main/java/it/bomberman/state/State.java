package it.bomberman.state;

import java.awt.Graphics;

import it.bomberman.display.DisplayController;


public abstract class State {
	
	public static State currentState=null;
	
	public static void setState(State state) {
		currentState=state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	
	//protected Game game;
	protected DisplayController c;
	public State(DisplayController c ) { //(Game game)
		this.c=c;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
