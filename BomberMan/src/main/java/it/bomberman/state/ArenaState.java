package it.bomberman.state;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import it.bomberman.hud.Hud;
import it.bomberman.input.KeyManager;

public class ArenaState extends GameState {

	/**
	 * 
	 */
	private int i = 0;
	private static final long serialVersionUID = 1L;
	private KeyManager keyManager;

	private final Hud hud;
	private ArenaModel arenaModel;

	public ArenaState(GameStateManager gsm) {
		this.gsm = gsm;
		this.keyManager = new KeyManager();
		this.arenaModel = new ArenaModelImpl(this.keyManager);

		this.hud= new Hud(this.arenaModel.getP1(), this.arenaModel.getP2());
	}

	public void init() {
		this.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(WIDTH * 3, HEIGHT * 3));
		setFocusable(true);
		requestFocus();
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
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1800, 900);
		Toolkit.getDefaultToolkit().sync();

//		wallie.render(g);	
//		p1.render(g);
//		p2.render(g);
		this.hud.render(g);
		this.arenaModel.getDrawables().stream().forEach(e -> e.render(g));
		Toolkit.getDefaultToolkit().sync();
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
