package it.bomberman.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.bomberman.state.GameStateManager;

public class GamePanel extends JPanel 
implements Runnable, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// dimensions
	public static final int WIDTH = 600;
	public static final int HEIGHT = 300;
	public static final int SCALE = 3;

	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	// image
	//private BufferedImage image;
	private Graphics2D g;

	JLabel imageLabel;
	// game state manager
	private GameStateManager gsm;
	private JFrame j;
	public GamePanel(JFrame j) {
		super();
		this.j=j;
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

	private void init(JFrame j) {
		g = (Graphics2D) this.j.getGraphics();
		running = true;
	}
	private void update() {
		gsm.update();
	}
	private void draw() {
		gsm.draw(g);
		Graphics g2 = getGraphics();
	}

	public void run() {
		init(j);
		long start;
		long elapsed;
		long wait;
		// game loop
		while(running) {
			update();
			if(this.gsm.equals(1)) {
				this.j.setVisible(false);
				this.j.dispose();
				running=false;}
			draw();
			start = System.nanoTime();
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void keyTyped(KeyEvent key) {}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}