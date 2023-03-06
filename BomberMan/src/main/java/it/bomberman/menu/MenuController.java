package it.bomberman.menu;

public class MenuController {

	private MenuView menuView;
	private boolean running = false;
	private long targetTime = 16666667; // 60fps
	private final long CONST = (long) 1e6;
	private long frameEnd;
	private long elapsed = 0;

	public MenuController(MenuView menuView) {
		this.menuView = menuView;
	}

	public void start() {
		this.running = true;
		Thread t = new Thread(this::run);
		t.start();
	}

	private void run() {
		while (this.running == true) {
			final long start = System.nanoTime();
			this.menuView.update();
			//this.menuView.draw();
			this.elapsed = System.nanoTime() - start;
			long wait = (targetTime - elapsed) / CONST + 1;
			if (wait < 0) {
				wait = 5;
			}
			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}
			final long tmp = System.nanoTime();
			this.frameEnd = tmp;
		}
	}
}
