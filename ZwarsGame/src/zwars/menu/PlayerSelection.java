package zwars.menu;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.JOptionPane;

import zwars.Game;
import zwars.ImageLoader;
/**
  class to render the player selection of both players on screen
 */
public class PlayerSelection implements GameStateObject{

	private String playerName; // name of the player as he inputs
	private BufferedImage sniper;// image for sniper type
	private BufferedImage shotgun;//image for shotgun type
	private BufferedImage rifle;// image for rifle type
	private ImageLoader loader = new ImageLoader();
	private BufferedImage background;//background image
	private BufferedImage logo;

	public PlayerSelection() {
		this.playerName = JOptionPane.showInputDialog("enter name of  player");
		String path = new File("blue tank.png").getAbsolutePath();
		this.sniper = loader.loadImage(path);
		path = new File("red tank1.png").getAbsolutePath();
		this.shotgun = loader.loadImage(path);
		path = new File("yellow tank.png").getAbsolutePath();
		this.rifle = loader.loadImage(path);
		path = new File("startImage.jpg").getAbsolutePath();
		this.background = loader.loadImage(path);
		path = new File("logo.jpg").getAbsolutePath();
		this.logo = loader.loadImage(path);
		/*this.sniper = loader.loadImage("C:\\javaproject\\blue tank.png");
		this.shotgun = loader.loadImage("C:\\javaproject\\red tank1.png");
		this.rifle = loader.loadImage("C:\\javaproject\\yellow tank.png");
		this.background = loader.loadImage("C:\\javaproject\\startImage.jpg");
		this.logo = loader.loadImage("C:\\javaproject\\logo.jpg");*/
	}

	/**
	  renders the player selection options and the name of the player.
	 */
	public void render(Graphics g) {
		g.drawImage(logo,400,0,null);
		g.drawImage(background,0,Game.HEIGHT/2 +100,1400,350,null);
		Font f = new Font("Arial", Font.ITALIC, 20);
		g.setColor(Color.WHITE);
		g.setFont(f);
		g.drawString(playerName, 300,  200);
		g.drawRect(Game.WIDTH/4 - 200, Game.HEIGHT/2 - 200 , 200, 200);
		g.drawRect(Game.WIDTH/2 - 200, Game.HEIGHT/2 - 200 , 200, 200);
		g.drawRect(Game.WIDTH*3/4 - 200, Game.HEIGHT/2 - 200 , 200, 200);

		//BufferedImage sniper = loader.crop(sprite, 0, 0, 32,32);
		g.drawString("Sniper", Game.WIDTH/4 - 190,  Game.HEIGHT/2 + 50);
		g.drawImage(sniper, Game.WIDTH/4 - 128,Game.HEIGHT/2 - 128, null);


		//BufferedImage shotgun = loader.crop(sprite, 0, 0, 32,32);
		g.drawString("shotgun", Game.WIDTH/2 - 190,  Game.HEIGHT/2 + 50);
		g.drawImage(shotgun, Game.WIDTH/2 - 128,Game.HEIGHT/2 - 128, null);


		//BufferedImage rifle = loader.crop(sprite, 0, 0, 32,32);
		g.drawString("rifle", Game.WIDTH*3/4 - 190,  Game.HEIGHT/2 + 50);
		g.drawImage(rifle, Game.WIDTH*3/4 - 128,Game.HEIGHT/2 - 128, null);

	}
	/**
	 * gives the name of the player
	 * @return name of the player
	 */
	public String getPlayerName () {
		return this.playerName;
	}

}
