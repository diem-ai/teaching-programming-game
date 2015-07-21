package tests.game.ai.shared.player;

import static org.junit.Assert.*;
import game.ai.shared.model.PlayerState;
import game.ai.shared.player.HumanPlayer;
import game.ai.shared.player.IPlayer;

import org.junit.Test;
import org.junit.runners.JUnit4;
/**
 * This test for {@link IPlayer} class by using {@link JUnit4} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public class TestHumanPlayer extends TestAbstractPlayer{
	/**
	 * @see {@link HumanPlayer#toString()} </br>
	 */
	@Test
	public void testToString(){
		player.setState(PlayerState.CROSS);
		String s = "I'm a human player, I take " + PlayerState.CROSS.name();
		assertEquals(s, player.toString());
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.player.TestAbstractPlayer#getDefaultConstructor()
	 */
	@Override
	public IPlayer getDefaultConstructor() {
		return new HumanPlayer();
	}
}
