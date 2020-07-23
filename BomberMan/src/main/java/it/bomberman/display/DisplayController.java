package it.bomberman.display;

import it.bomberman.entity.creature.Player;
import it.bomberman.hud.HudController;
import it.bomberman.hud.HudModel;
import it.bomberman.hud.HudView;
import it.bomberman.input.KeyManager;
import it.bomberman.state.*;

public class DisplayController {

	private DisplayView view;
	private DisplayModel model;
	private boolean running;
	private State gameState;
	private Player p;
	private Player p1;
	private KeyManager keyManager;
	private HudController hudCon;
	
	
	
	public KeyManager getKeyManager() {
		return keyManager;
	}

	public DisplayController(DisplayView view, DisplayModel model) {
		this.model = model;
		this.view = view;
		gameState = new GameState(this);
		State.setState(gameState);
		keyManager= new KeyManager();
		//PLAYER
		p = new Player(this, 0, 0,1);
		p1=new Player(this, 0,100,2);
		//LISTENER KEY
		this.view.getFrame().addKeyListener(keyManager);
		
		

		HudModel hudMod = new HudModel();
		HudView hudView = new HudView(new Player(this, 0, 0, 1));
		//HUD
		hudCon= new HudController(hudMod, hudView);
		
		
	}

	public synchronized void start() {
		running = true;
		run();
	}

	public synchronized void stop() {
		running = false;
	}

	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 16D;

		int frames = 0;
		int ticks = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			while (delta >= 1) {
				ticks++;
				this.model.tick();
				this.keyManager.tick();
				this.p.tick();
				this.p1.tick();
				delta -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				this.view.render(this.model.getTickCount());
				this.p.render(this.view.getGraphics());
				this.p1.render(this.view.getGraphics());
				this.hudCon.render(this.view.getGraphics());

			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " frames");
				frames = 0;
				ticks = 0;
			}
		}
	}
}
