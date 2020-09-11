package it.bomberman.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import it.bomberman.gfx.DefaultValues;

public class ArenaState extends GameState {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArenaState(GameStateManager gsm) {
		this.gsm = gsm;
	}

	
	public void init() {
		// Inizzializza bg game

	}

	@Override
	public void update() {
		// Aggiornamento degli sprite bombe explosion etc..
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 1800, 900);

		// bg.draw(g)
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
