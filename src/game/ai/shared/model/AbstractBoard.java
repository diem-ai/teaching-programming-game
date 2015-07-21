package game.ai.shared.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import game.ai.connect4.model.Connect4Board;
import game.ai.shared.model.specification.IBoard;
import game.ai.shared.view.specification.IGameView;
import game.ai.tictactoe.model.TictactoeBoard;
/**
 * This abstract class will implement common methods shared for </br> 
 * Both {@link TictactoeBoard} and {@link Connect4Board} extending from {@link IBoard} </br>
 * @author btdiem </br>
 * @version 1.0
 *
 */
public abstract class AbstractBoard extends Observable implements IBoard {

	protected Cell[][] cells;
	protected int rows;
	protected int cols;
	protected int connector;
	protected GameState gameState;
	protected PlayerState playerState;

	/**
	 * Returns a default row value for each game </br>
	 * This method should be implemented by a specific class </br>
	 * @return {@link Integer} </br>
	 */
	public abstract int getDefaultRows();
	/**
	 * Return a default column value for each game </br>
	 * This method should be implemented by a specific class </br>
	 * @return {@link Integer} </br>
	 */
	public abstract int getDefaultCols();
	/**
	 * Return a default connector </br>
	 * This method should be implemented by a specific class </br>
	 * @return {@link Integer} </br>
	 */
	public abstract int getDefaultConnector();
	
	public AbstractBoard(int rows, int cols, int connector){
		initialize(rows, cols, connector);
		gameState = GameState.PLAYING;

	}
//	public AbstractBoard(int rows, int cols, int connector, IPen pen){
//		this(rows, cols, connector);
//		
//		//playerState = getRandomPlayerState();
//		//System.out.println("Who takes " + playerState + " will go first");
//	}
	
