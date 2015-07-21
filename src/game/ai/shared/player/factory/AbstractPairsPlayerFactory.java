package game.ai.shared.player.factory;

import game.ai.shared.player.PairsPlayer;
import game.ai.shared.player.factory.specification.IPairsPlayerFactory;
import game.ai.shared.player.specification.PairsPlayerSpecification;

/**
 * This abstract class extends from {@link IPairsPlayerFactory} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */

public abstract class AbstractPairsPlayerFactory  implements IPairsPlayerFactory{
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.player.factory.specification.IPairsPlayerFactory#createPairsPlayer()
	 */
	public PairsPlayerSpecification createPairsPlayer(){
		return new PairsPlayer(createFirstPlayer(), createSecondPlayer());
	}
	
}
