
public class Board {
    private Tile[][] board;
    private int rows;
    private int cols;
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
	
    
}
