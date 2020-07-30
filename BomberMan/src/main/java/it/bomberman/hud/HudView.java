package it.bomberman.hud;

import java.awt.Canvas;
import java.awt.Font;
import javax.imageio.ImageIO;

public class HudView extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public HudView(){
		try {
			//ImageIO.read(getClass().getResourceAsStream("/textures/HUD.png"));
			new Font("Arial", Font.PLAIN, 14);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
