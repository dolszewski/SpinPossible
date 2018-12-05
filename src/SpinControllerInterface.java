import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public interface SpinControllerInterface {
	
	
	public void spin();
	
	public void unHighlightTiles();
	
	public Tile[] switchTiles(Tile[] a);

	public void highlightTiles();
	
	public void selected(int row, int column);

	public void emptySpinStack();
	
	public void easyBoard();
	
	public void hardBoard();
    
	public void startGame();
	
	public void saveBoard(String name);
	
	public void loadBoard(String name);
	
	public String[] loadNames();
	
	public void deleteSavedBoard(String name);
	
	public boolean isAName(String newName);
	
}
