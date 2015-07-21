package tests.game.ai.shared.view;

import java.awt.Graphics;
import java.util.Observable;

import javax.swing.JPanel;

import game.ai.connect4.model.Connect4Board;
import game.ai.shared.view.specification.IGameView;
import game.ai.tictactoe.model.TictactoeBoard;
import game.ai.shared.model.GameState;
import game.ai.shared.model.PlayerState;
import game.ai.shared.model.specification.*;
import game.ai.shared.controller.specification.*;
import game.ai.shared.view.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
/**
 * This is abstract View test class that is shared by {@link TictactoeBoard} and {@link Connect4Board} game </br> 
 * @author btdiem
 * @version 1.0
 *
 */
public abstract class TestGameView {
	
	IGameView view;
	IBoard board;
	IGameController controller;
	/**
	 * Return a specific implementation of {@link IBoard} </br>
	 * @return {@link IBoard} </br>
	 */
	public abstract IBoard getBoard();
	/**
	 * Return the specific tile for each game </br>
	 * @return {@link String} </br>
	 */
	public abstract String getGameTitle();
	
	@Before
	public void setUp() throws Exception {
		board = getBoard();
		controller = Mockito.mock(IGameController.class);
	}

	@After
	public void tearDown() throws Exception {
		board = null;
		controller = null;
	}
	/**
	 * @see {@link GameView#GameView(String, IBoard, IGameController)} </br>
	 * Expecting there is no exception thrown when creating constructor </br>
	 */
	@Test
	public void testConstructor() {
		view = new GameView (getGameTitle(), board, controller);
	}
	/**
	 * @see {@link GameView#display()} </br>
	 * Expecting there is no exception thrown when calling {@link GameView#display()} </br>
	 */
	@Test
	public void testDisplay(){
		view = new GameView (getGameTitle(), board, controller);
		view.display();
	}
	/**
	 * @see {@link GameView#GameView(String, IBoard, IGameController)} </br>
	 * @see {@link GameView#update(Observable, Object)} </br>
	 */
	@Test
	public void testUpdate(){
		view = new GameView (getGameTitle(), board, controller);
		view.update((Observable) board, PlayerState.CROSS);
	}
	/**
	 * @see {@link GameView#paint(java.awt.Graphics)} </br> 
	 */
	@Test 
	public void testPaint(){
		view = new GameView (getGameTitle(), board, controller);
		((GameView)view).repaint();
	}
	/**
	 * @see {@link JPanel#paintComponents(java.awt.Graphics)} </br>
	 * Mock {@link IBoard#getGameState()} returns {@link GameState#PLAYING} </br>
	 * Mock {@link IBoard#getPlayerState()} returns {@link PlayerState#CROSS} </br> 
	 */
	@Test
	public void testPaintComponent1(){
		Graphics g = Mockito.mock(Graphics.class);
		Mockito.when(board.getGameState()).thenReturn(GameState.PLAYING);
		//Mockito.when(board.getPlayerState()).thenReturn(PlayerState.CROSS);
		view = new GameView (getGameTitle(), board, controller);
		((GameView)view).repaint();
		view.getTemplate().paintComponents(g);
		//assertEquals("X's Turn", view.getStatusBar().getText());
		
	}
}
