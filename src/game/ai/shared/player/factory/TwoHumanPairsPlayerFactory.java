package game.ai.shared.player.factory;

import game.ai.shared.player.HumanPlayer;
import game.ai.shared.player.IPlayer;
import game.ai.shared.player.factory.specification.IPairsPlayerFactory;
import game.ai.shared.player.specification.PairsPlayerSpecification;
/**
 * This class is an implementation of {@link IPairsPlayerFactory} and {@link AbstractPairsPlayerFactory} </br>
 * Create a {@link PairsPlayerSpecification} with <{@link HumanPlayer}, {@link HumanPlayer}> players </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public class TwoHumanPairsPlayerFactory extends AbstractPairsPlayerFactory implements IPairsPlayerFactory{
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
		return new HumanPlayer();
	}
	
	//create a singleton for each call
	private static class LazyHolder {
        public static final TwoHumanPairsPlayerFactory INSTANCE = new TwoHumanPairsPlayerFactory();
	}

	public static TwoHumanPairsPlayerFactory getInstance(){
		return LazyHolder.INSTANCE;
	}


}
