package zwars;

import java.awt.*;

import javax.swing.JFrame;
/**
  main container class for the game
 */
public class Window extends Canvas {


	JFrame  jFrame;

	public Window(int width, int height, String title, Game game) {
		jFrame = new JFrame(title);

		jFrame.setPreferredSize(new Dimension(width,height));
		jFrame.setMaximumSize(new Dimension(width, height));
		jFrame.setMinimumSize(new Dimension(width, height));

		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		jFrame.add(game);

		jFrame.setVisible(true);
		this.requestFocus();

		game.start();
	}

}
