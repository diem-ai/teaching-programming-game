package game.ai.connect4.factory;

import game.ai.shared.factory.specification.AbstractGameFactory;
import game.ai.shared.factory.specification.IGameFactory;
/**
 * This is an implementation of {@link IGameFactory} and {@link AbstractGameFactory} </br>
 * It sets the specific values for game </br>
 * @author btdiem </br>
 * @version </br>
 *
 */
public class Connect4GameFactory extends AbstractGameFactory implements IGameFactory{
	
	final String className = "game.ai.connect4.model.Connect4Board";
	final int cols = 7;
	final int rows = 7;
	final int connector = 4;
	final String title = "Connect4 Game";
	/*
	 * (non-Javadoc)
	 * @see game.ai.factory.specification.IGameFactory#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.factory.AbstractGameFactory#getConnector()
	 */
	@Override
	public int getConnector() {
		
		return connector;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.factory.AbstractGameFactory#getCols()
	 */
	@Override
	public int getCols() {		
		return cols;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.factory.AbstractGameFactory#getRows()
	 */
	@Override
	public int getRows() {		
		return rows;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.factory.AbstractGameFactory#getBoardClassName()
	 */
	@Override
	public String getBoardClassName() {	
		return className;
	}
	//create a singleton for each call
	private static class LazyHolder {
        public static final Connect4GameFactory INSTANCE = new Connect4GameFactory();
	}
	public static Connect4GameFactory getInstance(){
		return LazyHolder.INSTANCE;
	}


}
