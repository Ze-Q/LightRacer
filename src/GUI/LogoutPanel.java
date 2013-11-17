package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class LogoutPanel {
	
	private static final Dimension MAIN_SIZE = new Dimension(MainWindow.WIDTH, MainWindow.HEIGHT);
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("Choose player(s) to logout");
	private JButton PlayerOne;
	private JButton PlayerTwo;
	private JButton Both;
	private JButton Cancel;

	public LogoutPanel() {
		
		mainPanel.setLayout(null);
		PlayerOne = new JButton("Player 1");
		PlayerTwo = new JButton("Player 2");
		Both = new JButton("Both");
		Cancel = new JButton ("Cancel");

		mainPanel.add(title);
		mainPanel.add(PlayerOne);
		mainPanel.add(PlayerTwo);
		mainPanel.add(Both);
		mainPanel.add(Cancel);
		
		Dimension size = new Dimension(100,25);
		title.setBounds(415, 50, size.width, size.height);
		PlayerOne.setBounds(400, 150, size.width, size.height);
		PlayerTwo.setBounds(375, 250, 150, size.height);
		Both.setBounds(375, 350, 150, size.height);
		Cancel.setBounds(400, 450, size.width, size.height);
		
		
	}
	public void addPlayerOneActionListener(ActionListener listener) {
		PlayerOne.addActionListener(listener);
	}

	public void addPlayerTwoActionListener(ActionListener listener) {
		PlayerTwo.addActionListener(listener);
	}
	
	public void addBothActionListener(ActionListener listener) {
		Both.addActionListener(listener);
	}
	
	public void addCancelActionListener(ActionListener listener) {
		Cancel.addActionListener(listener);
	}
	
	public JComponent getMainComponent() {
		return mainPanel;
	}
}