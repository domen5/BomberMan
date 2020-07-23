package it.bomberman.hud;

import java.awt.Graphics;

public class HudController {

	private HudModel hm;
	private HudView hv;

	public HudController(HudModel hm, HudView hv) {
		this.hv=hv;
		this.hm=hm;
	}

	public void render(Graphics g) {
		this.hm.draw(g);
	}
}
