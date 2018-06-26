package zwars;

import java.awt.event.*;

import java.awt.image.BufferedImage;
import java.io.*;
import zwars.menu.PlayerSelection;
import zwars.player.*;
import zwars.threads.*;
import zwars.world.*;
/**
 Class to handle mouse events for various selections during the game
 */
public class MouseInput implements MouseListener {

	private Handler handler;
	private ImageLoader loader = new ImageLoader();
	private TimeDisplay td;
	private Spawner spawner;
	private Timer timer;
	private Game game;

	public MouseInput(Game game, TimeDisplay td) {
		this.game = game;
		this.handler = game.handler;
		this.td = td;

	}

	/**
	overriding the method of listener
	*/
	public void mouseClicked(MouseEvent e) {
		float x = e.getX();
		float y = e.getY();
		if(Game.gameState == GameState.Pause) {
			if(y > Game.HEIGHT/2 - 200 && y < Game.HEIGHT/2 - 100 ) {
				if(x > Game.WIDTH/2 - 200 && x < Game.WIDTH/2 + 200) {

					Game.gameState = GameState.Game;

				}
			}
			else if(y > Game.HEIGHT/2  && y < Game.HEIGHT/2+100) {
				if(x > Game.WIDTH/2 - 200 && x < Game.WIDTH/2 + 200) {

					HUDP1.greenValue =255;
					HUDP2.greenValue =255;

					Game.gameState = GameState.Help;
					for(int i = this.handler.object.size()-1; i>=0; i--) {
						handler.object.remove(i);
					}
					for(int i = this.handler.mapObjects.size()-1; i>=0; i--) {
						handler.mapObjects.remove(i);
					}
					spawner.interrupt();
					spawner.terminate();
					timer.terminate();
					td.settime(0, 0);
				}
			}
			else if(y > Game.HEIGHT/2 + 200 && y < Game.HEIGHT/2 + 300) {
				if(x > Game.WIDTH/2 - 200 && x < Game.WIDTH/2 + 200) {

					HUDP1.greenValue =255;
					HUDP2.greenValue =255;

					Game.gameState = GameState.Menu;
					for(int i = this.handler.object.size()-1; i >=0; i--) {
							handler.object.remove(i);
					}
					for(int i = this.handler.mapObjects.size()-1; i>=0; i--) {
						handler.mapObjects.remove(i);
					}
					spawner.interrupt();
					spawner.terminate();
					timer.terminate();
					td.settime(0, 0);
				}
			}
		}
		else if(Game.gameState == GameState.Menu) {
			if(y > Game.HEIGHT/2 - 120 && y < Game.HEIGHT/2 -65 ) {
				if(x > Game.WIDTH - 300 && x < Game.WIDTH - 180) {
					game.p1=new PlayerSelection();
					Game.gameState = GameState.Player1Selection;


				}
			}else if(y > Game.HEIGHT/2 - 15 && y < Game.HEIGHT/2 + 40) {
				if(x > Game.WIDTH - 300 && x < Game.WIDTH - 180) {

					Game.gameState = GameState.Help;
				}
			}else if(y > Game.HEIGHT/2 + 85 && y < Game.HEIGHT/2 + 140) {
				if(x > Game.WIDTH - 300 && x < Game.WIDTH - 180) {

					System.exit(0);
				}
			}
		}
		else if(Game.gameState == GameState.Help) {
			if(y > 20 && y < 100 ) {
				if(x > 20 && x < 120) {

					Game.gameState = GameState.Menu;
				}
			}
		}
		else if(Game.gameState == GameState.Player1Selection) {
			if(x > Game.WIDTH/4 - 200 && x < Game.WIDTH/4) {
				if(y > Game.HEIGHT/2 - 200 && y < Game.HEIGHT/2) {
					String path = new File("blue tank.png").getAbsolutePath();
					BufferedImage image1 = loader.loadImage(path);
					//BufferedImage image1 = loader.loadImage("C:\\javaproject\\blue tank.png");
					//BufferedImage pi = loader.crop(image1, 0, 0, 32, 32);
					handler.addObject(new Player(800 ,64, 180, ID.Player1,PlayerType.Sniper, handler, image1, td));
					game.p2=new PlayerSelection();
					Game.gameState = GameState.Player2Selection;

				}
			}else if(x > Game.WIDTH/2 - 200 && x < Game.WIDTH/2) {
				if(y > Game.HEIGHT/2 - 200 && y < Game.HEIGHT/2) {
					String path = new File("red tank1.png").getAbsolutePath();
					BufferedImage image1 = loader.loadImage(path);
					//BufferedImage image1 = loader.loadImage("C:\\javaproject\\red tank1.png");
					//BufferedImage pi = loader.crop(sprite, 0, 0, 32, 32);
					handler.addObject(new Player(800 ,64, 180, ID.Player1,PlayerType.Shotgun, handler, image1, td));
					game.p2=new PlayerSelection();
					Game.gameState = GameState.Player2Selection;

				}
			}else if(x > Game.WIDTH*3/4 - 200 && x < Game.WIDTH*3/4) {
				if(y > Game.HEIGHT/2 - 200 && y < Game.HEIGHT/2) {
					//BufferedImage image1 = loader.loadImage("C:\\javaproject\\yellow tank.png");
					String path = new File("yellow tank.png").getAbsolutePath();
					BufferedImage image1 = loader.loadImage(path);
					handler.addObject(new Player(800 ,64, 180, ID.Player1,PlayerType.Rifle, handler, image1, td));
					game.p2=new PlayerSelection();
					Game.gameState = GameState.Player2Selection;

				}
			}

		}
		else if(Game.gameState == GameState.Player2Selection) {
			if(x > Game.WIDTH/4 - 200 && x < Game.WIDTH/4) {
				if(y > Game.HEIGHT/2 - 200 && y < Game.HEIGHT/2) {
					String path = new File("blue tank.png").getAbsolutePath();
					BufferedImage image1 = loader.loadImage(path);
					//BufferedImage image1 = loader.loadImage("C:\\javaproject\\blue tank.png");
					//BufferedImage pi = loader.crop(sprite, 0, 0, 32, 32);
					handler.addObject(new Player(600 ,700, 0, ID.Player2,PlayerType.Sniper, handler, image1, td));
					Game.gameState = GameState.Game;
					spawner = new Spawner(handler);
					spawner.start();
					//Timer
					timer = new Timer(td);
					timer.start();
					handler.addObject(td);

				}
			}else if(x > Game.WIDTH/2 - 200 && x < Game.WIDTH/2) {
				if(y > Game.HEIGHT/2 - 200 && y < Game.HEIGHT/2) {
					String path = new File("red tank1.png").getAbsolutePath();
					BufferedImage image1 = loader.loadImage(path);
					//BufferedImage image1 = loader.loadImage("C:\\javaproject\\red tank1.png");
					//BufferedImage pi = loader.crop(sprite, 0, 0, 32, 32);
					handler.addObject(new Player(600 ,700, 0, ID.Player2,PlayerType.Shotgun, handler, image1, td));
					Game.gameState = GameState.Game;
					spawner = new Spawner(handler);
					spawner.start();
					//Timer
					timer = new Timer(td);
					timer.start();
					handler.addObject(td);
				}
			}else if(x > Game.WIDTH*3/4 - 200 && x < Game.WIDTH*3/4) {
				if(y > Game.HEIGHT/2 - 200 && y < Game.HEIGHT/2) {
					String path = new File("yellow tank.png").getAbsolutePath();
					BufferedImage image1 = loader.loadImage(path);
					//BufferedImage image1 = loader.loadImage("C:\\javaproject\\yellow tank.png");
					//BufferedImage pi = loader.crop(sprite, 0, 0, 32, 32);
					handler.addObject(new Player(600 ,700, 0, ID.Player2,PlayerType.Rifle, handler, image1, td));
					Game.gameState = GameState.Game;
					spawner = new Spawner(handler);
					spawner.start();
					//Timer
					timer = new Timer(td);
					timer.start();
					handler.addObject(td);
				}
			}
		}
		else if(Game.gameState == GameState.End) {
			if(x > Game.WIDTH/2 - 500 && x < Game.WIDTH/2 - 100 ) {
				if(y > Game.HEIGHT/2 - 300 && y < Game.HEIGHT/2 - 200) {

					HUDP1.greenValue =255;
					HUDP2.greenValue =255;
					game.p1 = new PlayerSelection();
					Game.gameState = GameState.Player1Selection;
					spawner.interrupt();
					spawner.terminate();
					timer.terminate();
					td.settime(0, 0);
					for(int i = this.handler.object.size()-1; i>=0; i--) {
						handler.object.remove(i);
					}
					for(int i = this.handler.mapObjects.size()-1; i>=0; i--) {
						handler.mapObjects.remove(i);
					}
					int worldint = (int)((Math.random()*100)%3) + 1;
					String path = new File("world"+worldint+".txt").getAbsolutePath();
					World world=new World(path, handler);
					//World world=new World("C:\\javaproject\\world"+worldint+".txt", handler);
					game.setWorld(world);


				}
			}
			else if(x > Game.WIDTH/2 + 100 && x < Game.WIDTH/2 + 500 ) {
				if(y > Game.HEIGHT/2 - 300 && y < Game.HEIGHT/2 - 200) {

					System.exit(0);

				}
			}
		}

	}


	public void mousePressed(MouseEvent e) {


	}


	public void mouseReleased(MouseEvent e) {


	}


	public void mouseEntered(MouseEvent e) {


	}


	public void mouseExited(MouseEvent e) {


	}

}
