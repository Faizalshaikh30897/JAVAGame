package zwars.threads;

import zwars.Game;
import zwars.GameState;
/**
  thread to handle the timer and update the Time of battle.
 */
public class Timer implements Runnable {

	private TimeDisplay td;//object of displaying the time
	private Thread t;
	private volatile boolean run = true;//used to stop and restart the timer

	public Timer(TimeDisplay t) {
		this.td = t;
	}
	/**
	  method to start the timer thread.
	 */
	public void start() {
		t = new Thread(this);
		t.setDaemon(true);
		t.start();
	}
	/**
	  method to stop the timer thread.
	 */
	public void terminate() {
		run=false;
	}
	/**
	  updates the timerDisplay every second.
	 */
	public void run() {
		while(run) {
			if(Game.gameState == GameState.Game) {
				try {
					Thread.sleep(1000);
					td.setSeconds(td.getSeconds() + 1);


				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
