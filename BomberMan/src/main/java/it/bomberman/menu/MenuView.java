package it.bomberman.menu;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import it.bomberman.states.GameStateManager;

public class MenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	private transient GameStateManager gameStateManager;

	public MenuView() {
		gameStateManager = new GameStateManager(this);
		this.setName("BomberMan Menu");
		this.gameStateManager.setState(GameStateManager.MENUSTATE);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setPreferredSize(new Dimension(1200, 800));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		draw();
	}

	public void draw() {
		// default behavior
	}

	public void update() {
		this.gameStateManager.update();
	}
}
