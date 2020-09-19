package it.bomberman.hud;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import it.bomberman.entity.creature.Player;
import it.bomberman.gfx.Assets;

public class Hud {

	private int lifeP1;
	private int bombP1;
	private int bombP2;
	private int speedP1;
	private int speedP2;
	private int timer;
	private Player p1;
	private Player p2;
	private Color hudColor;
	private Font hudFont;

	public Hud(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.init();
	}

	public void init() {
		try {
			hudColor = new Color(250, 250, 250);
			hudFont = new Font("Century Gothic", Font.PLAIN, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		// timer=Timer.getTime();
		this.lifeP1 = this.p1.getHealth();
		this.p2.getHealth();
//		this.bombP1=p1.getBomb();
//		this.bombP2=p2.getBomb();
		this.speedP1 = this.p1.getSpeed();
		this.speedP2 = this.p2.getSpeed();
	}

	public void render(Graphics g) {
		g.setColor(hudColor);
		g.setFont(hudFont);
		g.drawRect(0, 0, 1800, 70);
		//P1
		
		g.drawString("P1 Stats: ", 100, 30);
		g.drawString("Life: " + p1.getHealth(), 250, 30);
		g.drawString("Speed: " + p1.getHealth(), 250, 60);
		
		g.drawString("TIMER", 825, 45);
		g.drawRect(800, 0, 120, 70);
		//P2
		g.drawString("P2 Stats: ", 1100, 30);
		g.drawString("Life: " + p1.getHealth(), 1250, 30);
		g.drawString("Speed: " + p1.getHealth(), 1250, 60);
	}
}
