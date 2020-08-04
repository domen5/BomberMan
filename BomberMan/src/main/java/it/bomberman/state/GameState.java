package it.bomberman.state;

import javax.swing.JFrame;

public abstract class GameState extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected GameStateManager gsm;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
}
