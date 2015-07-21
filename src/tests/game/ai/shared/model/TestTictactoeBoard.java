package tests.game.ai.shared.model;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import game.ai.shared.model.Cell;
import game.ai.shared.model.specification.IBoard;
import game.ai.tictactoe.model.TictactoeBoard;
import game.ai.shared.model.PlayerState;

import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
/**
 * This test for {@link IBoard}, {@link AbstractBoard} and {@link TictactoeBoard} by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem
 * @version 1.0
 */

public class TestTictactoeBoard extends TestAbstractBoard{
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#getDefaultRows()
	 */
	@Override
	public int getDefaultRows() {
		
		return 3;
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#getDefaultCols()
	 */
	@Override
	public int getDefaultCols() {
		
		return 3;
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#getDefaultConnector()
	 */
	@Override
	public int getDefaultConnector() {
		
		return 3;
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#getDefaultConstructor() </br>
	 * @see {@link TictactoeBoard#TictactoeBoard(Integer, Integer, Integer)} </br>
	 * 
	 */
	@Override
	public IBoard getDefaultConstructor() {
		return new TictactoeBoard(rows, cols, connector);
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#getConstructorHasNegativeRows() </br>
	 * @see {@link TictactoeBoard#TictactoeBoard(Integer, Integer, Integer)} </br>
	 */
	@Override
	public IBoard getConstructorHasNegativeRows() {
		return new TictactoeBoard(-1, cols, connector);
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#getConstructorHasNegativeCols() </br>
	 * @see {@link TictactoeBoard#TictactoeBoard(Integer, Integer, Integer)} </br>
	 */
	@Override
	public IBoard getConstructorHasNegativeCols() {
		
		return new TictactoeBoard(rows, -1, connector);
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.model.TestAbstractBoard#getConstructorHasNegativeConnector() </br>
	 * @see {@link TictactoeBoard#TictactoeBoard(Integer, Integer, Integer)} </br>
	 */
	@Override
	public IBoard getConstructorHasNegativeConnector() {
		
		return new TictactoeBoard(rows, cols, -1);
	}
	/**
	 * Case 1: rows < connector and cols >= connector </br>
	 * For this case, {@link IBoard#invariant()} returns false </br>
	 * Expecting constructor will throw an {@link IllegalArgumentException} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testNotSafeConstructor_1() {
		board = new TictactoeBoard(3, 4, 4);
	}
	/**
	 * 
	 * Case 1: rows >= connector and cols < connetor </br>
	 * For this case, {@link IBoard#invariant()} returns false </br>
	 * Expecting constructor will throw an {@link IllegalArgumentException} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testNotSafeConstructor_2() {
		board = new TictactoeBoard(4, 3, 4);
	}
	/**
	 * Case 1: rows < connetor and cols < connetor </br>
	 * For this case, {@link IBoard#invariant()} returns false </br>
	 * Expecting constructor will throw an {@link IllegalArgumentException} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testNotSafeConstructor_3() {
		board = new TictactoeBoard(3, 3, 4);
	}
	/**
	 * Create a winning row for {@link IBoard} </br>
	 * Expecting method {@link IBoard#isWin()} return true</br>
	 */
	@Override
	public void setBoardHasWiningRows() {
		board.getCells()[0][0].setValue(board.getPlayerState());
		board.getCells()[0][1].setValue(board.getPlayerState());
		board.getCells()[0][2].setValue(board.getPlayerState());
		//return null;
	}
	/**
	 * Create a winning column for {@link IBoard} </br>
	 * Expecting method {@link IBoard#isWin()} return true</br>
	 */
	@Override
	public void setBoardHasWiningCols() {
		
		board.getCells()[0][0].setValue(board.getPlayerState());
		board.getCells()[1][0].setValue(board.getPlayerState());
		board.getCells()[2][0].setValue(board.getPlayerState());
	}
	/**
	 * Create a winning low Diagonal row for {@link IBoard} </br>
	 * Expecting method {@link IBoard#isWin()} return true</br>
	 */
	@Override
	public void setBoardHasWiningLowDiagnoals() {
		
		board.getCells()[0][0].setValue(board.getPlayerState());
		board.getCells()[1][1].setValue(board.getPlayerState());
		board.getCells()[2][2].setValue(board.getPlayerState());
	}
	/**
	 * Create a winning high diagonal row for {@link IBoard} </br>
	 * Expecting method {@link IBoard#isWin()} return true</br>
	 */
	@Override
	public void setBoardHasWiningHighDiagnoals() {
		
		board.getCells()[2][0].setValue(board.getPlayerState());
		board.getCells()[1][1].setValue(board.getPlayerState());
		board.getCells()[0][2].setValue(board.getPlayerState());
	}
	/**
	 * @see {@link IBoard#isEmptyCell(int, int)}
	 */
	@Test
	public void testIsCellEmpty(){
		board.updatePlayer(PlayerState.CROSS);
		assertTrue(board.isEmptyCell(1, 1));
		board.updateCell(new Cell(1,1));
		assertFalse(board.isEmptyCell(1, 1));
	}
}
