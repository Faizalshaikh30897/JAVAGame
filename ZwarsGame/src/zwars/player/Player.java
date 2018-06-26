package zwars.player;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import zwars.*;
import zwars.TimerListener;
import zwars.threads.TimeDisplay;
import zwars.tile.Tile;

/**
 * class to handle all activities of both the players.
 */
public class Player extends GameObject{

	private Handler handler;
	//protected float gunx, guny;
	protected float angle, angularVel; // used to rotate the player
	private BufferedImage image;// image of the player depending on his type
	private int RageCount = 0;// used when player picks up rage spawn
	private PlayerType type;//type of player
	private float range, damage, health;// range,damage and health of the player depending on his type
	private boolean move = false, dir = false;//used to move the player in direction he is facing
	private boolean shooting = false;//used the introduce pauses between player bullets
	private int delay ;//amount of delay between player bullets depending on his type
	private TimeDisplay td;
	TimerListener listener = new TimerListener(this);
	Timer timer;

	public Player(float x, float y, float angle, ID player, PlayerType type, Handler handler, BufferedImage image, TimeDisplay td) {
		super(x, y, player);
		this.image = image;
		this.handler = handler;
		this.angle = angle;
		this.type = type;
		this.td = td;
		if(this.type == PlayerType.Rifle) {
			this.range = 500;
			this.damage = 5;
			this.health = 100;
			this.delay = 0;
			if(player == ID.Player1) {
				HUDP1.HEALTH = this.health;
				HUDP1.maxHealth = this.health;
			}
			else if(player == ID.Player2) {
				HUDP2.HEALTH = this.health;
				HUDP2.maxHealth = this.health;
			}
		}else if(this.type == PlayerType.Sniper) {
			this.range = 1500;
			this.damage = 15;
			this.health = 80;
			this.delay = 800;
			if(player == ID.Player1) {
				HUDP1.HEALTH = this.health;
				HUDP1.maxHealth = this.health;
			}
			else if(player == ID.Player2) {
				HUDP2.HEALTH = this.health;
				HUDP2.maxHealth = this.health;
			}
		}else if(this.type == PlayerType.Shotgun) {
			this.range = 300;
			this.damage = 8;
			this.delay = 500;
			this.health = 130;
			if(player == ID.Player1) {
				HUDP1.HEALTH = this.health;
				HUDP1.maxHealth = this.health;
			}
			else if(player == ID.Player2) {
				HUDP2.HEALTH = this.health;
				HUDP2.maxHealth = this.health;
			}
		}
		timer = new Timer(delay, listener);

	}
	/**
	 * handles the movement and rotation of the player and puts necessary constraints also check for collisions and delays of shooting.
	 */
	public void tick() {
	    //System.out.println("tick" + id);
		this.x += velX;
		this.y += velY;
		this.angle += angularVel;
		move();


		x = Game.clamp(x, Game.WIDTH -116 , 66);
		y = Game.clamp(y,Game.HEIGHT -116, 66);

		if(angle < 0) angle = 360 + angle;
		if(angle > 360) angle = angle-360;
		if(shooting) {

			timer.setRepeats(false);
			timer.start();
		}
		/*if(( td.getSeconds() - shootseconds) > this.delay ) {
			shooting = false;
		}*/
		collision();
	}
	/**
	 * decides the direction of the movement of the player at every tick and sets the movement if player is moving.
	 */
	private void move(){
		if(this.move){
			if(this.dir){
				this.setVelX((float)(5*Math.cos(Math.toRadians(this.getAngle()))));
				this.setVelY((float)(5*Math.sin(Math.toRadians(this.getAngle()))));
			}
			else{
				this.setVelX((float)(-5*Math.cos(Math.toRadians(this.getAngle()))));
				this.setVelY((float)(-5*Math.sin(Math.toRadians(this.getAngle()))));
			}
		}
		else{
			this.setVelY(0);
			this.setVelX(0);
		}
	}
	/**
	 * used to set the movement of the player and its direction.
	  @param m
	  @param d
	 */
	public void setMovement(boolean m, boolean d){
		this.move = m;
		this.dir = d;
	}

