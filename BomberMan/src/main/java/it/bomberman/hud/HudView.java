package it.bomberman.hud;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.imageio.ImageIO;

import it.bomberman.entity.creature.Player;

public class HudView extends Canvas{
	private Player p;
	public HudView(Player p){
		this.p=p;
		try {
			ImageIO.read(getClass().getResourceAsStream("/textures/HUD.png"));
			new Font("Arial", Font.PLAIN, 14);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Graphics getGraphics() {
		BufferStrategy bs = getBufferStrategy();
		return bs.getDrawGraphics();
	}
}
