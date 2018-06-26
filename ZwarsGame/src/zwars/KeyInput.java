package zwars;

import java.awt.event.*;


import zwars.player.Player;
import zwars.threads.TimeDisplay;

/**
 * Class to handle key events for the movement and shooting of both players
 */
public class KeyInput extends KeyAdapter{

	private Handler handler;
	private boolean p1shoot = false;
	private boolean p2shoot = false;


	public KeyInput(Handler handler) {

		this.handler = handler;
		//System.out.println("Constructor of KeyInput");
	}

	/**
	overriding the method of listener
	*/
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		//System.out.println(key);
		for(int i = 0; i< this.handler.object.size(); i++) {
			GameObject temp = this.handler.object.get(i);

			if(temp.getID() == ID.Player1) {
				Player player = (Player)temp;
				if(key == KeyEvent.VK_W){

					player.setMovement(true, true);

				}
				if(key == KeyEvent.VK_A){
					player.setAngularVel(-2);

				}
				if(key == KeyEvent.VK_S){

					player.setMovement(true, false);
					//temp.setVelY(5);
				}
				if(key == KeyEvent.VK_D) {
					player.setAngularVel(2);
					//temp.setVelX(5);
				}
			//	if(key == KeyEvent.VK_V) player.setAngularVel(2);
			//	if(key == KeyEvent.VK_B) player.setAngularVel(-2);
				if(key == KeyEvent.VK_SPACE) {
						if(!(p1shoot)) {
							p1shoot= true;
							player.shoot();

						}
				}
			}

			if(temp.getID() == ID.Player2) {
				Player player = (Player)temp;
				if(key == KeyEvent.VK_UP) {
					player.setMovement(true, true);
				}
				if(key == KeyEvent.VK_LEFT) player.setAngularVel(-2);
				if(key == KeyEvent.VK_DOWN) {
					player.setMovement(true, false);
				}
				if(key == KeyEvent.VK_RIGHT) player.setAngularVel(2);
				if(key == KeyEvent.VK_NUMPAD0) {
						if(!(p2shoot)) {
						p2shoot=true;
						player.shoot();

					}
				}
			}

		}


		if(key == KeyEvent.VK_ESCAPE && Game.gameState == GameState.Game) {
			Game.gameState = GameState.Pause;
			}

	}
	/**
	overriding the method of listener
	*/
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i< this.handler.object.size(); i++) {
			GameObject temp = this.handler.object.get(i);

			if(temp.getID() == ID.Player1) {
				Player player = (Player)temp;
				if(key == KeyEvent.VK_W){
					player.setMovement(false,false);


				}
				if(key == KeyEvent.VK_A){
					//player.setVelX(0);
					player.setAngularVel(0);
				}
				if(key == KeyEvent.VK_S){
					player.setMovement(false,false);

				}
				if(key == KeyEvent.VK_D) {
					player.setAngularVel(0);
					//temp.setVelX(0);
				}
				if(key == KeyEvent.VK_V) player.setAngularVel(0);
				if(key == KeyEvent.VK_B) player.setAngularVel(0);
				if(key == KeyEvent.VK_SPACE) p1shoot = false;
			}

			if(temp.getID() == ID.Player2) {
				Player player = (Player)temp;
				if(key == KeyEvent.VK_UP){
					player.setMovement(false, false);
				}
				if(key == KeyEvent.VK_LEFT){
					//player.setVelX(0);
					player.setAngularVel(0);
				}
				if(key == KeyEvent.VK_DOWN){
					player.setMovement(false, false);
				}
				if(key == KeyEvent.VK_RIGHT) {
					player.setAngularVel(0);
					//temp.setVelX(0);
				}
				if(key == KeyEvent.VK_NUMPAD1) player.setAngularVel(0);
				if(key == KeyEvent.VK_NUMPAD2) player.setAngularVel(0);
				if(key == KeyEvent.VK_NUMPAD0) p2shoot = false;
			}
		}
	}

}
