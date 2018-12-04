import javax.swing.JLabel;
import javax.swing.JFrame; // for JFrame
import javax.swing.JLabel; // for JLabel
import java.util.Random;
import javax.swing.ImageIcon; // for ImageIcon

public interface TileInterface {
	
	/**
	 * Gets if the number is upside-down or rightside-up.
	 * @param 
	 * @return	String "UP" or "DOWN".
	 */
	public String getSpin();
	
	/**
	 * Changes the direction of the number.
	 * @param 
	 * @return
	 */
	public void changeSpin() ;
	
	/**
	 * Gets the value of the tile and if the value is negative then it is upside-down.
	 * @param 
	 * @return	value between -9 to 9.
	 */
	public int getValue();
	
	/**
	 * Sets the X position of the tile.
	 * @param int x: X position.
	 * @return	
	 */
	public void setXPosition(int x);
	
	/**
	 * Sets the Y position of the tile.
	 * @param int y: Y position.
	 * @return	
	 */
	public void setYPosition(int y);
	
	/**
	 * Sets the tile to "Highlighted" or not "Highlighted".
	 * @param boolean highlight: True = Highlighted.
	 * @return	
	 */
	public void setHighlighted(boolean highlight);
	
	/**
	 * Checks to see if Tile is "Highlighted".
	 * @param 
	 * @return	boolean value true is "Highlighted".
	 */
	public boolean isHighlighted();
	
	/**
	 * Combines value, spin, and highlighted in string.
	 * @param 
	 * @return	example: "-2DOWN." Number 2 is upside-down and is highlighted (dot at the end).
	 */
	public String toString();

	/**
	 * Combines value, spin, and highlighted in string.
	 * @param 
	 * @return	example: "-2DOWN." Number 2 is upside-down and is highlighted (dot at the end).
	 */
	public void setImageName();
	
	/**
	 * Gets name of the image.
	 * @param 
	 * @return	string of image name.
	 */
	public String getImageName();
	
	/**
	 * Sets the Tile as the "First Highlighted" or "Selected".
	 * @param boolean firstHighlight: True = "First Highlighted"/"Selected"
	 * @return	
	 */
	public void setFirstHighlight(boolean firstHighlight);
	
	/**
	 * Reads the image file.
	 * @param 
	 * @return	
	 */
	public void setImage();
	
	/**
	 * Checks to see if a Tile is equal to this Tile.
	 * @param Tile tile: Another Tile to check
	 * @return	boolean where true is that the Tiles are equal to each other.
	 */
	public boolean equals(Tile tile);
	
	
	
}
