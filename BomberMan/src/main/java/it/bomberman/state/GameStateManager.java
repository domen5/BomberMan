package it.bomberman.state;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.bomberman.menu.MenuPanel;

public class GameStateManager {

	private ArrayList<Optional<GameState>> gameStates;
	private int currentState;

	public static final int MENUSTATE = 0;
	public static final int ARENA = 1;
	public static final int SETTINGS = 2;
	public JFrame j;

	public GameStateManager(JFrame j) {

		gameStates = new ArrayList<Optional<GameState>>();
		currentState = MENUSTATE;
		gameStates.add(Optional.empty());
		gameStates.add(Optional.empty());
		this.j = j;
	}

	public void setState(int state) {
		Optional<GameState> opt = this.gameStates.get(currentState);
		if(opt.isPresent()) {
			opt.get().removeAll();
		}
//		this.j.getContentPane().removeAll();
		
//		if (j.getContentPane().equals(oldPanel)) {
//			j.getContentPane().remove(oldPanel);
//		}
		opt = this.gameStates.get(state);
		if(opt.isEmpty()) {
			initState(state);
			opt = this.gameStates.get(state);
		}
		
		this.currentState = state;
		j.setContentPane(opt.get());
		//this.gameStates.get(state).addNotify();
		this.j.validate();
		opt.get().validate();
		opt.get().init();
	}

	public void update() {
		gameStates.get(currentState).get().update();
	}

	public void draw(java.awt.Graphics g) {
		gameStates.get(currentState).get().draw(g);
	}
	
	private void initState(int state) {
		GameState gs = null;
		
		if(state == MENUSTATE) {
			gs = new MenuPanel(this);
		}
		else if(state == ARENA) {
			gs = new ArenaState(this);
		}
		
		this.gameStates.set(state, Optional.of(gs));
	}
	
//	public void keyPressed(KeyEvent k) {
//		gameStates.get(currentState).keyPressed(k);
//	}
//	
//	public void keyReleased(KeyEvent k) {
//		gameStates.get(currentState).keyReleased(k);
//	}

}
