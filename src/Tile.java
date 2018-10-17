import javax.swing.JLabel;
import javax.swing.JFrame; // for JFrame
import javax.swing.JLabel; // for JLabel

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon; // for ImageIcon

public class Tile implements TileInterface{
	private static int currentNumber = 1;
	private String spin;
	private int value = 0;
	private BufferedImage image;
	private int xPosition = 0;
	private int yPosition = 0;
	private int width=40;
	private int height=40;
	private boolean isHighlighted = false;
	private String imageName;
	

	//xwidth
	//yheight
	public Tile() {
		this.value = currentNumber;
		this.spin = "UP";
		currentNumber++;
		setImageName();
		setImage();
		
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
		String h = "";
		if(isHighlighted)
			h = ".";
		String str = String.format("%d%S%S ", value, spin, h);
		return str;
	}
	public void setImageName() {
		imageName = value+getSpin()+".jpg";
		
	}
	public void setImage() {
		image = null;
		try {
			image = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			System.out.print("Failed to Load Image");
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
		//System.out.print(height);
		
	}
	public void draw(Graphics g) {
		setImageName();
		setImage();
		g.drawImage(image, xPosition, yPosition, width, height, null);
		if (isHighlighted) {
			g.setColor(new Color(255, 120, 120, 150));
			g.fillRect(xPosition, yPosition, width, height);
		}
	}
	
}
