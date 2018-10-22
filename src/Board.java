import java.awt.Graphics;

public class Board implements BoardInterface{
    private Tile[][] board;
    private int rows;
    private int cols;
    private int rowLength = 360;
    private int colLength = 360;
    public Board() {
    	rows = 3;
    	cols = 3;
    	this.board = new Tile[3][3];
    	instantiateBoard();
    }

	public Board(int n, int m) {
    	this.rows = n;
    	this.cols = m;
    	this.board = new Tile[n][m];
    	instantiateBoard();
    }
    public void instantiateBoard() {
    	for (int i = 0; i < rows; i++) {
    		for (int j = 0; j < cols; j++){
    			this.board[i][j] = new Tile();
    			this.board[i][j].setXPosition(i*rowLength/rows);
    			this.board[i][j].setYPosition(j*colLength/cols);

    		}
    	}
    }
    public void updatePositions() {
    	for (int i = 0; i < rows; i++) {
    		for (int j = 0; j < cols; j++){
    			this.board[i][j].setXPosition(i*rowLength/rows);
    			this.board[i][j].setYPosition(j*colLength/cols);

    		}
    	}
    }
    public Tile[][] getBoard() {
		return board;
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
	public int rowSelect(int x) {
		if (x < rowLength/rows) {
			return 0;
		}
		if (x < 2*rowLength/rows) {
			return 1;
		}
		if (x < rowLength) {
			return 2;
		}
		return 0;
	}
	public int colSelect(int y) {
		if (y < colLength/cols) {
			return 0;
		}
		if (y < 2*colLength/cols) {
			return 1;
		}
		if (y < colLength) {
			return 2;
		}
		return 0;
	}
    
}
