package it.bomberman.game;

import javax.swing.JFrame;

public class GameView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameView() {
		this.setName("BomberMan Game");
		this.setContentPane(new GamePanel(this));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
