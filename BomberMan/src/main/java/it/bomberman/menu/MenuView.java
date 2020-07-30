package it.bomberman.menu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton play = new JButton("play");
	JButton settings = new JButton("settings");
	JButton exit = new JButton("exit");
	JPanel panel = new JPanel();
	
	public MenuView() {
		panel.add(play);
		panel.add(settings);
		panel.add(exit);
		}
	public JPanel getPanel()
	{
		return this.panel;
	}
}
