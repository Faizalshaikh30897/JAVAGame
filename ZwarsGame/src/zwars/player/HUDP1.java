package zwars.player;

import java.awt.Graphics;

import zwars.*;
/**
  handles the health bar of player1
 */
public class HUDP1 {

	private Handler handler;
	public static float HEALTH;// health of player 1
	public static float greenValue = 200;// used to change colour of health bar
	public static float maxHealth;//maximum health of player 1 depends on his type

	public HUDP1(Handler handler) {
		this.handler = handler;
	}
	/**
	updates the values of required attributes for health of player 1
	*/
	public void tick() {
		//HEALTH = Game.clamp(HEALTH, 0 ,100);
		greenValue = Game.clamp(greenValue, 255,0);
		//System.out.println("greenvalue" + greenValue);
		if(HEALTH <= 0) {
			Game.gameState = GameState.End;
		}
	}


}
