package tests.game.ai.shared.player.factory;

import static org.junit.Assert.*;

import game.ai.shared.player.AIPlayer;
import game.ai.shared.player.HumanPlayer;
import game.ai.shared.player.factory.AbstractPairsPlayerFactory;
import game.ai.shared.player.factory.HumanAIPairsPlayerFactory;
import game.ai.shared.player.factory.specification.IPairsPlayerFactory;
import game.ai.shared.player.specification.PairsPlayerSpecification;

import org.junit.Test;
import org.junit.runners.JUnit4;
/**
 * This test for class {@link HumanAIPairsPlayerFactory} and {@link AbstractPairsPlayerFactory} and {@link IPairsPlayerFactory} </br>
 * by using {@link JUnit4} </br>
 * @author btdiem
 * @version 1.0
 *
 */
public class TestHumanAIPairsPlayerFactory {
	/**
	 * @see {@link HumanAIPairsPlayerFactory#createPairsPlayer()} </br>
	 * 
	 */
	@Test
	public void testCreatePairsPlayer(){
		PairsPlayerSpecification pairs = HumanAIPairsPlayerFactory.getInstance().createPairsPlayer();
		assertTrue(pairs.getFirstPlayer() instanceof HumanPlayer);
		assertTrue(pairs.getSecondPlayer() instanceof AIPlayer);
	}
}
