package it.bomberman.states;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import it.bomberman.hud.Hud;
import it.bomberman.input.KeyManager;

public class ArenaState extends GameState {

	private static final long serialVersionUID = 1L;
	private KeyManager keyManager;
	private final Hud hud;
	private ArenaModel arenaModel;
	private Color backgroundColor;

	public ArenaState(GameStateManager gsm) {
		this.gameStateManager = gsm;
		this.keyManager = new KeyManager();
		this.arenaModel = new ArenaModelImpl(this.keyManager);
		this.hud = new Hud(this.arenaModel.getPlayer1(), this.arenaModel.getPlayer2(), this.arenaModel.getClock());
	}

	public void init() {
		this.setLayout(new BorderLayout());
		// setPreferredSize(new Dimension(WIDTH * 3, HEIGHT * 3));
		setFocusable(true);
		requestFocus();
		backgroundColor = new Color(0, 153, 0);
	}

	@Override
	public void update() {
		this.keyManager.tick();
		this.arenaModel.update();
		this.hud.update();
		repaint();

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, 1200, 800);
		Toolkit.getDefaultToolkit().sync();
		if (arenaModel.gameOver()) {
			this.gameStateManager.setWinner(this.arenaModel.getWinner());
			this.gameStateManager.setState(GameStateManager.WINNER);
		} else {
			this.arenaModel.getDrawables().stream().forEach(e -> e.render(g));
			Toolkit.getDefaultToolkit().sync();
			this.hud.render(g);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		draw(g);
	}

	@Override
	public void addNotify() {
		super.addNotify();
		addKeyListener(this.keyManager);
	}
}
