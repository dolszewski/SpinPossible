import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TimerTask;

import javax.swing.JFrame;

import stack.ArrayStack;
import stack.StackInterface;

class SpinController extends TimerTask implements MouseListener, SpinControllerInterface{
    private JFrame gameJFrame;
    private Container gameContentPane;
    private boolean gameIsReady = false;
    private StackInterface<Integer[]> spinStack = new ArrayStack<>();
    private Board theBoard = new Board();
    private int[][] selectedArray = new int[2][2];
    private int numberSelected = 0;
    
    

	public static void main(String[] args) {
		
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
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
	public void spin(int first, int second) {
		// TODO Auto-generated method stub
		
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
				}
			}
		}
		else if(numberSelected == 1) {
			theBoard.getBoard()[selectedArray[0][0]][selectedArray[1][0]].setHighlighted(true);
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

	


}
