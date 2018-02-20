package simplegame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean p1shoot = false;
	private boolean p2shoot = false;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		//System.out.println("Constructor of KeyInput");
	}
	

	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		//System.out.println(key);
		for(int i = 0; i< this.handler.object.size(); i++) {
			GameObject temp = this.handler.object.get(i);
			
			if(temp.getID() == ID.Player1) {
				Player player = (Player)temp;
				if(key == KeyEvent.VK_W) temp.setVelY(-5);
				if(key == KeyEvent.VK_A) temp.setVelX(-5);
				if(key == KeyEvent.VK_S) temp.setVelY(5);
				if(key == KeyEvent.VK_D) temp.setVelX(5);
				if(key == KeyEvent.VK_V) player.setAngularVel(2);
				if(key == KeyEvent.VK_B) player.setAngularVel(-2);
				if(key == KeyEvent.VK_SPACE) {
						if(!(p1shoot)) {
						p1shoot= true;	
						float x = player.getX() + 16;// + (float)(10*Math.cos(Math.toRadians(player.getAngle())));
						float y = player.getY() + 16;// + (float)(10*Math.sin(Math.toRadians(player.getAngle())));
						PlayerBullet bullet = new PlayerBullet(x , y,ID.PlayerBullet, player.getID(),handler);
						handler.addObject(bullet);
						bullet.setVelX((float)(10*Math.cos(Math.toRadians(player.getAngle()))));
						bullet.setVelY((float)(10*Math.sin(Math.toRadians(player.getAngle()))));

						}
				}		
			}
			
			if(temp.getID() == ID.Player2) {
				Player player = (Player)temp;
				if(key == KeyEvent.VK_UP) temp.setVelY(-5);
				if(key == KeyEvent.VK_LEFT) temp.setVelX(-5);
				if(key == KeyEvent.VK_DOWN) temp.setVelY(5);
				if(key == KeyEvent.VK_RIGHT) temp.setVelX(5);
				if(key == KeyEvent.VK_NUMPAD1) player.setAngularVel(2);
				if(key == KeyEvent.VK_NUMPAD2) player.setAngularVel(-2);
				if(key == KeyEvent.VK_NUMPAD0) {
						if(!(p2shoot)) {
						p2shoot=true;
						float x = player.getX() + (float)(10*Math.cos(Math.toRadians(player.getAngle())));
						float y = player.getY() + (float)(10*Math.sin(Math.toRadians(player.getAngle())));
						PlayerBullet bullet = new PlayerBullet(x , y,ID.PlayerBullet, player.getID(), handler);
						handler.addObject(bullet);
						bullet.setVelX((float)(10*Math.cos(Math.toRadians(player.getAngle()))));
						bullet.setVelY((float)(10*Math.sin(Math.toRadians(player.getAngle()))));
					}
				}		
			}
			
		}
		    

		if(key == KeyEvent.VK_ESCAPE) System.exit(0);

	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i< this.handler.object.size(); i++) {
			GameObject temp = this.handler.object.get(i);
			
			if(temp.getID() == ID.Player1) {
				Player player = (Player)temp;
				if(key == KeyEvent.VK_W) temp.setVelY(0);
				if(key == KeyEvent.VK_A) temp.setVelX(0);
				if(key == KeyEvent.VK_S) temp.setVelY(0);
				if(key == KeyEvent.VK_D) temp.setVelX(0);
				if(key == KeyEvent.VK_V) player.setAngularVel(0);
				if(key == KeyEvent.VK_B) player.setAngularVel(0);
				if(key == KeyEvent.VK_SPACE) p1shoot = false;
			}
			
			if(temp.getID() == ID.Player2) {
				Player player = (Player)temp;
				if(key == KeyEvent.VK_UP) temp.setVelY(0);
				if(key == KeyEvent.VK_LEFT) temp.setVelX(0);
				if(key == KeyEvent.VK_DOWN) temp.setVelY(0);
				if(key == KeyEvent.VK_RIGHT) temp.setVelX(0);
				if(key == KeyEvent.VK_NUMPAD1) player.setAngularVel(0);
				if(key == KeyEvent.VK_NUMPAD2) player.setAngularVel(0);
				if(key == KeyEvent.VK_NUMPAD0) p2shoot = false;
			}
		}
	}

}
