package game.ai.shared.model.specification;

import java.awt.Graphics;
import java.util.Observer;

import game.ai.shared.model.Cell;
import game.ai.shared.model.PlayerState;
import game.ai.shared.model.GameState;
import game.ai.shared.player.IPlayer;
import game.ai.shared.rules.specification.HasInvariant;
import game.ai.shared.view.specification.IGameView;

/**
 * This class is an interface that defines methods for Model </br>
 * @author btdiem
 * @version 1.0
 */
public interface IBoard extends HasInvariant{

	/**
	 * 
	 * @return all the {@link Cell} of {@link IBoard} </br>
	 */
	public Cell[][] getCells();
	/**
	 * Return true if there is no any empty {@link Cell} in {@link IBoard} </br>
	 * @return {@link Boolean} </br>
	 */
	public boolean isDraw();
	/**
	 * Return true if {@link IBoard#isWin()} is true or {@link IBoard#isDraw()} is true </br>
	 * @return {@link Boolean} </br>
	 */
	public boolean isGameOver();
	/**
	 * Update the value of current {@link IPlayer} for cell</br>
	 * @param {{@link Cell} </br>
	 */
	public void updateCell(Cell cell);
   /**
    * Update the value for board and current {@link IPlayer} </br>
    * Notify the changes to all Views in list of {@link Observer} </br>
    */
	public void update(Cell cell);
	/**
	 * Return the value of current {@link IPlayer} </br>
	 * @return {@link PlayerState} </br>
	 */
	public PlayerState getPlayerState();
	/**
	 * Return the value of opponent of current {@link IPlayer} </br>
	 * @return {@link IPlayer} </br>
	 */
	public PlayerState getOpponentState();
	/**
	 * Return true of there a winning row in {@link IBoard} </br>
	 * @return {@link Boolean} </br>
	 */
	public boolean checkRows();
	/**
	 * Return true if there is a winning column in {@link IBoard} </br>
	 * @return {@link Boolean} </br>
	 */
	public boolean checkCols();
	/**
	 * Return true if there is a winning diagonal row in {@link IBoard}</br>
	 * @return {@link Boolean} </br>
	 */
	public boolean checkDiagonals();
	/**
	 * Return true if there is a winning low diagonal row, from the left to the right of {@link IBoard} </br>
	 * @return {@link Boolean} </br>
	 */
	public boolean checkLowDiagonals();
	/**
	 * Return true if there is a winning high diagonal row, from the right to the left of {@link IBoard} </br>
	 * @return {@link Boolean} </br>
	 */
	public boolean checkHighDiagonals();	
	/**
	 * Return true if the current {@link IPlayer} wins </br>
	 * @return {@link Boolean} </br>
	 */
	public boolean isWin();
	/**
	 * Return an array of empty {@link Cell} int the {@link IBoard}</br>
	 * @return array {@link Cell} </br>
	 */
	public Cell[] getOpenSpaces();
	/**
	 * Set {@link PlayerState#EMPTY} for the arrays of {@link Cell} in {@link IBoard}</br>
	 * @param cell - {@link Cell} </br>
	 */
	public void clearCell(Cell cell);
	/**
	 * Return a copy value and state of {@link IBoard}  </br>
	 * @return {@link IBoard} </br>
	 */
	public IBoard toCopy();
	/**
	 * Return the center {@link Cell} of {@link IBoard}
	 * @return {@link Cell}
	 */
	public Cell getCenter();
	/**
	 * Return the array of corner {@link Cell} of {@link IBoard} </br>
	 * @return array of {@link Cell} </br>
	 */
	public Cell[] getCorners();
	/**
	 * Open the view and trigger the player starts playing</br>
	 * @param view - {@link IGameView} </br>
	 */
	public void updateView(IGameView view);
	 /** This method will repaint {@link IBoard} when there is any change </br>
	 * @param g - {@link Graphics}
	 */
	public void paint(Graphics g);
	/**
	 * Return true if {@link Cell#getValue()} == {@link PlayerState#EMPTY} </br>
	 * @param selectedRow - a row selected by a user </br>
	 * @param selectedCol - a column selected by a user </br>
	 * @return {@link Boolean} </br>
	 */
	public boolean isEmptyCell(int selectedRow, int selectedCol);
	/**
	 * 
	 * @return the number of rows of {@link IBoard}
	 */
	public int getRows();
	/**
	 * 
	 * @return the number of cols of {@link IBoard}
	 */
	public int getCols();
	/**
	 * 
	 * @param posX - horiziontal position of {@link Cell} on {@link IBoard} </br>
	 * @param posY - Vertical position of {@link Cell} on {@link IBoard} </br>
	 * @return {@link Cell}
	 */
	public Cell getCellValue(int posX, int posY);
	/**
	 * 
	 * @return {@link GameState} value of {@link IBoard}
	 */
	public GameState getGameState();
	/**
	 * 
	 * @return connector value
	 */
	public int getConnector();
	/**
	 * Set a new value for current {@link IPlayer} </br>
	 * @param currentPlayer - {@link PlayerState} </br>
	 */
	public void updatePlayer(PlayerState currentPlayer);
	/**
	 * clear all the value of arrays of {@link Cell} </br>
	 * The current {@link PlayerState} is {@link PlayerState#EMPTY}
	 */
	public void clear();

}
