package game.ai.shared.player.factory;
import game.ai.shared.player.AIPlayer;
import game.ai.shared.player.HumanPlayer;
import game.ai.shared.player.factory.specification.IPairsPlayerFactory;
import game.ai.shared.player.specification.PairsPlayerSpecification;

import java.util.Random;

/**
 * This class to create randomly {@link PairsPlayerSpecification} with 3 modes: </br>
 * Mode 1: {@link AIPlayer} and {@link AIPlayer} </br>
 * Mode 2: {@link HumanPlayer} and {@link AIPlayer} </br>
 * Mode 3: {@link HumanPlayer} and {@link HumanPlayer} <br>
 * @author btdiem </br>
 * @version 1.0 <br>
 *
 */
public class PairsPlayerFactoryChoices {

	static Random random = new Random(); 
	/**
	 * Return a {@link Integer} from 0 to 3 </br>
	 * @return {@link Integer} </br>
	 */
	public static int getMode(){
		return random.nextInt(FactoryPairsPlayerType.values().length);
	}
	/**
	 * There are 3 modes of {@link IPairsPlayerFactory} <br>
	 * Mode 1: Create a {@link TwoAIPairsPlayerFactory} with {@link AIPlayer} and {@link AIPlayer} </br>
	 * Mode 2: Create a {@link HumanAIPairsPlayerFactory} with {@link HumanPlayer} and {@link AIPlayer} </br>
	 * Mode 3: Create a {@link TwoHumanPairsPlayerFactory} with {@link HumanPlayer} and {@link HumanPlayer} </br>
	 * @return {@link IPairsPlayerFactory}
	 */
	public static IPairsPlayerFactory getFactory(){
		int mode = getMode();
		switch(mode){
		case 0:
			return TwoAIPairsPlayerFactory.getInstance();
		case 1:
			return HumanAIPairsPlayerFactory.getInstance();
		default:
			return TwoHumanPairsPlayerFactory.getInstance();
		}
	}
	/**
	 * @see {@link IPairsPlayerFactory#createPairsPlayer()} </br>
	 * @return {@link PairsPlayerSpecification} </br>
	 */
	public static PairsPlayerSpecification getRandomPairsPlayer(){
		return getFactory().createPairsPlayer();
	}
	
}
