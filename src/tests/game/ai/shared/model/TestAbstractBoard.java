package tests.game.ai.shared.model;

import static org.junit.Assert.*;

import java.awt.Graphics2D;

import game.ai.shared.model.Cell;
import game.ai.shared.model.PlayerState;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.model.GameState;
import game.ai.shared.view.specification.IGameView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
/**
 * This is an abstract test class that defines some common testing method shared by </br> 
 * TestConnect4Board and {@link TestTictactoeBoard} by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem
 * @version 1.0
 *
 */
public abstract class TestAbstractBoard {
	
	IBoard board;
	int rows = 0;
	int cols = 0;
	int connector = 0;
	/**
	 * Return default number of rows of specific implementation of {@link IBoard} </br>
	 * @return {@link Integer} </br>
	 */
	public abstract int getDefaultRows();
	/**
	 * Return the default number of cols of specific implementation of {@link IBoard} </br>
	 * @return {@link Integer} </br>
	 */
	public abstract int getDefaultCols();
	/**
	 * Return the default connector value of specific implementation of {@link IBoard} </br>
	 * @return {@link Integer} </br>
	 */
	public abstract int getDefaultConnector();
	/**
	 * Return a default constructor of specific implementation of {@link IBoard} </br>
	 * @return {@link IBoard} </br>
	 */
	public abstract IBoard getDefaultConstructor();
	/**
	 * Return a default constructor of specific implementation of {@link IBoard} </br>
	 * But the number of rows of {@link IBoard} is negative </br>
	 * @return {@link IBoard} </br>
	 */
	public abstract IBoard getConstructorHasNegativeRows();
	/**
	 * Return a default constructor of specific implementation of {@link IBoard} </br>
	 * But the number of columns of {@link IBoard} is negative </br>
	 * @return {@link IBoard} </br>
	 */
	public abstract IBoard getConstructorHasNegativeCols();
	/**
	 * Return a default constructor of specific implementation of {@link IBoard} </br>
	 * But the connecttor value of {@link IBoard} is negative </br>
	 * @return {@link IBoard} </br>
	 */
	public abstract IBoard getConstructorHasNegativeConnector();
	/**
	 * Update the state of {@link IBoard} so that it has a winning row </br>
	 */
	public abstract void setBoardHasWiningRows();
	/**
	 * Update the state of {@link IBoard} so that it has a winning column </br>
	 */
	public abstract void setBoardHasWiningCols();
	/**
	 * Update the state of {@link IBoard} so that it has a winning low diagonal </br>
	 */
	public abstract void setBoardHasWiningLowDiagnoals();
	/**
	 * Update the state of {@link IBoard} so that it has a winning high diagonal </br>
	 */
	public abstract void setBoardHasWiningHighDiagnoals();	
	@Before
	public void setUp() throws Exception {
		rows = getDefaultRows();
		cols = getDefaultCols();
		connector = getDefaultConnector();
		board = getDefaultConstructor();
	}

