package zwars.menu;

import java.awt.*;
import java.io.*;
import zwars.Game;
import zwars.utils.Utils;
/**
  class to render the help screen
 */
public class Help implements GameStateObject {

	String[] content;//description of how to play

	public Help() {
		String path = new File("controls.txt").getAbsolutePath();
		String file= Utils.loadFileAsString(path);
		//String file= Utils.loadFileAsString("C:\\javaproject\\controls.txt");//file having description of help
		this.content =  file.split(";");
	}


	/*
	  called from the main Game class, renders the help screen and displays the controls.
	 */
	public void render(Graphics g) {
		Font f = new Font("Arial", Font.ITALIC, 20);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0 , Game.WIDTH, 100);
		g.setColor(Color.WHITE);
		g.setFont(f);
		g.drawString("HOW TO PLAY?", Game.WIDTH/2 - 120, 50);


		g.drawRect(20, 20, 100, 80);
		g.setColor(Color.red);
		g.fillRect(20, 20, 100, 80);
		g.setColor(Color.cyan);
		g.drawString("Back",30, 50);
		f= new Font(Font.SANS_SERIF,Font.BOLD,20);
		g.setFont(f);



		g.drawString(content[0], 100, 200);
		g.drawString(content[1], 100, 225);
		g.drawString(content[2], 100, 250);
		g.drawString(content[3], 100, 275);
		g.drawString(content[4], 100, 300);
		g.drawString(content[5], 100, 325);
		g.drawString(content[6], 100, 375);
		g.drawString(content[7], 100, 450);
		g.drawString(content[8], 100, 475);
		g.drawString(content[9], 100, 500);
		g.drawString(content[10], 100, 525);
		g.drawString(content[11], 100, 550);
		g.drawString(content[12], 100, 575);

	}


}
