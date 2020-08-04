package it.bomberman.menu;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.bomberman.state.GameStateManager;

public class MenuPanel extends JPanel 
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
	public MenuPanel(JFrame j) {
		super();
		this.j=j;
		imageLabel= new JLabel();
		try {
			this.setLayout(new BorderLayout());
			ImageIcon ii = new ImageIcon(this.getClass().getResource("/textures/bg.gif"));
			imageLabel.setIcon(ii);
			this.add(imageLabel, java.awt.BorderLayout.CENTER);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
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
		gsm = new GameStateManager();
		//GSM= MENUSTATE
	}
	private void update() {
		gsm.update();
	}
	private void draw() {
		gsm.draw(g);
	}
		
	public void run() {
		init(j);
		long start;
		long elapsed;
		long wait;
		// game loop
		while(running) {
			update();
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
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}
}