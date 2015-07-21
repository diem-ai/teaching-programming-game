package tests.game.ai.shared.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Component;
import java.awt.event.MouseEvent;

import game.ai.shared.controller.GameController;
import game.ai.shared.controller.specification.IGameController;
import game.ai.shared.model.Cell;
import game.ai.shared.model.GameState;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.player.specification.PairsPlayerSpecification;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
/**
 * This test for {@link IGameController} and {@link GameController} by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem
 * @version 1.0
 */
public class TestGameController {
	
	IGameController controller;
	IBoard board;
	PairsPlayerSpecification pairsPlayer;
	@Before
	public void setUp() throws Exception {
		board = Mockito.mock(IBoard.class);
		pairsPlayer = Mockito.mock(PairsPlayerSpecification.class);

	}

	@After
	public void tearDown() throws Exception {
		controller = null;
		board = null;
		pairsPlayer = null;
	}
	/**
	 * @see {@link GameController#GameController(IBoard)} </br>
	 */
	@Test
	public void testConstructor() {
		board = Mockito.mock(IBoard.class);
		pairsPlayer = Mockito.mock(PairsPlayerSpecification.class);
		controller = new GameController(board, pairsPlayer);
	}
	/**
	 * @see {@link GameController#GameController(IBoard)} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor_Exception1() {
		controller = new GameController(null, pairsPlayer);
	}
	/**
	 * @see {@link GameController#GameController(IBoard)} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor_Exception2() {
		controller = new GameController(board, null);
	}
	
	/**
	 * Case 1: selectedRow and selected Column is out of {@link IBoard} size <br>
	 * {@link IGameController#mouseClicked(MouseEvent)} will do nothing </br>
	 */
	@Test
	public void testMouseClicked1(){
		MouseEvent event = Mockito.mock(MouseEvent.class);
		int posX = 400;
		int posY = 200;
		Mockito.when(event.getX()).thenReturn(posX);
		Mockito.when(event.getY()).thenReturn(posY);

		board = Mockito.mock(IBoard.class);
		Mockito.when(board.getRows()).thenReturn(3);
		Mockito.when(board.getCols()).thenReturn(3);
		Mockito.when(board.getGameState()).thenReturn(GameState.PLAYING);
//		int selectedRow = 2;
//		int selectedCol = 1;
		controller = new GameController(board, pairsPlayer);
		controller.mouseClicked(event);
	}
	/**
	 * Case 2: selectedRow and selected Column is in {@link IBoard} size <br>
	 * The {@link Cell} at that position is not empty </br>
	 * {@link IGameController#mouseClicked(MouseEvent)} will do nothing </br>
	 */
	@Test
	public void testMouseClicked2(){
		MouseEvent event = Mockito.mock(MouseEvent.class);
		int posX = 200;
		int posY = 200;
		Mockito.when(event.getX()).thenReturn(posX);
		Mockito.when(event.getY()).thenReturn(posY);

		board = Mockito.mock(IBoard.class);
		Mockito.when(board.getRows()).thenReturn(3);
		Mockito.when(board.getCols()).thenReturn(3);
		Mockito.when(board.isEmptyCell(2, 2)).thenReturn(false);
		Mockito.when(board.getGameState()).thenReturn(GameState.PLAYING);
		controller = new GameController(board, pairsPlayer);
		controller.mouseClicked(event);
	}
	/**
	 * Case 3: selectedRow and selected Column is in {@link IBoard} size <br>
	 * The {@link Cell} at that position is empty </br>
	 * {@link IGameController#mouseClicked(MouseEvent)} will call {@link IBoard#update(Cell)} </br>
	 */
	@Test
	public void testMouseClicked3(){
		MouseEvent event = Mockito.mock(MouseEvent.class);
		int posX = 200;
		int posY = 200;
		Mockito.when(event.getX()).thenReturn(posX);
		Mockito.when(event.getY()).thenReturn(posY);

		board = Mockito.mock(IBoard.class);
		Mockito.when(board.getRows()).thenReturn(3);
		Mockito.when(board.getCols()).thenReturn(3);
		Mockito.when(board.isEmptyCell(2, 2)).thenReturn(true);
		Mockito.when(board.getGameState()).thenReturn(GameState.PLAYING);
		controller = new GameController(board, pairsPlayer);
		controller.mouseClicked(event);
	}
	/**
	 * Case 4: selectedRow is out of {@link IBoard} size and selected Column is in {@link IBoard} size <br>
	 * The {@link Cell} at that position is empty </br>
	 * {@link IGameController#mouseClicked(MouseEvent)} will do nothing </br>
	 */
	@Test
	public void testMouseClicked4(){
		MouseEvent event = Mockito.mock(MouseEvent.class);
		int posX = 200;
		int posY = 400;
		Mockito.when(event.getX()).thenReturn(posX);
		Mockito.when(event.getY()).thenReturn(posY);

		board = Mockito.mock(IBoard.class);
		Mockito.when(board.getRows()).thenReturn(3);
		Mockito.when(board.getCols()).thenReturn(3);
		controller = new GameController(board, pairsPlayer);
		Mockito.when(board.getGameState()).thenReturn(GameState.PLAYING);
		controller.mouseClicked(event);
	}
	/**
	 * Case 5: Game is over
	 * {@link IGameController#mouseClicked(MouseEvent)} will call {@link IBoard#clear()} </br>
	 */
	@Test
	public void testMouseClicked5(){
		MouseEvent event = Mockito.mock(MouseEvent.class);
		Component component = Mockito.mock(Component.class);
		Mockito.when(event.getComponent()).thenReturn(component);
		Mockito.doNothing().when(component).repaint();
		int posX = 200;
		int posY = 400;
		Mockito.when(event.getX()).thenReturn(posX);
		Mockito.when(event.getY()).thenReturn(posY);

		board = Mockito.mock(IBoard.class);
		Mockito.when(board.getGameState()).thenReturn(GameState.DRAW);
		
		controller = new GameController(board, pairsPlayer);
		controller.mouseClicked(event);
	}
	/**
	 * @see {@link PairsPlayerSpecification#play(IBoard)} </br>
	 * @see also {@link IGameController#play()} </br>
	 */
	@Test
	public void testPlay(){
		board = Mockito.mock(IBoard.class);
		pairsPlayer = Mockito.mock(PairsPlayerSpecification.class);
		Mockito.doNothing().when(pairsPlayer).play(board);
		controller = new GameController(board, pairsPlayer);
		controller.play();
	}
	/**
	 * @see {@link IGameController#canPlay()} </br>
	 * @see {@link IGameController#setCanPlay(boolean)} </br>
	 */
	@Test
	public void testCanPlay(){
		board = Mockito.mock(IBoard.class);
		pairsPlayer = Mockito.mock(PairsPlayerSpecification.class);
		controller = new GameController(board, pairsPlayer);
		assertFalse(controller.canPlay());
		controller.setCanPlay(true);
		assertTrue(controller.canPlay());
		
	}

}
