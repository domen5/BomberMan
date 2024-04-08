package it.bomberman.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	private static final int WIDTH = 49;
	private static final int HEIGHT = 49;
	private static final int HUDHEIGHT = 22;
	public static final BufferedImage simpleWall;
	public static final BufferedImage deathWall;
	public static final BufferedImage wall;

	public static final BufferedImage[] player_d;
	public static final BufferedImage[] player_u;
	public static final BufferedImage[] player_r;
	public static final BufferedImage[] player_l;
	public static final BufferedImage[] player_bomb;
	public static final BufferedImage[] player_d2;
	public static final BufferedImage[] player_u2;
	public static final BufferedImage[] player_r2;
	public static final BufferedImage[] player_l2;
	public static final BufferedImage[] player_bomb2;
	public static final BufferedImage[] bomb;
	public static final BufferedImage[] hud;
	public static final BufferedImage[] explosion;
	public static final BufferedImage[] upgrade;

	static {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/SpAll.png"));
		player_u = new BufferedImage[2];
		player_d = new BufferedImage[2];
		player_r = new BufferedImage[3];
		player_l = new BufferedImage[3];
		player_bomb = new BufferedImage[2];
		player_bomb2 = new BufferedImage[2];

		// player_d[0]= sheet.crop(0, 0, width, height);
		player_d[0] = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
		player_d[1] = sheet.crop(WIDTH * 2, 0, WIDTH, HEIGHT);

		// player_u[0]= sheet.crop(width*3, 0, width, height);
		player_u[0] = sheet.crop(WIDTH * 4, 0, WIDTH, HEIGHT);
		player_u[1] = sheet.crop(WIDTH * 5, 0, WIDTH, HEIGHT);

		player_r[1] = sheet.crop(WIDTH * 6, 0, WIDTH, HEIGHT);
		player_r[0] = sheet.crop(WIDTH * 7, 0, WIDTH, HEIGHT);
		player_r[2] = sheet.crop(WIDTH * 8, 0, WIDTH, HEIGHT);

		player_l[1] = sheet.crop(WIDTH * 9, 0, WIDTH, HEIGHT);
		player_l[0] = sheet.crop(WIDTH * 10, 0, WIDTH, HEIGHT);
		player_l[2] = sheet.crop(WIDTH * 11, 0, WIDTH, HEIGHT);

		player_bomb[1] = sheet.crop(WIDTH * 12, 0, WIDTH, HEIGHT);
		player_bomb[0] = sheet.crop(WIDTH * 13, 0, WIDTH, HEIGHT);

		player_u2 = new BufferedImage[2];
		player_d2 = new BufferedImage[2];
		player_r2 = new BufferedImage[3];
		player_l2 = new BufferedImage[3];

		player_d2[0] = sheet.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);
		player_d2[1] = sheet.crop(WIDTH * 2, HEIGHT, WIDTH, HEIGHT);

		player_u2[0] = sheet.crop(WIDTH * 4, HEIGHT, WIDTH, HEIGHT);
		player_u2[1] = sheet.crop(WIDTH * 5, HEIGHT, WIDTH, HEIGHT);

		player_r2[1] = sheet.crop(WIDTH * 6, HEIGHT, WIDTH, HEIGHT);
		player_r2[0] = sheet.crop(WIDTH * 7, HEIGHT, WIDTH, HEIGHT);
		player_r2[2] = sheet.crop(WIDTH * 8, HEIGHT, WIDTH, HEIGHT);

		player_l2[1] = sheet.crop(WIDTH * 9, HEIGHT, WIDTH, HEIGHT);
		player_l2[0] = sheet.crop(WIDTH * 10, HEIGHT, WIDTH, HEIGHT);
		player_l2[2] = sheet.crop(WIDTH * 11, HEIGHT, WIDTH, HEIGHT);

		player_bomb2[1] = sheet.crop(WIDTH * 12, HEIGHT, WIDTH, HEIGHT);
		player_bomb2[0] = sheet.crop(WIDTH * 13, HEIGHT, WIDTH, HEIGHT);

		bomb = new BufferedImage[6];
		for (int i = 0; i < bomb.length; i++)
			bomb[i] = sheet.crop(WIDTH * i, HEIGHT * 3, WIDTH, HEIGHT);

		hud = new BufferedImage[2];
		hud[0] = sheet.crop(2, 7, HUDHEIGHT, HUDHEIGHT);
		hud[1] = sheet.crop(WIDTH + 2, HEIGHT + 7, HUDHEIGHT, HUDHEIGHT);

		explosion = new BufferedImage[3];
		explosion[0] = sheet.crop(WIDTH * 5, HEIGHT * 3, WIDTH, HEIGHT);
		explosion[1] = sheet.crop(WIDTH * 6, HEIGHT * 3, WIDTH, HEIGHT);
		explosion[2] = sheet.crop(WIDTH * 7, HEIGHT * 3, WIDTH, HEIGHT);

		simpleWall = sheet.crop(WIDTH * 8, HEIGHT * 3, WIDTH, HEIGHT);
		wall = sheet.crop(WIDTH * 9, HEIGHT * 3, WIDTH, HEIGHT);
		deathWall = sheet.crop(WIDTH * 10, HEIGHT * 3, WIDTH, HEIGHT);

		upgrade = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			upgrade[i] = sheet.crop(WIDTH * (11 + i), HEIGHT * 3, WIDTH - 2, HEIGHT);
		}
	}
}
