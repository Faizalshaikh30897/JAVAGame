package simplegame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 359743678418694353L;
	public static final int WIDTH = 1920, HEIGHT = 1080;
	private boolean running = false;
	private Thread thread;
	private Handler handler;
	private HUDP1 hudp1;
	private HUDP2 hudp2;
	private BufferedImage sprite= null;
	private ImageLoader loader = new ImageLoader();
	private Menu menu;
	public static GameState gameState = GameState.Game;
	//private Random r;
	
	//constructor of Main Game class
	public Game() {
		this.requestFocus();
		handler = new Handler();
		hudp1 = new HUDP1(handler);
		hudp2 = new HUDP2(handler);
		
		menu = new Menu();
		
		
		new Window(WIDTH,HEIGHT,"SIMPLE GAME",this);
		
		this.addKeyListener(new KeyInput(handler));
		
		sprite = loader.loadImage("/sprite.png");
		BufferedImage playerImage = loader.crop(sprite,0,0,32,32);
		handler.addObject(new Player(640 ,64, 180, ID.Player1, handler, playerImage));
		handler.addObject(new Player(64 ,640, 0, ID.Player2, handler, playerImage));
			
	}
	
	
	//method to start thread
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		this.running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			this.running = false;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        @SuppressWarnings("unused")
		int frames = 0;
        while(running)
        {
        	long now = System.nanoTime();
        	delta += (now - lastTime) / ns;
        	lastTime = now;
        	while(delta >=1)
        	{
        		tick();
                delta--;
           }
           if(running)
        	   render();
           frames++;
                            
          /*if(System.currentTimeMillis() - timer > 1000)
          {
        	  timer += 1000;
        	  System.out.println("FPS: "+ frames);
        	  frames = 0;
          }*/
        }
        stop();
	}
	
	private void tick() {
		if(Game.gameState == GameState.Menu) {
			this.menu.tick();
		}
		else if(Game.gameState == GameState.Player1Selection) {
			
			
		}
		else if(Game.gameState == GameState.Player2Selection) {
			
		}else if(Game.gameState == GameState.Game) {
			
			handler.tick();
			hudp1.tick();
			hudp2.tick();
		}
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
	    Graphics g= bs.getDrawGraphics();
		//g.setColor(new Color(105,36,67));
	    g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,HEIGHT);

		if(Game.gameState == GameState.Menu) {
			this.menu.render(g);
		}
		else if(Game.gameState == GameState.Player1Selection) {
			
			BufferedImage playerImage = loader.crop(sprite,0,0,32,32);
			handler.addObject(new Player(640 ,64, 180, ID.Player1, handler, playerImage));
			
		}
		else if(Game.gameState == GameState.Player2Selection) {
			
			BufferedImage playerImage = loader.crop(sprite,0,0,32,32);
			handler.addObject(new Player(64 ,640, 0, ID.Player2, handler, playerImage));
			
		}else if(Game.gameState == GameState.Game) {
			
			
			
			
			handler.render(g);
			hudp1.render(g);
			hudp2.render(g);
		}
		
		
		g.dispose();
		bs.show();
		
	}
	
	public static float clamp(float var, float max, float min) {
		if(var > max) {
			return max;
		}
		else if(var < min) {
			return var = min;
		}
		else
			return var;
		
	}
	
	public static void main(String[] args) {
		new Game();
		
	}
}
