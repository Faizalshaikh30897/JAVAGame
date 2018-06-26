package zwars;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Handler {

	public List<GameObject> object = new ArrayList<>();	//list of objects rendered on screen
	public List<GameObject> mapObjects = new ArrayList<>();//list of map tiles rendered on screen

	public Handler() {

	}
	/**
	  ticks all the GameObjects currently in the game to update the values of their attributes and check  necessary constraints.
	 */
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject temp = object.get(i);
			temp.tick();
		}
	}
	/**
	  renders all the GameObjects currently in the game to draw them on screen.
	  @param g Graphics object on which the components have to be rendered.
	 */
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject temp = object.get(i);
			temp.render(g);
		}
	}
	/**
	  add a new GameObject to the list in Handler.
	  @param ob object of GameObjects subclasses.
	 */
	public void addObject(GameObject ob) {
		this.object.add(ob);
	}
	/**
	  remove an object from the list in handler.It wont be rendered so it gets deleted from the screen.
	  @param ob A GameObject to be removed.
	 */
	public void removeObject(GameObject ob) {
		this.object.remove(ob);
	}
	/**
	  add a new map tile to the list in Handler.
	  @param ob object of tiles loaded on screnn.
	 */
	public void addMapObject(GameObject ob) {
		this.mapObjects.add(ob);
	}
}
