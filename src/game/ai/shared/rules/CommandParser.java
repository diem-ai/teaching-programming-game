package game.ai.shared.rules;

import game.ai.shared.rules.specification.IChain;
import game.ai.shared.rules.specification.ICommand;
import game.ai.shared.rules.specification.IParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * This class is used to read properties files, verify the rule structures and convert to {@link CommandChain} object 
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public class CommandParser implements IParser{
	@Override
	@SuppressWarnings("resource")
	public IChain parse(String fileName) throws IllegalArgumentException, FileNotFoundException{
		if (fileName == null){
			throw new IllegalArgumentException("Input parameter is null");
		}
		File file = new File(fileName);
		if (! file.isFile()){
			System.out.println(fileName + "does not exist.");
			throw new IllegalArgumentException("File " + fileName + " does not exist.");
		}
		if (file.length() == 0){
			throw new IllegalArgumentException("File " + fileName + " is empty.");
		}
		
		Scanner scanner = new Scanner(file);
		Stack<ICommand> stack = new Stack<ICommand>();	
		while (scanner.hasNextLine()){
			String line = scanner.nextLine();
			try{
				ICommand command = parseString(line);
				stack.push(command);
			}catch(Exception e){
			}
		}//while
		if (stack.isEmpty()){
			throw new IllegalArgumentException("File " + file + " has not any correct format command.");
		}
		
		return RulesFactory.getInstance().buildCommandChain(stack);
	}
	@Override
	public ICommand parseString(String line) throws IllegalArgumentException{
		
		RulesFactory standardRules = RulesFactory.getInstance();
		int ifIndex = line.indexOf("if");
		int thenIndex = line.indexOf("then");
		//check the structure
		//it has to follow: IF condition THEN strategy
		if (ifIndex < 0 || thenIndex < 0 || ifIndex > thenIndex ){
			throw new IllegalArgumentException("Your rule is incorrect grammar.");
		}
		String condition = line.substring(ifIndex + 2, thenIndex).trim();
		String strategy = line.substring(thenIndex + 4).trim();
		//check lexical
		if (!standardRules.hasConditionRule(condition)){
			throw new IllegalArgumentException("Your rule does not exist in the system");
		}
		//check the semantic
		//it has to follow the standard rule definition
		//for example : if player_can_win then win
		if(!standardRules.hasStrategy(condition, strategy)){
			throw new IllegalArgumentException("Your rule does not follow the standard rule definition.\n"
					+ "It should be if " + condition + " then " + standardRules.getStategy(condition));
		}
		
		return new Command(condition, strategy);
	}

}
