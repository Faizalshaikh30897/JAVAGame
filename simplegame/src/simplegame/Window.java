package simplegame;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8408115443667566120L;


	public Window(int width, int height, String title, Game game) {
		JFrame jFrame = new JFrame(title);
		
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