	@After
	public void tearDown() throws Exception {
		board = null;
	}
	/**
	 * Verify the initial status of {@link IBoard} </br>
	 * @see {@link IBoard#invariant()} </br>
	 * @see {@link IBoard#getCols()} </br>
	 * @see {@link IBoard#getRows()} </br>
	 * @see {@link IBoard#getConnector()()} </br>
	 * Arrays of {@link Cell} are {@link PlayerState#EMPTY} </br>
	 * @see {@link Cell#getValue()}
	 */
	@Test
	public void testConstructor() {

		assertTrue(board.invariant());
		assertEquals(rows, board.getRows());
		assertEquals(cols, board.getCols());
		assertEquals(connector, board.getConnector());
		assertEquals(GameState.PLAYING, board.getGameState());
		Cell[][] cells = board.getCells();
		for(int i=0; i<rows; i++){
			for (int j=0; j<cols; j++){
				assertEquals(PlayerState.EMPTY, cells[i][j].getValue());
			}//for
		}//for
	}
	@Test
	public void testConstructorHasNegativeRows() {
		board = getConstructorHasNegativeRows();
		assertTrue(board.invariant());
		assertEquals(rows, board.getRows());
		assertEquals(cols, board.getCols());
		assertEquals(connector, board.getConnector());
		assertEquals(GameState.PLAYING, board.getGameState());
		Cell[][] cells = board.getCells();
		for(int i=0; i<rows; i++){
			for (int j=0; j<cols; j++){
				assertEquals(PlayerState.EMPTY, cells[i][j].getValue());
			}//for
		}//for
	}
	@Test
	public void testConstructorHasNegativeCols() {
		board = getConstructorHasNegativeCols();
		assertTrue(board.invariant());
		assertEquals(rows, board.getRows());
		assertEquals(cols, board.getCols());
		assertEquals(connector, board.getConnector());
		assertEquals(GameState.PLAYING, board.getGameState());
		Cell[][] cells = board.getCells();
		for(int i=0; i<rows; i++){
			for (int j=0; j<cols; j++){
				assertEquals(PlayerState.EMPTY, cells[i][j].getValue());
			}//for
		}//for
	}
	@Test
	public void testConstructorHasNegativeConnectors() {
		board = getConstructorHasNegativeConnector();
		assertTrue(board.invariant());
		assertEquals(rows, board.getRows());
		assertEquals(cols, board.getCols());
		assertEquals(connector, board.getConnector());
		assertEquals(GameState.PLAYING, board.getGameState());
		Cell[][] cells = board.getCells();
		for(int i=0; i<rows; i++){
			for (int j=0; j<cols; j++){
				assertEquals(PlayerState.EMPTY, cells[i][j].getValue());
			}//for
		}//for
	}
	/**
	 * Expecting method {@link IBoard#getCenter()} returns a {@link Cell} </br>
	 */
	@Test
	public void testGetCenter(){
		assertNotNull(board.getCenter());
	}
	/**
	 * @see {@link IBoard#getPlayerState()} </br>
	 * @see {@link IBoard#getOpponentState()} </br>
	 * Expecting {@link IBoard#getPlayerState()} returns a {@link PlayerState} value </br>
	 * Expecting {@link IBoard#getOpponentState()} returns a {@link PlayerState} value </br>
	 * Expecting these two values are different </br>
	 */
	@Test
	public void testPlayerState(){
		board.updatePlayer(PlayerState.CROSS);
		assertEquals(PlayerState.CROSS, board.getPlayerState());
		assertEquals(PlayerState.NOUGHT, board.getOpponentState());
	}
	/**
	 * Create a {@link Cell} with {@link PlayerState#CROSS} value </br>
	 * Expecting value of {@link Cell} is {@link PlayerState#EMPTY} after calling {@link IBoard#clearCell(Cell)} </br>
	 */
	@Test
	public void testClearCell(){
		Cell cell = new Cell(1, 2, PlayerState.CROSS);
		board.clearCell(cell);
		assertEquals(PlayerState.EMPTY, board.getCellValue(1, 2).getValue());
	}
	/**
	 * Get a {@link Cell} at the negative row position </br>
	 * Expecting {@link IBoard#getCells()} returns null </br>
	 */
	@Test 
	public void testGetCellValue_1(){
		int x = -1;
		int y = cols -1;
		assertNull(board.getCellValue(x, y));
	}
	/**
	 * Get a {@link Cell} at the negative row position </br>
	 * Expecting {@link IBoard#getCells()} returns null </br>
	 */
	@Test 
	public void testGetCellValue_2(){
		int x = rows -1;
		int y = -1;
		assertNull(board.getCellValue(x, y));
	}
	/**
	 * Get a {@link Cell} at the illegal row position </br>
	 * Expecting {@link IBoard#getCells()} returns null </br>
	 */
	@Test 
	public void testGetCellValue_3(){
		int x = rows;
		int y = cols -1;
		assertNull(board.getCellValue(x, y));
	}	
	/**
	 * Get a {@link Cell} at the negative col position </br>
	 * Expecting {@link IBoard#getCells()} returns null </br>
	 */
	@Test 
	public void testGetCellValue_4(){
		int x = rows -1;
		int y = cols;
		assertNull(board.getCellValue(x, y));
	}	
	/**
	 * Get a {@link Cell} at the leagal row position </br>
	 * Expecting {@link IBoard#getCells()} returns a {@link Cell} </br>
	 */
	@Test 
	public void testGetCellValue_5(){
		int x = 0;
		int y = 0;
		assertNotNull(board.getCellValue(x, y));
	}
	/**
	 * @see {@link IBoard#getCorners()} </br>
	 * Expecting {@link IBoard#getCorners()} returns arrays of 4 elements </br>
	 */
	@Test
	public void testGetCorners(){
		//board = getDefaultConstructor();
		Cell[] cells = board.getCorners();
		assertEquals(4, cells.length);
	}
	/**
	 * @see {@link IBoard#getOpenSpaces()} </br>
	 */
	@Test
	public void testGetOpenSpaces(){
		Cell[] cells = board.getOpenSpaces();
		assertEquals(rows*cols, cells.length);
		Cell cell = Mockito.mock(Cell.class);
		Mockito.when(cell.getRow()).thenReturn(0);
		Mockito.when(cell.getCol()).thenReturn(0);
		board.updateCell(cell);
		assertEquals(rows*cols -1 , board.getOpenSpaces().length);
	}
	/**
	 * @see {@link IBoard#changePlayerState()} </br>
	 * Keeping the current state by calling {@link IBoard#getPlayerState()} </br>
	 * Expecting the current state will differ from the previous one after calling {@link IBoard#changePlayerState()} </br>
	 */
	@Test
	public void testChangePlayerState(){
		PlayerState currentState = board.getPlayerState();
		board.updatePlayer(board.getOpponentState());
		assertNotSame(currentState, board.getPlayerState());
	}
	/**
	 * @see {@link IBoard#update(Cell)} </br>
	 * Expecting the value of {@link Cell} is current player state and the value of current player will be changed</br>
	 * after calling {@link IBoard#update(Cell)} </br>
	 */
	@Test
	public void testUpdate(){
		Cell cell = Mockito.mock(Cell.class);
		Mockito.when(cell.getRow()).thenReturn(0);
		Mockito.when(cell.getCol()).thenReturn(0);
		PlayerState currentState = board.getPlayerState();
		board.update(cell);
		assertEquals(currentState, board.getCellValue(0, 0).getValue());
	}
	/**
	 * @see {@link IBoard#isDraw()} </br>
	 * @see also {@link IBoard#getCells()} </br>
	 * @see also {@link Cell#setValue(PlayerState)} </br>
	 */
	@Test
	public void testIsDraw_ReturnTrue(){
		for(int i=0; i<board.getRows(); i++){
			for (int j=0; j<board.getCols(); j++){
				board.getCells()[i][j].setValue(PlayerState.NOUGHT);
			}
		}
		assertTrue(board.isDraw());
	}
	/**
	 * @see {@link IBoard#isDraw()} </br>
	 * @see also {@link IBoard#getCells()} </br>
	 * @see also {@link Cell#setValue(PlayerState)} </br>
	 */
	@Test
	public void testIsDraw_ReturnFalse(){
		for(int i=1; i<board.getRows(); i++){
			for (int j=1; j<board.getCols(); j++){
				board.getCells()[i][j].setValue(PlayerState.NOUGHT);
			}
		}
		assertFalse(board.isDraw());
	}	
	/**
	 * @see {@link TestAbstractBoard#setBoardHasWiningRows()} </br>
	 * Expecting method {@link IBoard#checkRows()} return true </br>
	 */
	@Test
	public void testCheckHasWinRow(){
		assertFalse(board.checkRows());
		setBoardHasWiningRows();
		assertTrue(board.checkRows());
	}
	/**
	 * @see {@link TestAbstractBoard#setBoardHasWiningCols()} </br>
	 * Expecting method {@link IBoard#checkCols()} returns true </br>
	 */
	@Test
	public void testCheckHasWinCol(){
		assertFalse(board.checkCols());
		setBoardHasWiningCols();
		assertTrue(board.checkCols());
	}
	/**
	 * @see {@link TestAbstractBoard#setBoardHasWiningLowDiagnoals()} </br>
	 * Expecting method {@link IBoard#checkLowDiagonals()} returns true </br>
	 */
	@Test
	public void testCheckHasLowDiagonalRow(){
		assertFalse(board.checkLowDiagonals());
		setBoardHasWiningLowDiagnoals();
		assertTrue(board.checkLowDiagonals());
	}	
	/**
	 * @see {@link TestAbstractBoard#setBoardHasWiningHighDiagnoals()} </br>
	 * Expecting method {@link IBoard#checkHighDiagonals()} returns true </br>
	 */
	@Test
	public void testCheckHasHighDiagonalRow(){
		assertFalse(board.checkHighDiagonals());
		setBoardHasWiningHighDiagnoals();
		assertTrue(board.checkHighDiagonals());
	}
	/**
	 * 
	 * Mock method {@link IBoard#checkRows()} returns true</br>
	 * Mock method {@link IBoard#checkCols()} returns false</br>
	 * Mock method {@link IBoard#checkLowDiagonals()} returns false</br>
	 * Mock {@link IBoard#checkHighDiagonals()} returns false</br>
	 * Expecting {@link IBoard#isWin()} returns true</br>
	 */
	@Test
	public void testIsWin1(){
		IBoard spy = Mockito.spy(board);
		Mockito.when(spy.checkRows()).thenReturn(true);
		Mockito.when(spy.checkCols()).thenReturn(false);
		Mockito.when(spy.checkLowDiagonals()).thenReturn(false);
		Mockito.when(spy.checkHighDiagonals()).thenReturn(false);
		assertTrue(spy.isWin());
	}
	/**
	 * 
	 * Mock method {@link IBoard#checkRows()} returns false</br>
	 * Mock method {@link IBoard#checkCols()} returns true</br>
	 * Mock method {@link IBoard#checkLowDiagonals()} returns false</br>
	 * Mock {@link IBoard#checkHighDiagonals()} returns false</br>
	 * Expecting {@link IBoard#isWin()} returns true</br>
	 */
	@Test
	public void testIsWin2(){
		IBoard spy = Mockito.spy(board);
		Mockito.when(spy.checkRows()).thenReturn(false);
		Mockito.when(spy.checkCols()).thenReturn(true);
		Mockito.when(spy.checkLowDiagonals()).thenReturn(false);
		Mockito.when(spy.checkHighDiagonals()).thenReturn(false);
		assertTrue(spy.isWin());
	}
	/**
	 * 
	 * Mock method {@link IBoard#checkRows()} returns false</br>
	 * Mock method {@link IBoard#checkCols()} returns false</br>
	 * Mock method {@link IBoard#checkLowDiagonals()} returns true</br>
	 * Mock {@link IBoard#checkHighDiagonals()} returns false</br>
	 * Expecting {@link IBoard#isWin()} returns true</br>
	 */
	@Test
	public void testIsWin3(){
		IBoard spy = Mockito.spy(board);
		Mockito.when(spy.checkRows()).thenReturn(false);
		Mockito.when(spy.checkCols()).thenReturn(false);
		Mockito.when(spy.checkLowDiagonals()).thenReturn(true);
		Mockito.when(spy.checkHighDiagonals()).thenReturn(false);
		assertTrue(spy.isWin());
	}	
	/**
	 * 
	 * Mock method {@link IBoard#checkRows()} returns false</br>
	 * Mock method {@link IBoard#checkCols()} returns false</br>
	 * Mock method {@link IBoard#checkLowDiagonals()} returns false</br>
	 * Mock {@link IBoard#checkHighDiagonals()} returns true</br>
	 * Expecting {@link IBoard#isWin()} returns true</br>
	 */
	@Test
	public void testIsWin4(){
		IBoard spy = Mockito.spy(board);
		Mockito.when(spy.checkRows()).thenReturn(false);
		Mockito.when(spy.checkCols()).thenReturn(true);
		Mockito.when(spy.checkLowDiagonals()).thenReturn(false);
		Mockito.when(spy.checkHighDiagonals()).thenReturn(false);
		assertTrue(spy.isWin());
	}
	/**
	 * 
	 * Mock method {@link IBoard#checkRows()} returns false</br>
	 * Mock method {@link IBoard#checkCols()} returns false</br>
	 * Mock method {@link IBoard#checkLowDiagonals()} returns false</br>
	 * Mock {@link IBoard#checkHighDiagonals()} returns false</br>
	 * Expecting {@link IBoard#isWin()} returns false</br>
	 */
	@Test
	public void testIsWin5(){
		IBoard spy = Mockito.spy(board);
		Mockito.when(spy.checkRows()).thenReturn(false);
		Mockito.when(spy.checkCols()).thenReturn(false);
		Mockito.when(spy.checkLowDiagonals()).thenReturn(false);
		Mockito.when(spy.checkHighDiagonals()).thenReturn(false);
		assertFalse(spy.isWin());
	}
	/**
	 * @see {@link IBoard#updateView(IGameView)} </br>
	 */
	@Test
	public void testUpdateView(){
		IGameView view = Mockito.mock(IGameView.class);
		Mockito.doNothing().when(view).display();
		board.updateView(view);
	}
	/**
	 * Mock method {@link IBoard#isDraw()} returns true </br>
	 * Mock method {@link IBoard#isWin()} returns false </br>
	 * Expecting method {@link IBoard#isGameOver()} returns true </br>
	 */
	@Test
	public void testIsGameOver1(){
		IBoard spy = Mockito.spy(board);
		Mockito.when(spy.isDraw()).thenReturn(true);
		Mockito.when(spy.isWin()).thenReturn(false);
		assertTrue(spy.isGameOver());
	}
	/**
	 * Mock method {@link IBoard#isDraw()} returns false </br>
	 * Mock method {@link IBoard#isWin()} returns true </br>
	 * Expecting method {@link IBoard#isGameOver()} returns true </br>
	 */
	@Test
	public void testIsGameOver2(){
		IBoard spy = Mockito.spy(board);
		Mockito.when(spy.isDraw()).thenReturn(false);
		Mockito.when(spy.isWin()).thenReturn(true);
		assertTrue(spy.isGameOver());
	}
	/**
	 * Mock method {@link IBoard#isDraw()} returns false </br>
	 * Mock method {@link IBoard#isWin()} returns false </br>
	 * Expecting method {@link IBoard#isGameOver()} returns false </br>
	 */
	@Test
	public void testIsGameOver3(){
		IBoard spy = Mockito.spy(board);
		Mockito.when(spy.isDraw()).thenReturn(false);
		Mockito.when(spy.isWin()).thenReturn(false);
		assertFalse(spy.isGameOver());
	}
	/**
	 * Mock method {@link IBoard#isWin()} returns true </br>
	 * Expecting {@link IBoard#getGameState()} does not return {@link GameState#DRAW} or {@link GameState#PLAYING} </br>
	 */
	@Test
	public void testGameState1(){
		IBoard spy = Mockito.spy(board);
		Mockito.when(spy.isWin()).thenReturn(true);
		assertNotSame(GameState.DRAW, spy.getGameState());
		assertNotSame(GameState.PLAYING, spy.getGameState());
	}
	/**
	 * Mock method {@link IBoard#isWin()} returns false </br>
	 * Mock method {@link IBoard#isDraw()} returns true </br>
	 * Expecting {@link IBoard#getGameState()} returns {@link GameState#DRAW}</br>
	 */
	@Test
	public void testGameState2(){
		IBoard spy = Mockito.spy(board);
		Mockito.when(spy.isWin()).thenReturn(false);
		Mockito.when(spy.isDraw()).thenReturn(true);
		assertSame(GameState.DRAW, spy.getGameState());
	}
	/**
	 * Mock method {@link IBoard#isWin()} returns false </br>
	 * Mock method {@link IBoard#isDraw()} returns false </br>
	 * Expecting {@link IBoard#getGameState()} returns {@link GameState#PLAYING}</br>
	 */
	@Test
	public void testGameState3(){
		IBoard spy = Mockito.spy(board);
		Mockito.when(spy.isWin()).thenReturn(false);
		Mockito.when(spy.isDraw()).thenReturn(false);
		assertSame(GameState.PLAYING, spy.getGameState());
	}	
	/**
	 * Mock {@link IBoard#checkLowDiagonals()} returns true </br>
	 * Mock {@link IBoard#checkHighDiagonals()} returns false </br>
	 * Expecting {@link IBoard#checkDiagonals()} returns true </br>
	 */
	@Test
	public void testCheckDiagonals1(){
		IBoard spy = Mockito.spy(board);
		Mockito.when(spy.checkLowDiagonals()).thenReturn(true);
		Mockito.when(spy.checkHighDiagonals()).thenReturn(false);
		assertTrue(spy.checkDiagonals());
	}
	/**
	 * Mock {@link IBoard#checkLowDiagonals()} returns false </br>
	 * Mock {@link IBoard#checkHighDiagonals()} returns true </br>
	 * Expecting {@link IBoard#checkDiagonals()} returns true </br>
	 */
	@Test
	public void testCheckDiagonals2(){
		IBoard spy = Mockito.spy(board);
		Mockito.when(spy.checkLowDiagonals()).thenReturn(false);
		Mockito.when(spy.checkHighDiagonals()).thenReturn(true);
		assertTrue(spy.checkDiagonals());
	}	
	/**
	 * Mock {@link IBoard#checkLowDiagonals()} returns false </br>
	 * Mock {@link IBoard#checkHighDiagonals()} returns false </br>
	 * Expecting {@link IBoard#checkDiagonals()} returns false </br>
	 */
	@Test
	public void testCheckDiagonals3(){
		IBoard spy = Mockito.spy(board);
		Mockito.when(spy.checkLowDiagonals()).thenReturn(false);
		Mockito.when(spy.checkHighDiagonals()).thenReturn(false);
		assertFalse(spy.checkDiagonals());
	}
	/**
	 * @see {@link IBoard#paint(java.awt.Graphics)} </br>
	 */
	@Test
	public void testPaint(){
		Graphics2D graphic = Mockito.mock(Graphics2D.class);
		board.paint(graphic);
	}
	/**
	 * @see {@link IBoard#toCopy()} </br>
	 * @see also {@link IBoard#getCols()} </br>
	 * @see also {@link IBoard#getRows()} </br>
	 * @see also {@link IBoard#getPlayerState()} </br>
	 */
	@Test
	public void testToCopy(){
		IBoard board = getDefaultConstructor();
		IBoard copy = board.toCopy();
		assertEquals(copy.getCols(), board.getCols());
		assertEquals(copy.getRows(), board.getRows());
		assertEquals(copy.getPlayerState(), board.getPlayerState());
	}
	/**
	 * @see {@link IBoard#clear()} </br>
	 * @see {@link IBoard#getGameState()} </br>
	 */
	@Test
	public void testClear(){
		IBoard board = getDefaultConstructor();
		board.clear();
		assertEquals(GameState.PLAYING, board.getGameState());
	}

}
