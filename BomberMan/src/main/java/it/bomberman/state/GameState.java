package it.bomberman.state;

import javax.swing.JPanel;

public abstract class GameState extends JPanel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected GameStateManager gsm;
	
	public abstract void init();

	public abstract void update();

	public abstract void draw(java.awt.Graphics g);

}
