package tictactoe;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TicTacToeFrame extends JFrame {
	
	
	static JTextField statusBar;


	public TicTacToeFrame() {
		setLayout(new BorderLayout());
		
		GamePanel panel = new GamePanel();
		add(panel, BorderLayout.CENTER);

		statusBar = new JTextField("Player1's Turn");
		statusBar.setEditable(false);
		add(statusBar, BorderLayout.SOUTH);

		setTitle("Tic Tac Toe!");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 300, 300);
	}
	
}
