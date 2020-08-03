package it.bomberman.launcher;



import it.bomberman.display.DisplayController;
import it.bomberman.display.DisplayModel;
import it.bomberman.display.DisplayView;
import it.bomberman.entity.creature.Player;
import it.bomberman.gfx.Assets;
import it.bomberman.hud.HudController;
import it.bomberman.hud.HudModel;
import it.bomberman.hud.HudView;
import it.bomberman.menu.MenuController;
import it.bomberman.menu.MenuModel;
import it.bomberman.menu.MenuView;

public class BomberManLauncher {


	public static void main(String[] args) {
		
		
		//PARTE IL MENU POI IL MENU DEVE CREARE IL GIOCOOOO
		Assets.init();
		MenuModel menuModel =new MenuModel();
		MenuView menuView =new MenuView ();
		MenuController menuController =new MenuController(menuModel, menuView);
		menuController.start();
		
		
		//QUESTO NON DEVE ESSERE QUI
//		DisplayView view = new DisplayView();
//		DisplayModel model = new DisplayModel();
//		DisplayController display = new DisplayController(view, model);
//		display.start();
	}
}
