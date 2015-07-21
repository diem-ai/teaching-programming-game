package game.ai.shared.model;

import game.ai.shared.model.specification.IBoard;
import game.ai.shared.view.specification.IGameView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * The main implementation is derived from {@link http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html#zz-5.2} </br>
 * Look at class Cell.java for more detail </br>
 * The idea is that asks the {@link Cell} draws when {@link IBoard} is changed </br>
 * @author btdiem
 * @version 1.0
 */
public  class Cell {

	private int col;
	private int row;
	private PlayerState value;
	
	public Cell(int row, int col){
		this.col = col;
		this.row = row;
		value = PlayerState.EMPTY;
	}
	public Cell(int row, int col, PlayerState state){
		this(row, col);
		value = state;
	}
	
	public PlayerState getValue() {
		return value;
	}
	public void setValue(PlayerState value) {
		this.value = value;
	}
	public int getCol() {
		return col;
	}
	public int getRow() {
		return row;
	}
	public void clear(){
		value = PlayerState.EMPTY;
	}
	public void paint(Graphics g){
		// Use Graphics2D which allows us to set the pen's stroke
	      Graphics2D g2d = (Graphics2D)g;
	      g2d.setStroke(new BasicStroke(IGameView.SYMBOL_STROKE_WIDTH,
	            BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); // Graphics2D only
	      // Draw the Seed if it is not empty
	      int x1 = col * IGameView.CELL_SIZE + IGameView.CELL_PADDING;
	      int y1 = row * IGameView.CELL_SIZE + IGameView.CELL_PADDING;
	      if (value == PlayerState.CROSS) {
	         g2d.setColor(Color.RED);//should read from configuration
	         int x2 = (col + 1) * IGameView.CELL_SIZE - IGameView.CELL_PADDING;
	         int y2 = (row + 1) * IGameView.CELL_SIZE - IGameView.CELL_PADDING;
	         g2d.drawLine(x1, y1, x2, y2);
	         g2d.drawLine(x2, y1, x1, y2);
	      } else if (value == PlayerState.NOUGHT) {
	         g2d.setColor(Color.BLUE);
	         g2d.drawOval(x1, y1, IGameView.SYMBOL_SIZE, IGameView.SYMBOL_SIZE);
	         g2d.fillOval(x1, y1, IGameView.SYMBOL_SIZE, IGameView.SYMBOL_SIZE);
	      }
	}

}
