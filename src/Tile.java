import javax.swing.JLabel;

public class Tile {
	private static int currentNumber = 1;
	private String spin;
	private int value = 0;
	private JLabel image = new JLabel();
	private int xPosition = 0;
	private int yPosition = 0;
	private boolean isHighlighted = false;
	private String imageName;
	//xwidth
	//yheight
	public Tile() {
		
		this.value = currentNumber;
		this.spin = "UP";
		currentNumber++;
	}
	public String getSpin() {
		if (value < 0) {
			spin = "DOWN";
		} else {
			spin = "UP";
		}
		return spin;
	}
	public void changeSpin() {
		value = value * -1;
	}
	public int getValue() {
		return value;
	}

	public void setXPosition(int x) {
		xPosition = x;
	}
	public void setYPosition(int y) {
		yPosition = y;
	}
	public void setHighlighted(boolean highlight) {
		isHighlighted=highlight;
	}
	public String toString() {
		String str = String.format("%d%S ", value, spin);
		return str;
	}
	public void setImageName() {
		imageName = value+getSpin();
	}
	
}
