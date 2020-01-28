package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GameListener implements ActionListener {
	
	GamePanel panel;
	JButton[][] buttons = new JButton[3][3];
	Integer turn;
	Integer count;
	
	
	public GameListener(GamePanel p) {
		this.panel = p;
		this.turn = panel.getTurn();
		this.count = panel.getCount();
		this.buttons = panel.getButtons();
	}
	
	public void actionPerformed(ActionEvent e) {
		count++;
		JButton b = (JButton)e.getSource();
		Integer[] index = (Integer[]) b.getClientProperty("INDEX");
		
		b.putClientProperty("OWNER", turn);
		BufferedImage xImage = null;
		BufferedImage oImage = null;
		try {
			System.out.println(panel.getWidth() + " x " + panel.getHeight());
			if(panel.getWidth()> 290) {
				xImage = ImageIO.read(getClass().getResource("resources/images/HiRes/X.gif"));
				 oImage = ImageIO.read(getClass().getResource("resources/images/HiRes/O.gif"));
			}else {
			 xImage = ImageIO.read(getClass().getResource("resources/images/MedRes/X.gif"));
			 oImage = ImageIO.read(getClass().getResource("resources/images/MedRes/O.gif"));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Icon xIcon = new ImageIcon(xImage);
		Icon oIcon = new ImageIcon(oImage);
		//b.setText((turn == 1 ? "X": "O"));
		b.setIcon((turn == 1 ? xIcon : oIcon));
		b.setEnabled(false);
		boolean result = checkVictoryCondition(index);
		if(result) {
			JOptionPane.showMessageDialog(null, "Player " + turn.toString() +  " WINS!");
			initComponents();
		}else {
			if(turn == 1) {
				turn = 2;
				TicTacToeFrame.statusBar.setText("Player2's Turn");
			}else {
				turn = 1;
				TicTacToeFrame.statusBar.setText("Player1's Turn");
			}
		}
		if(count == 9) {
			JOptionPane.showMessageDialog(null, "Cat's Game");
			initComponents();
		}
	}
	
	private Integer getOwner(JButton b) {
		return (Integer)b.getClientProperty("OWNER");
	}
	
	private boolean checkVictoryCondition(Integer[] index) {
		Integer a = index[0];
		Integer b = index[1];
		int i;
		
		//check row
		for(i = 0; i < 3; i++) {
			if(getOwner(buttons[a][i]) != getOwner(buttons[a][b]))
				break;
		}
		if(i == 3)
			return true;
		
		//check columns
		for(i = 0; i < 3; i++) {
			if(getOwner(buttons[i][b]) != getOwner(buttons[a][b]))
				break;
		}
		if(i == 3)
			return true;
		//check diagonal
		if((a==2&&b==2)||(a==0&&b==0)||(a==1&&b==1)||(a==0&&b==2)||(a==2&&b==0)) {
			//left
			for(i=0; i < 3; i++) {
				if(getOwner(buttons[i][i]) != getOwner(buttons[a][b]))
					break;
			}
			if(i == 3)
				return true;	
			
			//right
			for(i=0; i < 3; i++) {
				if((getOwner(buttons[0][2]) == getOwner(buttons[a][b])) && 
						(getOwner(buttons[1][1]) == getOwner(buttons[a][b])) && 
						(getOwner(buttons[2][0]) == getOwner(buttons[a][b])))
					return true;
			}
		}
		
		return false;
	}
	
	private void initComponents() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				buttons[i][j].putClientProperty("OWNER", null);
				buttons[i][j].setEnabled(true);
				buttons[i][j].setText("");
				buttons[i][j].setIcon(null);
			}
		turn = 1;
		count = 0;
		TicTacToeFrame.statusBar.setText("Player1's Turn");
		panel.revalidate();
		panel.repaint();
	}

}
