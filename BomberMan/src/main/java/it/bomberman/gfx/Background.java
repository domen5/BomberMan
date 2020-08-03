package it.bomberman.gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import it.bomberman.gfx.DefaultValues;

public class Background {
private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background(String s, double ms) {
		
		try {
			image = ImageIO.read(
				getClass().getResourceAsStream(s)
			);
			moveScale = ms;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % DefaultValues.WIDTH;
		this.y = (y * moveScale) % DefaultValues.HEIGHT;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(image, (int)x, (int)y, null);
		
		if(x < 0) {
			g.drawImage(
				image,
				(int)x + DefaultValues.WIDTH,
				(int)y,
				null
			);
		}
		if(x > 0) {
			g.drawImage(
				image,
				(int)x - DefaultValues.WIDTH,
				(int)y,
				null
			);
		}
	}
}
