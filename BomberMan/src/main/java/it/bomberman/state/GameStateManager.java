package it.bomberman.state;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.bomberman.menu.MenuPanel;

public class GameStateManager {

	private ArrayList<GameState> gameStates;
	private int currentState;

	public static final int MENUSTATE = 0;
	public static final int ARENA = 1;
	public static final int SETTINGS = 2;
	public JFrame j;

	public GameStateManager(JFrame j) {

		gameStates = new ArrayList<GameState>();
		currentState = MENUSTATE;
		gameStates.add(new MenuPanel(this));
		gameStates.add(new ArenaState(this));
		this.j = j;
	}

	public void setState(int state) {
		JPanel oldPanel= this.gameStates.get(currentState);
		this.j.getContentPane().removeAll();
		
//		if (j.getContentPane().equals(oldPanel)) {
//			j.getContentPane().remove(oldPanel);
//		}
		this.currentState = state;
		JPanel newPanel = this.gameStates.get(state);
		j.setContentPane(newPanel);
		this.j.validate();
	}

	public void update() {
		gameStates.get(currentState).update();
	}

	public void draw(java.awt.Graphics g) {
		gameStates.get(currentState).draw(g);
	}

//	public void keyPressed(KeyEvent k) {
//		gameStates.get(currentState).keyPressed(k);
//	}
//	
//	public void keyReleased(KeyEvent k) {
//		gameStates.get(currentState).keyReleased(k);
//	}

}
