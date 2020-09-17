package it.bomberman.menu;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.bomberman.gfx.DefaultValues;
import it.bomberman.state.GameState;
import it.bomberman.state.GameStateManager;

public class MenuPanel extends GameState implements KeyListener {

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
	private ImageIcon ii;
	// image
	// private BufferedImage image;
	//private Graphics2D g;

	JLabel imageLabel;
	// game state manager
	private GameStateManager gsm;

	private int currentChoice = 0;
	private String[] options = { "Start", "Settings", "Quit" };

	private Color titleColor;
	private Font titleFont;
	private Font font;
	private volatile int menuX;
	private volatile boolean cambios = false;

	public MenuPanel(GameStateManager gsm) {
		super();
		this.gsm = gsm;
	}



	public void init() {
		imageLabel = new JLabel();
		// Prende la gif e aggiunge
		try {
			this.setLayout(new BorderLayout());
			ii = new ImageIcon(this.getClass().getResource("/textures/bg2.gif"));
			ii.setImage(ii.getImage().getScaledInstance(WIDTH * SCALE, HEIGHT * SCALE, Image.SCALE_DEFAULT));
			imageLabel.setIcon(ii);
			this.add(imageLabel, java.awt.BorderLayout.CENTER);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		try {
			titleColor = new Color(250, 250, 250);
			titleFont = new Font("Century Gothic", Font.PLAIN, 60);
			font = new Font("Arial", Font.PLAIN, 30);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}

	public void update() {
		if (cambios)
			menuX--;
		else
			menuX++;
		if (menuX == 70)
			cambios = true;
		if (menuX == 0)
			cambios = false;
		// serve o non serve?
		
		repaint();
	}

//	public void run() {
//		long start;
//		long elapsed;
//		long wait;
//		// game loop
//		while (running) {
//			update();
//			if (this.gsm.equals(0)) {
//				running = false;
//				System.exit(0);
//			}
//			/****************
//			 * 
//			 * Bisogna capire come prendere la roba per il verso giusto :) Intendo la parte
//			 * grafica
//			 * 
//			 */
//			Graphics2D g = (Graphics2D) this.getGraphics();
//			draw(g);
//			start = System.nanoTime();
//			elapsed = System.nanoTime() - start;
//			wait = targetTime - elapsed / 1000000;
//			if (wait < 0)
//				wait = 5;
//			try {
//				Thread.sleep(wait);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}

	private void select() {
		if (currentChoice == 0) {
			// start
			this.gsm.setState(GameStateManager.ARENA);
			//this.gsm.update();
			running = false;
		}
		if (currentChoice == 1) {
			// settings
		}
		if (currentChoice == 2) {
			System.exit(0);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		draw(g);
	}

	@Override
	public void draw(Graphics g2) {
		// gsm.draw(g);
		g2.drawImage(ii.getImage(), WIDTH * SCALE, HEIGHT * SCALE, null);

		g2.setColor(titleColor);
		g2.setFont(titleFont);
		
		// perchè quello che succede nel metodo update non ha effetto qui?
//		if (cambios)
//			menuX-=20;
//		else
//			menuX+=20;
//		if (menuX >= 70)
//			cambios = true;
//		if (menuX <= 0)
//			cambios = false;
		
		g2.drawString(DefaultValues.NAME, 650 + menuX, 200);
		Toolkit.getDefaultToolkit().sync();

		// draw menu options
		g2.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g2.setColor(Color.CYAN);
			} else {
				g2.setColor(Color.WHITE);
			}
			g2.drawString(options[i], 700, 300 + i * 35);
		}
		Toolkit.getDefaultToolkit().sync();
		//g2.dispose();
	}
	
	
//	@Override
//	public void paintComponents(Graphics g) {
//		// TODO Auto-generated method stub
//		super.paintComponents(g);
//		
//		draw(g);
//	}
	
	public void addNotify() {
		super.addNotify();
//		if (thread == null) {
//			thread = new Thread(this);
		addKeyListener(this);
//			thread.start();
//		}
	}

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		if (k.getKeyCode() == KeyEvent.VK_ENTER) {
			select();
		}
		if (k.getKeyCode() == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (k.getKeyCode() == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}