package game.ai.shared.rules.specification;

import game.ai.shared.model.specification.IBoard;
/**
 * This class is an interface that defines behavior a chain of rules applied to {@link AIPlayer} </br>
 * @author btdiem
 * @version 1.0
 */
public interface IChain {
	/**
	 * Set next {@link IChain} </br>
	 * @param successor - {@link IChain} </br>
	 */
	public void setSuccessor(IChain successor);
	/**
	 * Apply the the current {@link ICommand} in {@link IChain} for {@link IBoard} </br>
	 * @param board - {@link IBoard} </br>
	 */
	public void execute(IBoard board);
	/**
	 * Return {@link ICommand} of {@link IChain} </br>
	 * @return {@link ICommand} </br>
	 */
	public ICommand getCommand();
	/**
	 * Return the next {@link IChain} </br>
	 * @return {@link IChain} </br>
	 */
	public IChain getSuccessor();
}
