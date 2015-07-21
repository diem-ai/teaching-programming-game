package tests.game.ai.shared.player;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import game.ai.shared.model.PlayerState;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.player.AIPlayer;
import game.ai.shared.player.IPlayer;
import game.ai.shared.rules.specification.ICommand;
import game.ai.shared.rules.specification.IChain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
/**
 * This test for {@link IPlayer} and {@link AIPlayer} using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public class TestAIPlayer extends TestAbstractPlayer{
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.player.TestAbstractPlayer#getDefaultConstructor()
	 */
	@Override
	public IPlayer getDefaultConstructor() {
		
		return new AIPlayer();
	}
	/**
	 * @see {@link AIPlayer#toString()} </br>
	 * @see also {@link IPlayer#setState(PlayerState)} </br>
	 */
	@Test
	public void testToString(){
		player.setState(PlayerState.NOUGHT);
		String s = "I'm an AI player, I take " + PlayerState.NOUGHT.name();
		assertEquals(s, player.toString());
	}
	/**
	 * @see {@link AIPlayer#move(IBoard)} </br>
	 */
	@Test
	public void testMove(){
		IBoard board = Mockito.mock(IBoard.class);
		player.move(board);
	}
	@Test
	public void testImportFile() throws IllegalArgumentException, FileNotFoundException, IOException{
		((AIPlayer)player).importRules(createCorrectFormatTestData().getPath());
	}
	 @Rule
	 public TemporaryFolder folder = new TemporaryFolder();
	 /**
	  * Create a temporary file that has all wrong format {@link ICommand} </br>
	  * @throws IOException </br>
	  */
	 public  File createCorrectFormatTestData() throws IOException {
		 	File testFile = folder.newFile("rules.txt");
	        BufferedWriter out = new BufferedWriter(new FileWriter(testFile));
	        out.write("if player_can_win then win\n");
	        out.write("if inline_cell_is_free then take_inline_cell\n");
	        out.close();
	        return testFile;
	 }	
	 /**
	  * @see {@link AIPlayer#applyCommandChain(IChain)} </br>
	  */
	 @Test
	 public void testApplyCommandChain(){
		 IChain commandChain = Mockito.mock(IChain.class);
		 ((AIPlayer)player).applyCommandChain(commandChain);
	 }

}
