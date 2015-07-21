package game.ai.shared.view.specification;

import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.runners.JUnit4;
/**
 * This class is an interface of View. It defines some constants to paint the View </br>
 * @author btdiem
 * @version 1.0
 */
public interface IGameView extends Observer{

		 final int VIEW_WIDTH = 500;
		 final int VIEW_HEIGHT = 500;
		 final int BORDER = 20;
	 
	   public static final int ROWS = 3;  // ROWS by COLS cells
	   public static final int COLS = 3;
	   //public static final String TITLE = "Tic Tac Toe";
	 
	   // Name-constants for the various dimensions used for graphics drawing
	   public static final int CELL_SIZE = 80; // cell width and height (square)
	   public static final int CANVAS_WIDTH = CELL_SIZE * COLS;  // the drawing canvas
	   public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
	   public static final int GRID_WIDTH = 8;  // Grid-line's width
	   public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; // Grid-line's half-width
	   // Symbols (cross/nought) are displayed inside a cell, with padding from border
	   public static final int CELL_PADDING = CELL_SIZE / 6;
	   public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;
	   public static final int SYMBOL_STROKE_WIDTH = 8; // pen's stroke width
	   /**
	    * Display this View </br>
	    */
	   public void display();
	   /**
	    * Return the {@link JPanel} inside {@link JFrame} </br>
	    * This method is exposed public for {@link JUnit4} only </br>
	    * The resource is derived from another source so it should be tested well </br>
	    * @return {@link JPanel} </br>
	    */
	   public JPanel getTemplate();
//	   /**
//	    * This method is exposed public for {@link JUnit4} only </br>
//	    * The resource is derived from another source so it should be tested well </br>
//	    * @return {@link JLabel} </br>
//	    */
//	   public JLabel getStatusBar();
}
