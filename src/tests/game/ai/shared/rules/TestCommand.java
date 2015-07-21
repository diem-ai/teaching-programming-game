package tests.game.ai.shared.rules;

import static org.junit.Assert.*;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.model.Cell;
import game.ai.shared.model.PlayerState;
import game.ai.shared.rules.Command;
import game.ai.shared.rules.specification.ICommand;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
/**
 * This test for {@link ICommand} and {@link Command } by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public class TestCommand {

	ICommand command;
	String condition = "default";
	String strategy = "default";
	@Before
	public void setUp(){
		command = new Command(condition, strategy);
	}
	@After
	public void tearDown(){
		command = null;
	}
	/**
	 * @see {@link Command#Command(String, String)} </br>
	 */
	@Test
	public void testConstructor(){
		assertEquals(condition, command.getCondition());
		assertEquals(strategy, command.getStrategy());
	}
	/**
	 * Condition property of {@link ICommand} is null </br>
	 * Expecting an {@link IllegalArgumentException} thrown </br>
	 * @see {@link Command#Command(String, String)} </br>
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_Exception1(){
		command = new Command(null, strategy);
	}
	/**
	 * Strategy property of {@link ICommand} is null </br>
	 * Expecting an {@link IllegalArgumentException} thrown </br>
	 * @see {@link Command#Command(String, String)} </br>
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_Exception2(){
		command = new Command(condition, null);
	}
	
	/**
	 * Case 1: {@link IBoard} is null </br>
	 * Expecting method {@link ICommand#invoke(IBoard)} returns false </br>
	 */
	@Test
	public void testInvoke1(){
		
		IBoard board = null;
		assertFalse(command.invoke(board));
	}
	/**
	 * Case 2: {@link IBoard} is full </br>
	 * Expecting method {@link ICommand#invoke(IBoard)} returns false </br>
	 * 
	 */
	@Test
	public void testInvoke2(){
		
		IBoard board = Mockito.mock(IBoard.class);
		Mockito.when(board.isDraw()).thenReturn(true);
		assertFalse(command.invoke(board));
	}
	/**
	 * Case 3:{@link ICommand#invoke(IBoard)} a rule that does not exist in the system </br>
	 * Expecting {@link ICommand#invoke(IBoard)} returns false </br>
	 */
	@Test
	public void testInvoke3(){
		IBoard board = Mockito.mock(IBoard.class);
		Mockito.when(board.isDraw()).thenReturn(false);
		assertFalse(command.invoke(board));
	}
	@Test
	public void testInvoke4(){
		command = new Command("center_is_free", "take_center");
		Cell cell = Mockito.mock(Cell.class);
		Mockito.when(cell.getValue()).thenReturn(PlayerState.EMPTY);		
		IBoard board = Mockito.mock(IBoard.class);
		Mockito.when(board.getCenter()).thenReturn(cell);
		Mockito.when(board.isDraw()).thenReturn(false);
		assertTrue(command.invoke(board));
	}

	@Test
	public void testToString(){
		String s = "if ".concat(condition).concat(" then ").concat(strategy);
		assertEquals(s, command.toString());
	}

}
