package game.ai.shared.player;

import game.ai.shared.model.PlayerState;
import game.ai.shared.controller.specification.*;
/**
 * This class extends from {@link IPlayer}, implement some methods shared by {@link AIPlayer} and {@link HumanPlayer} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */

public abstract class AbstractPlayer implements IPlayer{

	protected PlayerState playerState;
	protected IPlayer opponent;
	protected IGameController controller;
	/**
	 * When creating an {@link IPlayer} instance, 
	 * default value is {@link PlayerState#EMPTY} </br>
	 */
	public AbstractPlayer(){
		playerState = PlayerState.EMPTY;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.player.IPlayer#setState(game.ai.shared.model.PlayerState)
	 */
	@Override
	public void setState(PlayerState state){
		playerState = state;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.player.IPlayer#getState()
	 */
	@Override
	public PlayerState getState(){
		return playerState;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.player.IPlayer#setOpponent(game.ai.shared.player.IPlayer)
	 */
	@Override
	public void setOpponent(IPlayer opponent) {
		this.opponent = opponent;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.player.IPlayer#getOpponent()
	 */
	@Override
	public IPlayer getOpponent() {
		return this.opponent;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playerState.hashCode();
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractPlayer other = (AbstractPlayer) obj;
		if (playerState != other.playerState)
			return false;
		return true;
	}
	@Override
	public void addController(IGameController controller) {
		this.controller = controller;
	}
	
	
	
}
