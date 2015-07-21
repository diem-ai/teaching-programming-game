package game.ai.tictactoe.factory;

import game.ai.shared.factory.specification.*;

public class TictactoeGameFactory extends AbstractGameFactory implements IGameFactory{

	final String className = "game.ai.tictactoe.model.TictactoeBoard";
	final int cols = 3;
	final int rows = 3;
	final int connector = 3;
	final String title = "Tictactoe Game";
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
        public static final TictactoeGameFactory INSTANCE = new TictactoeGameFactory();
	}

	public static TictactoeGameFactory getInstance(){
		return LazyHolder.INSTANCE;
	}

//	@Override
//	public IPen createGraphicPen() {
//		// TODO Auto-generated method stub
//		return new TictactoePen();
//	}

}
