package zwars.menu;

import java.awt.*;
import zwars.Game;
/**
 * class to render the pause game screen
 */
public class Pause implements GameStateObject{
	public Pause() {

	}

	/**
	 *  renders the pause game screen. Has option to either quit or resume the game. Also has option to go to controls screen which will restart the game.
	 */
	public void render(Graphics g) {
		Font f = new Font("Arial", Font.ITALIC, 20);
		g.setColor(Color.WHITE);
		g.setFont(f);
		g.drawString("PAUSE", Game.WIDTH/2 - 180, Game.HEIGHT/2 - 250   );
		g.drawString("RESUME", Game.WIDTH/2 - 180, Game.HEIGHT/2 - 150   );
		g.drawString("HOW TO PLAY?", Game.WIDTH/2 - 180, Game.HEIGHT/2 + 50);
		g.drawString("Menu", Game.WIDTH/2 - 180, Game.HEIGHT/2 + 250);
		g.drawRect(Game.WIDTH/2 - 200, Game.HEIGHT/2 - 200 , 400, 100);
		g.drawRect(Game.WIDTH/2 - 200, Game.HEIGHT/2 - 00 , 400, 100);
		g.drawRect(Game.WIDTH/2 - 200, Game.HEIGHT/2 + 200 , 400, 100);
	}

}
