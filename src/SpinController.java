import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

class SpinController extends TimerTask implements MouseListener, SpinControllerInterface, ActionListener{
    private JFrame gameJFrame;
    private JPanel gameContentPane;
    private boolean gameIsReady = false;
    private int gameWidth = 800;
    private int gameHeight = 400;
    private java.util.Timer gameTimer = new java.util.Timer();
    private Board b;
    private JButton spinButton;
    private JButton undoButton;
    private int buttonSizeX = 100;
    private int buttonSizeY = 60;
    private Font buttonFont = new Font("Tahoma", Font.BOLD, 20);
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem newGameItem;
    private JMenuItem exitItem;
    

	public static void main(String[] args) {
		SpinController spin = new SpinController();
	}
	public SpinController() {
		b = new Board();
		gameJFrame = new JFrame();
		gameJFrame.setSize(gameWidth, gameHeight);
		gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameContentPane = new myPane();
		gameJFrame.setContentPane(gameContentPane);
		gameContentPane.setLayout(null);
		spinButton = new JButton();
		spinButton.setText("SPIN");
		spinButton.setFont(buttonFont);
		spinButton.setBounds(gameWidth/2 + 100, gameHeight/3 -50 ,buttonSizeX, buttonSizeY);
		spinButton.addActionListener(this);
		undoButton = new JButton();
		undoButton.setText("UNDO");
		undoButton.setFont(buttonFont);
		undoButton.setBounds(gameWidth/2 + 100, gameHeight/3 *2 -50 ,buttonSizeX, buttonSizeY);
		undoButton.addActionListener(this);
		gameContentPane.add(spinButton);
		gameContentPane.add(undoButton);
		newGameItem = new JMenuItem();
        newGameItem.setText("New Game");
        newGameItem.addActionListener(this);
        exitItem = new JMenuItem();
        exitItem.setText("Exit");
        exitItem.addActionListener(this);
        menu = new JMenu();
        menu.add(newGameItem);
        menu.add(exitItem);
        menu.setText("Menu");
        menuBar = new JMenuBar();
        menuBar.add(menu);
        gameJFrame.setJMenuBar(menuBar);
		gameJFrame.setVisible(true);
		gameTimer.schedule(this, 0, 40); 
	
		
		
		
	}
	private class myPane extends JPanel {
		private static final long serialVersionUID = 1L;
		public myPane() {
			super();
			super.setBackground(Color.WHITE);

		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			b.drawBoard(g);
			
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		gameJFrame.repaint();
		gameContentPane.repaint();
		
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
	}
    
}
