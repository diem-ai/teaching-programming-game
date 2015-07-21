package tests.game.ai.simulation;

import game.ai.shared.player.AIPlayer;
import game.ai.shared.player.HumanPlayer;
import game.ai.shared.player.factory.PairsPlayerFactoryChoices;
import game.ai.shared.player.specification.PairsPlayerSpecification;

/**
 * This is a simulation for creating a random {@link AIPlayer} with 3 modes : </br>
 * Mode 1: A pairs of {@link AIPlayer} and {@link AIPlayer} </br>
 * Mode 2: A pairs of {@link HumanPlayer} and {@link AIPlayer} </br>
 * Mode 3: A pairs of {@link HumanPlayer} and {@link HumanPlayer} </br>
 * @author btdiem
 * @version 1.0
 */
public class RandomAIPlayerSimulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(DateHeader.dateString());
		PairsPlayerSpecification pairsPlayer = PairsPlayerFactoryChoices.getRandomPairsPlayer();
		System.out.println(pairsPlayer.toString());
	}

}
