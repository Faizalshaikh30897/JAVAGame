package zwars.menu;


import java.awt.*;
import zwars.*;
import java.awt.image.*;
import java.io.*;

import zwars.Game;
/**
  class to render the main menu of the game. first screen to be rendered when the game starts.
 */
public class Menu implements GameStateObject {
	BufferedImage image;//background image
	ImageLoader loader = new ImageLoader();
	public Menu() {
		String path = new File("BACKF.jpg").getAbsolutePath();
		System.out.println(path);
		image = loader.loadImage(path);
	}
	/*
	   renders the main menu screen. Gives options to start or quit the game. Also has option to go to the help screen.
	 */
	public void render(Graphics g) {
		g.drawImage(image,0,0,Game.WIDTH,Game.HEIGHT,null);
		
		/*g.drawRect(Game.WIDTH - 300, Game.HEIGHT/2 - 120 , 120, 55);
		g.drawRect(Game.WIDTH - 300, Game.HEIGHT/2 - 15 , 120, 55);
		g.drawRect(Game.WIDTH - 300, Game.HEIGHT/2 + 85 , 120, 55);*/




	}


}