	protected void setCells(Cell[][] cells){
		this.cells = cells;
	}
	/**
	 * The method will check the valid imput data </br>
	 * If they are invalid, they are assigned a default value </br>
	 * The initial value of arrays of {@link Cell} is {@link PlayerState#EMPTY} </br>
	 */
	private void initialize(int rows, int cols, int connector) {
		if (rows <= 0 ){
			rows = getDefaultRows();
		}
		if (cols <= 0){
			cols = getDefaultCols();
		}
		if (connector <= 0){
			connector = getDefaultConnector();
		}
		this.rows = rows;
		this.cols = cols;
		this.connector = connector;
		if (!invariant()){
			throw new IllegalArgumentException("Constructor is not safe.");
		}
		cells = new Cell[rows][cols];
		for(int i=0; i < rows; i++){
			for (int j=0; j < cols; j++){
				cells[i][j] = new Cell(i,j);
			}
		}
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#getConnector()
	 */
	@Override
	public int getConnector() {
		return this.connector;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#getCells()
	 */
	@Override
	public Cell[][] getCells(){
		return this.cells;
	}
   /*
    * (non-Javadoc)
    * @see game.ai.shared.model.specification.IBoard#isDraw()
    */
   public boolean isDraw() {
      for (int row = 0; row < rows; row++) {
         for (int col = 0; col < cols; col++) {
            if (cells[row][col].getValue() == PlayerState.EMPTY) {
              return false; // an empty seed found, not a draw, exit
            }
         }
      }
      return true; 
   }

   /*
    * (non-Javadoc)
    * @see game.ai.shared.model.specification.IBoard#update(game.ai.shared.model.Cell)
    */
   @Override  
   public void update(Cell cell){
	   updateCell(cell);
	   this.setChanged();
	   notifyObservers();
   }
   /*
    * (non-Javadoc)
    * @see game.ai.shared.model.specification.IBoard#updateCell(game.ai.shared.model.Cell)
    */
   @Override
   public void updateCell(Cell cell){
	   cells[cell.getRow()][cell.getCol()].setValue(playerState);
   }
   @Override
	public void paint(Graphics g) {
	      // Draw the grid-lines
	  g.setColor(Color.GRAY);
      for (int row = 1; row < rows; ++row) {
          g.fillRoundRect(0, IGameView.CELL_SIZE * row - IGameView.GRID_WIDHT_HALF,
        		  IGameView.CELL_SIZE* rows - 1, IGameView.GRID_WIDTH,
                IGameView.GRID_WIDTH, IGameView.GRID_WIDTH);
       }
      for (int col = 1; col < cols; ++col) {
         g.fillRoundRect(IGameView.CELL_SIZE * col - IGameView.GRID_WIDHT_HALF, 0,
               IGameView.GRID_WIDTH, IGameView.CELL_SIZE * cols - 1,
               IGameView.GRID_WIDTH, IGameView.GRID_WIDTH);
      }
      for (int row = 0; row < rows; row++) {
          for (int col = 0; col < cols; col++) {
        	  cells[row][col].paint(g);
          }
       }      
	}
   /*
    * (non-Javadoc)
    * @see game.ai.shared.model.specification.IBoard#clear()
    */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		  for (int row = 0; row < rows; ++row) {
		         for (int col = 0; col < cols; ++col) {
		        	 cells[row][col].setValue(PlayerState.EMPTY);
		         }
		     }
		  //this.playerState = PlayerState.EMPTY;
		  this.gameState = GameState.PLAYING;
		  
	}
/*
    * (non-Javadoc)
    * @see game.ai.shared.model.specification.IBoard#isWin()
    */
   @Override
   public boolean isWin(){
	   return checkRows() || checkCols() || checkDiagonals();
   }
   /*
    * (non-Javadoc)
    * @see game.ai.shared.model.specification.IBoard#checkRows()
    */
   @Override
   public boolean checkRows(){
	   for (int row=0; row < rows; row ++){
		   for (int col=0; col < cols; col ++){
			   int mark=0;
			   boolean found = true;
			   while((mark < connector) && found){
				   found = found && (col+mark<cols) && (cells[row][col+mark].getValue() == playerState);
				   mark ++;
			   }
			   if(found && mark==connector) return true;
		   }
	   }
	   return false;
   }
   /*
    * (non-Javadoc)
    * @see game.ai.shared.model.specification.IBoard#checkCols()
    */
   @Override
   public boolean checkCols(){
	   for (int col=0; col < cols; col ++){
		   for (int row=0; row < rows; row ++){
			   int mark=0;
			   boolean found = true;
			   while((mark < connector) && found){
				   found = found && (row+mark<rows) 
						   && (cells[row+mark][col].getValue() == playerState);
				   mark++;
			   }
			   if(found && mark==connector) return true;
		   }
	   }
	   return false;
   }
   /*
    * (non-Javadoc)
    * @see game.ai.shared.model.specification.IBoard#checkDiagonals()
    */
   @Override
   public boolean checkDiagonals(){
	   return checkLowDiagonals() || checkHighDiagonals();
   }
   /*
    * (non-Javadoc)
    * @see game.ai.shared.model.specification.IBoard#checkLowDiagonals()
    */
   @Override
   public boolean checkLowDiagonals(){
	   for (int row=0; row < rows ; row ++){
		   for (int col=0; col < cols; col ++){
			   int mark = 0;
			   boolean found = true;
			   while(mark < connector && found){
				   found = found && (row+mark<rows && col+mark<cols) 
						   && cells[row+mark][col+mark].getValue()==playerState;
				   mark++;
			   }
			   if (found && mark==connector) return true;
		   }
	   }
	   return false;
   }
   /*
    * (non-Javadoc)
    * @see game.ai.shared.model.specification.IBoard#checkHighDiagonals()
    */
   @Override
   public boolean checkHighDiagonals(){
	   for (int row=rows-1; row >=0; row --){
		   for (int col=0; col < cols; col ++){
			   int mark = 0;
			   boolean found = true;
			   while(mark < connector && found){
				   found = found && (row-mark>=0 && col+mark<cols) && cells[row-mark][col+mark].getValue() == playerState;
				   mark++;
			   } 
			   if (found && mark==connector) return true;		  
		   }
		   
	   }
	   return false;
   }
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#updateView(game.ai.shared.view.IGameView)
	 */
	@Override
	public void updateView(IGameView view) {
		view.display();
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#isGameOver()
	 */
	@Override
	public boolean isGameOver(){
		return isDraw() || isWin();
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#getOpenSpaces()
	 */
	@Override
	public Cell [] getOpenSpaces(){
		
		List<Cell> openSpaces = new ArrayList<Cell>();
		for (int i=0; i<rows; i++){
			   for (int j=0; j<cols; j++){
				   if(cells[i][j].getValue() == PlayerState.EMPTY){
					   openSpaces.add(cells[i][j]);
				   }
			   }
		}
		return openSpaces.toArray(new Cell[]{});
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#clearCell(game.ai.shared.model.Cell)
	 */
	@Override
	public void clearCell(Cell iCell) {
		cells[iCell.getRow()][iCell.getCol()].setValue(PlayerState.EMPTY);
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#getPlayerState()
	 */
	@Override
	public PlayerState getPlayerState() {
		return this.playerState;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#getOpponentState()
	 */
	@Override
	public PlayerState getOpponentState() {
		return (playerState == PlayerState.CROSS ? 
				PlayerState.NOUGHT : PlayerState.CROSS);
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#getCenter()
	 */
	@Override
	public Cell getCenter() {
		return cells[rows/2][cols/2];
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#getCorners()
	 */
	@Override
	public Cell[] getCorners() {
		return new Cell[]{cells[0][0], 
				cells[rows-1][0], 
				cells[rows-1][cols-1], 
				cells[0][cols-1]};
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#getRows()
	 */
	@Override
	public int getRows() {
		return this.rows;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#getCols()
	 */
	@Override
	public int getCols() {
		return this.cols;
	}	
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#getCellValue(int, int)
	 */
	@Override
	public Cell getCellValue(int posX, int posY) {
		
		if (posX < 0 || posX >= rows || posY < 0 || posY >= cols){
			return null ;
		}
		return cells[posX][posY];
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#getGameState()
	 */
	@Override
	public GameState getGameState() {
		if (isWin()){
			gameState = (playerState==PlayerState.CROSS) ? GameState.CROSS_WIN : GameState.NOUGHT_WIN; 
		}else if (isDraw()){
			gameState = GameState.DRAW;
		}else{
			gameState = GameState.PLAYING;
		}
		return gameState;
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.rules.specification.HasInvariant#invariant()
	 */
	@Override
	public boolean invariant() {
		return 	( connector <= rows ) && ( connector <= cols );
	}
	/*
	 * (non-Javadoc)
	 * @see game.ai.shared.model.specification.IBoard#updatePlayer(game.ai.shared.model.PlayerState)
	 */
	@Override
	public void updatePlayer(PlayerState currentPlayer) {
		this.playerState = currentPlayer;
	}
	
}
