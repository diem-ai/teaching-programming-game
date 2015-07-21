package game.ai.shared.player.factory;

import game.ai.shared.player.AIPlayer;
import game.ai.shared.player.IPlayer;
import game.ai.shared.player.factory.specification.IPairsPlayerFactory;
import game.ai.shared.player.specification.PairsPlayerSpecification;

/**
 * This class is an implementation of {@link AbstractPairsPlayerFactory} and {@link IPairsPlayerFactory} </br>
 * Create a {@link PairsPlayerSpecification} with <{@link AIPlayer}, {@link AIPlayer}> players  </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public class TwoAIPairsPlayerFactory extends AbstractPairsPlayerFactory implements IPairsPlayerFactory{
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.player.factory.specification.IPairsPlayerFactory#createFirstPlayer()
	 */
	@Override
	public IPlayer createFirstPlayer() {
		return new AIPlayer();
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
        public static final TwoAIPairsPlayerFactory INSTANCE = new TwoAIPairsPlayerFactory();
	}

	public static TwoAIPairsPlayerFactory getInstance(){
		return LazyHolder.INSTANCE;
	}


	
}
