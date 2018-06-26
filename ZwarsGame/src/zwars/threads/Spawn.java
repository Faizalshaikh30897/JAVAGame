package zwars.threads;

import java.awt.*;
import zwars.*;
import zwars.player.*;
/**
 class to handle different type of spawns that takes place during battle.
 */
public class Spawn extends GameObject{

	private SpawnID sid;//type of the spawn healthpack or rage
	private Handler handler;


	public Spawn(float x, float y, ID id, SpawnID sid, Handler handler) {
		super(x, y, id);
		this.sid = sid;
		this.handler = handler;
	}
	/**
	  used to check collision of the spawn at every iteration.
	 */
	public void tick() {
		collision();

	}
	/**
	  handles collision of the player with the spawn and sets the necessary values of the player depending on the type of spawn.
	 */
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if(this.getBounds().intersects(temp.getBounds())) {
				if(temp.getID() == ID.Player1) {
					Player player = (Player)temp;
					if(this.sid == SpawnID.HealthPack) {

						HUDP1.HEALTH = Game.clamp(HUDP1.HEALTH + 20, HUDP1.maxHealth, 0);
						HUDP1.greenValue += 20;
					}
					else if(this.sid == SpawnID.Rage) {
						player.setRageCount(10);

					}
					handler.removeObject(this);

				}
				else if(temp.getID() == ID.Player2) {
					Player player = (Player)temp;
					if(this.sid == SpawnID.HealthPack) {
						HUDP2.HEALTH = Game.clamp(HUDP2.HEALTH + 20, HUDP2.maxHealth, 0);
						HUDP2.greenValue += 20;
					}
					else if(this.sid == SpawnID.Rage) {
						player.setRageCount(10);

					}
					handler.removeObject(this);
				}
			}
		}
	}
	/**
	  renders the spawn on screen.
	 */
	public void render(Graphics g) {
		if(this.sid == SpawnID.HealthPack) {
			g.setColor(Color.GREEN);
			g.fillRect((int)x, (int)y, 16,16);
		}
		else if(this.sid == SpawnID.Rage) {
			g.setColor(Color.RED);
			g.fillRect((int)x, (int)y, 16,16);
		}

	}
	/**
	  used to check the type of the spawn.
	  @return id of the spawn. used in collision to update the values as required.
	 */
	public SpawnID getSpawnID() {
		return this.sid;
	}
	/**
	  returns bounds of this spawn. used in collisions.
	 */
	public Rectangle getBounds() {

		return new Rectangle((int)x,(int) y, 16, 16);
	}

}
