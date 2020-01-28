package tictactoe;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	JButton[][] buttons = new JButton[3][3];
	Integer turn;
	Integer count;

	public GamePanel() {
		setLayout(new GridLayout(3, 3));
		turn = 1;
		count = 0;
		GameListener listener = new GameListener(this);
		
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].putClientProperty("INDEX", new Integer[] { i, j });
				buttons[i][j].putClientProperty("OWNER", null);
				buttons[i][j].addActionListener(listener);
				add(buttons[i][j]);
			}
		
	}
	
	public Integer getTurn(){return turn;}
	public Integer getCount() {return count;}
	public JButton[][] getButtons(){return buttons;}
}
