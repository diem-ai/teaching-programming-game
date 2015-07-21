package game.ai.shared.factory.specification;

import java.lang.reflect.Constructor;

import game.ai.shared.controller.GameController;
import game.ai.shared.controller.specification.IGameController;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.player.factory.PairsPlayerFactoryChoices;
import game.ai.shared.player.specification.PairsPlayerSpecification;
import game.ai.shared.view.GameView;
import game.ai.shared.view.specification.IGameView;
/**
 * This is an abstract class that extends from {@link IGameFactory} </br>
 * This class will implement some common methods shared among specific games </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public abstract class AbstractGameFactory implements IGameFactory {
	/*
	 * (non-Javadoc)
	 * @see game.ai.factory.specification.IGameFactory#playGame()
	 */
	@Override
	public void playGame() {
		try{
			//player should be composition of controller
			IBoard board = createBoard();
			PairsPlayerSpecification pairsPlayer = PairsPlayerFactoryChoices.getRandomPairsPlayer();
			IGameController controller = new GameController(board, pairsPlayer);
			IGameView view = new GameView(getTitle(), board, controller);
			board.updateView(view);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.factory.specification.IGameFactory#createBoard()
	 */
	@Override
	public IBoard createBoard() throws Exception {
		Constructor<?> contructor = Class.forName(getBoardClassName()).
				getConstructor(Integer.class, Integer.class, Integer.class);
		return (IBoard) contructor.newInstance(getRows(), getCols(), getConnector());

	}
	/**
	 * Return a connector value of a game </br>
	 * @return {@link Integer} </br>
	 */
	public abstract int getConnector();
	/**
	 * Return the value of the number of column of a game </br>
	 * @return {@link Integer} </br>
	 */
	public abstract int getCols();
	/**
	 * Return the value of the number of rows of a game </br>
	 * @return {@link Integer} </br>
	 */
	public abstract int getRows();
	/**
	 * Return the class name of game </br>
	 * @return {@link String} </br>
	 */
	public abstract String getBoardClassName();
	
}
