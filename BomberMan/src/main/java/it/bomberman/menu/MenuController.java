package it.bomberman.menu;

public class MenuController {

	private MenuModel menuModel;
	private MenuView menuview;
	public MenuController(MenuModel menuModel, MenuView menuview) {
		this.menuModel=menuModel;
		this.menuview=menuview;
	}	
}
