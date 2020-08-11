package it.bomberman.map;

public class GameMap {
	private MapSpot[][] GridTiles; //mappa alla griglia in spazio 2d
	
	public GameMap(int columns, int rows) {
		GridTiles = new MapSpot[rows>=3?rows:3][columns>=5?columns:5];
		//Minimo 3 righe and 5 colone
	}
	
	public void LoadMap(int columns, int rows, MapSpot[] spawnPoints, MapSpot[] indestructableWalls) {
		//caricare la mappa usando informazioni da fuori
	}
	
	public void PanicMode() {
		//quando il timer sta per finire
	}
	
	
	
	
}
