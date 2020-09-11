package it.bomberman.state;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public abstract class GameState extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected GameStateManager gsm;

	public abstract void update();

	public abstract void draw(java.awt.Graphics2D g);

}
