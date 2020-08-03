package it.bomberman.display;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JComponent;


import it.bomberman.gfx.DefaultValues;

public class DisplayView extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame = null;
	
	public DisplayView() {
		setMinimumSize(new Dimension(DefaultValues.WIDTH * DefaultValues.SCALE, DefaultValues.HEIGHT * DefaultValues.SCALE));
		setMaximumSize(new Dimension(DefaultValues.WIDTH * DefaultValues.SCALE, DefaultValues.HEIGHT * DefaultValues.SCALE));
		setPreferredSize(new Dimension(DefaultValues.WIDTH * DefaultValues.SCALE, DefaultValues.HEIGHT * DefaultValues.SCALE));
		frame = new JFrame(DefaultValues.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void render(int tickCount) {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.dispose();
		bs.show();
	}

	public JFrame getFrame() {
		return frame;
	}
}
