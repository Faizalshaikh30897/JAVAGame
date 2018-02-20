package simplegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerBullet extends GameObject{

	private Handler handler;
	private ID parent;
	
	
	public PlayerBullet(float x, float y, ID id,ID parentId,  Handler handler) {
		super(x, y, id);
		this.parent = parentId;
		this.handler = handler;
		
	}

	@Override
	public void tick() {
	    this.x += velX;
	    this.y += velY;
	    
	    if(x > Game.WIDTH || x < 0) handler.removeObject(this);
	    if(y > Game.HEIGHT || y < 0) handler.removeObject(this);
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, 4, 4);
		
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int) y, 16, 16);
	}

	public ID getParent() {
		return this.parent;
	}
	
	public void setParent(ID id) {
		this.parent = id;
	}
}
