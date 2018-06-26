package zwars.threads;

import java.util.Random;

import zwars.*;
/**
  thread on which different spawns are generated and handled.
 */
public class Spawner implements Runnable {

	private Random r = new Random();
	float positions[][] = new float[4][2];//used to store the possible positions on map of the spawn
	private Handler handler;
	Thread t1 = new Thread(this);
	private volatile boolean run = true;//used to handle stoping and restarting of spawner thread

	public Spawner(Handler handler) {


		this.handler = handler;
		positions[0][0] = 800;
		positions[0][1] = 190;
		positions[1][0] = 800;
		positions[1][1] = 760;
		positions[2][0] = 70;
		positions[2][1] = 460;
		positions[3][0] = 1500;
		positions[3][1] = 460;


	}
	/**
	 * method to start the spawner thread.
	 */
	public void start() {
		t1.setDaemon(true);
		t1.start();

	}
	/**
	 * method to stop the spawner thread.
	 */
	public void terminate() {
		run=false;
	}
	/**
	 * creates the spawn at random positions at specified time interval.
	 */
	public void run() {

		while(run) {

			//System.out.println("in while loop");
			if(Game.gameState == GameState.Game) {

				try {
					Thread.sleep(20000);
					int random = r.nextInt(2);

					if(random == 1) {
						int pos = r.nextInt(4);
						//Spawn spawn = new Spawn(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.Spawn, SpawnID.HealthPack, handler);
						Spawn spawn = new Spawn(positions[pos][0],positions[pos][1], ID.Spawn, SpawnID.HealthPack,handler);
						handler.addObject(spawn);

					}
					else if(random == 0) {
						int pos = r.nextInt(4);
						//Spawn spawn = new Spawn(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.Spawn, SpawnID.Rage, handler);
						Spawn spawn = new Spawn(positions[pos][0],positions[pos][1], ID.Spawn, SpawnID.Rage,handler);
						handler.addObject(spawn);

					}
				} catch (InterruptedException e) {
					System.out.println("Thread: "+t1.getName()+"was interrupted");
				}
			}
		}

	}
	/**
	used to interrupt the sleeping thread when game ends
	*/
	public void interrupt(){
		this.t1.interrupt();
	}


}
