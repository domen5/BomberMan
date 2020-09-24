package it.bomberman.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import it.bomberman.collisions.CollisionManager;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Vector2;
import it.bomberman.entity.creature.Entity;
import it.bomberman.entity.creature.EntityController;
import it.bomberman.entity.creature.Player;
import it.bomberman.entity.creature.PowerUp;
import it.bomberman.entity.creature.Wall;
import it.bomberman.entity.creature.WallFactory;
import it.bomberman.entity.creature.WallFactoryImpl;
import it.bomberman.hud.Clock;
import it.bomberman.entity.creature.PowerUp.PowerUpType;
import it.bomberman.input.KeyManager;

public class ArenaModelImpl implements ArenaModel, EntityController {
	private CollisionManager collisionMan;
	private WallFactory wallF;
//	private KeyManager keyManager;
	private Player p1;
	private Player p2;
	private List<Entity> registerLater;
	private List<Entity> removeLater;
	private Random rd;
	ReentrantLock lock = new ReentrantLock();
	private Clock clock;
	private boolean gameOver = false;
	private Optional<Player> winner = Optional.empty();
	private List<Entity> entities;

	public ArenaModelImpl(KeyManager keyManager) {
		p1 = new Player(70, 135, 1, keyManager, this);
		p2 = new Player(1660, 735, 2, keyManager, this);

		this.clock = new Clock(200);

		this.entities = new ArrayList<Entity>();
		this.registerLater = new ArrayList<Entity>();
		this.removeLater = new ArrayList<Entity>();
		this.collisionMan = new CollisionManager();
		this.wallF = new WallFactoryImpl();
		initMapLimitWalls();
		register(p1);
		register(p2);
	}

	@Override
	public void update() {
//		this.p1.tick();
//		this.p2.tick();

		this.registerLater.stream().forEach(this::registerListed);
		this.registerLater.clear();
		this.removeLater.stream().forEach(this::removeListed);
		this.registerLater.clear();
		this.entities.stream().forEach(Entity::tick);

	}

	public void checkClock() {

		if (this.clock.getTime() == "000") {
			gameOver = true;
			if (p1.getHealth() > p2.getHealth()) {
				this.winner = Optional.of(p1);
				System.out.println("P1 vince");
			} else if (p2.getHealth() > p1.getHealth()) {
				this.winner = Optional.of(p2);
				System.out.println("P2 vince");
			} else {
				System.out.println("PARI");
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
		this.lock.lock();
		this.registerLater.add(entity);
		this.lock.unlock();
	}

	private void registerListed(Entity entity) {
		this.collisionMan.register(entity);
		this.entities.add(entity);
	}

	@Override
	public List<Entity> getDrawables() {
		// non thread safe?
		this.lock.lock();
		List<Entity> l = Collections.unmodifiableList(this.entities);
		this.lock.unlock();
		return l;
	}

	private void initMapLimitWalls() {
		List<Wall> walls = new ArrayList<Wall>();
		int xLimit = 19;
		int yLimit = 9;
		int unit = WallFactoryImpl.DEFAULT_WALL_WIDTH;
		rd = new Random();
		IntStream.range(0, xLimit).forEach(i -> walls.add(wallF.hardWall(unit * i - 50, 50, this)));
		IntStream.range(0, xLimit)
				.forEach(i -> walls.add(wallF.hardWall(unit * i - 50, 50 + unit * (yLimit - 1), this)));
		IntStream.range(1, yLimit).forEach(i -> walls.add(wallF.hardWall(-50, 50 + i * unit, this)));
		IntStream.range(1, yLimit)
				.forEach(i -> walls.add(wallF.hardWall((xLimit - 1) * unit - 50, 50 + i * unit, this)));

		// walls.add(wallF.hardWall(500, 500, this));

		for (int i = 1; i < xLimit - 1; i++) {
			for (int j = 1; j < yLimit - 1; j++) {
				if (j % 2 == 0 && i % 2 == 0) {
					Wall w = wallF.hardWall(unit * i - 50, unit * j + 50, this);
					walls.add(w);
				} else if (correctBuild(i, j)) {
					int r = rd.nextInt(6);
					if (r < 4) {
						Wall w = wallF.simpleWall(unit * i - 50, unit * j + 50, this);
						walls.add(w);
					} else if (r == 5) {
						{
							PowerUp p = PowerUp.PowerUpBuilder.newBuilder().setController(this).setX(unit * i - 25)
									.setY(unit * j + 75)
									.setType(PowerUpType.values()[rd.nextInt(PowerUpType.values().length)]).setValue(1)
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
		int xLimit = 19;
		int yLimit = 9;

		return !((i == 1 && (j == 1 || j == 2)) || (j == 1 && (i == 1 || i == 2)))
				&& !((i == xLimit - 2 && (j == yLimit - 2 || j == yLimit - 3))
						|| (j == yLimit - 2 && (i == xLimit - 2 || i == xLimit - 3)));
	}

	@Override
	public void notifyDisposal(Entity entity) {
		this.lock.lock();
		this.removeLater.add(entity);
		this.lock.unlock();
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
	public Player getP1() {
		return this.p1;
	}

	@Override
	public Player getP2() {
		return this.p2;
	}

	@Override
	public void checkAndResolveCollisions(ICollidable coll) {
		this.collisionMan.checkAndResolveCollisions(coll);
	}

}
