package it.bomberman.display;

<<<<<<< HEAD
=======
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;

>>>>>>> ce4d1e9799360540479b58cca75277d610d2da3e
public class DisplayModel {

	private int tickCount = 0;

	public DisplayModel() {

	}
	
	public void initializeWorld() {
		
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
