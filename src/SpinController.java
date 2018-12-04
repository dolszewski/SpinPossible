import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;






class SpinController extends TimerTask implements MouseListener, SpinControllerInterface, ActionListener{

    private JFrame gameJFrame;
    private JPanel gameContentPane;
    private boolean gameIsReady = false;
    private Stack<Integer[][]> spinStack = new Stack<Integer[][]>();
    private Stack<Integer> numberSelectedStack = new Stack<Integer>();
    private Stack<Board> undoBoard = new Stack<Board>();
    private Board theBoard;
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
    private Object options[] = {"1", "2", "3"};
    private int numberHighlightedTiles = 0;
    private int numberOfSpins = 0;
    private int row = 3;
    private int column = 3;
    private int rowLength =0;
    private int colLength = 0;
    private int initialSpins = 0;
    private boolean isFree;
    private int[][] holderBoard;


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
			if (gameIsReady){
				theBoard.drawBoard(g);
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (gameIsReady) {
		    gameJFrame.repaint();
		    gameContentPane.repaint();
		} else {
			startGame();
			//JOptionPane.showMessageDialog(gameJFrame, "Select your game size");
			
		}
		
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

		
		if (y < rowLength && x < colLength) {
			unHighlightTiles();
			selected(theBoard.colSelect(y, rowLength),theBoard.rowSelect(x, colLength));


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
		if(theBoard.isIdentity() && !isFree) {
			int option = JOptionPane.showConfirmDialog(gameJFrame, "You Won!!! \n It took " + (numberOfSpins) + " spins. \n Would you like to try again?", "", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION) {
				for(int i = 0; i < row; i++) {
					for(int j = 0; j < column; j++) {
						theBoard.getBoard()[i][j].setValue(holderBoard[i][j]);
					}
				}
				emptySpinStack();
				numberSelected = 0;
				selectedArray = new int[2][2];
				numberOfSpins = 0;
				unHighlightTiles();
			}
			else
				gameIsReady = false;
		}

		
		theBoard.updatePositions();
	}
	public static int[][] copy(int[][] a){
		int[][] temp = {{a[0][0],a[0][1]},{a[1][0],a[1][1]}};
		return temp;
	}
	public void unHighlightTiles() {
		numberHighlightedTiles = 0;
		//theBoard.getBoard()[selectedArray[0][0]][selectedArray[0][1]].setFirstHighlight(false);
		for(int i =0; i< theBoard.getRows(); i++){
			for (int j = 0; j< theBoard.getCols(); j++) {
				theBoard.getBoard()[i][j].setHighlighted(false);
				theBoard.getBoard()[i][j].setFirstHighlight(false);
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
		if(numberSelected == 2) {
			theBoard.getBoard()[selectedArray[0][0]][selectedArray[0][1]].setFirstHighlight(true);
			theBoard.getBoard()[selectedArray[1][0]][selectedArray[1][1]].setFirstHighlight(true);
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
			theBoard.getBoard()[selectedArray[0][0]][selectedArray[0][1]].setFirstHighlight(true);
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
			if(numberSelected > 0) {
				spinStack.push(convert(selectedArray,2,2));
				numberSelectedStack.push(numberSelected);
				numberOfSpins++;
				spin();
				System.out.println("Spin: " + numberOfSpins);
			}
			else
				System.out.println("Nothing to spin!");
		}
		if( e.getSource().equals(exitItem)) {
			saveBoard("Test");
			System.exit(0);
		}
		if(e.getSource().equals(newGameItem)){
			emptySpinStack(); //Need to write this function
			startGame();
		}
		if(e.getSource().equals(undoButton)) {
			if(!spinStack.isEmpty()) {
				unHighlightTiles();
				selectedArray = convertBack(spinStack.pop(),2,2);
				numberSelected = numberSelectedStack.pop();
				highlightTiles();

				spin();
				numberOfSpins--;
				System.out.println("Spin: " + numberOfSpins);

			}
			else
				System.out.println("Nothing to undo!");
		}
		
	}
	

	public void emptySpinStack() {
		// TODO Auto-generated method stub
		while(!spinStack.isEmpty()) {
			spinStack.pop();
			numberSelectedStack.pop();
		}
		
	}
	public Integer[][] convert(int[][] a, int row, int col){
		Integer[][] b = new Integer[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				b[i][j] = (int) a[i][j];
			}
		}
		return b;
	}
	
	public int[][] convertBack(Integer[][] a, int row, int col){
		int[][] b = new int[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				b[i][j] = (int) a[i][j];
			}
		}
		return b;
	}
	
	public Tile[][] convertBoard(Tile[][] a, int row, int col){
		Tile[][] b = new Tile[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				b[i][j] = (Tile) a[i][j];
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
		initialSpins = numSpins;
		for(int i = 0; i < numSpins; i++) {
			unHighlightTiles();
			tempSelectedArray[0][0] = randomNum.nextInt(row);
			tempSelectedArray[0][1] = randomNum.nextInt(column);
			tempSelectedArray[1][0] = randomNum.nextInt(row);
			tempSelectedArray[1][1] = randomNum.nextInt(column);
			selectedArray = tempSelectedArray;
			numberSelected = 2;
			highlightTiles();
			spin();
		}
		unHighlightTiles();
		selectedArray = new int[2][2];
		numberSelected = 0;
		System.out.println("Can beat " + initialSpins + " spins?");
	}
	
	public void hardBoard() {
		int[][] tempSelectedArray = new int[2][2];
		int max = 10;
		int min = 8;
		Random randomNum = new Random();
		int numSpins = max + randomNum.nextInt(min);
		initialSpins = numSpins;
		for(int i = 0; i < numSpins; i++) {
			unHighlightTiles();
			tempSelectedArray[0][0] = randomNum.nextInt(row);
			tempSelectedArray[0][1] = randomNum.nextInt(column);
			tempSelectedArray[1][0] = randomNum.nextInt(row);
			tempSelectedArray[1][1] = randomNum.nextInt(column);
			selectedArray = tempSelectedArray;
			numberSelected = randomNum.nextInt(2)+1;
			highlightTiles();
			spin();
		}
		unHighlightTiles();
		selectedArray = new int[2][2];
		numberSelected = 0;
		System.out.println("Can beat " + initialSpins + " spins?");
	}
    
	public void startGame() {
		JTextField rows = new JTextField();
		JTextField columns = new JTextField();
		Object[] options = {"Rows:", rows, "Columns: ", columns};
		boolean gameSetUpDone = false;
		while(!gameSetUpDone) {
			int option = JOptionPane.showConfirmDialog(gameJFrame, options, "New Game", JOptionPane.OK_CANCEL_OPTION);
			if (option != JOptionPane.CANCEL_OPTION){

				
				if(rows.getText().isEmpty() ||columns.getText().isEmpty() ){
					JOptionPane.showMessageDialog(gameJFrame, "You did not enter valid rows or columns. Please enter an integer less than or equal to 3");

				}else if (Integer.parseInt(rows.getText()) > 3 || Integer.parseInt(columns.getText()) >3 ){
					JOptionPane.showMessageDialog(gameJFrame, "You did not enter valid rows or columns. Please enter an integer less than or equal to 3");
				} else {
					row = Integer.parseInt(rows.getText());
					column = Integer.parseInt(columns.getText());
					gameSetUpDone = true;
				}
				
			} else {
				
			}
		}
		
		
		Object[] difficulty = {"Free", "Easy","Hard"};
		String gamePlay = (String)JOptionPane.showInputDialog(gameJFrame, "Select Difficulty","Customized Dialog", JOptionPane.PLAIN_MESSAGE, null, difficulty, "Free");
		theBoard = new Board(row, column);
		if (gamePlay.equals("Easy")){
			while(theBoard.isIdentity())
				easyBoard();
			isFree = false;
		} else if (gamePlay.equals("Hard")) {
			while(theBoard.isIdentity())
				hardBoard();
			isFree = false;
		} else if (gamePlay.equals("Free"))
			isFree = true;
		rowLength = theBoard.getRowLength();
		colLength = theBoard.getColLength();
		emptySpinStack();
		numberSelected = 0;
		selectedArray = new int[2][2];
		numberOfSpins = 0;
		holderBoard = new int[row][column];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				holderBoard[i][j] = theBoard.getBoard()[i][j].getValue();
			}
		}
		holderBoard = convertBack(convert(holderBoard,row,column),row,column);
		gameIsReady = true;
		loadNames();
	}
	
