package zwars.world;

import java.awt.Graphics;

import zwars.Handler;
import zwars.ID;
import zwars.tile.Tile;
import zwars.utils.Utils;
/**
handles the world (map). renders the map on screen.
*/
public class World {

	private int width, height;//width and height of the screen in terms of  no. of tiles

	private int[][] tiles;//stores id of tile to show which type of tiles are rendered at whot position

	private Handler handler;

	public World(String path, Handler handler){
	this.handler = handler;

	loadWorld(path);

	}
	/**
	inherited from gameobject may be used to update values of attributes at each clock tick
	*/
	public void tick(){

	}
	/**
	redners the whole map on screen by rendering each tile
	*/
	public void render(Graphics g){

		for(int y=0 ; y<height ; y++){
			for(int x=0 ; x<width ; x++){

				if(getTile(x,y) != null) {
					getTile(x,y).render(g);
				}
			}
		}

	}

	/**
	used to get a tile based on its type obtained from loadFileAsString
	@param x no of row in array
	@param y no of column in array
	@return object of Tile of appropriate type
	*/
	public Tile getTile(int x,int y){
		Tile t;
		int xpos = x*Tile.TILEWIDTH;
		int ypos = y*Tile.TILEHEIGHT;
		if(tiles[x][y] == Tile.GRASSTILE) {
			t= new Tile(xpos, ypos , ID.Tile, tiles[x][y] ,Assets.grass);
		}else if(tiles[x][y] == Tile.HOUSETILE) {

			t= new Tile(xpos, ypos , ID.Tile, tiles[x][y] ,Assets.house);
		}else if(tiles[x][y] == Tile.LOGTILE) {

			t= new Tile(xpos, ypos , ID.Tile, tiles[x][y] ,Assets.log);
		}else if(tiles[x][y] == Tile.ROADTILE) {

			t= new Tile(xpos, ypos , ID.Tile, tiles[x][y] ,Assets.road);
		}else if(tiles[x][y] == Tile.TREETILE) {

			t= new Tile(xpos, ypos , ID.Tile, tiles[x][y] ,Assets.tree);
		}else if(tiles[x][y] == Tile.WALLTILE) {

			t= new Tile(xpos, ypos , ID.Tile, tiles[x][y] ,Assets.wall);
		}else {

			t= new Tile(xpos, ypos , ID.Tile, tiles[x][y] ,Assets.grass);
		}
		return t;
	}

	private void loadWorld(String path){

		String file= Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);



		tiles = new int[width][height];

		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);

				int xpos = x*Tile.TILEWIDTH;
				int ypos = y*Tile.TILEHEIGHT;

				if(tiles[x][y] != Tile.GRASSTILE) {

					handler.addMapObject(getTile(x,y));
				}
			}
		}
	}

	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
}
