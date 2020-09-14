package it.bomberman.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;

import it.bomberman.state.GameStateManager;

public class MenuView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Color titleColor;
	private Font titleFont;
	private Font font;
	private GameStateManager gsm;

	public MenuView() {
		gsm= new GameStateManager(this);
		this.setName("BomberMan Menu");
		this.setContentPane(new MenuPanel(gsm));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		//this.gsm = gsm;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		draw();
	}
	
	public void draw() {
		//this.gsm.draw(getGraphics());
	}

	public void update() {
		this.gsm.update();
		//repaint();
	}
}
