package game.ai.shared.player;

import java.util.Random;

import game.ai.shared.controller.specification.IGameController;
import game.ai.shared.model.PlayerState;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.player.specification.PairsPlayerSpecification;

/**
 * This class is an implementation of {@link PairsPlayerSpecification} </br>
 * Two {@link IPlayer} will play until {@link IBoard#getGameState()} is over </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public class PairsPlayer implements PairsPlayerSpecification {

	IPlayer first;
	IPlayer second;
	boolean turn= true;
	/**
	 * Instead of randomly choosing the value for {@link PairsPlayerSpecification} </br>
	 * I made the decision that {@link PlayerState#CROSS} will be assigned for the first {@link IPlayer} </br>
	 * @param first - {@link IPlayer} </br>
	 * @param second - {@link IPlayer} </br>
	 */
	public PairsPlayer(IPlayer first, IPlayer second){
		if (first == null || second == null){
			throw new IllegalArgumentException("Input parameter is null.");
		}
		this.first = first;
		this.second = second;
		initialize();
	}
	/**
	 * Set the {@link PlayerState} value for each {@link IPlayer} </br>
	 */
	private void initialize(){
		
		PlayerState firstState = getRandomPlayerState();
		PlayerState secondState = (firstState == PlayerState.CROSS ? PlayerState.NOUGHT : PlayerState.CROSS);
		this.first.setState(firstState);
		this.second.setState(secondState);
		this.first.setOpponent(second);
		this.second.setOpponent(first);
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.player.specification.PairsPlayerSpecification#getFirstPlayer()
	 */
	@Override
	public IPlayer getFirstPlayer() {
		return this.first; 
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.player.specification.PairsPlayerSpecification#getSecondPlayer()
	 */
	@Override
	public IPlayer getSecondPlayer() {
		return this.second;
	}

	@Override
	public void play(IBoard board) {

		while(!board.isGameOver()){
			if(turn){
				board.updatePlayer(first.getState());
				first.move(board);
			}else{
				board.updatePlayer(second.getState());
				second.move(board);
			}
			turn = !turn;
		}//while
		//finish game
		//board.clear();
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#getRandomPlayerState()
	 */
	@Override
	public PlayerState getRandomPlayerState() {
		
		int ran = new Random().nextInt(2);
		if (ran == 0){
			return PlayerState.CROSS;
		}else{
			return PlayerState.NOUGHT;
		}
	}
	@Override
	public void addController(IGameController controller) {
		first.addController(controller);
		second.addController(controller);
	}

}
