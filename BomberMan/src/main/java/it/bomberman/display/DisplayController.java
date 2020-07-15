package it.bomberman.display;

public class DisplayController {

	private DisplayView view;
	private DisplayModel model;
	private boolean running;

	public DisplayController(DisplayView view, DisplayModel model) {
		this.model = model;
		this.view = view;
	}

	public void generateGame() {
		//model.start();
	}

	public static void main(String[] args) {
		DisplayView view = new DisplayView();
		DisplayModel model = new DisplayModel();
		DisplayController d = new DisplayController(view, model);
		d.start();
		//d.generateGame();
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

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (delta >= 1) {
				ticks++;
				this.model.tick();
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
