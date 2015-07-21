
package game.ai.shared.rules.specification;

import game.ai.shared.model.specification.IBoard;

/**
 * This class is an interface that defines behaviors of {@link Command} </br>
 * @author btdiem
 * @version 1.0
 *
 */
public interface ICommand {
    /**
     * This method is called when {@link ICommand#getCondition()} is satisfied  </br>
     * @param board - the current {@link IBoard} status </br>
     * @return true if command is executed </br>
     * @return false if the condition does not satisfy </br> or {@link IBoard} is null </br>
     * or {@link IBoard#isDraw()} </br>
     */
	public boolean invoke(IBoard board);
	/**
	 * Return condition value of {@link ICommand} </br>
	 * @return {@link String} </br>
	 */
	public String getCondition();
	/**
	 * Return strategy value of {@link ICommand} </br>
	 * @return {@link String} </br>
	 */
	public String getStrategy();
}
