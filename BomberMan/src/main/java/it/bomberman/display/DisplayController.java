package it.bomberman.display;


public class DisplayController {

	private DisplayView view;
	private DisplayModel model;

	public DisplayController(DisplayView view, DisplayModel model) {
		this.model = model;
		this.view = view;
	}

	public void generateGame() {
		model.start();
	}

	public static void main(String[] args) {
		DisplayView view = new DisplayView();
		DisplayModel model = new DisplayModel();
		DisplayController d = new DisplayController(view, model);
		d.generateGame();
	}
}
