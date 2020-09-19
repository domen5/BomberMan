package it.bomberman.state;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import it.bomberman.collisions.CollisionManager;
import it.bomberman.entity.creature.Player;
import it.bomberman.entity.creature.Wall;
import it.bomberman.entity.creature.WallFactory;
import it.bomberman.entity.creature.WallFactoryImpl;
import it.bomberman.hud.Hud;
import it.bomberman.input.KeyManager;

public class ArenaState extends GameState {

	/**
	 * 
	 */
	private int i = 0;
	private static final long serialVersionUID = 1L;
	private Player p1;
	private Player p2;
	private WallFactory wallF;
	private Wall wallie;
	private KeyManager keyManager;
	private CollisionManager collisionMan;
	private final Hud hud;
//	private

	public ArenaState(GameStateManager gsm) {
		this.gsm = gsm;
		this.keyManager = new KeyManager();
		this.collisionMan = new CollisionManager();
		this.wallF = new WallFactoryImpl();
		p1 = new Player(0, 0, 1, this.keyManager, this.collisionMan);
		p2 = new Player(300, 0, 2, this.keyManager, this.collisionMan);
		this.hud= new Hud(this.p1, this.p2);
		wallie = wallF.SimpleWall(500, 500);
		this.collisionMan.add(p1);
		this.collisionMan.add(p2);
		this.collisionMan.add(wallie);
	}

	public void init() {

		this.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(WIDTH * 3, HEIGHT * 3));
		setFocusable(true);
		requestFocus();
	}

	@Override
	public void update() {
		// Aggiornamento degli sprite bombe explosion etc..
		// this.i++;
		// this.p.tick();
		this.keyManager.tick();
		this.p1.tick();
		this.p2.tick();
		this.hud.update();
		repaint();
	}

	@Override
	public void draw(Graphics g) {
		// System.out.println(i);
//		this.i++;

//		if (this.i <= 30) {
//			g2.setColor(Color.WHITE);
//		} else if (this.i <= 60) {
		g.setColor(Color.BLACK);
//		}
//		else {
//			i = 0;
//		}
		g.fillRect(0, 0, 1800, 900);
		Toolkit.getDefaultToolkit().sync();

		wallie.render(g);	
		p1.render(g);
		p2.render(g);
		this.hud.render(g);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		draw(g);
	}


	public void addNotify() {
		super.addNotify();
//		if (thread == null) {
//			thread = new Thread(this);
		addKeyListener(this.keyManager);
//			thread.start();
//		}
	}

}
