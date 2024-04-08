package it.bomberman.states;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MenuState extends GameState implements KeyListener {
	private static final long serialVersionUID = 1L;
	// dimensions
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	public static final int SCALE = 3;
	public static final String NAME = "BOMBERMAN";
	private ImageIcon imageIcon;
	JLabel imageLabel;
	private int currentChoice = 0;
	private String[] options = { "Start", "Help", "Quit" };
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private AtomicInteger menuX;
	private volatile boolean directionFlag = false;

	public MenuState(GameStateManager gameStateManager) {
		super();
		this.menuX = new AtomicInteger(0);
		this.gameStateManager = gameStateManager;
	}

	public void init() {
		try {
			// Set up the background image
			imageLabel = new JLabel();
			imageIcon = new ImageIcon(this.getClass().getResource("/textures/bg2.gif"));
			imageIcon.setImage(
					imageIcon.getImage().getScaledInstance(WIDTH * SCALE, HEIGHT * SCALE, Image.SCALE_DEFAULT));
			imageLabel.setIcon(imageIcon);
			this.add(imageLabel, BorderLayout.CENTER);

			// Set up the title color and font
			titleColor = new Color(250, 250, 250);
			titleFont = new Font("Century Gothic", Font.PLAIN, 60);
			font = new Font("Arial", Font.PLAIN, 40);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}

	public void update() {
		if (directionFlag) {
			menuX.incrementAndGet();
		} else {
			menuX.decrementAndGet();
		}
		if (menuX.get() == 70) {
			directionFlag = true;
		}
		if (menuX.get() == 0) {
			directionFlag = false;
		}
		repaint();
	}

	private void select() {
		switch (currentChoice) {
			case 0:
				this.gameStateManager.setState(GameStateManager.ARENA);
				break;
			case 1:
				this.gameStateManager.setState(GameStateManager.HELP);
				break;
			default:
				System.exit(0);
				break;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		draw(g);
	}

	@Override
	public void draw(Graphics g2) {
		g2.drawImage(imageIcon.getImage(), WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.setColor(titleColor);
		g2.setFont(titleFont);
		g2.drawString(NAME, 400 + menuX.get(), 200);
		Toolkit.getDefaultToolkit().sync();

		// draw menu options
		g2.setFont(font);
		for (int i = 0; i < options.length; i++) {
			g2.setColor(i == currentChoice ? Color.CYAN : Color.WHITE);
			g2.drawString(options[i], 570, 520 + i * 55);
		}
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void addNotify() {
		super.addNotify();
		addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent k) {
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
		// Not useed
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Not useed
	}
}