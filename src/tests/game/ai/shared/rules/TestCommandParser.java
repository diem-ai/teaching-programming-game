package tests.game.ai.shared.rules;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import game.ai.shared.rules.CommandParser;
import game.ai.shared.rules.specification.IChain;
import game.ai.shared.rules.specification.ICommand;
import game.ai.shared.rules.specification.IParser;
import game.ai.shared.rules.specification.IRuleCollection;
/**
 * This test for {@link IParser} and {@link ICommandParser} class by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem 
 * @version 1.0
 *
 */
public class TestCommandParser {

	IParser parser;
	 File testFile;//this is tempory file for testing
	 @Rule
	 public TemporaryFolder folder = new TemporaryFolder();
	 /**
	  * Create a temporary file that has correct format {@link ICommand} </br>
	  * @throws IOException </br>
	  */
	 public  void createWrongFormatTestData() throws IOException {
		 	testFile = folder.newFile("rules.txt");
	        BufferedWriter out = new BufferedWriter(new FileWriter(testFile));
	        out.write("if A then B\n");
	        out.write("if D then C\n");
	        out.close();
	 }
	 /**
	  * Create a temporary file that has all wrong format {@link ICommand} </br>
	  * @throws IOException </br>
	  */
	 public  void createCorrectFormatTestData() throws IOException {
		 	testFile = folder.newFile("rules.txt");
	        BufferedWriter out = new BufferedWriter(new FileWriter(testFile));
	        out.write("if player_can_win then win\n");
	        out.write("if inline_cell_is_free then take_inline_cell\n");
	        out.close();
	 }	 
	 /**
	  * Create a tempory file that has correct format {@link ICommand} </br>
	  * @throws IOException
	  */
	 public  void createEmptyFile() throws IOException {
		 	testFile = folder.newFile("rules.txt");
	 }
	@Before
	public void setUp() throws Exception {
		parser = new CommandParser();
	}

	@After
	public void tearDown() throws Exception {
		parser = null;
	}
	/**
	 * Case 1: There is no IF key work in {@link ICommand} </br>
	 * Expecting method {@link IParser#parseString(String)} throws {@link IllegalArgumentException} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testParseString_Exception1() {
		
		String line = "A then B";
		parser.parseString(line);
	}
	/**
	 * Case 2: There is no Then key work in {@link ICommand} </br>
	 * Expecting method {@link IParser#parseString(String)} throws {@link IllegalArgumentException} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testParseString_Exception2() {
		
		String line = "if A B";
		parser.parseString(line);
	}	
	/**
	 * Case 3: There is If and Then key work in {@link ICommand} </br>
	 * But the order of If and Then is wrong </br>
	 * Expecting method {@link IParser#parseString(String)} throws {@link IllegalArgumentException} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testParseString_Exception3() {
		
		String line = "then A if B";
		parser.parseString(line);
	}	
	/**
	 * Case 4: Input {@link ICommand} is correct </br>
	 * But it does not exist in the {@link IRuleCollection} </br>
	 * Expecting method {@link IParser#parseString(String)} throws {@link IllegalArgumentException} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testParseString_Exception4() {
		
		String line = "if A then B";
		parser.parseString(line);
	}
	/**
	 * Case 4: Input {@link ICommand} is correct </br>
	 * But The semantic of {@link ICommand} is wrong </br>
	 * Expecting method {@link IParser#parseString(String)} throws {@link IllegalArgumentException} </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testParseString_Exception5() {
		
		String line = "if player_can_win then B";
		parser.parseString(line);
	}
	/**
	 * Case 4: Input {@link ICommand} is correct </br>
	 * Expecting method {@link IParser#parseString(String)} returns {@link ICommand} </br>
	 */
	@Test
	public void testParseString() {
		
		String line = "if player_can_win then win";
		ICommand command = parser.parseString(line);
		assertEquals("player_can_win", command.getCondition());
		assertEquals("win", command.getStrategy());
	}
	/**Case 1:
	 * Filename parameter is null </br>
	 * Expecting method {@link IParser#parse(String)} throws {@link IllegalArgumentException}
	 * @throws FileNotFoundException 
	 * @throws IllegalArgumentException 
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testParse_Exception1() throws IllegalArgumentException, FileNotFoundException{
		parser.parse(null);
	}
	/**Case 2:
	 * Filename parameter is not null but this file does not exist </br>
	 * Expecting method {@link IParser#parse(String)} throws {@link IllegalArgumentException}
	 * @throws FileNotFoundException 
	 * @throws IllegalArgumentException 
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testParse_Exception2() throws IllegalArgumentException, FileNotFoundException{
		parser.parse("C:\\");
	}	
	/**Case 3:
	 * Create a tempory file with correct format {@link ICommand} </br>
	 * Expecting method {@link IParser#parse(String)} returns {@link IChain} </br>
	 * @throws IOException 
	 */
	@Test
	public void testParse_1() throws IOException{
		createCorrectFormatTestData();
		IChain chain = parser.parse(testFile.getAbsolutePath());
		assertNotNull(chain);
	}
	/**Case 4:
	 * Create a empty tempory file with correct format {@link ICommand} </br>
	 * Expecting method {@link IParser#parse(String)} throws {@link IllegalArgumentException} </br>
	 * @throws IOException 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testParse_Exception3() throws IOException{
		createEmptyFile();
		parser.parse(testFile.getAbsolutePath());
	}	
	/**Case 5:
	 * Create a temporary file with all wrong format {@link ICommand} </br>
	 * Expecting method {@link IParser#parse(String)} returns {@link IllegalArgumentException} </br>
	 * @throws IOException 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testParse_Exception4() throws IOException{
		createWrongFormatTestData();
		parser.parse(testFile.getAbsolutePath());
	}	

}
