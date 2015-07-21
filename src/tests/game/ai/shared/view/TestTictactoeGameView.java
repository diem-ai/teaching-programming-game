package tests.game.ai.shared.view;

import game.ai.shared.model.AbstractBoard;
import game.ai.shared.model.specification.IBoard;

import org.junit.runners.JUnit4;
import org.mockito.Mockito;
/**
 * This test for {@link IGameView} and {@link GameView} by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem
 * @version 1.0
 *
 */
public class TestTictactoeGameView extends TestGameView{
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.view.TestGameView#getBoard()
	 */
	@Override
	public IBoard getBoard() {
		AbstractBoard board = Mockito.mock(AbstractBoard.class);
		Mockito.when(board.getRows()).thenReturn(3);
		Mockito.when(board.getCols()).thenReturn(3);
		return board;
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.view.TestGameView#getGameTitle()
	 */
	@Override
	public String getGameTitle() {
		// TODO Auto-generated method stub
		return "Tic tac toe Game View";
	}


}
