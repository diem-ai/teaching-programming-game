package tests.game.ai.shared.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import game.ai.shared.model.Cell;
import game.ai.shared.model.PlayerState;
import game.ai.shared.model.specification.IBoard;
import game.ai.connect4.model.*;

import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
/**
 * This test for {@link IBoard}, {@link AbstractBoard} and {@link Connect4Board} by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem
 * @version 1.0
 */
public class TestConnect4Board extends TestAbstractBoard{
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#getDefaultRows()
	 */
	@Override
	public int getDefaultRows() {
		return 7;
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#getDefaultCols()
	 */
	@Override
	public int getDefaultCols() {
		return 6;
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#getDefaultConnector()
	 */
	@Override
	public int getDefaultConnector() {
		
		return 4;
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#getDefaultConstructor()
	 */
	@Override
	public IBoard getDefaultConstructor() {
		
		return new Connect4Board(getDefaultRows(), 
								getDefaultCols(), 
								getDefaultConnector());
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#getConstructorHasNegativeRows()
	 */
	@Override
	public IBoard getConstructorHasNegativeRows() {
		
		return new Connect4Board(-1, cols, connector);
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#getConstructorHasNegativeCols()
	 */
	@Override
	public IBoard getConstructorHasNegativeCols() {
		return new Connect4Board(rows, -1, connector);
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#getConstructorHasNegativeConnector()
	 */
	@Override
	public IBoard getConstructorHasNegativeConnector() {
		
		return new Connect4Board(rows, cols, -1);
	}
	
	/**
	 * Case 1: rows < connetor and cols >= connetor </br>
	 * For this case, {@link IBoard#invariant()} returns false </br>
	 * Expecting constructor will throw an {@link IllegalArgumentException} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testNotSafeConstructor_1() {
		board = new Connect4Board(7, 8, 8);
	}
	/**
	 * 
	 * Case 1: rows >= connetor and cols < connetor </br>
	 * For this case, {@link IBoard#invariant()} returns false </br>
	 * Expecting constructor will throw an {@link IllegalArgumentException} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testNotSafeConstructor_2() {
		board = new Connect4Board(8, 6, 7);
	}
	/**
	 * Case 1: rows < connetor and cols < connetor </br>
	 * For this case, {@link IBoard#invariant()} returns false </br>
	 * Expecting constructor will throw an {@link IllegalArgumentException} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testNotSafeConstructor_3() {
		board = new Connect4Board(7, 6, 8);
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#setBoardHasWiningRows()
	 * Create a winning row for {@link IBoard} </br>
	 * Expecting method {@link IBoard#isWin()} return true</br>
	 */
	@Override
	public void setBoardHasWiningRows() {
		board.updatePlayer(PlayerState.CROSS);
		board.getCells()[0][0].setValue(PlayerState.CROSS);
		board.getCells()[0][1].setValue(PlayerState.CROSS);
		board.getCells()[0][2].setValue(PlayerState.CROSS);
		board.getCells()[0][3].setValue(PlayerState.CROSS);;
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#setBoardHasWiningCols()
	 * Create a winning column for {@link IBoard} </br>
	 * Expecting method {@link IBoard#isWin()} return true</br>
	 */
	@Override
	public void setBoardHasWiningCols() {
		board.updatePlayer(PlayerState.CROSS);
		board.getCells()[0][0].setValue(PlayerState.CROSS);
		board.getCells()[1][0].setValue(PlayerState.CROSS);
		board.getCells()[2][0].setValue(PlayerState.CROSS);
		board.getCells()[3][0].setValue(PlayerState.CROSS);
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#setBoardHasWiningLowDiagnoals()
	 * Create a winning low diagonal row for {@link IBoard} </br>
	 * Expecting method {@link IBoard#isWin()} return true</br>
	 */
	@Override
	public void setBoardHasWiningLowDiagnoals() {
		board.updatePlayer(PlayerState.CROSS);
		board.getCells()[0][2].setValue(PlayerState.CROSS);
		board.getCells()[1][3].setValue(PlayerState.CROSS);
		board.getCells()[2][4].setValue(PlayerState.CROSS);
		board.getCells()[3][5].setValue(PlayerState.CROSS);		
		//return null;
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#setBoardHasWiningHighDiagnoals()
	 * Create a winning high diagonal row for {@link IBoard} </br>
	 * Expecting method {@link IBoard#isWin()} return true</br>
	 */
	@Override
	public void setBoardHasWiningHighDiagnoals() {
		board.updatePlayer(PlayerState.CROSS);
		board.getCells()[6][2].setValue(PlayerState.CROSS);
		board.getCells()[5][3].setValue(PlayerState.CROSS);
		board.getCells()[4][4].setValue(PlayerState.CROSS);
		board.getCells()[3][5].setValue(PlayerState.CROSS);
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#testUpdate()
	 * @see {@link IBoard#update(Cell)} </br>
	 * Expecting the value of {@link Cell} is current player state and the value of current player will be changed</br>
	 * after calling {@link IBoard#update(Cell)} </br>
	 */
	@Override
	@Test
	public void testUpdate(){
		Cell cell = Mockito.mock(Cell.class);
		Mockito.when(cell.getRow()).thenReturn(0);
		Mockito.when(cell.getCol()).thenReturn(0);
		PlayerState currentState = board.getPlayerState();
		board.update(cell);
		assertEquals(currentState, board.getCellValue(6, 0).getValue());
	}
	/**
	 * @see {@link IBoard#isEmptyCell(int, int)} <br>
	 */
	@Test
	public void testIsCellEmpty(){
		assertTrue(board.isEmptyCell(1, 1));
		board.updateCell(new Cell(1,1));
		assertTrue(board.isEmptyCell(1, 1));
	}
	
}
