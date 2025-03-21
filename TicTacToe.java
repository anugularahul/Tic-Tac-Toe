import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {

    private JFrame frame = new JFrame("Tic Tac Toe");
    private JButton[] buttons = new JButton[9];
    private JLabel message = new JLabel("X's turn");
    private boolean xTurn = true;

    public TicTacToe() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
		
		JPanel buttonPanel = new JPanel();
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetBoard();
			}
		});
		buttonPanel.add(resetButton);

        frame.add(panel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(message, BorderLayout.NORTH);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();

        if (button.getText().equals("")) {
            if (xTurn) {
                button.setText("X");
                button.setBackground(Color.RED);
                message.setText("O's turn");
            } else {
                button.setText("O");
                button.setBackground(Color.BLUE);
                message.setText("X's turn");
            }

            if (checkForWin()) {
                message.setText(button.getText() + " wins!");
                for (JButton b : buttons) {
                    b.setEnabled(false);
                }
            } else if (checkForDraw()) {
                message.setText("It's a draw!");
            } else {
                xTurn = !xTurn;
            }
        }
    }

    private boolean checkForWin() {
        return checkRow(0, 1, 2) || checkRow(3, 4, 5) || checkRow(6, 7, 8) ||
               checkRow(0, 3, 6) || checkRow(1, 4, 7) || checkRow(2, 5, 8) ||
               checkRow(0, 4, 8) || checkRow(2, 4, 6);
    }

    private boolean checkRow(int a, int b, int c) {
        return buttons[a].getText().equals(buttons[b].getText()) &&
               buttons[b].getText().equals(buttons[c].getText()) &&
               !buttons[a].getText().equals("");
    }

    private boolean checkForDraw() {
        for (JButton b : buttons) {
            if (b.getText().equals("")) {
                return false;
            }
        }

        return true;
    }
	
	private void resetBoard() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
            buttons[i].setBackground(null);
            buttons[i].setEnabled(true);
        }
        xTurn = true;
        message.setText("X's turn");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}