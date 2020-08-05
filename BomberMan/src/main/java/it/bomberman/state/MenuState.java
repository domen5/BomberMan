package it.bomberman.state;

import it.bomberman.gfx.DefaultValues;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int currentChoice = 0;
	private String[] options = {
			"Start",
			"Settings",
			"Quit"
	};

	private Color titleColor;
	private Font titleFont;
	private Font font;

	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;

		try {
			titleColor = new Color(250, 250, 250);
			titleFont = new Font(
					"Century Gothic",
					Font.PLAIN,
					60);
			font = new Font("Arial", Font.PLAIN, 30);	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {}

	
	public void draw(Graphics2D g) {

		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString(DefaultValues.NAME, 700, 200);

		// draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.CYAN);
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 700, 300 + i * 35);
		}	
	}

	private void select() {
		if(currentChoice == 0) {
			// start


		}
		if(currentChoice == 1) {
			// settings
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {}

	@Override
	public void update() {

	}
}