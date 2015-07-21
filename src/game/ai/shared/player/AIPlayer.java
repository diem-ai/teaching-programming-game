package game.ai.shared.player;

import java.io.FileNotFoundException;

import game.ai.shared.model.specification.IBoard;
import game.ai.shared.rules.CommandParser;
import game.ai.shared.rules.RulesFactory;
import game.ai.shared.rules.specification.IChain;
import game.ai.shared.rules.specification.IParser;
/**
 * This class is an implementation of {@link IPlayer} and {@link AbstractPlayer}</br>
 * AI Player updates new value for {@link IBoard} by executing {@link CommandChain} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public class AIPlayer extends AbstractPlayer implements IPlayer{

	private IChain commandChain;
	/**
	 * When initializing, AI player will be added {@link IRuleCollection} that are defined by system </br>
	 * 
	 */
	public AIPlayer(){
		commandChain = RulesFactory.getInstance().createRandomRules();
	}
	/*
	 * {@link IChain} will decide which strategy should be applied to win the opponent </br>
	 * Then, after {@link ICommand#invoke(IBoard)} is called, returned value will be applied for {@link IBoard} </br>  
	 * (non-Javadoc)
	 * @see game.ai.shared.player.IPlayer#move(game.ai.shared.model.specification.IBoard)
	 */
	public void move(IBoard board){
		commandChain.execute(board);
	}
	/**
	 * This method is called when user want imports the rules from text file </br>
	 * @param fileName - {@link String} </br>
	 * @see also {@link IParser#parse(String)} </br>
	 * @throws FileNotFoundException </br>
	 * @throws IllegalArgumentException </br>
	 */
	public void importRules(String fileName) throws IllegalArgumentException, 
													FileNotFoundException{	
		
		IParser parser = new CommandParser();
		IChain commandChain = parser.parse(fileName);
		applyCommandChain(commandChain);
		
	}
	/**
	 * This method is called when user wants to select rules from system library </br>
	 * and they are applied to {@link AIPlayer} </br>
	 * @param commandChain - {@link IChain}
	 */
	public void applyCommandChain(IChain commandChain){
		if (commandChain == null){
			throw new IllegalArgumentException("Input parameter is null.");
		}
		this.commandChain = commandChain;
	}
	public IChain getCommandChain(){
		return this.commandChain;
	}

	@Override
	public String toString(){
		return "I'm an AI player, I take " + getState().name();
	}
	
	
}
