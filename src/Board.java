import java.awt.Graphics;

public class Board implements BoardInterface{
    private Tile[][] board;
    private int rows;
    private int cols;
    private int rowLength;
    private int colLength;
    public Board() {
    	rows = 3;
    	cols = 3;
    	rowLength = 360;
    	colLength = 360;
    	this.board = new Tile[3][3];
    	instantiateBoard();
    }

	public Board(int n, int m) {
    	this.rows = n;
    	this.cols = m;
    	rowLength = 120*n;
    	colLength = 120*m;
    	this.board = new Tile[n][m];
    	instantiateBoard();
    }
    public void instantiateBoard() {
    	Tile.resetCurrentNumber();
    	for (int i = 0; i < rows; i++) {
    		for (int j = 0; j < cols; j++){
    			this.board[i][j] = new Tile();
    			this.board[i][j].setXPosition(j*rowLength/rows);
    			this.board[i][j].setYPosition(i*colLength/cols);
    			

    		}
    	}
    }
    public void updatePositions() {
    	for (int i = 0; i < rows; i++) {
    		for (int j = 0; j < cols; j++){
    			this.board[i][j].setXPosition(j*rowLength/rows);
    			this.board[i][j].setYPosition(i*colLength/cols);

    		}
    	}
    }
    public Tile[][] getBoard() {
		return board;
	}
    public void setBoard(Tile[][] board) {
    		this.board = board;
    }
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}
	public String toString() {
		String str ="";
		for (int i = 0; i < rows; i++) {
    		for (int j = 0; j < cols; j++){
    			str = str + board[i][j].toString();
    		}
    		str = str +"\n";
    	}
		return str;
	}
	public void drawBoard(Graphics g) {
		for (int i = 0; i < rows; i++) {
    		for (int j = 0; j < cols; j++){
    			this.board[i][j].draw(g);;
    		}
    	}
	}

	public int rowSelect(int x, int rowLength) {
		if (x < rowLength/cols) {
			return 0;
		}
		if (x < 2*rowLength/cols) {
			return 1;
		}
		if (x < rowLength) {
			return 2;
		}
		return 0;
	}
	public int colSelect(int y,int colLength) {
		if (y < colLength/rows) {
			return 0;
		}
		if (y < 2*colLength/rows) {
			return 1;
		}
		if (y < colLength) {
			return 2;
		}
		return 0;
	}

	public void switchTiles(int[] first, int[] second) {
		Tile temp = new Tile();
		temp = this.board[first[0]][first[1]];
		this.board[first[0]][first[1]] = this.board[second[0]][second[1]];
		this.board[second[0]][second[1]] = temp;
	}
	
	public boolean isIdentity() {
		int value = 0;
		for (int i = 0; i < rows; i++) {
	    		for (int j = 0; j < cols; j++){
	    			value++;
	    			if(this.board[i][j].getValue() != value)
	    				return false;
	    		}
		}
		return true;
	}
	public int getRowLength() {
		return rowLength;
	}
	public int getColLength() {
		return colLength;
	}

    
}
