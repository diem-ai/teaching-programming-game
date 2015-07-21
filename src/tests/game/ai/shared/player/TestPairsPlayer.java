package tests.game.ai.shared.player;

import static org.junit.Assert.*;

import java.util.Observable;

import game.ai.shared.controller.specification.IGameController;
import game.ai.shared.model.AbstractBoard;
import game.ai.shared.model.GameState;
import game.ai.shared.model.PlayerState;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.player.IPlayer;
import game.ai.shared.player.PairsPlayer;
import game.ai.shared.player.specification.PairsPlayerSpecification;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.internal.verification.AtLeast;
import org.mockito.verification.VerificationMode;
/**
 * This test for {@link PairsPlayerSpecification} class by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public class TestPairsPlayer {
	
	PairsPlayerSpecification pairsPlayer;
	IPlayer first;
	IPlayer second;
	
	@Before
	public void setUp() throws Exception {
		first = Mockito.mock(IPlayer.class);
		Mockito.when(first.getState()).thenReturn(PlayerState.CROSS);
		Mockito.when(first.getOpponent()).thenReturn(second);
		second = Mockito.mock(IPlayer.class);
		Mockito.when(second.getState()).thenReturn(PlayerState.NOUGHT);
		Mockito.when(second.getOpponent()).thenReturn(first);
		pairsPlayer = new PairsPlayer(first, second);
	}

	@After
	public void tearDown() throws Exception {
		first = null;
		second = null;
		pairsPlayer = null;
	}
	/**
	 * @see {@link PairsPlayer#getFirstPlayer()} </br>
	 * @see {@link PairsPlayer#getSecondPlayer()} </br>
	 */
	@Test
	public void testConstructor() {
		assertEquals(first, pairsPlayer.getFirstPlayer());
		assertEquals(second, pairsPlayer.getSecondPlayer());
		assertEquals(PlayerState.CROSS, first.getState());
		assertEquals(PlayerState.NOUGHT, second.getState());
	}
	/**
	 * The first parameter is null </br>
	 * Expecting {@link PairsPlayer#PairsPlayer(IPlayer, IPlayer)} throws an {@link IllegalArgumentException} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor_Exception1(){
		pairsPlayer = new PairsPlayer(null, second);
	}
	/**
	 * The second parameter is null </br>
	 * Expecting {@link PairsPlayer#PairsPlayer(IPlayer, IPlayer)} throws an {@link IllegalArgumentException} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor_Exception2(){
		pairsPlayer = new PairsPlayer(first, null);
	}
	/**
	 * The first and second parameter is null </br>
	 * Expecting {@link PairsPlayer#PairsPlayer(IPlayer, IPlayer)} throws an {@link IllegalArgumentException} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor_Exception3(){
		pairsPlayer = new PairsPlayer(null, null);
	}
//	/**
//	 * 
//	 * @see PairsPlayer#update(Observable, Object) </br>
//	 */
//	@Test
//	public void testUpdate1(){
//		AbstractBoard board = Mockito.mock(AbstractBoard.class);
//		pairsPlayer.update( board, PlayerState.NOUGHT);
//	}
//	/**
//	 * @see PairsPlayer#update(Observable, Object) </br>
//	 */
//	@Test
//	public void testUpdate2(){
//		AbstractBoard board = Mockito.mock(AbstractBoard.class);
//		pairsPlayer.update( board, PlayerState.CROSS);
//	}
//	/**
//	 * @see PairsPlayer#update(Observable, Object) </br>
//	 */
//	@Test
//	public void testUpdate3(){
//		AbstractBoard board = Mockito.mock(AbstractBoard.class);
//		pairsPlayer.update( board,new String());
//	}
//	/**
//	 * @see PairsPlayer#update(Observable, Object) </br>
//	 */
//	@Test
//	public void testUpdate4(){
//		AbstractBoard board = Mockito.mock(AbstractBoard.class);
//		pairsPlayer.update( board,new String());
//	}	
	/**
	 * Mock {@link IBoard#isGameOver()} returns false, false true to cover all cases</br>
	 * @see PairsPlayerSpecification#play(game.ai.shared.model.specification.IBoard) </br>
	 */
	@Test
	public void testPlay(){
		IBoard board = Mockito.mock(IBoard.class);
		Mockito.when(board.isGameOver()).thenReturn(false, false, true);
		pairsPlayer.play(board);
	}
	/**
	 * @see {@link PairsPlayerSpecification#addController(IGameController)}
	 * Expecting this method runs without error </br>
	 */
	@Test
	public void testAddController(){
		IGameController controller = Mockito.mock(IGameController.class);
		pairsPlayer.addController(controller);
	}

}
