package it.bomberman.hud;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.bomberman.entity.creature.Player;

public class HudModel {

	private BufferedImage image;
	private Font font;
	private Player p1;
	private Player p2;
	
	private int health;
	private float speed;
	
	public void whatDraw(Player p) {
		health= p.getHealth() ; 
		speed=p.getSpeed();
	}
	
	public void draw(Graphics g) {
		
		//QUi prende i dati da p1 e p2 e dalla classe timer e li stampa a video
		//Da stampare: Vite, NBombe, Timer, immagini hud
		
		g.drawImage(image, 0, 10, null);
		g.setFont(font);
		//P1
		g.drawString("Scritta hud player 1", 100, 25);
		g.drawString("PLAYER 1 GANGSTA PINGU", 100, 60);
		g.drawString("HEALTH  "+health, 100, 80);
		g.drawString("SPEED  "+speed, 100, 100);
		//P2
		g.drawString("Scritta hud player 2", 400, 25);
		g.drawString("PLAYER 2 LORD MORRI", 400, 60);
		g.drawString("HEALTH  "+health, 400, 80);
		g.drawString("SPEED  "+speed, 400, 100);
		
		//Timer
		g.drawString("Timer fine gioco", 1000, 40);
		g.drawString("Timer che scorre", 1100, 40);
		
		
		
	}

}
