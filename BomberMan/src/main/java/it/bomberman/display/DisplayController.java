package it.bomberman.display;

import it.bomberman.entity.creature.Player;
//import it.bomberman.entity.creature.Player2;
import it.bomberman.hud.HudController;
import it.bomberman.hud.HudModel;
import it.bomberman.hud.HudView;
import it.bomberman.input.KeyManager;
import it.bomberman.menu.MenuView;
import it.bomberman.state.*;

public class DisplayController {

	private DisplayView displayView;
	private DisplayModel displayModel;
	private boolean running;
	private Player p;
	private Player p1;
	//private Player2 p2;
	private KeyManager keyManager;
	private HudController hudController;
	private HudController hudCon;
	private GameStateManager gameState;

	MenuView menu= new MenuView();

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public DisplayController(DisplayView displayView, DisplayModel displayModel) {
		this.displayModel = displayModel;
		this.displayView = displayView;
		InitGame();
	}

	public void InitGame() {
		keyManager= new KeyManager();
		//PLAYER
		p = new Player(this, 0, 0,1);
		p1=new Player(this, 300,0,2);
		//LISTENER KEY
		this.displayView.getFrame().addKeyListener(keyManager);
		HudModel hudMod = new HudModel();
		HudView hudView = new HudView();
		//HUD
		hudController= new HudController(hudMod, hudView);
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
		double nsPerTick = 1000000000D / 60D;

		int frames = 0;
		int ticks = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		double elapsedTime = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			elapsedTime = (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			while (delta >= 1) {
				ticks++;

				this.displayModel.tick();
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
				//COSA DISEGNO?
				this.displayView.render(this.displayModel.getTickCount());
				this.p.render(this.displayView.getGraphics());
				this.p1.render(this.displayView.getGraphics());
				this.hudController.render(this.displayView.getGraphics());		

			}
		}

		if (System.currentTimeMillis() - lastTimer >= 1000) {
			lastTimer += 1000;
			System.out.println(ticks + " ticks, " + frames + " frames");
			frames = 0;
			ticks = 0;
		}
	}
}
