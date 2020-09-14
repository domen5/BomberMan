package it.bomberman.state;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import it.bomberman.entity.creature.Player;
import it.bomberman.gfx.DefaultValues;

public class ArenaState extends GameState {

	/**
	 * 
	 */
	private int i = 0;
	private static final long serialVersionUID = 1L;
	private Player p;
	private Player p1;

	public ArenaState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	private void init() {
		 p = new Player(0, 0,1);
		p1=new Player(300,0,2);
		
		this.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(WIDTH * 3, HEIGHT * 3));
		setFocusable(true);
		requestFocus();

	}

	@Override
	public void update() {
		// Aggiornamento degli sprite bombe explosion etc..
		//this.i++;
		//this.p.tick();
		this.p.tick();
		repaint();
	}

	@Override
	public void draw(Graphics g2) {
		//System.out.println(i);
//		this.i++;
		
//		if (this.i <= 30) {
//			g2.setColor(Color.WHITE);
//		} else if (this.i <= 60) {
			g2.setColor(Color.BLACK);
//		}
//		else {
//			i = 0;
//		}
		g2.fillRect(0, 0, 1800, 900);
		Toolkit.getDefaultToolkit().sync();
		
		p.render(g2);
		//p1.render(g2);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		draw(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		this.p.keyPressed(e);
		this.p1.keyPressed(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.p.keyPressed(e);
		this.p1.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public void addNotify() {
		super.addNotify();
//		if (thread == null) {
//			thread = new Thread(this);
			addKeyListener(this);
//			thread.start();
//		}
	}

}
