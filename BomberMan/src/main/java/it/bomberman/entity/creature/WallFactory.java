package it.bomberman.entity.creature;

public interface WallFactory {
	public Wall mapLimitWall(int x, int y, EntityController controller);
	public Wall simpleWall(int x, int y, EntityController controller);
	public Wall deathWall(int x, int y, EntityController controller);
}
