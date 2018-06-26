package zwars;
/**
 * Enum to store various states the game would be in. Different screens would be rendered for different states.
 */
public enum GameState {
	Menu(),
	Player1Selection(),
	Player2Selection(),
	Help(),
	Pause(),
	Game(),
	End();
	
}
