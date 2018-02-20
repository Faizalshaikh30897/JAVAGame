package simplegame;

import java.awt.Color;
import java.awt.Graphics;

public class HUDP2 {

	private Handler handler;
	public static float HEALTH = 100;
	public static float greenValue = 255;
	
	public HUDP2(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {
		//HEALTH = Game.clamp(HEALTH, 0 ,100);
		greenValue = Game.clamp(greenValue, 255,0);
		//System.out.println("greenvalue" + greenValue);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(500, 10, 200, 32);
		
		g.setColor(new Color(120, (int)greenValue, 0));
		g.fillRect(500, 10, (int)HEALTH*2, 32);
	}
	
}
