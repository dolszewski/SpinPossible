
public class Tile {
	private static int currentNumber = 0;
	private String spin;
	private int value;
	public Tile() {
		currentNumber++;
		this.value = currentNumber;
		this.spin = "UP";
	}
	public String getSpin() {
		return spin;
	}
	public void setSpin(String spin) {
		this.spin = spin;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}
