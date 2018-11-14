import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.lang.reflect.Array;

import java.util.Random;

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
    private Stack<Integer[][]> spinStack = new Stack<Integer[][]>();
    private Stack<Integer> numberSelectedStack = new Stack<Integer>();
    private Stack<Board> undoBoard = new Stack<Board>();
    private Board theBoard = new Board();
    private int[][] selectedArray = new int[2][2];
    private int[][] tempSelectedArray = new int[2][2];
    private int numberSelected = 0;
    private int gameWidth = 800;
    private int gameHeight = 400;
    private java.util.Timer gameTimer = new java.util.Timer();
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
    private int numberOfSpins = 0;

	public static void main(String[] args) {
		SpinController spin = new SpinController();
	}
	public SpinController() {
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
		gameContentPane.addMouseListener(this);
		
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
		
		easyBoard();
		
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
		int x = e.getX();
		int y = e.getY();
		if (x < 360 && y < 360) {
		unHighlightTiles();
		selected(theBoard.colSelect(y), theBoard.rowSelect(x));

		highlightTiles();
		}
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
			counter = 0;
			for(int i = 0; i < theBoard.getRows(); i++) {
				for(int j = 0; j < theBoard.getCols(); j++) {
					if(theBoard.getBoard()[i][j].isHighlighted()) {
						theBoard.getBoard()[i][j] = tempArray[counter];
						counter++;
					}
				}
			}
			
		}

		System.out.println("I won? " + theBoard.isIdentity());

		
		theBoard.updatePositions();
	}
	public static int[][] copy(int[][] a){
		int[][] temp = {{a[0][0],a[0][1]},{a[1][0],a[1][1]}};
		return temp;
	}
	public void unHighlightTiles() {
		numberHighlightedTiles = 0;
		theBoard.getBoard()[selectedArray[0][0]][selectedArray[0][1]].setFirstHighlight(false);
		for(int i =0; i< theBoard.getRows(); i++){
			for (int j = 0; j< theBoard.getCols(); j++) {
				theBoard.getBoard()[i][j].setHighlighted(false);
			}
		}
	}

	
	public Tile[] switchTiles(Tile[] a) {
		for(int i = 0; i < a.length/2; i++) {
			Tile temp = a[i];
			a[i] = a[a.length-1-i];
			a[a.length-1-i] = temp;
		}
		return a;
	}


	
	@Override
	public void highlightTiles() {
		theBoard.getBoard()[selectedArray[0][0]][selectedArray[0][1]].setFirstHighlight(true);
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
		//theBoard.updatePositions();
	}

	


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(spinButton)) {
			spinStack.push(convert(selectedArray));
			numberSelectedStack.push(numberSelected);
			spin();
			numberOfSpins++;
		}
		if( e.getSource().equals(exitItem)) {
			System.exit(0);
		}
		if(e.getSource().equals(newGameItem)){
			
		}
		if(e.getSource().equals(undoButton)) {
			if(!spinStack.isEmpty()) {
				unHighlightTiles();
				selectedArray = convertBack(spinStack.pop());
				numberSelected = numberSelectedStack.pop();
				highlightTiles();

				spin();
				numberOfSpins--;

			}
			else
				System.out.println("Nothing to undo!");
		}
		
	}
	

	public Integer[][] convert(int[][] a){
		Integer[][] b = new Integer[2][2];
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length; j++) {
				b[i][j] = (int) a[i][j];
			}
		}
		return b;
	}
	
	public int[][] convertBack(Integer[][] a){
		int[][] b = new int[2][2];
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length; j++) {
				b[i][j] = (int) a[i][j];
			}
		}
		return b;
	}
	
	public void easyBoard() {
		int[][] tempSelectedArray = new int[2][2];
		int max = 5;
		int min = 3;
		Random randomNum = new Random();
		int numSpins = max - randomNum.nextInt(min);
		for(int i = 0; i < numSpins; i++) {
			unHighlightTiles();
			tempSelectedArray[0][0] = randomNum.nextInt(3);
			tempSelectedArray[0][1] = randomNum.nextInt(3);
			tempSelectedArray[1][0] = randomNum.nextInt(3);
			tempSelectedArray[1][1] = randomNum.nextInt(3);
			selectedArray = tempSelectedArray;
			numberSelected = 2;
			highlightTiles();
			spin();
		}
		unHighlightTiles();
		selectedArray = new int[2][2];
		numberSelected = 0;
	}
	
	public void hardBoard() {
		int[][] tempSelectedArray = new int[2][2];
		int max = 10;
		int min = 8;
		Random randomNum = new Random();
		int numSpins = max + randomNum.nextInt(min);
		for(int i = 0; i < numSpins; i++) {
			unHighlightTiles();
			tempSelectedArray[0][0] = randomNum.nextInt(3);
			tempSelectedArray[0][1] = randomNum.nextInt(3);
			tempSelectedArray[1][0] = randomNum.nextInt(3);
			tempSelectedArray[1][1] = randomNum.nextInt(3);
			selectedArray = tempSelectedArray;
			numberSelected = randomNum.nextInt(2)+1;
			highlightTiles();
			spin();
		}
		unHighlightTiles();
		selectedArray = new int[2][2];
		numberSelected = 0;
	}
    
}
