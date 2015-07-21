package game.ai.shared.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import game.ai.shared.controller.specification.IGameController;
import game.ai.shared.model.Cell;
import game.ai.shared.model.GameState;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.player.specification.PairsPlayerSpecification;
import game.ai.shared.view.specification.IGameView;

public class GameController extends MouseAdapter implements IGameController {

	IBoard board;
	boolean canPlay=false;
	PairsPlayerSpecification pairsPlayer;
	
	public GameController(IBoard board, PairsPlayerSpecification pairsPlayer){
		if (board == null || pairsPlayer == null){
			throw new IllegalArgumentException("Input paramater is null.");
		}
		this.pairsPlayer = pairsPlayer;
		this.pairsPlayer.addController(this);
		this.board = board;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (board.getGameState() == GameState.PLAYING){
//			if (!pairsPlayer.isPlaying()){
//				pairsPlayer.play(board);
//				return;
//			}
			//if (pairsPlayer.isPlaying()){
		        int rowSelected = e.getY() / IGameView.CELL_SIZE;
		        int colSelected = e.getX() / IGameView.CELL_SIZE;
		       
				//check the position is valid
		        if (rowSelected < board.getRows() && colSelected < board.getCols()){
		        	 //System.out.println("event is called");
		        	//Cell cell = new Cell(rowSelected, colSelected);
		        	//check cell is free
		        	if (board.isEmptyCell(rowSelected, colSelected)){
		        		board.update(new Cell(rowSelected, colSelected));
		        		canPlay = true;
		        	}
		        }
		}else{
			board.clear();
			e.getComponent().repaint();
			//pairsPlayer.play(board);
		}

	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.controller.specification.IGameController#play()
	 */
	@Override
	public void play() {
		pairsPlayer.play(board);
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.controller.specification.IGameController#canPlay()
	 */
	@Override
	public boolean canPlay() {
		return canPlay;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.controller.specification.IGameController#setCanPlay(boolean)
	 */
	@Override
	public void setCanPlay(boolean flag) {
		this.canPlay = flag;
	}

}