	/**
	 * used to check collison of the current player with other mapobjects and bullets and take necessary actions.
	 */

	private void collision() {

		for(int i =0; i<handler.mapObjects.size(); i++) {
			GameObject temp = handler.mapObjects.get(i);
			if(temp.getID() == ID.Tile) {
				Tile tile = (Tile)temp;

				if(tile.getBounds().intersects(this.getBounds())) {
					this.setVelY(Game.clamp(this.getVelY(), 100, 0));
				}
				if(tile.getBounds().intersects(this.getBoundsBottom())){
					this.setVelY(Game.clamp(this.getVelY(), 0, -100));
				}
				if(tile.getBounds().intersects(this.getBoundsLeft())){
					this.setVelX(Game.clamp(this.getVelX(), 100, 0));
				}
				if(tile.getBounds().intersects(this.getBoundsRight())){
					this.setVelX(Game.clamp(this.getVelX(), 0, -100));
				}
			}

		}
		for(int i =0; i<handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if(temp.getID() == ID.Player1 || temp.getID() == ID.Player2) {
				if(temp.getID() != this.getID()) {
					Player p = (Player)temp;
					if(this.getFullBounds().intersects(p.getFullBounds())) {
						if(dir){
							if(this.angle>=0 && this.angle <90) {
								if(p.angle>=0 && p.angle <90){
									if(this.getX() > p.getX()){
										this.setVelX(Game.clamp(this.getVelX(), 100, 0));
										this.setVelY(Game.clamp(this.getVelY(), 100, 0));
									}else{
										this.setVelX(Game.clamp(this.getVelX(), 0, -100));
										this.setVelY(Game.clamp(this.getVelY(), 0, -100));
									}
								}else{
									this.setVelX(Game.clamp(this.getVelX(), 0, -100));
									this.setVelY(Game.clamp(this.getVelY(), 0, -100));
								}

							}else if(this.angle >=90 && this.angle<180) {
								if(p.angle>=90 && p.angle <180){
									if(this.getX() < p.getX()){
										this.setVelX(Game.clamp(this.getVelX(), 100, 0));
										this.setVelY(Game.clamp(this.getVelY(), 100, 0));
									}else{
										this.setVelY(Game.clamp(this.getVelY(), 0, -100));
										this.setVelX(Game.clamp(this.getVelX(), 100, 0));
									}
								}else{
									this.setVelY(Game.clamp(this.getVelY(), 0, -100));
									this.setVelX(Game.clamp(this.getVelX(), 100, 0));
								}

							}else if(this.angle >=180 && this.angle<270) {
								if(p.angle>=180 && p.angle <270){
									if(this.getX() < p.getX()){
										this.setVelX(Game.clamp(this.getVelX(), 0, -100));
										this.setVelY(Game.clamp(this.getVelY(), 0, -100));
									}else{
										this.setVelX(Game.clamp(this.getVelX(), 100, 0));
										this.setVelY(Game.clamp(this.getVelY(), 100, 0));
									}
								}else{
									this.setVelX(Game.clamp(this.getVelX(), 100, 0));
									this.setVelY(Game.clamp(this.getVelY(), 100, 0));
								}

							}else if(this.angle >=270 && this.angle<360){
								if(p.angle>=270 && p.angle <360){
									if(this.getX() > p.getX()){
										this.setVelX(Game.clamp(this.getVelX(), 100, 0));
										this.setVelY(Game.clamp(this.getVelY(), 0, -100));
									}else{
										this.setVelX(Game.clamp(this.getVelX(), 0, -100));
										this.setVelY(Game.clamp(this.getVelY(), 100, 0));
									}
								}else{
									this.setVelX(Game.clamp(this.getVelX(), 0, -100));
									this.setVelY(Game.clamp(this.getVelY(), 100, 0));
								}
								this.setVelX(Game.clamp(this.getVelX(), 0, -100));
								this.setVelY(Game.clamp(this.getVelY(), 100, 0));
							}
						}


					}//if 3

				}//if 2
			}// if 1

		}//for loop end
		/*for(int i =0; i<handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if(temp.getID() == ID.Player1 || temp.getID() == ID.Player2) {
				if(temp.getID() != this.getID()) {
					Player p = (Player)temp;
					if(this.getFullBounds().intersects(p.getFullBounds())) {
						HUDP1.HEALTH -=10;
						HUDP2.HEALTH -=10;

					}//if 3

				}//if 2
			}// if 1

		}//for loop end*/

	}//method end

