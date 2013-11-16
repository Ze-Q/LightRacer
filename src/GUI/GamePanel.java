package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GamePanel {
	private JPanel mainPanel = new JPanel();

	private JLabel title = new JLabel("New Game Menu");
	private JLabel speedLabel;
	private JTextField speed;
	private JButton set;
	private JButton start;
	private JButton abort;

	public GamePanel() {
		
		mainPanel.setLayout(null);

		speedLabel = new JLabel("Set Speed" + ": ");
		speedLabel.setLabelFor(speed);
		speed = new JTextField(10);
		start = new JButton("Start Game");
		abort = new JButton("Abort Game");
		set = new JButton("Set");

		mainPanel.add(title);
		mainPanel.add(start);
		mainPanel.add(abort);
		mainPanel.add(speedLabel);
		mainPanel.add(speed);
		mainPanel.add(set);

		Dimension size = new Dimension(100,25);	
		abort.setBounds(375, 450, 150, size.height);
		title.setBounds(400, 50, size.width, size.height);
		start.setBounds(400, 400, size.width, size.height);
		speedLabel.setBounds(350, 150, size.width, size.height);
		speed.setBounds(450, 150, size.width, size.height);
		set.setBounds(400, 350, size.width, size.height);
	}

	public void addAbortBtnActionListener(ActionListener listener) {
		   abort.addActionListener(listener);
	}
	
	public void addStartBtnActionListener(ActionListener listener) {
		   start.addActionListener(listener);
	}
	
	public JComponent getMainComponent() {
	   return mainPanel;
	}
}
