package it.bomberman.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import it.bomberman.entities.Player;
import it.bomberman.gfx.Assets;

public class Hud {

	private Player player1;
	private Player player2;
	private Font hudFont;
	private Clock clock;

	public Hud(Player p1, Player p2, Clock clock) {
		this.player1 = p1;
		this.player2 = p2;
		this.init();
		this.clock = clock;
	}

	public void init() {
		try {
			hudFont = new Font("Century Gothic", Font.PLAIN, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		this.player1.getHealth();
		this.player2.getHealth();
		this.player1.getSpeed();
		this.player2.getSpeed();
		this.player1.getBombsNumber();
		this.player2.getBombsNumber();
		this.player1.getBombExtension();
		this.player2.getBombExtension();
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, 1200, 70);
		g.setColor(Color.BLACK);
		g.setFont(hudFont);
		g.fillRect(0, 0, 1200, 70);
		g.setColor(Color.WHITE);
		g.drawImage(Assets.hud[0], 20, 0, 72, 70, null);
		g.drawString("P1 Stats: ", 100, 30);
		g.drawString("Bombs: " + this.player1.getBombsNumber(), 350, 30);
		g.drawString("Extension: " + this.player1.getBombExtension(), 350, 60);
		g.drawString("Life: " + this.player1.getHealth(), 250, 30);
		g.drawString("Speed: " + this.player1.getSpeed(), 250, 60);
		g.drawImage(Assets.hud[1], 670, 0, 72, 70, null);
		g.drawString("P2 Stats: ", 750, 30);
		g.drawString("Bombs: " + this.player2.getBombsNumber(), 1050, 30);
		g.drawString("Extension: " + this.player2.getBombExtension(), 1050, 60);
		g.drawString("Life: " + this.player2.getHealth(), 950, 30);
		g.drawString("Speed: " + this.player2.getSpeed(), 950, 60);
		g.setFont(new Font("Century Gothic", Font.PLAIN, 40));
		g.drawString(clock.getTime() + "", 550, 45);
		g.drawRect(530, 0, 120, 70);
	}
}
