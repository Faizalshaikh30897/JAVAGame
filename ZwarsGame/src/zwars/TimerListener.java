package zwars;

import java.awt.event.*;


import zwars.player.Player;

/**
 class to implement actionListener to handle delays in shooting bullets for both players
 */
public class TimerListener implements ActionListener {
	private Player player;//player on which a delay has to be affected before he can shoot the next bullet

	public TimerListener(Player player) {
		this.player = player;

	}
	/**
	overriding the method of listener
	*/
	public void actionPerformed(ActionEvent e) {
		//System.out.println("action event fired");
		player.setShooting(false);

	}

}