	/**
	getter method for angle
	*/
	public float getAngle() {
		return this.angle;
	}
	/**
	getter method for angular velocity
	*/
	public float getAngularVel() {
		return this.angularVel;
	}
	/**
	setter method for angle
	*/
	public void setAngle(float a) {
		this.angle = a;
	}
	/**
	setter method for angular velocity
	*/
	public void setAngularVel(float v) {
		this.angularVel = v;
	}
	/**
	getter method for range
	*/
	public float getRange() {
		return this.range;
	}
	/**
	getter method for damage
	*/
	public float getDamage() {
		return this.damage;
	}
	/**
	getter method for health
	*/
	public float getHealth() {
		return this.health;
	}
	/**
	setter method for range
	*/
	public void setRange(float r) {
		this.range = r;
	}
	/**
	setter method for damage
	*/
	public void setDamage(float d) {
		this.damage = d;
	}
	/**
	setter method for health
	*/
	public void setHealth(float h) {
		this.health = h;
	}
	/**
	getter method for rage count
	*/
	public int getRageCount() {
		return this.RageCount;
	}
	/**
	setter method for rage count
	*/
	public void setRageCount(int r) {
		this.RageCount = r;
	}
	/**
	getter method for image of player
	*/
	public BufferedImage getImage() {
		return this.image;
	}
	/**
	getter method for player type
	*/
	public PlayerType getPlayerType() {
		return this.type;
	}
	/**
	 * method to render the player and its health on screen and also rotate the player if angle has changed.
	 */
	public void render(Graphics g) {
		//g.drawImage(image,(int) x,(int) y, null);

		Graphics2D gg = (Graphics2D)g.create();

		gg.rotate(Math.toRadians(angle),(int)x+24,(int)y+24);

		gg.drawImage(image, (int)x, (int)y,48,48, null);
	    gg.dispose();

		//g.drawImage(image, (int)x, (int)y, null);
	    if(this.id == ID.Player1) {
			g.setColor(new Color(170, (int)HUDP1.greenValue, 0));
			g.fillRect((int)x-10, (int)y-50, (int)HUDP1.HEALTH, 5);
		}else {
			g.setColor(new Color(170, (int)HUDP2.greenValue, 0));
			g.fillRect((int)x-10, (int)y-50, (int)HUDP2.HEALTH, 5);
		}
	    float xAim = (float)(100*Math.cos(Math.toRadians(angle)));
	    float yAim = (float)(100*Math.sin(Math.toRadians(angle)));
	    g.setColor(Color.RED);
	    g.fillRect((int)(this.x + xAim + 24), (int)(this.y + (int)yAim + 24), 8, 8);
	   // g.drawRect(this.getBoundsRight().x, this.getBoundsRight().y, this.getBoundsRight().width, this.getBoundsRight().height);
	   // g.drawRect(this.getBounds().x, this.getBounds().y, this.getBounds().width, this.getBounds().height);
	   // g.drawRect(this.getBoundsLeft().x, this.getBoundsLeft().y, this.getBoundsLeft().width, this.getBoundsLeft().height);
	   // g.drawRect(this.getBoundsBottom().x, this.getBoundsBottom().y, this.getBoundsBottom().width, this.getBoundsBottom().height);
	}
	/**
	 * method to set the shooting state of the player used to implement delay.
	 * @param s
	 */
	public void setShooting(boolean s) {
		this.shooting = s;
	}
	/**
	getter method for knowing if player is shooting
	*/
	public boolean isShooting() {
		return this.shooting;
	}
	/**
	getter method for delay
	*/
	public int getDelay() {
		return this.delay;
	}
	/**
	 * method to shoot bullets from the player depending on the player type.
	 */
	public void shoot() {

		float x = this.getX() + 24;// + (float)(10*Math.cos(Math.toRadians(player.getAngle())));
		float y = this.getY() + 24;// + (float)(10*Math.sin(Math.toRadians(player.getAngle())));
		if(this.type == PlayerType.Rifle) {
			PlayerBullet bullet = new PlayerBullet(x , y,ID.PlayerBullet, this,handler);
			handler.addObject(bullet);
			bullet.setVelX((float)(10*Math.cos(Math.toRadians(this.angle))));
			bullet.setVelY((float)(10*Math.sin(Math.toRadians(this.angle))));
		}
		else if(!shooting) {
			this.shooting = true;

			if(this.type == PlayerType.Shotgun) {
				PlayerBullet bullet = new PlayerBullet(x , y,ID.PlayerBullet, this,handler);
				handler.addObject(bullet);
				bullet.setVelX((float)(10*Math.cos(Math.toRadians(this.angle + 15))));
				bullet.setVelY((float)(10*Math.sin(Math.toRadians(this.angle + 15))));

				bullet = new PlayerBullet(x , y,ID.PlayerBullet, this,handler);
				handler.addObject(bullet);
				bullet.setVelX((float)(10*Math.cos(Math.toRadians(this.angle))));
				bullet.setVelY((float)(10*Math.sin(Math.toRadians(this.angle))));

				bullet = new PlayerBullet(x , y,ID.PlayerBullet, this,handler);
				handler.addObject(bullet);
				bullet.setVelX((float)(10*Math.cos(Math.toRadians(this.angle - 15))));
				bullet.setVelY((float)(10*Math.sin(Math.toRadians(this.angle - 15))));
			}else if(this.type == PlayerType.Sniper){
				PlayerBullet bullet = new PlayerBullet(x , y,ID.PlayerBullet, this,handler);
				handler.addObject(bullet);
				bullet.setVelX((float)(20*Math.cos(Math.toRadians(this.angle))));
				bullet.setVelY((float)(20*Math.sin(Math.toRadians(this.angle))));

			}
		}
	}
	/**
	 * method to get the top bounds of the current player used during collision from top.
	 */
	public Rectangle getBounds() {

		return new Rectangle((int)x + 5, (int)y, (int)48 - 10 , (int)48/2);
	}
	/**
	 * method to get the bottom bounds of the current player used during collision from bottom.
	 */
	public Rectangle getBoundsBottom() {

		return new Rectangle((int)x + 5, (int)(y + 48/2), (int)48 -10 , (int)48/2 -1);
	}
	/**
	 * method to get the left bounds of the current player used during collision from left.
	 */
	public Rectangle getBoundsLeft() {

		return new Rectangle((int)x , (int)y, (int)5 , (int)48);
	}
	/**
	 * method to get the right bounds of the current player used during collision from right.
	 */
	public Rectangle getBoundsRight() {

		return new Rectangle((int)(x+48-5), (int)y, (int)5 , (int)48);
	}
	/**
	 * method to get the full bounds of the current player used during collision from any direction.
	 */
	public Rectangle getFullBounds() {
		return new Rectangle((int)x +2, (int)y+2, (int)48 +2 , (int)48+2);
	}
}
