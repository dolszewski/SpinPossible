import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TimerTask;


public interface SpinControllerInterface {
	
	public void spin();
	
	public void selected(int row, int column);
	
	public void highlightTiles();
	
}
