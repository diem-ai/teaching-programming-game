package game.ai.shared.rules.specification;

import java.util.Properties;
import java.util.Stack;

import game.ai.shared.model.Cell;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.player.AIPlayer;
import game.ai.shared.player.IPlayer;

public interface IRuleCollection {
	//public final String breakCommand = "if inline_cell_is_free then take_inline_cell";
	/**
	 * Return a {@link Cell} that {@link IPlayer} can win</br>
	 * @param board - the current status of {@link IBoard}</br>
	 * @return {@link Cell}</br>
	 */
	public Cell player_can_win(IBoard board);
	/**
	 * Return a {@link Cell} that opponent of {@link IPlayer} can win</br>
	 * @param board - the current status of {@link IBoard}</br>
	 * @return {@link Cell}</br>
	 */
	public Cell opponent_can_win(IBoard board);
	/**
	 * Return a {@link Cell} that player can create a fork </br>
	 * @param board - the current status of {@link IBoard} </br>
	 * @return {@link Cell} </br>
	 */
//	public Cell player_can_create_fork(IBoard board);
	/**
	 * Return a {@link Cell} that opponent of current {@link IPlayer} can create a fork </br>
	 * @param board - the current status of {@link IBoard} </br>
	 * @return {@link Cell} </br>
	 */
//	public Cell opponent_can_create_fork(IBoard board);
	/**
	 * Return an empty center {@link Cell} of {@link IBoard} </br>
	 * Return null if this {@link Cell} is filled </br>
	 * @param board - the current status of {@link IBoard} </br>
	 * @return {@link Cell} or null </br>
	 */
	public Cell center_is_free(IBoard board);
	/**
	 * Return an empty corner {@link Cell} of {@link IBoard} </br>
	 * Return null if this {@link Cell} is filled </br>
	 * @param board - the current status of {@link IBoard} </br>
	 * @return {@link Cell} or null </br>
	 */
	public Cell corner_is_free(IBoard board);
	/**
	 * Return an opposite corner {@link Cell} of {@link IBoard} of opponent </br>
	 * Return null if this {@link Cell} is filled </br>
	 * @param board - the current status of {@link IBoard} </br>
	 * @return {@link Cell} or null </br>
	 */
	public Cell opponent_has_opposite_corner(IBoard board);
	/**
	 * Return any empty {@link Cell} of {@link IBoard} </br>
	 * Return null if {@link IBoard#isDraw()} is true </br>
	 * @param board - the current status of {@link IBoard} </br>
	 * @return {@link Cell} or null  </br>
	 */
	public Cell inline_cell_is_free(IBoard board);
	/**
	 * Return a {@link IChain} of {@link ICommand} and its successor </br>
	 * @param stack of {@link ICommand} </br>
	 * @return {@link IChain} </br>
	 */
	public IChain buildCommandChain(Stack<ICommand> stack);
	/**
	 * Return a {@link IChain} of {@link ICommand} of System </br>
	 * @return {@link IChain} </br>
	 */
	public IChain getStandardCommandChain();
	/**
	 * Return a map that contains {@link ICommand#condition} as the key and {@link ICommand#strategy} as the value </br>
	 * @return {@link Properties} </br>
	 */
	public Properties getRules();
	/**
	 * This function will create a new collection of rules for AI player </br>
	 */	
	public IChain createRandomRules();
	/**
	 * This method will find its methods that can match with {@link ICommand#getCondition()} </br>
	 * Then executing the method </br>
	 * If in the collection of strategy has no a break command, the system will add it to user's strategy as last option </br>
	 * @param command - {@link ICommand} </br>
	 * @param board - {@link IBoard} </br>
	 * @return {@link Cell} or Null </br>
	 */
	public Object invokeCommand(ICommand command, IBoard board);
	/**
	 * Return a {@link ICommand} that can run in case all rules of {@link AIPlayer} can not be applied for {@link IBoard} </br>
	 * @return {@link ICommand} </br>
	 */
	public ICommand getBreakCommand();
}
