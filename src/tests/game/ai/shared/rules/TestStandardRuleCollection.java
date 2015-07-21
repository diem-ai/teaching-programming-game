package tests.game.ai.shared.rules;

import static org.junit.Assert.*;
import game.ai.shared.model.Cell;
import game.ai.shared.model.PlayerState;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.rules.RulesFactory;
import game.ai.shared.rules.specification.ICommand;
import game.ai.shared.rules.specification.IRuleCollection;

import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
/**
 * This test for {@link IRuleCollection} and {@link RulesFactory} by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem
 * @version 1.0
 */
public class TestStandardRuleCollection {

	@Test
	public void testConstructor(){
		assertNotSame(0, RulesFactory.getInstance().getRules().size());
	}
	/**
	 * @see {@link RulesFactory#hasConditionRule(String)} </br>
	 */
	@Test
	public void testHasCondition(){
		String condition = "inline_cell_is_free";
		assertTrue(RulesFactory.getInstance().hasConditionRule(condition));
	}
	/**
	 * @see {@link RulesFactory#hasStrategy(String, String)} </br>
	 */
	@Test
	public void testHasStrategy(){
		String condition = "inline_cell_is_free";
		String strategy = "take_inline_cell";
		assertTrue(RulesFactory.getInstance().hasStrategy(condition, strategy));
	}
	/**
	 * @see {@link RulesFactory#getStategy(String)} </br>
	 */
	@Test
	public void testGetStrategy(){
		String condition = "inline_cell_is_free";
		String strategy = "take_inline_cell";
		assertEquals(strategy, RulesFactory.getInstance().getStategy(condition));
	}

	/**
	 * Case 1 : {@link IBoard#getOpenSpaces()} returns null </br>
	 */
	@Test
	public void testPlayerCanWin1(){
		IBoard board = Mockito.mock(IBoard.class);
		IBoard copy = Mockito.mock(IBoard.class);
		Mockito.when(board.toCopy()).thenReturn(copy);
		Mockito.when(copy.getOpenSpaces()).thenReturn(null);
		assertNull(RulesFactory.getInstance().player_can_win(board));
	}

