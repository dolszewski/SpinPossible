
public class Board {
    private Tile[][] board;
    private int rows;
    private int cols;
    public Board() {
    	board = new Tile[3][3];
    	rows = 3;
    	cols = 3;
    }

	public Board(int n, int m) {
    	board = new Tile[n][m];
    	this.rows = n;
    	this.cols = m;
    }
    public void instantiateBoard() {
    	for (int i = 0; i < rows; i++) {
    		for (int j = 0; j < cols; j++){
    			board[i][j] = new Tile();
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
	
    
}
