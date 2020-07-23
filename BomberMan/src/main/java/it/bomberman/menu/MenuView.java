package it.bomberman.menu;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton play = new JButton("play");
	JButton settings = new JButton("settings");
	JButton exit = new JButton("exit");
	JButton mainMenu = new JButton("main menu");
		
	CardLayout layout = new CardLayout();
	
	JPanel panel = new JPanel();
	JPanel game = new JPanel();
	JPanel menu = new JPanel(); 
}
