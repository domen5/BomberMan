package it.bomberman.states;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;
import it.bomberman.collisions.CollisionManager;
import it.bomberman.collisions.ICollidable;
import it.bomberman.entities.Entity;
import it.bomberman.entities.EntityController;
import it.bomberman.entities.Player;
import it.bomberman.entities.PowerUp;
import it.bomberman.entities.Wall;
import it.bomberman.entities.WallFactory;
import it.bomberman.entities.WallFactoryImpl;
import it.bomberman.entities.PowerUp.PowerUpType;
import it.bomberman.hud.Clock;
import it.bomberman.input.KeyManager;

public class ArenaModelImpl implements ArenaModel, EntityController {
	private CollisionManager collisionMan;
	private WallFactory wallFactory;
	private Player player1;
	private Player player2;
	private List<Entity> registerLater;
	private List<Entity> removeLater;
	private Clock clock;
	private boolean gameOver = false;
	private Optional<Player> winner;
	private List<Entity> entities;
	private static final int WALL_SPAWN_PROBABILITY = 60;
	private static final int POWER_UP_SPAWN_PROBABILITY = 20;

	public ArenaModelImpl(KeyManager keyManager) {
		player1 = new Player(70, 135, 1, keyManager, this);
		player2 = new Player(1060, 635, 2, keyManager, this);

		this.winner = Optional.empty();
		this.clock = new Clock(200);
		this.entities = new ArrayList<>();
		this.registerLater = new ArrayList<>();
		this.removeLater = new ArrayList<>();
		this.collisionMan = new CollisionManager();
		this.wallFactory = new WallFactoryImpl();
		initMapLimitWalls();
		register(player1);
		register(player2);
	}

	@Override
	public void update() {
		this.registerLater.stream().forEach(this::registerListed);
		this.registerLater.clear();
		this.removeLater.stream().forEach(this::removeListed);
		this.registerLater.clear();
		this.entities.stream().forEach(Entity::tick);
		this.checkClock();
	}

	public void checkClock() {
		if ("000".equals(this.clock.getTime()) || this.player1.getHealth() == 0 || this.player2.getHealth() == 0) {
			gameOver = true;
			if (player1.getHealth() > player2.getHealth()) {
				this.winner = Optional.of(player1);
			} else if (player2.getHealth() > player1.getHealth()) {
				this.winner = Optional.of(player2);
			}
		}
	}

	public Optional<Player> getWinner() {
		return this.winner;
	}

	public boolean gameOver() {
		return this.gameOver;
	}

	public Clock getClock() {
		return this.clock;
	}

	@Override
	public void register(Entity entity) {
		this.registerLater.add(entity);
	}

	private void registerListed(Entity entity) {
		this.collisionMan.register(entity);
		this.entities.add(entity);
	}

	@Override
	public List<Entity> getDrawables() {
		return Collections.unmodifiableList(this.entities);
	}

	private void initMapLimitWalls() {
		Random random;
		List<Wall> walls = new ArrayList<>();
		int xLimit = 13;
		int yLimit = 8;
		int unit = WallFactoryImpl.DEFAULT_WALL_WIDTH;
		random = new Random();

		// muri limite mappa
		IntStream.range(0, xLimit).forEach(i -> {
			walls.add(wallFactory.hardWall(unit * i - 50, 50, this));
			walls.add(wallFactory.hardWall(unit * i - 50, 50 + unit * (yLimit - 1), this));
		});
		IntStream.range(1, yLimit).forEach(i -> {
			walls.add(wallFactory.hardWall(-50, 50 + i * unit, this));
			walls.add(wallFactory.hardWall((xLimit - 1) * unit - 50, 50 + i * unit, this));
		});

		for (int i = 1; i < xLimit - 1; i++) {
			for (int j = 1; j < yLimit - 1; j++) {
				if (j % 2 == 0 && i % 2 == 0) {
					Wall w = wallFactory.hardWall(unit * i - 50, unit * j + 50, this);
					walls.add(w);
				} else if (correctBuild(i, j)) {
					int r = random.nextInt(100);
					if (r <= WALL_SPAWN_PROBABILITY) {
						Wall w = wallFactory.simpleWall(unit * i - 50, unit * j + 50, this);
						walls.add(w);
					} else if (r <= POWER_UP_SPAWN_PROBABILITY + WALL_SPAWN_PROBABILITY) {
						{
							PowerUp p = PowerUp.PowerUpBuilder.newBuilder().setController(this).setX(unit * i - 25)
									.setY(unit * j + 75)
									.setType(PowerUpType.values()[random.nextInt(PowerUpType.values().length)])
									.setValue(1)
									.build();
							register(p);
						}
					}
				}
			}
		}
		walls.forEach(this::register);
	}

	private boolean correctBuild(int i, int j) {
		int xLimit = 13;
		int yLimit = 8;
		// gli slot vicino a ciascun Player devono essere lasciati liberi da Wall
		return !((i == 1 && (j == 1 || j == 2)) || (j == 1 && (i == 1 || i == 2)))
				&& !((i == xLimit - 2 && (j == yLimit - 2 || j == yLimit - 3))
						|| (j == yLimit - 2 && (i == xLimit - 2 || i == xLimit - 3)));
	}

	@Override
	public void notifyDisposal(Entity entity) {
		this.removeLater.add(entity);
	}

	private void removeListed(Entity entity) {
		this.entities.remove(entity);
		this.collisionMan.remove(entity);
	}

	@Override
	public boolean verifyCollision(ICollidable col) {
		return this.collisionMan.verifyCollision(col);
	}

	@Override
	public Player getPlayer1() {
		return this.player1;
	}

	@Override
	public Player getPlayer2() {
		return this.player2;
	}

	@Override
	public void checkAndResolveCollisions(ICollidable coll) {
		this.collisionMan.checkAndResolveCollisions(coll);
	}

}
