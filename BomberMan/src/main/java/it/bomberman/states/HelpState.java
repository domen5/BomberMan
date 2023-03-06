package it.bomberman.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import it.bomberman.gfx.Assets;

public class HelpState extends GameState implements KeyListener {

	private static final long serialVersionUID = 1L;
	private GameStateManager gsm;
	private int currentChoice = 0;
	private Font fontTitle, subTitle, paragraph, fontCh;
	private int upgradeSpace = 150;

	private String[] options = { "Start Game", "Quit" };

	public HelpState(GameStateManager gsm) {
		super();
		this.gsm = gsm;
	}

	@Override
	public void init() {
		try {
			fontTitle = new Font("Century Gothic", Font.PLAIN, 45);
			subTitle = new Font("Century Gothic", Font.PLAIN, 25);
			paragraph = new Font("Century Gothic", Font.PLAIN, 14);
			fontCh = new Font("Century Gothic", Font.PLAIN, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setFocusable(true);
		requestFocus();
	}

	private void select() {
		switch (currentChoice) {
			case 0:
				this.gsm.setState(GameStateManager.ARENA);
				break;
			case 1:
				System.exit(0);
				break;
		}
	}

	@Override
	public void update() {
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		draw(g);
	}

	public void addNotify() {
		super.addNotify();
		addKeyListener(this);
	}

	private int getUpgradeDistance(int times, int distance) {
		int upgradePadding = 20;
		return upgradeSpace + 120* times + distance * upgradePadding;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, 1200, 800);
		g.setFont(fontTitle);
		g.setColor(Color.BLACK);
		g.fillRect(360, 650, 550, 100);
		g.setColor(Color.WHITE);
		g.drawRect(360, 650, 550, 100);
		g.setColor(Color.WHITE);
		g.drawString("---BomberMan Rules---", 360, 50);
		g.setFont(subTitle);
		g.drawString("Players: ", 160, 120);
		g.drawString("Upgrades:", 900, 120);
		g.drawString("Walls:", 160, 330);

		// PLAYER DRAW
		g.setFont(paragraph);
		g.drawString("PLAYER 1 KEYS: ", 20, 150);
		g.drawImage(Assets.player_d[1], 40, 150, 100, 100, null);
		g.drawString("WASD to move ", 20, 270);
		g.drawString("SPACE to drop the bomb", 20, 290);
		// Player2
		g.drawString("PLAYER 2 KEYS: ", 250, 150);
		g.drawImage(Assets.player_d2[1], 270, 150, 100, 100, null);
		g.drawString("ARROW to move", 250, 270);
		g.drawString("ENTER to drop the bomb", 250, 290);

		// WALLS
		g.drawImage(Assets.simpleWall, 40, 350, 100, 100, null);
		g.drawString("This is a Simple wall:", 20, 460);
		g.drawString("you can destroy it with", 20, 480);
		g.drawString("your bomb and sometimes", 20, 500);
		g.drawString("it will drop upgrades!", 20, 520);
		g.drawImage(Assets.wall, 280, 350, 100, 100, null);
		g.drawString("This is a Wall:", 250, 460);
		g.drawString("you can't destroy it...", 250, 480);
		g.drawString("It delimits the playing area", 250, 500);

		// UPGRADE
		g.drawImage(Assets.upgrade[0], 750, getUpgradeDistance(0,0), 100, 100, null);
		g.drawString("The BOMBERMAN SHOES: ", 860, getUpgradeDistance(0,1));
		g.drawString("Put your fantastic new sneakers in your feet", 860, getUpgradeDistance(0,2));
		g.drawString("and run fast then the explosion!", 860, getUpgradeDistance(0,3));
		g.drawString("INCREASE YOUR SPEED BY 1", 860, getUpgradeDistance(0,4));

		g.drawImage(Assets.upgrade[1], 750, getUpgradeDistance(1,0), 100, 100, null);
		g.drawString("The BOMBERMAN BOMB: ", 860, getUpgradeDistance(1,1));
		g.drawString("Drop only one bomb is boring...", 860, getUpgradeDistance(1,2));
		g.drawString("Now your pocket is bigger!", 860, getUpgradeDistance(1,3));
		g.drawString("INCREASE YOUR BOMB NUMBER BY 1", 860, getUpgradeDistance(1,4));

		g.drawImage(Assets.upgrade[2], 750, getUpgradeDistance(2,0), 100, 100, null);
		g.drawString("The BOMBERMAN HEART: ", 860, getUpgradeDistance(2,1));
		g.drawString("playing with bombs is not safe...", 860, getUpgradeDistance(2,2));
		g.drawString("But now you can play more safety!", 860, getUpgradeDistance(2,3));
		g.drawString("INCREASE YOUR LIFE BY 1", 860, getUpgradeDistance(2,4));

		g.drawImage(Assets.upgrade[3], 750, getUpgradeDistance(3,0), 100, 100, null);
		g.drawString("The BOMBERMAN CANDLE STICK: ", 860, getUpgradeDistance(3,1));
		g.drawString("Does your enemy run faster than you?", 860, getUpgradeDistance(3,2));
		g.drawString("Now you will have no more problems!", 860, getUpgradeDistance(3,3));
		g.drawString("INCREASE YOUR EXPLOSION RANGE BY 1", 860, getUpgradeDistance(3,4));

		// g.drawString("Bomberman is an arcade game in which two or more players
		// compete", 470, 200);
		// g.drawString("against each other trying to make the opponent lose by blowing
		// him", 470, 220);
		// g.drawString("up with bombs.", 470, 240);
		// g.drawString("The game ends when only one player is left with lives or when
		// ", 470, 260);
		// g.drawString("the game timer expires.", 470, 280);
		// g.drawString("There are breakable and unbreakable wall.", 470, 300);
		// g.drawString("Breakable walls can be destroyed using bombs.", 470, 320);
		// g.drawString("A destoyed wall can drop upgrades.", 470, 340);
		// g.drawString("Each bomb explodes a few seconds after it was dropped ", 470,
		// 360);
		// g.setFont(new Font("Century Gothic", Font.PLAIN, 29));
		// // g.drawString("CHOOSE STARTGAME AND HAVE FUN!!!", 470, 540);

		g.setFont(fontCh);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.CYAN);
			} else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 600, 690 + i * 35);
		}
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			select();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
