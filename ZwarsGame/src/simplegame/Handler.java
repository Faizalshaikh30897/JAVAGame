package simplegame;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<>();	
	
	public Handler() {
		
	}
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject temp = object.get(i);
			temp.tick();
		}
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject temp = object.get(i);
			temp.render(g);
		}
	}
	
	public void addObject(GameObject ob) {
		this.object.add(ob);
	}
	
	public void removeObject(GameObject ob) {
		this.object.remove(ob);
	}
}
