package it.bomberman.entity.creature;

public interface WallFactory {
	public Wall MapLimitWall(int x, int y, int width, int height);
	public Wall SimpleWall(int x, int y);
	public Wall DeathWall(int x, int y);
}
