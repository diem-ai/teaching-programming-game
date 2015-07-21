package tests.game.ai.shared.player;

import static org.junit.Assert.*;
import game.ai.shared.model.PlayerState;
import game.ai.shared.player.IPlayer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
/**
 * This is an abstract test class for {@link IPlayer} by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem
 * @version 1.0
 */
public abstract class TestAbstractPlayer {
	/**
	 * The specific implementation of {@link IPlayer} return a constructor </br>
	 * @return {@link IPlayer} </br>
	 */
	public abstract IPlayer getDefaultConstructor();
	IPlayer player;
	@Before
	public void setUp(){
		player = getDefaultConstructor();
	}
	@After
	public void tearDown(){
		player = null;
	}
	@Test
	public void testConstructor(){
		assertEquals(PlayerState.EMPTY, player.getState());
	}
	/**
	 * @see {@link IPlayer#getState()} </br>
	 * @see {@link IPlayer#setState(PlayerState)} </br>
	 */
	@Test
	public void testSetState() {
		player.setState(PlayerState.CROSS);
		assertEquals(PlayerState.CROSS, player.getState());
	}
	/**
	 * @see {@link IPlayer#getOpponent()} </br>
	 * @see {@link IPlayer#setOpponent(IPlayer)} </br>
	 */
	@Test
	public void testSetOpponent(){
		IPlayer opponent = Mockito.mock(IPlayer.class);
		player.setOpponent(opponent);
		assertEquals(opponent, player.getOpponent());
	}

}
