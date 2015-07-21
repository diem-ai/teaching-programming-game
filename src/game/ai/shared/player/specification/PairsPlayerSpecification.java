package game.ai.shared.player.specification;

import java.util.Observable;
import java.util.Observer;

import game.ai.shared.model.PlayerState;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.player.IPlayer;
import game.ai.shared.controller.specification.*;

/**
 * This is an interface for Pair Player implementation </br>
 * {@link PairsPlayerSpecification} is an {@link Observer} that listens to the change of {@link IBoard} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public interface PairsPlayerSpecification{
	/**
	 * Return the first player of {@link PairsPlayerSpecification} </br>
	 * @return {@link IPlayer} </br>
	 */
	public IPlayer getFirstPlayer();
	/**
	 * Return the second {@link IPlayer} of {@link PairsPlayerSpecification} </br>
	 * @return {@link IPlayer} </br>
	 */
	public IPlayer getSecondPlayer();
	/**
	 * {@link IBoard} will add {@link PairsPlayerSpecification} to observer list </br>
	 * @param board - is an {@link Observable} </br>
	 */
	public void play(IBoard board);
	/**
	 * Assign randomly {@link PlayerState#CROSS} or {@link PlayerState#NOUGHT} for the current {@link IPlayer}</br>
	 * @return {@link PlayerState} </br>
	 */
	public PlayerState getRandomPlayerState();
	/**
	 * Register itself with {@link IGameController} </br>
	 * @param controller - {@link IGameController} </br>
	 */
	public void addController(IGameController controller);


}
