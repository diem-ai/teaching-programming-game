package game.ai.shared.controller.specification;

import java.awt.event.MouseListener;

import game.ai.shared.player.HumanPlayer;
import game.ai.shared.player.specification.PairsPlayerSpecification;
/**
 * This is an interface to define the methods to handle value from {@link HumanPlayer} to game </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public interface IGameController extends MouseListener {
	/**
	 * Return value of this method is used to monitor {@link HumanPlayer} </br>
	 * @return {@link Boolean} </br>
	 */
	public boolean canPlay();
	/**
	 * Set new value for canPlay status </br>
	 * @param flag - {@link Boolean} </br>
	 */
	public void setCanPlay(boolean flag);
	/**
	 * {@link PairsPlayerSpecification} start playing game</br>
	 */
	public void play();
}
