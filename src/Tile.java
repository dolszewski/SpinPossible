import javax.swing.JLabel;

public class Tile {
	private static int currentNumber = 1;
	private String spin;
	private int value = 0;
	private JLabel image = new JLabel();
	private int xPosition = 0;
	private int yPosition = 0;
	public Tile() {
		
		this.value = currentNumber;
		this.spin = "UP";
		currentNumber++;
	}
	public String getSpin() {
		return spin;
	}
	public void changeSpin() {
		if (spin.equals("UP")) {
			spin = "DOWN";
		}
		else {
			spin = "UP";
		}
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
	public String toString() {
		String str = String.format("%d%S ", value, spin);
		return str;
	}
	
}
