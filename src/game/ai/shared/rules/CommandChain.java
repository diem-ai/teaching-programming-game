package game.ai.shared.rules;

import game.ai.shared.model.specification.IBoard;
import game.ai.shared.rules.specification.IChain;
import game.ai.shared.rules.specification.ICommand;
/**
 * This class is an implementation of {@link IChain} </b>
 * Creat a chain of {@link ICommand} for {@link AIPlayer} to play </br>  
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public class CommandChain implements IChain{
	
	private IChain successor;
	private ICommand command;

	public CommandChain(ICommand command){
		if (command == null){
			throw new IllegalArgumentException("Input Parameter is null.");
		}
		this.command = command;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IChain#setSuccessor(game.ai.shared.model.rules.specification.IChain)
	 */
	@Override
	public void setSuccessor(IChain successor){
		this.successor = successor;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IChain#getCommand()
	 */
	@Override
	public ICommand getCommand() {
		return this.command;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IChain#getSuccessor()
	 */
	@Override
	public IChain getSuccessor() {
		return this.successor;
	}	
	
	/**
	 * If {@link ICommand#invoke(IBoard)} returns false </br>
	 * the successor will execute </br>
	 * The loop is repeated until there is command satisfying  or no {@link ICommand} </br>
	 */
	public void execute(IBoard board) {
		
		if (!command.invoke(board)){
			if (successor != null){
				successor.execute(board);
			}
		}
	}
	/**
	 * Normalize the user's view
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		IChain commandChain = this;
		do{
			sb.append(commandChain.getCommand().toString()).append("\n");
			commandChain = commandChain.getSuccessor();
			
		}while(commandChain != null);
		
		return sb.toString();
	}


	
	
	
	
}
