package zwars.player;

import java.awt.*;
import zwars.*;
/**
 *	class to create the bullets for both players.
 */
public class PlayerBullet extends GameObject{

	private Handler handler;
	private Player parent;//the player object who shot this bullet


	public PlayerBullet(float x, float y, ID id,Player parent,  Handler handler) {
		super(x, y, id);
		this.parent = parent;
		this.handler = handler;

	}


	/**
	 * handles the movement  of the bullet and puts necessary constraints also check for collisions.
	 */
	public void tick() {
	    this.x += velX;
	    this.y += velY;

	    double distance = Math.sqrt(Math.abs(Math.abs((x - this.parent.getX())*(x - this.parent.getX())) + Math.abs((y - this.parent.getY())*(y - this.parent.getY()))));

	    if((float)distance > this.parent.getRange()) handler.removeObject(this);
	    collision();
	}
	/**
	 * handles collision of bullet with various items. reduces opposite player health based on the parent players type and its ragecount.
	 */
	private void collision() {

		for(int i =0; i<handler.mapObjects.size(); i++) {
			GameObject temp = handler.mapObjects.get(i);
			if(temp.getBounds().intersects(this.getBounds())) {
				handler.removeObject(this);
			}

		}

		for(int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if(temp.getID() == ID.Player1) {
				Player player = (Player) temp;
				if(this.getBounds().intersects(temp.getBounds())) {
					Player bulletParent = this.getParent();
					if(bulletParent.getID() != player.getID()) {


						if(bulletParent.getRageCount() > 0) {

							HUDP1.HEALTH-= (bulletParent.getDamage() + 10);
							HUDP1.greenValue -= (bulletParent.getDamage() + 10);
							bulletParent.setRageCount(bulletParent.getRageCount() - 1);
						}
						else {
							HUDP1.HEALTH-= bulletParent.getDamage();
							HUDP1.greenValue -= bulletParent.getDamage();
						}
						handler.removeObject(this);


					}
				}
			}
			else if(temp.getID() == ID.Player2) {
				Player player = (Player) temp;
				if(this.getBounds().intersects(player.getFullBounds())) {
					Player bulletParent = this.getParent();
					if(bulletParent.getID() != player.getID()) {


						if(bulletParent.getRageCount() > 0) {

							HUDP2.HEALTH-= (bulletParent.getDamage() + 10);
							HUDP2.greenValue -= (bulletParent.getDamage() + 10);
							bulletParent.setRageCount(bulletParent.getRageCount() - 1);
						}
						else {
							HUDP2.HEALTH-= bulletParent.getDamage();
							HUDP2.greenValue -= bulletParent.getDamage();
						}
						handler.removeObject(this);


					}
				}
			}


		}

	}

	/**
	 * renders the bullet of player on screen.
	 */
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, 4, 4);

	}

	/**
	 * returns the bounds of the bullet. Used for checking collisions.
	 */
	public Rectangle getBounds() {

		return new Rectangle((int)x,(int) y, 16, 16);
	}
	/**
	 * used to get the player who shot this bullet.
	 * @return object of the shooting player.
	 */
	public Player getParent() {
		return this.parent;
	}
}
