package it.bomberman.menu;

import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Dimension;


import it.bomberman.states.GameStateManager;

public class MenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	private GameStateManager gsm;

	public MenuView() {
		gsm = new GameStateManager(this);
		this.setName("BomberMan Menu");
		this.gsm.setState(GameStateManager.MENUSTATE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

	}

	public void update() {
		this.gsm.update();
	}
}
