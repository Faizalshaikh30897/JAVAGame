package zwars.tile;

import java.awt.*;
import java.awt.image.BufferedImage;

import zwars.GameObject;
import zwars.ID;
/**
class to make a tile of the world on screen can be of many types. Obstacles on map are also a tile.
*/
public class Tile extends GameObject{
	
	public static int GRASSTILE = 0;
	public static int HOUSETILE = 1;
	public static int LOGTILE = 2;
	public static int ROADTILE = 3;
	public static int TREETILE = 4;
	public static int WALLTILE = 5;


	public static int TILEWIDTH =64, TILEHEIGHT =64; //size of tile

	protected final int id;
	protected BufferedImage texture;

	public Tile(float x, float y, ID type, int id, BufferedImage texture){
		super(x,y,type);
		this.id=id;
		this.texture = texture;

	}
	/**
	inherited from gameobject may be used to update values of attributes at every render
	*/
	public void tick(){

	}
	/**
	method renders the given tile on screen.
	@param g graphics on which tile has to be rendered
	*/
	public void render(Graphics g){
		g.drawImage(texture, (int)x, (int)y,TILEWIDTH,TILEHEIGHT,null);
		g.setColor(Color.RED);
		//g.drawRect(this.getBounds().x, this.getBounds().y, this.getBounds().width, this.getBounds().height);
	}

	/**
	@return returns the type as integer id of the tile
	*/
	public int getId(){
		return id;
	}

	/**
	return the bounded rectangle of the tile used to detect collisions.
	*/
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, TILEWIDTH, TILEHEIGHT);
	}
}
