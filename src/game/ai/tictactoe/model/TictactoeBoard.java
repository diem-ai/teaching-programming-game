package game.ai.tictactoe.model;

import game.ai.shared.model.AbstractBoard;
import game.ai.shared.model.PlayerState;
import game.ai.shared.model.specification.IBoard;
/**
 * This class is an implementation of {@link IBoard} and extends from {@link AbstractBoard} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public class TictactoeBoard extends AbstractBoard implements IBoard {

	public TictactoeBoard(Integer rows, Integer cols, Integer connector) {
		super(rows, cols, connector);
	}
	
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#isEmptyCell(int, int)
	 */
   @Override
	public boolean isEmptyCell(int selectedRow, int selectedCol) {
		return cells[selectedRow][selectedCol].getValue().equals(PlayerState.EMPTY);
	}

	@Override
	public IBoard toCopy() {
		TictactoeBoard copy = new TictactoeBoard(rows, cols, connector);
		copy.setCells(getCells().clone());
		copy.updatePlayer(getPlayerState());
		return copy;
	}
	@Override
	public int getDefaultRows() {
		// TODO Auto-generated method stub
		return 3;
	}
	@Override
	public int getDefaultCols() {
		// TODO Auto-generated method stub
		return 3;
	}
	@Override
	public int getDefaultConnector() {
		// TODO Auto-generated method stub
		return 3;
	}

}
