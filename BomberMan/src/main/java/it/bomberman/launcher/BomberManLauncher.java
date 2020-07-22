package it.bomberman.launcher;



import it.bomberman.display.DisplayController;
import it.bomberman.display.DisplayModel;
import it.bomberman.display.DisplayView;
import it.bomberman.gfx.Assets;

public class BomberManLauncher {

		
	public static void main(String[] args) {
		Assets.init();
		DisplayView view = new DisplayView();
		DisplayModel model = new DisplayModel();
		DisplayController d = new DisplayController(view, model);
		d.start();
		// d.generateGame();
	}
}
