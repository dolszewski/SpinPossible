import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;





class SpinController extends TimerTask implements MouseListener, SpinControllerInterface, ActionListener{

    private JFrame gameJFrame;
    private JPanel gameContentPane;
    private boolean gameIsReady = false;
    private Stack spinStack = new Stack();
    private Board theBoard = new Board();
    private int[][] selectedArray = new int[2][2];
    private int numberSelected = 0;
    private int gameWidth = 800;
    private int gameHeight = 400;
    private java.util.Timer gameTimer = new java.util.Timer();
    private Board b;
    private JButton spinButton;
    private JButton undoButton;
    private int buttonSizeX = 100;
    private int buttonSizeY = 60;
    private Font buttonFont = new Font("Tahoma", Font.BOLD, 20);
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem newGameItem;
    private JMenuItem exitItem;
    private int numberHighlightedTiles = 0;
    

	public static void main(String[] args) {
		SpinController spin = new SpinController();
	}
	public SpinController() {
		b = new Board();
		gameJFrame = new JFrame();
		gameJFrame.setSize(gameWidth, gameHeight);
		gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameContentPane = new myPane();
		gameJFrame.setContentPane(gameContentPane);
		gameContentPane.setLayout(null);
		spinButton = new JButton();
		spinButton.setText("SPIN");
		spinButton.setFont(buttonFont);
		spinButton.setBounds(gameWidth/2 + 100, gameHeight/3 -50 ,buttonSizeX, buttonSizeY);
		spinButton.addActionListener(this);
		undoButton = new JButton();
		undoButton.setText("UNDO");
		undoButton.setFont(buttonFont);
		undoButton.setBounds(gameWidth/2 + 100, gameHeight/3 *2 -50 ,buttonSizeX, buttonSizeY);
		undoButton.addActionListener(this);
		gameContentPane.add(spinButton);
		gameContentPane.add(undoButton);
		newGameItem = new JMenuItem();
        newGameItem.setText("New Game");
        newGameItem.addActionListener(this);
        exitItem = new JMenuItem();
        exitItem.setText("Exit");
        exitItem.addActionListener(this);
        menu = new JMenu();
        menu.add(newGameItem);
        menu.add(exitItem);
        menu.setText("Menu");
        menuBar = new JMenuBar();
        menuBar.add(menu);
        gameJFrame.setJMenuBar(menuBar);
		gameJFrame.setVisible(true);
		gameTimer.schedule(this, 0, 40); 
	
		
		
		
	}
	private class myPane extends JPanel {
		private static final long serialVersionUID = 1L;
		public myPane() {
			super();
			super.setBackground(Color.WHITE);

		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			theBoard.drawBoard(g);
			
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		gameJFrame.repaint();
		gameContentPane.repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void spin() {
		// TODO Auto-generated method stub
		if(numberSelected == 0) {
			System.out.println("Nothing to spin");
		}
		else if(numberSelected == 1) {
			theBoard.getBoard()[selectedArray[0][0]][selectedArray[0][1]].changeSpin();
		}
		else if(numberSelected == 2) {
			Board tempBoard = theBoard;
			Tile[] tempArray = new Tile[numberHighlightedTiles];
			int counter = 0;
			for(int i = 0; i < tempBoard.getRows(); i++) {
				for(int j = 0; j < tempBoard.getCols(); j++) {
					if(tempBoard.getBoard()[i][j].isHighlighted()) {
						tempBoard.getBoard()[i][j].changeSpin();
						tempArray[counter] = tempBoard.getBoard()[i][j];
						counter++;
					}
				}
			}
			tempArray = switchTiles(tempArray);
			
			int minA = min(selectedArray[0][0], selectedArray[1][0]);
			int maxA = max(selectedArray[0][0], selectedArray[1][0]);
			int minB = min(selectedArray[0][1], selectedArray[1][1]);
			int maxB = max(selectedArray[0][1], selectedArray[1][1]);
			counter = 0;
			for(int i = minA; i < maxA+1; i++) {
				for(int j = minB; j < maxB+1; j++) {
					theBoard.getBoard()[i][j] = tempArray[counter];
					numberHighlightedTiles--;
					counter++;
				}
			}
		}
		
	}
	
	public Tile[] switchTiles(Tile[] a) {
		for(int i = 0; i < a.length/2; i++) {
			Tile temp = a[i];
			a[i] = a[a.length-i];
			a[a.length-i] = temp;
		}
		return a;
	}

	
	@Override
	public void highlightTiles() {
		// TODO Auto-generated method stub
		if(numberSelected == 2) {
			int minA = min(selectedArray[0][0], selectedArray[1][0]);
			int maxA = max(selectedArray[0][0], selectedArray[1][0]);
			int minB = min(selectedArray[0][1], selectedArray[1][1]);
			int maxB = max(selectedArray[0][1], selectedArray[1][1]);
			
			for(int i = minA; i < maxA+1; i++) {
				for(int j = minB; j < maxB+1; j++) {
					theBoard.getBoard()[i][j].setHighlighted(true);
					numberHighlightedTiles++;
				}
			}
		}
		else if(numberSelected == 1) {
			theBoard.getBoard()[selectedArray[0][0]][selectedArray[0][1]].setHighlighted(true);
		}

	}
	
	public int min(int a, int b) {
		if(a < b)
			return a;
		else
			return b;
	}
	
	public int max(int a, int b) {
		if(a > b)
			return a;
		else
			return b;
	}


	@Override
	public void selected(int row, int column) {
		// TODO Auto-generated method stub
		if(numberSelected == 0) { 
			selectedArray[0][0] = row;
			selectedArray[0][1] = column;
			numberSelected++;
		}
		else if(numberSelected == 1) {
			if(selectedArray[0][0] == row && selectedArray[0][1] == column)
				numberSelected--;
			else {
			selectedArray[1][0] = row;
			selectedArray[1][1] = column;
			numberSelected++;
			}
		}
		else if(numberSelected == 2) {
			if(selectedArray[1][0] == row && selectedArray[1][1] == column)
				numberSelected--;
			else if(selectedArray[0][0] == row && selectedArray[0][1] == column) {
				numberSelected--;
				selectedArray[0][0] = selectedArray[1][0];
				selectedArray[0][1] = selectedArray[1][1];
			}
			else {
				selectedArray[1][0] = row;
				selectedArray[1][1] = column;
			}
		}
	}

	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(spinButton)) {
			spin();
		}
	}
    
}
