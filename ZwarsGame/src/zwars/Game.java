package zwars;

import java.awt.*;
import java.awt.image.*;
import java.util.Random;
import java.io.*;
import zwars.menu.*;

import zwars.player.HUDP1;
import zwars.player.HUDP2;
import zwars.threads.*;
import zwars.menu.Menu;
import zwars.world.Assets;
import zwars.world.World;

/**
 Main Game class: Initializes all GUI and backend components and creates necessary threads for the game to run
 */

public class Game extends Canvas implements Runnable{


	public static final int WIDTH = 1600, HEIGHT = 960;//width and height of the game screen
	private boolean running = false;//used to run the game class on thread
	private Thread thread;
	public Handler handler;
	private HUDP1 hudp1;//health bar of player 1
	private HUDP2 hudp2;//health bar of player 2
	private World world;

	private ImageLoader loader = new ImageLoader();
	//private BufferedImage sprite = loader.loadImage("/sprite.png");
	private Menu menu;
	private Help help;
	private GameEnd gameend;
	private Pause pause;
	private Spawner spawner;
	private Timer timer;
	private TimeDisplay td = new TimeDisplay(800,50,ID.TimeDisplay);//display to show time at top
	public static GameState gameState = GameState.Menu;
	public PlayerSelection p1 ;
	public PlayerSelection p2 ;
	Random r = new Random();


	//private Random r;


	public Game() {

		handler = new Handler();
		hudp1 = new HUDP1(handler);
		hudp2 = new HUDP2(handler);


		menu = new Menu();
		help = new Help();
		pause = new Pause();
		gameend= new GameEnd(this);
		int worldint = (int)((Math.random()*100)%3) + 1;
		String path = new File("world"+worldint+".txt").getAbsolutePath();
		//world=new World("C:\\javaproject\\world"+worldint+".txt", handler);
		world=new World(path, handler);

		new Window(WIDTH,HEIGHT,"SIMPLE GAME",this);

		//p1=new PlayerSelection(JOptionPane.showInputDialog("enter name of first player"));
		//p2=new PlayerSelection(JOptionPane.showInputDialog("enter name of second player"));

		Assets.init();
		if(Game.gameState == GameState.Game){
			spawner = new Spawner(handler);
			spawner.start();
			timer = new Timer(td);
			timer.start();
			handler.addObject(td);
		}

		this.addMouseListener(new MouseInput(this,td));
		this.addKeyListener(new KeyInput(handler));



	}
	/**
	setter method for world
	*/
	public void setWorld(World world){
		this.world = world;
	}
	/**
	 Creates the thread for game and starts it.
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		this.running = true;
	}
	/**
	  Stops the game thread called at the end of game or when the user exits
	 */
	public synchronized void stop() {
		try {
			thread.join();
			this.running = false;

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	  Run the main thread renders and ticks the game screen and all its components subsequently
	 */
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
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

         /* if(System.currentTimeMillis() - timer > 1000)
          {
        	  timer += 1000;
        	  System.out.println("FPS: "+ frames);
        	  frames = 0;
          }*/
        }
        stop();
	}
	/**
	   Calls the tick method of all components of the game in order to update their attributes and check necessary constraints.
	 */
	private void tick() {
		if(Game.gameState == GameState.Game) {
			world.tick();
			handler.tick();
			hudp1.tick();
			hudp2.tick();
		}

	}
	/**
	  renders the screen. Creates the necessary buffers and graphic object and calls render method of all components of the game.
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2);
			return;
		}
	    Graphics g= bs.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
    	g.setColor(Color.BLACK);

    	g.fillRect(0,0,WIDTH,HEIGHT);
	    //g.setColor(new Color(105,36,67));

		if(Game.gameState == GameState.Pause) {
			this.world.render(g);
			this.handler.render(g);
			this.pause.render(g);
		}
		else if(Game.gameState == GameState.Menu) {
			this.menu.render(g);
		}
		else if(Game.gameState == GameState.Help) {
			this.help.render(g);
		}
		else if(Game.gameState == GameState.Player1Selection) {

			p1.render(g);

			//handler.addObject(new Player(640 ,64, 180, ID.Player1,PlayerType.Shotgun, handler, playerImage));

		}
		else if(Game.gameState == GameState.Player2Selection) {

			p2.render(g);
			//handler.addObject(new Player(64 ,640, 0, ID.Player2,PlayerType.Sniper, handler, playerImage));

		}else if(Game.gameState == GameState.Game) {

			world.render(g);
			//timer = new Timer(td);
			//timer.start();

			handler.render(g);


		}else if( Game.gameState == GameState.End) {


			this.gameend.render(g);

		}



		g.dispose();
		bs.show();

	}
	/**
	  Used to constraint value of a variable within a range.
	  @param var the variable to be clamped.
	  @param max maximum value allowed for that variable.
	  @param min minimum value allowed for that variable.
	  @return returns the appropriate value of the variable. returns Max if variable is greater than max and returns  min if variable is smaller than min.
	 */
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
	/**
	main method of project
	*/
	public static void main(String[] args) {
		new Game();

	}
}
