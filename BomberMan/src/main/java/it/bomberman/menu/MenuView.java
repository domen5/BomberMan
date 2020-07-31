package it.bomberman.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import it.bomberman.buttons.MyButtons;
public class MenuView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	public Rectangle playButton = new Rectangle(740, 200, 250, 50);
	//	public Rectangle settingsButton = new Rectangle(740, 300, 250, 50);
	//	public Rectangle exitButton = new Rectangle(740, 400, 250, 50); 
	public JButton btnPlay = new JButton("Play");
	public JButton btnSettings = new JButton("Settings");
	public MyButtons btnExit = new MyButtons("Exit");
	public JFrame frame; 
	public MenuView() {
		JFrame frame = new JFrame("MENU BOMBERMAN");
		JPanel panel = new JPanel();
		Dimension size = btnPlay.getPreferredSize();
		btnPlay.setBounds(300, 180, size.width, size.height);

		frame.setSize(700, 300);
		panel.add(btnPlay);
		panel.add(btnSettings);
		panel.add(btnExit);
		frame.getContentPane().add(panel);

		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);
		frame.setTitle("Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	//	public void render(Graphics g) {
	//		
	//		Graphics2D g2d = (Graphics2D)g;
	//		Font fnt= new Font("arial", Font.BOLD, 50);
	//		g.setFont(fnt);
	//		g.setColor(Color.black);
	//		g.drawString("BOMBER MAN", 700, 100);
	//		Font fnt1= new Font("arial", Font.BOLD, 30);
	//		g.setFont(fnt1);
	//		g.drawString("PLAY", playButton.x+83, playButton.y+35);
	//		g2d.draw(playButton);
	//		g.drawString("SETTINGS", settingsButton.x+53, settingsButton.y+35);
	//		g2d.draw(settingsButton);
	//		g.drawString("EXIT", exitButton.x+95, exitButton.y+35);
	//		g2d.draw(exitButton);
	//	}
}
