package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class LogoutPanel {
	
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("Choose player(s) to logout");
	private JButton playerOne;
	private JButton playerTwo;
	private JButton both;
	private JButton cancel;

	public LogoutPanel() {
		
		mainPanel.setLayout(null);
		playerOne = new JButton("Player 1");
		playerTwo = new JButton("Player 2");
		both = new JButton("Both");
		cancel = new JButton ("Cancel");

		mainPanel.add(title);
		mainPanel.add(playerOne);
		mainPanel.add(playerTwo);
		mainPanel.add(both);
		mainPanel.add(cancel);
		
		Dimension size = new Dimension(100,25);
		title.setBounds(375, 50, 200, size.height);
		playerOne.setBounds(400, 150, size.width, size.height);
		playerTwo.setBounds(400, 250, size.width, size.height);
		both.setBounds(400, 350, size.width, size.height);
		cancel.setBounds(400, 450, size.width, size.height);
		
		
	}
	public void addPlayerOneActionListener(ActionListener listener) {
		playerOne.addActionListener(listener);
	}

	public void addPlayerTwoActionListener(ActionListener listener) {
		playerTwo.addActionListener(listener);
	}
	
	public void addBothActionListener(ActionListener listener) {
		both.addActionListener(listener);
	}
	
	public void addCancelActionListener(ActionListener listener) {
		cancel.addActionListener(listener);
	}
	
	public JComponent getMainComponent() {
		return mainPanel;
	}
}
