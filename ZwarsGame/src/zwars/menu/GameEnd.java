package zwars.menu;

import java.awt.*;
import zwars.*;
import zwars.player.*;
/**
 * class to render the end game screen
 */
public class GameEnd implements GameStateObject{
	private Game game;

	public GameEnd(Game game) {
		this.game = game;
	}


	/*
	 * called from the main Game class, renders the end game screen and displays the winner. Also gives options to restart or quit the game.
	 */
	public void render(Graphics g) {
		Font f = new Font("Arial", Font.ITALIC, 20);
		g.setColor(Color.WHITE);
		g.drawRect(Game.WIDTH/2 - 500, Game.HEIGHT/2 - 300 , 400, 100);
		g.drawRect(Game.WIDTH/2 + 100, Game.HEIGHT/2 - 300 , 400, 100);
		g.setColor(Color.red);
		g.fillRect(Game.WIDTH/2 - 500, Game.HEIGHT/2 - 300 , 400, 100);
		g.fillRect(Game.WIDTH/2 + 100, Game.HEIGHT/2 - 300 , 400, 100);
		g.setColor(Color.WHITE);
		g.setFont(f);
		g.drawString("RESTART", Game.WIDTH/2 - 480, Game.HEIGHT/2 - 250   );
		g.drawString("QUIT", Game.WIDTH/2 + 180, Game.HEIGHT/2 - 250);
		if(HUDP1.HEALTH <= 0) {
			//Font f = new Font("Arial", Font.ITALIC, 32);
			g.setColor(Color.YELLOW);
			g.setFont(f);
			g.drawString(game.p2.getPlayerName()+ " wins", Game.WIDTH/2, Game.HEIGHT/2);
			for(int i = 0; i < game.handler.object.size(); i++) {

				if(game.handler.object.get(i).getID() == ID.Player2) {
					Player p2 = (Player)game.handler.object.get(i);
					g.drawImage(p2.getImage(), Game.WIDTH/2, Game.HEIGHT/2 + 100,96,96, null );
				}
			}
		}
		else if(HUDP2.HEALTH <= 0) {
			//Font f = new Font("Arial", Font.ITALIC, 32);
			g.setColor(Color.YELLOW);
			g.setFont(f);
			g.drawString(game.p1.getPlayerName()+ " wins", Game.WIDTH/2, Game.HEIGHT/2);
			for(int i = 0; i < game.handler.object.size(); i++) {

				if(game.handler.object.get(i).getID() == ID.Player1) {
					Player p2 = (Player)game.handler.object.get(i);
					g.drawImage(p2.getImage(), Game.WIDTH/2, Game.HEIGHT/2 + 100,96,96, null );
				}
			}
		}else{
			if(HUDP1.HEALTH >HUDP2.HEALTH){
				g.setColor(Color.YELLOW);
				g.setFont(f);
				g.drawString(game.p1.getPlayerName()+ " wins", Game.WIDTH/2, Game.HEIGHT/2);
				for(int i = 0; i < game.handler.object.size(); i++) {

					if(game.handler.object.get(i).getID() == ID.Player1) {
						Player p2 = (Player)game.handler.object.get(i);
						g.drawImage(p2.getImage(), Game.WIDTH/2, Game.HEIGHT/2 + 100,96,96, null );
					}
				}
			}else if(HUDP1.HEALTH < HUDP2.HEALTH){
				g.setColor(Color.YELLOW);
				g.setFont(f);
				g.drawString(game.p2.getPlayerName()+ " wins", Game.WIDTH/2, Game.HEIGHT/2);
				for(int i = 0; i < game.handler.object.size(); i++) {

					if(game.handler.object.get(i).getID() == ID.Player2) {
						Player p2 = (Player)game.handler.object.get(i);
						g.drawImage(p2.getImage(), Game.WIDTH/2, Game.HEIGHT/2 + 100,96,96, null );
					}
				}
			}else{
				g.setColor(Color.YELLOW);
				g.setFont(f);
				g.drawString("Game Tied", Game.WIDTH/2, Game.HEIGHT/2);

			}
		}
	}

}
