package it.bomberman.game;

import javax.swing.JFrame;

import it.bomberman.menu.MenuPanel;

public class GameView extends JFrame{

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
