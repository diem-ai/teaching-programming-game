package tests.game.ai.shared.player.factory;

import static org.junit.Assert.*;
import game.ai.shared.player.AIPlayer;
import game.ai.shared.player.factory.AbstractPairsPlayerFactory;
import game.ai.shared.player.factory.TwoAIPairsPlayerFactory;
import game.ai.shared.player.factory.specification.IPairsPlayerFactory;
import game.ai.shared.player.specification.PairsPlayerSpecification;
import org.junit.Test;
import org.junit.runners.JUnit4;
/**
 * This test for {@link TwoAIPairsPlayerFactory} and {@link AbstractPairsPlayerFactory} and {@link IPairsPlayerFactory} classes </br>
 * by using {@link JUnit4} </br>
 * @author btdiem
 * @version 1.0
 *
 */
public class TestTwoAIPairsPlayerFactory {
	/**
	 * @see {@link TwoAIPairsPlayerFactory#createPairsPlayer()} </br>
	 */
	@Test
	public void testCreatePairsPlayer() {
		PairsPlayerSpecification pairs = TwoAIPairsPlayerFactory.getInstance().createPairsPlayer();
		assertTrue(pairs.getFirstPlayer() instanceof AIPlayer);
		assertTrue(pairs.getSecondPlayer() instanceof AIPlayer);
	}

}
