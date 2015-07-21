
package game.ai.shared.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;

import game.ai.shared.model.specification.*;
import game.ai.shared.controller.specification.*;
import game.ai.shared.model.PlayerState;
import game.ai.shared.model.GameState;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import game.ai.shared.view.specification.IGameView;
/**
 * The main implementation is derived from {@link http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html#zz-5.2} </br>
 * Look at GameMain.java on that website for more detail </br>
 * The changes of design and implementation are made to apply for my own design that follows MVC model </br>
 * @author btdiem
 * @version 1.0
 */
public class GameView extends JFrame implements IGameView {
	
	String title;
	JLabel statusBar; 
	IBoard board;
	IGameController controller;
	SwingTemplateJPanel swingTemplate;
	
	public GameView(String title, IBoard board, IGameController controller){
		
		this.title = title;
		this.board = board;
		this.controller = controller;
		((Observable)board).addObserver(this);
		//create view
		setTitle(title);
		//create a swing template
		swingTemplate = new SwingTemplateJPanel(controller, board);
		swingTemplate.repaint();//ask to paint table
		//add to frame
		//setContentPane(swingTemplate);
		//set close button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Setup the status bar (JLabel) to display status message
        statusBar = new JLabel("");
        statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(swingTemplate, BorderLayout.CENTER);
        cp.add(statusBar, BorderLayout.PAGE_END); // same as SOUTH

	}
	private static final long serialVersionUID = 1L;

	@Override
	public void display() {
        pack();             // "this" JFrame packs its components
        setLocationRelativeTo(null); // center the application window
        setVisible(true);            // show it
        controller.play();
	}

	@Override
	public void update(Observable o, Object arg) {
		IBoard board = (IBoard)o;
		board.paint(getGraphics());
		this.repaint();	

	}
	/**
	 * This class creates a {@link JPanel} and a StatusBar using {@link JLabel} </br>
	 * It will add {@link IGameCtroller} as a Listener </br>
	 * Repaint the {@link IBoard} when {@link IBoard} has the change </br>
	 * @author btdiem
	 * @version 1.0
	 */
	@SuppressWarnings("serial")
	class SwingTemplateJPanel extends JPanel{
		// Status Bar
		IGameController controller;
		IBoard board;
		
		public SwingTemplateJPanel(IGameController controller, IBoard board) {
			this.controller = controller;
			this.board = board;
			
			setPreferredSize(new Dimension(CELL_SIZE * board.getRows(), CELL_SIZE * board.getCols()));
			this.addMouseListener(controller);
		}
		// invoke via repaint()
		  @Override
		   public void paintComponent(Graphics g) {
			  	//System.out.println("paint is called");
		         super.paintComponent(g);    // fill background
		         setBackground(Color.WHITE); // set its background color
		         board.paint(g);
		         // Print status-bar message
		         if (board.getGameState() == GameState.PLAYING) {
		            statusBar.setForeground(Color.BLACK);
		            if (board.getPlayerState() == PlayerState.CROSS) {
		               statusBar.setText("X's Turn");
		            } else if (board.getPlayerState() == PlayerState.NOUGHT) {
		               statusBar.setText("O's Turn");
		            }
		         } else if (board.getGameState() == GameState.DRAW) {
		            statusBar.setForeground(Color.RED);
		            statusBar.setText("It's a Draw!");
		         } else if (board.getGameState() == GameState.CROSS_WIN) {
		            statusBar.setForeground(Color.RED);
		            statusBar.setText("'X' Won! Click to play again.");
		         } else if (board.getGameState() == GameState.NOUGHT_WIN) {
		            statusBar.setForeground(Color.RED);
		            statusBar.setText("'O' Won! Click to play again.");
		         }
		      }	 // Custom painting codes	 
		   
	}
	@Override
	public JPanel getTemplate() {
		// TODO Auto-generated method stub
		return this.swingTemplate;
	}

//	@Override
//	public JLabel getStatusBar() {
//		// TODO Auto-generated method stub
//		return statusBar;
//	}

}
