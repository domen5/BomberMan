package it.bomberman.state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import it.bomberman.collisions.CollisionManager;
import it.bomberman.collisions.ICollidable;
import it.bomberman.entity.creature.Entity;
import it.bomberman.entity.creature.EntityController;
import it.bomberman.entity.creature.Player;
import it.bomberman.entity.creature.Wall;
import it.bomberman.entity.creature.WallFactory;
import it.bomberman.entity.creature.WallFactoryImpl;
import it.bomberman.input.KeyManager;

public class ArenaModelImpl implements ArenaModel, EntityController {
	private CollisionManager collisionMan;
	private WallFactory wallF;
//	private KeyManager keyManager;
	private Player p1;
	private Player p2;
	private List<Entity> registerLater;
	private List<Entity> removeLater;
	ReentrantLock lock = new ReentrantLock();

	private List<Entity> entities;

	public ArenaModelImpl(KeyManager keyManager) {
		p1 = new Player(110, 90, 1, keyManager, this);
		p2 = new Player(410, 90, 2, keyManager, this);

		this.entities = new ArrayList<Entity>();
		this.registerLater =  new ArrayList<Entity>();
		this.removeLater =  new ArrayList<Entity>();
		this.collisionMan = new CollisionManager();
		this.wallF = new WallFactoryImpl();
		initMapLimitWalls();
		register(p1);
		register(p2);
		register(wallF.simpleWall(500, 500, this));
		
	}

	@Override
	public void update() {
//		this.p1.tick();
//		this.p2.tick();

		this.registerLater.stream().forEach(this::registerListed);
		this.registerLater = new ArrayList<Entity>();
		this.removeLater.stream().forEach(this::removeListed);
		this.registerLater = new ArrayList<Entity>();
		this.entities.stream().forEach(Entity::tick);		
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
		//non thread safe?
		this.lock.lock();
		List<Entity> l = Collections.unmodifiableList(this.entities);
		this.lock.unlock();
		return l;
	}

	private void initMapLimitWalls() {
		List<Wall> walls = new ArrayList<Wall>();
		int xLimit = 18;
		int yLimit = 8;
		int unit = Wall.DEFAULT_WALL_WIDTH;
		IntStream.range(0, xLimit).forEach(i -> walls.add(wallF.mapLimitWall(unit*i, 80, this)));
		IntStream.range(0, xLimit).forEach(i -> walls.add(wallF.mapLimitWall(unit*i, 80+unit*(yLimit-1), this)));
		IntStream.range(1, yLimit).forEach(i -> walls.add(wallF.mapLimitWall(0, 80+i*unit, this)));
		IntStream.range(1, yLimit).forEach(i -> walls.add(wallF.mapLimitWall((xLimit-1)*unit, 80+i*unit, this)));
		walls.forEach(this::register);
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
