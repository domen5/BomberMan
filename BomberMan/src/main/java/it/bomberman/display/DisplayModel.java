package it.bomberman.display;

public class DisplayModel {

	private int tickCount = 0;

	public DisplayModel() {
		
	}
	public void tick() {
		tickCount++;
		/*
		 * for (int i = 0; i < pixels.length; i++) { pixels[i] = i + tickCount; }
		 */
	}
	
	public int getTickCount() {
		return this.tickCount;
	}
}
