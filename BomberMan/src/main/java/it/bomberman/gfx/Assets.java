package it.bomberman.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	//Immagini dello sheet
	private static final int width=64, height=64;
	public static BufferedImage  wall, bomb, upgrade;
	public static BufferedImage [] player_d, player_u, player_r, player_l;
	public static void init() {
		//64x64
		
		SpriteSheet sheet= new SpriteSheet(ImageLoader.loadImage("/textures/SpAll.png"));
		player_u=new BufferedImage[2];
		player_d=new BufferedImage[2];
		player_r=new BufferedImage[3];
		player_l=new BufferedImage[3];

		//player_d[0]= sheet.crop(0, 0, width, height);
		player_d[0]= sheet.crop(width, 0, width, height);
		player_d[1]= sheet.crop(width*2, 0, width, height);
				
		//player_u[0]= sheet.crop(width*3, 0, width, height);
		player_u[0]= sheet.crop(width*4, 0, width, height);
		player_u[1]= sheet.crop(width*5, 0, width, height);
		
		player_r[1]= sheet.crop(width*6, 0, width, height);
		player_r[0]= sheet.crop(width*7, 0, width, height);
		player_r[2]= sheet.crop(width*8, 0, width, height);
		
		player_l[1]= sheet.crop(width*9, 0, width, height);
		player_l[0]= sheet.crop(width*10, 0, width, height);
		player_l[2]= sheet.crop(width*11, 0, width, height);
		
		//wall=sheet.crop(x, y, width, height);
		//bomb=sheet.crop(x, y, width, height);
		//upgrade=sheet.crop(x, y, width, height);
	}
	
	//classe innestata per player: d/u/l/r
	
	private void initPlayer() {
		
	}
}
