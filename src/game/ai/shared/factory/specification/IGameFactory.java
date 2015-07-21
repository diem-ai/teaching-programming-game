package game.ai.shared.factory.specification;

import game.ai.shared.model.specification.IBoard;
/**
 * This is an interface to produce specific game model  </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public interface IGameFactory {
	/**
	 * 
	 * @return - {@link IBoard} </br>
	 * @throws Exception </br>
	 */
	public IBoard createBoard() throws Exception;
	/**
	 * Return a game title <br> 
	 * @return {@link String} </br>
	 */
	public String getTitle();
	/**
	 * This method will start playing game </br>
	 */
	public void playGame();
	
}
