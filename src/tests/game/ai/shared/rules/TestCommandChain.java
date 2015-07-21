package tests.game.ai.shared.rules;

import static org.junit.Assert.*;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.rules.CommandChain;
import game.ai.shared.rules.specification.IChain;
import game.ai.shared.rules.specification.ICommand;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
/**
 * This test for {@link IChain} and {@link CommandChain} using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem
 * @version 1.0
 *
 */
public class TestCommandChain {

	IChain chain;
	ICommand command;
	
	@Before
	public void setUp() throws Exception {
		command = Mockito.mock(ICommand.class);
		chain = new CommandChain(command);
	}

	@After
	public void tearDown() throws Exception {
		command = null;
		chain = null;
	}
	/**
	 * @see {@link CommandChain#CommandChain(ICommand)} </br>
	 * 
	 */
	@Test
	public void testConstructor() {
		assertEquals(command, chain.getCommand());
	}
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor_Exception() {
		chain = new CommandChain(null);
	}
	/**
	 * Case 1: {@link ICommand#invoke(IBoard)} returns true </br>
	 */
	@Test
	public void testExecute1(){
		IBoard board = Mockito.mock(IBoard.class);
		Mockito.when(command.invoke(board)).thenReturn(true);
		chain.execute(board);
	}
	/**
	 * Case 2: {@link ICommand#invoke(IBoard)} returns false </br>
	 * {@link IChain#getSuccessor()} is null </br>
	 * 
	 */
	@Test
	public void testExecute2(){
		IBoard board = Mockito.mock(IBoard.class);
		Mockito.when(command.invoke(board)).thenReturn(false);
		chain.execute(board);
	}
	/**
	 * Case 2: {@link ICommand#invoke(IBoard)} returns false </br>
	 * {@link IChain#getSuccessor()} is not null </br>
	 * 
	 */
	@Test
	public void testExecute3(){
		IBoard board = Mockito.mock(IBoard.class);
		Mockito.when(command.invoke(board)).thenReturn(false);
		IChain successor = Mockito.mock(IChain.class);
		chain.setSuccessor(successor);
		chain.execute(board);
	}	
	/**
	 * Return {@link IChain#toString()} without successor </br>
	 */
	@Test
	public void testToString1(){
		Mockito.when(command.getCondition()).thenReturn("A");
		Mockito.when(command.getStrategy()).thenReturn("B");
		//String s = "If ".concat("A").concat(" Then ").concat("B");
		assertNotNull(chain.toString());
	}
	/**
	 * Return {@link IChain#toString()} having a successor </br>
	 */
	@Test
	public void testToString2(){
		Mockito.when(command.getCondition()).thenReturn("A");
		Mockito.when(command.getStrategy()).thenReturn("B");
		IChain successor = Mockito.mock(IChain.class);
		ICommand cmd =Mockito.mock(ICommand.class);
		Mockito.when(successor.getCommand()).thenReturn(cmd);
		chain.setSuccessor(successor);
		assertNotNull(chain.toString());
	}	
}
