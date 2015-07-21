package game.ai.shared.player;

import game.ai.shared.model.specification.IBoard;
/**
 * This class is an implementation of {@link IPlayer} </br>
 * Human player updates board via Mouse Event
 * @author btdiem
 *
 */
public class HumanPlayer extends AbstractPlayer implements IPlayer {
	/*
	 * (non-Javadoc)</br>
	 * @see game.ai.shared.player.IPlayer#move(game.ai.shared.model.specification.IBoard)</br>
	 * For HumanPlayer, the moving is done by Human and the value is updated for IBoard by IGameController </br>
	 */
	@Override
	public void move(IBoard board) {
		while(!controller.canPlay());
		controller.setCanPlay(false);
	}
	@Override
	public String toString(){
		return "I'm a human player, I take " + getState().name();
	}

}
