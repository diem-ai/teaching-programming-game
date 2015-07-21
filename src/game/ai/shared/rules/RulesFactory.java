package game.ai.shared.rules;


import game.ai.shared.model.Cell;
import game.ai.shared.model.PlayerState;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.player.AIPlayer;
import game.ai.shared.rules.specification.IChain;
import game.ai.shared.rules.specification.ICommand;
import game.ai.shared.rules.specification.IRuleCollection;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map.Entry;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Stack;

/**
 * This class to create {@link ICommand} of playing strategy for system </br>
 * Creating a random rules as library that can be applied for {@link AIPlayer} </br>
 * This class is used to check the rules that are defined by users </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public class RulesFactory implements IRuleCollection{

	private String propertyFile = "standard_rules.properties";
	private Properties rules = new Properties();
	private Random random = new Random();
	/**
	 * This is singleton class so set private for constructor </br>
	 */
	private RulesFactory(){
		try{
			rules.load(RulesFactory.class.getClassLoader().
					getResourceAsStream(propertyFile));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public boolean hasConditionRule(String condition){
		return rules.containsKey(condition);
	}
	
	public boolean hasStrategy(String condition, String strategy){
		return rules.getProperty(condition).equals(strategy);
	}
	
	public String getStategy(String condition){
		return rules.getProperty(condition);
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IRuleCollection#player_can_win(game.ai.shared.model.specification.IBoard)
	 */
	@Override
	public Cell player_can_win(IBoard board) {
		IBoard copy = board.toCopy();
		Cell[] openSpaces = copy.getOpenSpaces();
		if (openSpaces != null){
			for (Cell iCell : openSpaces){
				//cell.setValue(opponentState);
				copy.update(iCell);
				if (copy.isWin()){
					return iCell;
				}
				copy.clearCell(iCell);
			}				
		}
		return null;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IRuleCollection#opponent_can_win(game.ai.shared.model.specification.IBoard)
	 */
	@Override
	public Cell opponent_can_win(IBoard board) {
		
		IBoard copy = board.toCopy();
		copy.updatePlayer(board.getOpponentState());
		Cell[] openSpaces = copy.getOpenSpaces();
		if (openSpaces != null){
			for (Cell iCell : openSpaces){
				copy.update(iCell);
				if (copy.isWin()){
					return iCell;
				}
				copy.clearCell(iCell);
			}				
		}
		return null;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IRuleCollection#player_can_create_fork(game.ai.shared.model.specification.IBoard)
	 */
//	@Override
//	public Cell player_can_create_fork(IBoard board) {
//		IBoard copy = board.toCopy();
//		Cell[] openSpaces = copy.getOpenSpaces();
//		if (openSpaces != null){
//			for (Cell iCell : openSpaces){
//				//cell.setValue(opponentState);
//				copy.updateCell(iCell);
//				if (copy.hasFork()){
//					return iCell;
//				}
//				copy.clearCell(iCell);
//			}				
//		}
//		return null;
//	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IRuleCollection#opponent_can_create_fork(game.ai.shared.model.specification.IBoard)
	 */
//	@Override
//	public Cell opponent_can_create_fork(IBoard board) {
//			//IPlayer opponent = board.getOpponent();
//		IBoard copy = board.toCopy();
//		copy.changePlayerState();
//		Cell[] openSpaces = copy.getOpenSpaces();
//		if (openSpaces != null){
//			for (Cell iCell : openSpaces){
//				//cell.setValue(opponentState);
//				copy.updateCell(iCell);
//				if (copy.hasFork()){
//					return iCell;
//				}
//				copy.clearCell(iCell);
//			}				
//		}
//		return null;
//	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IRuleCollection#center_is_free(game.ai.shared.model.specification.IBoard)
	 */
	@Override
	public Cell center_is_free(IBoard board) {
		Cell iCell = board.getCenter();
		return iCell.getValue() == PlayerState.EMPTY ? iCell : null;
	}

	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IRuleCollection#corner_is_free(game.ai.shared.model.specification.IBoard)
	 */
	@Override
	public Cell corner_is_free(IBoard board) {
		
		Cell[] cells = board.getCorners();
		if (cells != null){
			for (Cell iCell : cells){
				if (iCell.getValue() == PlayerState.EMPTY){
					return iCell;
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IRuleCollection#opponent_has_opposite_corner(game.ai.shared.model.specification.IBoard)
	 */
	@Override
	public Cell opponent_has_opposite_corner(IBoard board) {

		Cell[] cells = board.getCorners();
		if (cells == null){
			return null;
		}
		if (cells[0].getValue() == board.getOpponentState()){
			if (cells[2].getValue() == PlayerState.EMPTY){
				return cells[2];
			}
		}else if(cells[0].getValue() == PlayerState.EMPTY){
			if (cells[2].getValue() == board.getOpponentState()){
				return cells[0];
			}
			
		}
		if (cells[1].getValue() == board.getOpponentState()){
			if (cells[3].getValue() == PlayerState.EMPTY){
				return cells[3];
			}
		}else if(cells[1].getValue() == PlayerState.EMPTY){
			if (cells[3].getValue() == board.getOpponentState()){
				return cells[1];
			}
			
		}		
		return null;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IRuleCollection#inline_cell_is_free(game.ai.shared.model.specification.IBoard)
	 */
	@Override
	public Cell inline_cell_is_free(IBoard board) {
		Cell[] cells = board.getOpenSpaces();
		if (cells != null){
			for (Cell iCell : cells){
				if (iCell.getValue() == PlayerState.EMPTY){
					return iCell;
				}
			}
			
		}
		return null;
	}
	//create a singleton for each call
	private static class LazyHolder {
        public static final RulesFactory INSTANCE = new RulesFactory();
	}
	
	public static RulesFactory getInstance(){
		return LazyHolder.INSTANCE;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IRuleCollection#getStandardCommandChain()
	 */
	@Override
	public IChain getStandardCommandChain(){
		
		Stack<ICommand> stack = new Stack<ICommand>();
		for(Entry<Object, Object> entry : rules.entrySet()){
			stack.push(new Command(entry.getKey().toString(), entry.getValue().toString()));
		}
		return buildCommandChain(stack);
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IRuleCollection#createRandomRules()
	 */
	@Override
	public IChain createRandomRules() {
		//shuffle rule collection
		List<Object> list = Arrays.asList(rules.keySet().toArray(new Object[]{}));
		Collections.shuffle(list);
		//randomize to choose the number of elements
		int ran = 0;
		while(ran < 1){
			ran = random.nextInt(rules.size());
		}
		List<Object> subList = list.subList(0, ran);
		Stack<ICommand> stack = new Stack<ICommand>();
		//if the random selection has no a break condition, add it as a last rule of collection
		for (Object condition : subList){
			ICommand command = new Command(condition.toString(), getStategy(condition.toString()) );
			stack.push(command);
		}
		if (!subList.contains(getBreakCommand().getCondition())){
			stack.push(getBreakCommand());
		}
		return buildCommandChain(stack);
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IRuleCollection#buildCommandChain(java.util.Stack)
	 */
	@Override
	public IChain buildCommandChain(Stack<ICommand> stack){
		
		IChain commandChain = null;	
		IChain successor = null;
		while(!stack.isEmpty()){
			ICommand command = stack.pop();
			commandChain = new CommandChain(command);
			if (successor != null){
				commandChain.setSuccessor(successor);
			}
			successor = commandChain;
		}
		return commandChain;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.IRuleCollection#getRules()
	 */
	@Override
	public Properties getRules() {
		return rules;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.rules.specification.IRuleCollection#invokeCommand(game.ai.shared.rules.specification.ICommand, game.ai.shared.model.specification.IBoard)
	 */
	@Override
	public Object invokeCommand(ICommand command, IBoard board) {
		if (!rules.containsKey(command.getCondition())){
			return null;
		}else{
			try {
				Method m = getInstance().getClass().getDeclaredMethod(command.getCondition(), IBoard.class);
				return m.invoke(getInstance(), board);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return null;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.rules.specification.IRuleCollection#getBreakCommand() </br>
	 * For this implementation, break command is : if inline_cell_is_free then take_inline_cell </br> 
	 */
	@Override
	public ICommand getBreakCommand() {
		
		return new Command("inline_cell_is_free", "take_inline_cell");
	}	
}
