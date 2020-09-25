package it.bomberman.launcher;

import it.bomberman.menu.MenuController;
import it.bomberman.menu.MenuView;

public class BomberManLauncher {

	public static void main(String[] args) {
		// PARTE IL MENU POI IL MENU DEVE CREARE IL GIOCOOOO
		MenuView menuView = new MenuView();
		MenuController menuController = new MenuController(menuView);
		menuController.start();
	}
}