	public void saveBoard(String name) {
		PrintWriter writer;
		try {
			String filename = "savedBoard.txt";
			Queue<String> holder = new LinkedList<>();
			File myFile = new File(filename);
			try {
				Scanner input = new Scanner(myFile);
				while(input.hasNextLine()) {
					holder.add(input.nextLine()+"");
				}
				input.close();
			}
			catch(Exception e) {
				System.out.println("File \"" + filename + "\" not found.");	
				e.printStackTrace();
			}
			writer = new PrintWriter(filename, "UTF-8");
			while(!holder.isEmpty()) {
				writer.println(holder.poll());
			}
			writer.println(name+"_");
			writer.println(theBoard.getRows()+"");
			writer.println(theBoard.getCols()+"");
			writer.println(numberOfSpins + "");
			for(int i = 0; i < theBoard.getRows(); i++) {
				for(int j = 0; j < theBoard.getCols(); j++) {
					writer.println(theBoard.getBoard()[i][j].getValue() + "");
				}
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadBoard(String name) {
		String filename = "savedBoard.txt";
		File myFile = new File(filename);
		try
		{
		Scanner input = new Scanner(myFile);
		if(input.nextLine().equals(name)) {
			int row = input.nextInt();
			input.nextLine();
			int col = input.nextInt();
			input.nextLine();
			numberOfSpins = input.nextInt();
			theBoard = new Board(row, col);
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					theBoard.getBoard()[i][j].setValue(input.nextInt());
					input.nextLine();
				}
			 }
			 
		}
		
		input.close();
		}
		catch(Exception e)
		{
			System.out.println("File \"" + filename + "\" not found.");	
			e.printStackTrace();
		}
	}
	
	public String[] loadNames() {
		String filename = "savedBoard.txt";
		Queue<String> holder = new LinkedList<>();
		File myFile = new File(filename);
		try
		{
		Scanner input = new Scanner(myFile);
		while(input.hasNextLine()) {
			String a = input.nextLine();
			if(a.contains("_")) {
				holder.add(a.substring(0, a.length()-1));
			}
		}
		String[] a = new String[holder.size()];
		int i = 0;
		while(!holder.isEmpty()) {
			a[i] = holder.poll();
			System.out.println(a[i]);
			i++;
		}
		input.close();
		return a;
		}
		catch(Exception e)
		{
			System.out.println("File \"" + filename + "\" not found.");	
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteSavedBoard(String name) {
		PrintWriter writer;
		try {
			String filename = "savedBoard.txt";
			Queue<String> holder = new LinkedList<>();
			File myFile = new File(filename);
			try {
				Scanner input = new Scanner(myFile);
				while(input.hasNextLine()) {
					String a = input.nextLine();
					if(a.equals(name)) {
						int row = input.nextInt();
						input.nextLine();
						int col = input.nextInt();
						input.nextLine();
						for(int i = 0; i < row*col+1; i++) {
							input.nextLine();
						}
					}
					else
						holder.add(a+"");
				}
				input.close();
			}
			catch(Exception e) {
				System.out.println("File \"" + filename + "\" not found.");	
				e.printStackTrace();
			}
			writer = new PrintWriter(filename, "UTF-8");
			while(!holder.isEmpty()) {
				writer.println(holder.poll());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isAName(String newName) {
		String[] names = loadNames();
		if(newName.length() > 20) {
			JOptionPane.showMessageDialog(gameJFrame, "20 character max.");
			return true;
		}
		for(int i = 0; i < names.length; i++) {
			if(newName.equals(names[i])) {
				JOptionPane.showMessageDialog(gameJFrame, "Name already taken.");
				return true;
			}
		}
		if(!newName.matches(".*[a-z].*")) {
			JOptionPane.showMessageDialog(gameJFrame, "Must contain a letter (a-z).");
			return true;
		}
		
		return false;
	}
}
