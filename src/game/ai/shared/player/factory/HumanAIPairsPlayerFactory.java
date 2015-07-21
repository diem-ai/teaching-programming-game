package game.ai.shared.player.factory;

import game.ai.shared.player.AIPlayer;
import game.ai.shared.player.HumanPlayer;
import game.ai.shared.player.IPlayer;
import game.ai.shared.player.factory.specification.IPairsPlayerFactory;
import game.ai.shared.player.specification.PairsPlayerSpecification;
/**
 * This class is an implementation of {@link IPairsPlayerFactory} and {@link AbstractPairsPlayerFactory} </br>
 * Create a {@link PairsPlayerSpecification} with < {@link HumanPlayer}, {@link AIPlayer}> players </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */

public class HumanAIPairsPlayerFactory extends AbstractPairsPlayerFactory implements IPairsPlayerFactory{
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.player.factory.specification.IPairsPlayerFactory#createFirstPlayer()
	 */
	@Override
	public IPlayer createFirstPlayer() {
		return new HumanPlayer();
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.player.factory.specification.IPairsPlayerFactory#createSecondPlayer()
	 */
	@Override
	public IPlayer createSecondPlayer() {
		return new AIPlayer();
	}
	
	//create a singleton for each call
	private static class LazyHolder {
        public static final HumanAIPairsPlayerFactory INSTANCE = new HumanAIPairsPlayerFactory();
	}

	public static HumanAIPairsPlayerFactory getInstance(){
		return LazyHolder.INSTANCE;
	}

}
