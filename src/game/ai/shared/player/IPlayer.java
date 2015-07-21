package game.ai.shared.player;

import game.ai.shared.model.PlayerState;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.controller.specification.*;
/**
 * This class is an interface for {@link IPlayer} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public interface IPlayer {
	/**
	 * Set {@link PlayerState} value for {@link IPlayer} </br>
	 * @param state - {@link PlayerState} </br>
	 */
	public void setState(PlayerState state);
	/**
	 * This action is done by {@link HumanPlayer} or {@link AIPlayer} </br>
	 * The state of {@link IBoard} will be changed following their actions </br>
	 * @param board - {@link IBoard} </br>
	 */
	public void move(IBoard board);
	/**
	 * 
	 * @return {@link PlayerState} </br>
	 */
	public PlayerState getState();
	/**
	 * Set a opponent player for the current {@link IPlayer} </br>
	 * @param opponent - {@link IPlayer} </br>
	 */
	public void setOpponent(IPlayer opponent);
	/**
	 * Return the opponent player of current {@link IPlayer} </br>
	 * @return {@link IPlayer} </br>
	 */
	public IPlayer getOpponent();
	/**
	 * Register itself with {@link IGameController} </br>
	 * {@link IGameController} only handles {@link HumanPlayer} </br>
	 * @param controller - {@link IGameController} </br>
	 */
	public void addController(IGameController controller);
}