	/**
	 * Case 2 : {@link IBoard#getOpenSpaces()} returns array of {@link Cell} </br>
	 * {@link IBoard#isWin()} returns true </br>
	 */
	@Test
	public void testPlayerCanWin2(){
		IBoard board = Mockito.mock(IBoard.class);
		IBoard copy = Mockito.mock(IBoard.class);
		Mockito.when(board.toCopy()).thenReturn(copy);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell2 = Mockito.mock(Cell.class);
		Mockito.when(cell2.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell [] cells = new Cell[]{cell1, cell2};		
		Mockito.when(copy.getOpenSpaces()).thenReturn(cells);
		Mockito.doNothing().when(copy).update(cells[0]);
		Mockito.when(copy.isWin()).thenReturn(true);
		assertEquals(cells[0], RulesFactory.getInstance().player_can_win(board));
	}
	/**
	 * Case 3:  {@link IBoard#getOpenSpaces()} returns array of {@link Cell} </br>
	 * {@link IBoard#isWin()} always returns false </br>
	 */
	@Test
	public void testPlayerCanWin3(){
		
		IBoard board = Mockito.mock(IBoard.class);
		IBoard copy = Mockito.mock(IBoard.class);
		Mockito.when(board.toCopy()).thenReturn(copy);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell2 = Mockito.mock(Cell.class);
		Mockito.when(cell2.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell [] cells = new Cell[]{cell1, cell2};		
		Mockito.when(copy.getOpenSpaces()).thenReturn(cells);
		Mockito.doNothing().when(copy).update(cells[0]);
		Mockito.doNothing().when(copy).update(cells[1]);
		Mockito.when(copy.isWin()).thenReturn(false);
		assertNull(RulesFactory.getInstance().player_can_win(board));
		
	}
	/**
	 * Case 1 : {@link IBoard#getOpenSpaces()} returns null </br>
	 */
	@Test
	public void testOpponentCanWin1(){
		
		IBoard board = Mockito.mock(IBoard.class);
		IBoard copy = Mockito.mock(IBoard.class);
		Mockito.when(board.toCopy()).thenReturn(copy);
		Mockito.when(copy.getOpenSpaces()).thenReturn(null);
		assertNull(RulesFactory.getInstance().opponent_can_win(board));
	}
	/**
	 * Case 2 : {@link IBoard#getOpenSpaces()} returns array of {@link Cell} </br>
	 * {@link IBoard#isWin()} returns true </br>
	 */	
	@Test
	public void testOpponentCanWin2(){
		
		IBoard board = Mockito.mock(IBoard.class);
		IBoard copy = Mockito.mock(IBoard.class);
		Mockito.when(board.toCopy()).thenReturn(copy);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell2 = Mockito.mock(Cell.class);
		Mockito.when(cell2.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell [] cells = new Cell[]{cell1, cell2};		
		Mockito.when(copy.getOpenSpaces()).thenReturn(cells);
		Mockito.doNothing().when(copy).update(cells[0]);
		Mockito.when(copy.isWin()).thenReturn(true);
		assertEquals(cells[0], RulesFactory.getInstance().opponent_can_win(board));
	}
	/**
	 * Case 3:  {@link IBoard#getOpenSpaces()} returns array of {@link Cell} </br>
	 * {@link IBoard#isWin()} always returns false </br>
	 */
	@Test
	public void testOpponentCanWin3(){
		
		IBoard board = Mockito.mock(IBoard.class);
		IBoard copy = Mockito.mock(IBoard.class);
		Mockito.when(board.toCopy()).thenReturn(copy);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell2 = Mockito.mock(Cell.class);
		Mockito.when(cell2.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell [] cells = new Cell[]{cell1, cell2};		
		Mockito.when(copy.getOpenSpaces()).thenReturn(cells);
		Mockito.doNothing().when(copy).update(cells[0]);
		Mockito.doNothing().when(copy).update(cells[1]);
		Mockito.when(copy.isWin()).thenReturn(false);
		assertNull(RulesFactory.getInstance().opponent_can_win(board));
		
	}
	/**
	 * Case 1: {@link IBoard#getCenter()} returns a {@link Cell} having {@link PlayerState#CROSS} </br>
	 * Expecting method {@link RulesFactory#center_is_free(IBoard)} returns null </br>
	 */
	@Test
	public void testCenterIsFree1(){
		
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell = Mockito.mock(Cell.class);
		Mockito.when(cell.getValue()).thenReturn(PlayerState.CROSS);		
		Mockito.when(board.getCenter()).thenReturn(cell);
		assertNull(RulesFactory.getInstance().center_is_free(board));
	}
	/**
	 * Case 1: {@link IBoard#getCenter()} returns a {@link Cell} having {@link PlayerState#EMPTY} </br>
	 * Expecting method {@link RulesFactory#center_is_free(IBoard)} returns a {@link Cell} </br>
	 */
	@Test
	public void testCenterIsFree2(){
		
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell = Mockito.mock(Cell.class);
		Mockito.when(cell.getValue()).thenReturn(PlayerState.EMPTY);
		Mockito.when(board.getCenter()).thenReturn(cell);
		assertEquals(cell, RulesFactory.getInstance().center_is_free(board));
	}	
	/**
	 * Case 1: {@link IBoard#getCorners()} returns null </br>
	 * Expecting method {@link RulesFactory#corner_is_free(IBoard)} return null </br>
	 */
	@Test
	public void testCornerIsFree1(){
		IBoard board = Mockito.mock(IBoard.class);
		Mockito.when(board.getCorners()).thenReturn(null);
		assertNull(RulesFactory.getInstance().corner_is_free(board));
	}
	/**
	 * Case 2: {@link IBoard#getCorners()} returns arrays of {@link Cell} </br>
	 * This arrays has no any empty {@link Cell} </br>
	 * Expecting method {@link RulesFactory#corner_is_free(IBoard)} return null </br>
	 */
	@Test
	public void testCornerIsFree2(){
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell2 = Mockito.mock(Cell.class);		
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell3 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell cell4 = Mockito.mock(Cell.class);		
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.NOUGHT);		
		Cell[] cells = new Cell[]{cell1, cell2, cell3, cell4};		
		Mockito.when(board.getCorners()).thenReturn(cells);
		assertNull(RulesFactory.getInstance().corner_is_free(board));
	}
	/**
	 * Case 3: {@link IBoard#getCorners()} returns arrays of {@link Cell} </br>
	 * One of {@link Cell} of this arrays is empty </br>
	 * Expecting method {@link RulesFactory#corner_is_free(IBoard)} return {@link Cell} </br>
	 */
	@Test
	public void testCornerIsFree3(){
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.EMPTY);
		Cell cell2 = Mockito.mock(Cell.class);		
		Mockito.when(cell2.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell3 = Mockito.mock(Cell.class);
		Mockito.when(cell3.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell cell4 = Mockito.mock(Cell.class);		
		Mockito.when(cell4.getValue()).thenReturn(PlayerState.NOUGHT);		
		Cell[] cells = new Cell[]{cell1, cell2, cell3, cell4};		
		Mockito.when(board.getCorners()).thenReturn(cells);
		assertEquals(cells[0],RulesFactory.getInstance().corner_is_free(board));
	}	
	/**
	 * Case 1: {@link IBoard#getCorners()} returns null </br>
	 * Expecting {@link RulesFactory#opponent_has_opposite_corner(IBoard)} return null </br>
	 */
	@Test
	public void testOpponentHasOppositeCorner1(){
		IBoard board = Mockito.mock(IBoard.class);
		Mockito.when(board.getCorners()).thenReturn(null);
		assertNull(RulesFactory.getInstance().opponent_has_opposite_corner(board));
	}
	/**
	 * Case 2: {@link IBoard#getCorners()} returns arrays of {@link Cell} </br>
	 * Mock {@link IBoard#getOpponentState()} returns {@link PlayerState#CROSS} </br>
	 * Cells[0] has {@link PlayerState#CROSS} and Cells[2] has {@link PlayerState#EMPTY} </br>
	 * Expecting {@link RulesFactory#opponent_has_opposite_corner(IBoard)} return Cells[2] </br>
	 */
	@Test
	public void testOpponentHasOppositeCorner2(){
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell2 = Mockito.mock(Cell.class);		
		Mockito.when(cell2.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell3 = Mockito.mock(Cell.class);
		Mockito.when(cell3.getValue()).thenReturn(PlayerState.EMPTY);
		Cell cell4 = Mockito.mock(Cell.class);		
		Mockito.when(cell4.getValue()).thenReturn(PlayerState.NOUGHT);		
		Cell[] cells = new Cell[]{cell1, cell2, cell3, cell4};		
		Mockito.when(board.getCorners()).thenReturn(cells);
		Mockito.when(board.getOpponentState()).thenReturn(PlayerState.CROSS);
		assertEquals(cells[2],RulesFactory.getInstance().opponent_has_opposite_corner(board));
	}	
	/**
	 * Case 3: {@link IBoard#getCorners()} returns arrays of {@link Cell} </br>
	 * Mock {@link IBoard#getOpponentState()} returns {@link PlayerState#CROSS} </br>
	 * Cells[0] has {@link PlayerState#EMPTY} value and Cells[2] has {@link PlayerState#CROSS} value </br>
	 * Expecting {@link RulesFactory#opponent_has_opposite_corner(IBoard)} return Cells[0] </br>
	 */
	@Test
	public void testOpponentHasOppositeCorner3(){
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.EMPTY);
		Cell cell2 = Mockito.mock(Cell.class);		
		Mockito.when(cell2.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell3 = Mockito.mock(Cell.class);
		Mockito.when(cell3.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell4 = Mockito.mock(Cell.class);		
		Mockito.when(cell4.getValue()).thenReturn(PlayerState.NOUGHT);		
		Cell[] cells = new Cell[]{cell1, cell2, cell3, cell4};		
		Mockito.when(board.getCorners()).thenReturn(cells);
		Mockito.when(board.getOpponentState()).thenReturn(PlayerState.CROSS);
		assertEquals(cells[0], RulesFactory.getInstance().opponent_has_opposite_corner(board));
	}
	/**
	 * Case 4: {@link IBoard#getCorners()} returns arrays of {@link Cell} </br>
	 * Mock {@link IBoard#getOpponentState()} returns {@link PlayerState#CROSS} </br>
	 * Cells[0] has {@link PlayerState#NOUGHT} value and Cells[2] has {@link PlayerState#EMPTY} </br>
	 * Expecting {@link RulesFactory#opponent_has_opposite_corner(IBoard)} return null </br>
	 */
	@Test
	public void testOpponentHasOppositeCorner4(){
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell cell2 = Mockito.mock(Cell.class);		
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell3 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.EMPTY);
		Cell cell4 = Mockito.mock(Cell.class);		
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.NOUGHT);		
		Cell[] cells = new Cell[]{cell1, cell2, cell3, cell4};		
		Mockito.when(board.getCorners()).thenReturn(cells);
		Mockito.when(board.getOpponentState()).thenReturn(PlayerState.CROSS);
		assertNull(RulesFactory.getInstance().opponent_has_opposite_corner(board));
	}
	/**
	 * Case 4: {@link IBoard#getCorners()} returns arrays of {@link Cell} </br>
	 * Mock {@link IBoard#getOpponentState()} returns {@link PlayerState#CROSS} </br>
	 * Cells[0] has {@link PlayerState#CROSS} value and Cells[2] has {@link PlayerState#NOUGHT} </br>
	 * Expecting {@link RulesFactory#opponent_has_opposite_corner(IBoard)} return null </br>
	 */
	@Test
	public void testOpponentHasOppositeCorner5(){
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell2 = Mockito.mock(Cell.class);		
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell3 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell cell4 = Mockito.mock(Cell.class);		
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.NOUGHT);		
		Cell[] cells = new Cell[]{cell1, cell2, cell3, cell4};		
		Mockito.when(board.getCorners()).thenReturn(cells);
		Mockito.when(board.getOpponentState()).thenReturn(PlayerState.CROSS);
		assertNull(RulesFactory.getInstance().opponent_has_opposite_corner(board));
	}	
	/**
	 * Case 2: {@link IBoard#getCorners()} returns arrays of {@link Cell} </br>
	 * Mock {@link IBoard#getOpponentState()} returns {@link PlayerState#CROSS} </br>
	 * Cells[1] has {@link PlayerState#CROSS} value and Cells[3] has {@link PlayerState#EMPTY} value </br> 
	 * Expecting {@link RulesFactory#opponent_has_opposite_corner(IBoard)} return Cells[3] </br>
	 */
	@Test
	public void testOpponentHasOppositeCorner6(){
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell cell2 = Mockito.mock(Cell.class);		
		Mockito.when(cell2.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell3 = Mockito.mock(Cell.class);
		Mockito.when(cell3.getValue()).thenReturn(PlayerState.EMPTY);
		Cell cell4 = Mockito.mock(Cell.class);		
		Mockito.when(cell4.getValue()).thenReturn(PlayerState.EMPTY);		
		Cell[] cells = new Cell[]{cell1, cell2, cell3, cell4};		
		Mockito.when(board.getCorners()).thenReturn(cells);
		Mockito.when(board.getOpponentState()).thenReturn(PlayerState.CROSS);
		assertEquals(cells[3],RulesFactory.getInstance().opponent_has_opposite_corner(board));
	}	
	/**
	 * Case 3: {@link IBoard#getCorners()} returns arrays of {@link Cell} </br>
	 * Mock {@link IBoard#getOpponentState()} returns {@link PlayerState#CROSS} </br>
	 * Cells[1] has {@link PlayerState#EMPTY} value and Cells[2] has {@link PlayerState#CROSS} </br>
	 * Expecting {@link RulesFactory#opponent_has_opposite_corner(IBoard)} return Cells[1] </br>
	 */
	@Test
	public void testOpponentHasOppositeCorner7(){
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell cell2 = Mockito.mock(Cell.class);		
		Mockito.when(cell2.getValue()).thenReturn(PlayerState.EMPTY);
		Cell cell3 = Mockito.mock(Cell.class);
		Mockito.when(cell3.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell4 = Mockito.mock(Cell.class);		
		Mockito.when(cell4.getValue()).thenReturn(PlayerState.CROSS);		
		Cell[] cells = new Cell[]{cell1, cell2, cell3, cell4};	
		Mockito.when(board.getCorners()).thenReturn(cells);
		Mockito.when(board.getOpponentState()).thenReturn(PlayerState.CROSS);
		assertEquals(cells[1], RulesFactory.getInstance().opponent_has_opposite_corner(board));
	}
	/**
	 * Case 8: {@link IBoard#getCorners()} returns arrays of {@link Cell} </br>
	 * Mock {@link IBoard#getOpponentState()} returns {@link PlayerState#CROSS} </br>
	 * Cells[1] has {@link PlayerState#NOUGHT} value and Cells[3] has {@link PlayerState#EMPTY} </br>
	 * Expecting {@link RulesFactory#opponent_has_opposite_corner(IBoard)} return null </br>
	 */
	@Test
	public void testOpponentHasOppositeCorner8(){
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell cell2 = Mockito.mock(Cell.class);		
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell cell3 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.EMPTY);
		Cell cell4 = Mockito.mock(Cell.class);		
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.EMPTY);
		Cell[] cells = new Cell[]{cell1, cell2, cell3, cell4};	
		Mockito.when(board.getCorners()).thenReturn(cells);
		Mockito.when(board.getOpponentState()).thenReturn(PlayerState.CROSS);
		assertNull(RulesFactory.getInstance().opponent_has_opposite_corner(board));
	}
	/**
	 * Case 9: {@link IBoard#getCorners()} returns arrays of {@link Cell} </br>
	 * Mock {@link IBoard#getOpponentState()} returns {@link PlayerState#CROSS} </br>
	 * Cells[1] has {@link PlayerState#CROSS} value and Cells[3] has {@link PlayerState#NOUGHT} </br>
	 * Expecting {@link RulesFactory#opponent_has_opposite_corner(IBoard)} return null </br>
	 */
	@Test
	public void testOpponentHasOppositeCorner9(){
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell2 = Mockito.mock(Cell.class);		
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell3 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell cell4 = Mockito.mock(Cell.class);		
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell[] cells = new Cell[]{cell1, cell2, cell3, cell4};		
		Mockito.when(board.getCorners()).thenReturn(cells);
		Mockito.when(board.getOpponentState()).thenReturn(PlayerState.CROSS);
		assertNull(RulesFactory.getInstance().opponent_has_opposite_corner(board));
	}	
	/**
	 * Case 1: {@link IBoard#getOpenSpaces()} returns null </br>
	 * Expecting {@link RulesFactory#inline_cell_is_free(IBoard)} returns null </br>
	 */
	@Test
	public void testInlineCellIsFree1(){
		
		IBoard board = Mockito.mock(IBoard.class);
		Mockito.when(board.getOpenSpaces()).thenReturn(null);
		assertNull(RulesFactory.getInstance().inline_cell_is_free(board));
	}
	/**
	 * Case 2: {@link IBoard#getOpenSpaces()} returns arrays of {@link Cell} </br>
	 * There is not any empty {@link Cell} in the array </br>
	 * Expecting {@link RulesFactory#inline_cell_is_free(IBoard)} returns null </br>
	 */
	@Test
	public void testInlineCellIsFree2(){
		
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.CROSS);
		Cell cell2 = Mockito.mock(Cell.class);		
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.NOUGHT);
		//Cell[] cells = new Cell[]{new Cell(PlayerState.CROSS), new Cell(PlayerState.NOUGHT)};
		Cell[] cells = new Cell[]{cell1, cell2};
		Mockito.when(board.getOpenSpaces()).thenReturn(cells);
		assertNull(RulesFactory.getInstance().inline_cell_is_free(board));
	}
	/**
	 * Case 3: {@link IBoard#getOpenSpaces()} returns arrays of {@link Cell} </br>
	 * There is an empty {@link Cell} in the array </br>
	 * Expecting {@link RulesFactory#inline_cell_is_free(IBoard)} returns a {@link Cell} </br>
	 */
	@Test
	public void testInlineCellIsFree3(){
		
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell1 = Mockito.mock(Cell.class);
		Mockito.when(cell1.getValue()).thenReturn(PlayerState.EMPTY);
		Cell cell2 = Mockito.mock(Cell.class);
		Mockito.when(cell2.getValue()).thenReturn(PlayerState.NOUGHT);
		Cell[] cells = new Cell[]{cell1, cell2};
		Mockito.when(board.getOpenSpaces()).thenReturn(cells);
		assertEquals(cells[0], RulesFactory.getInstance().inline_cell_is_free(board));
	}
	/**
	 * @see {@link RulesFactory#getStandardCommandChain()} </br>
	 */
	@Test
	public void testGetStandardCommandChain(){
		assertNotNull(RulesFactory.getInstance().getStandardCommandChain());
	}
	@Test
	public void testCreateRandomRules(){
		assertNotNull(RulesFactory.getInstance().createRandomRules());
	}
	/**
	 * {@link ICommand} does not exist in {@link IRuleCollection} </br>
	 * Expecting method {@link IRuleCollection#invokeCommand(ICommand, IBoard)} returns null </br>
	 */
	@Test
	public void testInvokeCommand1(){
		ICommand command = Mockito.mock(ICommand.class);
		IBoard board = Mockito.mock(IBoard.class);
		Mockito.when(command.getCondition()).thenReturn("any");
		assertNull(RulesFactory.getInstance().invokeCommand(command, board));
	}
	/**
	 * {@link ICommand} exists in {@link IRuleCollection} </br>
	 * Expecting method {@link IRuleCollection#invokeCommand(ICommand, IBoard)} returns a {@link Cell} </br>
	 */
	@Test
	public void testInvokeCommand2(){
		ICommand command = Mockito.mock(ICommand.class);
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell = Mockito.mock(Cell.class);
		Mockito.when(cell.getValue()).thenReturn(PlayerState.EMPTY);
		Mockito.when(board.getOpenSpaces()).thenReturn(new Cell[]{cell});
		Mockito.when(board.isDraw()).thenReturn(false);
		Mockito.when(command.getCondition()).thenReturn("inline_cell_is_free");
		assertEquals(cell, RulesFactory.getInstance().invokeCommand(command, board));
	}
	/**
	 * {@link ICommand} exists in {@link IRuleCollection} </br>
	 * Mock {@link IBoard#getCenter()} returns a not empty {@link Cell} </br>
	 * Expecting method {@link IRuleCollection#invokeCommand(ICommand, IBoard)} returns null </br>
	 */
	@Test
	public void testInvokeCommand3(){
		ICommand command = Mockito.mock(ICommand.class);
		IBoard board = Mockito.mock(IBoard.class);
		Cell cell = Mockito.mock(Cell.class);
		Mockito.when(cell.getValue()).thenReturn(PlayerState.CROSS);		
		Mockito.when(board.getCenter()).thenReturn(cell);
		Mockito.when(board.isDraw()).thenReturn(false);
		Mockito.when(command.getCondition()).thenReturn("inline_cell_is_free");
		assertNull(RulesFactory.getInstance().invokeCommand(command, board));
	}
	
}
