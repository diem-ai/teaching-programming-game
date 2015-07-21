package game.ai.shared.player.factory.specification;

import game.ai.shared.player.IPlayer;
import game.ai.shared.player.specification.PairsPlayerSpecification;
/**
 * This class is an interface to create {@link PairsPlayerSpecification} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public interface IPairsPlayerFactory {
	/**
	 * Create the first {@link IPlayer} of {@link PairsPlayerSpecification} </br>
	 * @return {@link IPlayer} </br>
	 */
	public IPlayer createFirstPlayer();
	/**
	 * Create the second {@link IPlayer} of {@link PairsPlayerSpecification} </br>
	 * @return {@link IPlayer} </br>
	 */
	public IPlayer createSecondPlayer();
	/**
	 * Create a {@link PairsPlayerSpecification} that contains a pairs of {@link IPlayer} </br>
	 * @return {@link PairsPlayerSpecification} </br>
	 */
	public PairsPlayerSpecification createPairsPlayer();

}
