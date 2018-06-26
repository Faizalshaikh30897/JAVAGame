package zwars.threads;

import java.awt.*;
import zwars.*;
/**
  class to display the time on screen.
 */
public class TimeDisplay extends GameObject {

	private int mm = 0, ss =0;//minutes and seconds of time display

	public TimeDisplay(float x, float y, ID id) {
		super(x, y, id);
	}
	/**
	  updates the time variables and checks necessary constraints.
	 */
	public void tick() {

		if(this.ss >=60){
			this.ss -= 60;
			this.mm ++;
			
		}
		if(this.mm == 5){
			Game.gameState = GameState.End;
		}

	}

	/**
	 * renders the timer on screen.
	 */
	public void render(Graphics g) {
		if(Game.gameState == GameState.Game) {
			Font font = new Font("Arial", Font.BOLD, 24);
			g.setFont(font);
			//int stringWidth = (g.getFontMetrics().stringWidth(mm + " : " + ss));
			//System.out.println(mm + " : " + ss);
			g.setColor(Color.WHITE);
			g.drawString(mm + " : " + ss, (int)x,(int) y);
		}

	}
	/**
	setter method for minutes
	*/
	public void setMinutes(int m) {
		this.mm = m;
	}
	/**
	setter method for both minutes and seconds
	*/
	public void settime(int m,int s) {
		this.mm = m;
		this.ss = s;
	}
	/**
	setter method for seconds
	*/
	public void setSeconds(int s) {
		this.ss = s;
	}
	/**
	getter method for minutes
	*/
	public int getMinutes() {
		return this.mm;
	}
	/**
	getter method for seconds
	*/
	public int getSeconds() {
		return this.ss;
	}
	/**
	getter method for bounds of the displayed timer
	*/
	public Rectangle getBounds() {

		return new Rectangle((int)x,(int)y, 40,24);
	}

}
