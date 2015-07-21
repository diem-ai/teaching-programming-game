package game.ai.shared.rules;

import game.ai.shared.model.Cell;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.rules.specification.ICommand;
/**
 * This class is an implementation of interface {@link ICommand} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public class Command implements ICommand {

	private String condition;
	private String strategy;
	
	public Command(String condition, String strategy){
		if (condition == null){
			throw new IllegalArgumentException("Condition parameter is null.");
		}
		if (strategy == null){
			throw new IllegalArgumentException("Strategy parameter is null.");
		}
		this.condition = condition.trim();
		this.strategy = strategy.trim();
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.ICommand#invoke(game.ai.shared.model.specification.IBoard)
	 */
	@Override
	public boolean invoke(IBoard board) {
		if (board == null || board.isDraw()){
			return false;
		}
		Object cell = RulesFactory.getInstance().invokeCommand(this, board);
		if (cell == null)
			return false;
		else{
			board.update((Cell)cell);
			return true;
		}
		
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return "if ".concat(condition).concat(" then ").concat(strategy);
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.rules.specification.ICommand#getCondition()
	 */
	@Override
	public String getCondition() {
		
		return this.condition;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.rules.specification.ICommand#getStrategy()
	 */
	@Override
	public String getStrategy() {
		
		return this.strategy;
	}
	 
	

}
