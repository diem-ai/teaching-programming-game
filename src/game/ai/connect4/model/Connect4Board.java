package game.ai.connect4.model;

import game.ai.shared.model.AbstractBoard;
import game.ai.shared.model.Cell;
import game.ai.shared.model.PlayerState;
import game.ai.shared.model.specification.IBoard;
/**
 * This is an implementation of {@link IBoard} and {@link AbstractBoard} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public class Connect4Board extends AbstractBoard implements IBoard {

	public Connect4Board(Integer rows, Integer cols, Integer connector) {
 		super(rows, cols, connector);
	}
	
	@Override
	public IBoard toCopy() {
		Connect4Board copy = new Connect4Board(rows, cols, connector);
		copy.setCells(getCells().clone());
		copy.updatePlayer(getPlayerState());
		return copy;
	}
	@Override
	public int getDefaultRows() {
		return 7;
	}
	@Override
	public int getDefaultCols() {
		return 6;
	}
	@Override
	public int getDefaultConnector() {
		return 4;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.AbstractBoard#updateCell(game.ai.shared.model.Cell)
	 * For Connect4 game, to update an empty cell, starting finding from the bottom row </br>
	 */
	@Override
	public void updateCell(Cell cell) {
		
		int selectedCol = cell.getCol();
		// Look for an empty cell starting from the bottom row
		 for (int row = rows -1; row >= 0; row--) {
			 if (cells[row][selectedCol].getValue() == PlayerState.EMPTY){
				 super.updateCell(cells[row][selectedCol]);
				 break;
			 }
		 }
			
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#isEmptyCell(int, int)
	 * Look for an empty cell starting from the bottom row </b>
	 */
	@Override
	public boolean isEmptyCell(int selectedRow, int selectedCol) {
		
		 for (int row = rows -1; row >= 0; row--) {
			 if (cells[row][selectedCol].getValue() == PlayerState.EMPTY){
				 return true;
			 }
		 }
		return false;
	}
	
	
}//class
		
	


