package game.ai.shared.rules.specification;

import game.ai.shared.rules.CommandChain;

import java.io.FileNotFoundException;
/**
 * This is an interface that defines methods to compile the text file imported by the user </br>
 * @author btdiem
 * @version 1.0
 *
 */
public interface IParser {
	/**
	 * @param fileName - File that defines a list of rule following  the format of rules.properties</br>
	 * If there is any wrong format rule, the system will ignore and work with the next one </br>
	 * If all {@link ICommand} defined in file are wrong, throw {@link IllegalArgumentException} </br>
	 * @return {@link CommandChain}</br>
	 * @throws IllegalArgumentException - An exception will be thrown if file is empty or does not exist or the rule definition is incorrect.</br>
	 * @throws FileNotFoundException 
	 */
	public IChain parse(String fileName) throws IllegalArgumentException, FileNotFoundException;
	/**
	 * Verify the command structure and command semantic</br>
	 * @param line - a line in text file</br>
	 * @return {@link CommandChain}</br>
	 * @throws IllegalArgumentException </br>
	 */
	public ICommand parseString(String line) throws IllegalArgumentException;
}
