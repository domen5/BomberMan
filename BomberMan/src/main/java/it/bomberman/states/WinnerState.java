package it.bomberman.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;

import it.bomberman.entities.Player;
import it.bomberman.gfx.Assets;

public class WinnerState extends GameState implements KeyListener {

	private static final long serialVersionUID = 1L;
	private transient Optional<Player> player;
	private Font fontTitle;
	private Font fontPar;
	private int currentChoice = 0;
	private String[] options = { "Retry", "Quit" };

	public WinnerState(GameStateManager gameStateManager) {
		super();
		this.gameStateManager = gameStateManager;
		this.player = this.gameStateManager.getWinner();
	}

	private void select() {
		if (currentChoice == 0) {
			this.gameStateManager.setState(GameStateManager.ARENA);
			this.gameStateManager.update();
		} else {
			System.exit(0);
		}
	}

	@Override
	public void addNotify() {
		super.addNotify();
		addKeyListener(this);
	}

	@Override
	public void init() {
		try {
			fontTitle = new Font("Century Gothic", Font.PLAIN, 100);
			fontPar = new Font("Century Gothic", Font.PLAIN, 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setFocusable(true);
		requestFocus();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		draw(g);
	}

	@Override
	public void update() {
		repaint();
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, 1200, 800);
		g.setFont(fontTitle);
		g.setColor(Color.black);
		g.drawRect(400, 200, 400, 500);
		g.fillRect(400, 200, 400, 500);
		if (player.isEmpty()) {
			g.setColor(Color.white);
			g.drawString("Draw", 450, 200);
		} else {
			Player p = player.get();
			g.setColor(Color.WHITE);
			g.drawString("Player " + p.getPlayerNumb() + " Win", 300, 200);
		}
		g.drawImage(Assets.player_d[1], 200, 200, 500, 500, null);
		g.drawImage(Assets.player_d2[0], 720, 200, 500, 500, null);
		g.setFont(fontPar);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.CYAN);
			} else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 530, 400 + i * 75);
		}
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			select();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Not used
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not used
	}

}
