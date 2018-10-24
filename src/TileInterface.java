import javax.swing.JLabel;
import javax.swing.JFrame; // for JFrame
import javax.swing.JLabel; // for JLabel
import java.util.Random;
import javax.swing.ImageIcon; // for ImageIcon

public interface TileInterface {
	
	public String getSpin();

	public void changeSpin() ;
	public int getValue();
	public void setXPosition(int x);
	public void setYPosition(int y);
	public void setHighlighted(boolean highlight);
	public String toString();

	public void setImageName();
}
