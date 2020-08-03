package it.bomberman.menu;

import javax.swing.JFrame;
public class MenuView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuView() {

		JFrame window = new JFrame("BomberMan Menu");
		window.setContentPane(new MenuPanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

}
