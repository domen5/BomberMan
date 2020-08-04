package it.bomberman.menu;

import javax.swing.JFrame;
public class MenuView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuView() {
		this.setName("BomberMan Menu");
		this.setContentPane(new MenuPanel(this));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
