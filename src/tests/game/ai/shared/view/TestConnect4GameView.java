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
public class TestConnect4GameView extends TestGameView {
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.view.TestGameView#getBoard()
	 */
	@Override
	public IBoard getBoard() {
		
		AbstractBoard board = Mockito.mock(AbstractBoard.class);
		Mockito.when(board.getRows()).thenReturn(7);
		Mockito.when(board.getCols()).thenReturn(6);
		return board;
	}
	/*
	 * (non-Javadoc)
	 * @see tests.game.ai.shared.view.TestGameView#getGameTitle()
	 */
	@Override
	public String getGameTitle() {
		// TODO Auto-generated method stub
		return "Connect4 Game View";
	}

}
