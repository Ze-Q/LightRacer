package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class HelpPanel {
	
	private JPanel mainPanel = new JPanel();

	private JLabel title = new JLabel("Help");
	private JLabel actionLabel;
	private JButton gameRules;
	private JButton controls;
	private JButton loadMap;
	private JButton about;
	private JButton back;

	public HelpPanel() {
		
		mainPanel.setLayout(null);
		back = new JButton("Return to Main Menu");
		
		actionLabel = new JLabel("");
		gameRules = new JButton ("Game Rules");
		controls = new JButton ("Controls");
		loadMap = new JButton ("Maps");
		about = new JButton ("About");
		
		/*
		
		
		controls = new JLabel();
		
		loadMap = new JLabel();

		about = new JLabel ();
		*/
		
		mainPanel.add(title);
		mainPanel.add(back);
		mainPanel.add(actionLabel);
		mainPanel.add(gameRules);
		mainPanel.add(controls);
		mainPanel.add(loadMap);
		mainPanel.add(about);
		
		Dimension size = new Dimension(100,25);
		back.setBounds(350, 450, 200, size.height);
		title.setBounds(415, 50, size.width, size.height);
		actionLabel.setBounds(450, 50, 400, 400);
		gameRules.setBounds(100, 150, 200, size.height);
		controls.setBounds(100, 200, 200, size.height);
		loadMap.setBounds(100, 250, 200, size.height);
		about.setBounds(100, 300, 200, size.height);
		
		gameRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionLabel.setText("<html><P>Light Racer features a login system in which users "
						+ "can register a new account as well as login with their existing username. "
						+ "This allows for an extensive statistics system which tracks "
						+ "the results of matches between opponents. Upon the completion "
						+ "of every 3 round long game, relevant statistics are displayed. "
						+ "Moreover, players can access their lifetime statistics and view "
						+ "the top 10 players of all time on a dedicated screen that is "
						+ "accessible from the main menu.</p></html>");
			}
		});
		
		controls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionLabel.setText("<html><P>Players control their vehicles using the same keyboard "
						+ "similar to many popular old arcade racing games. The user on the left (User 1) "
						+ "uses W, A, S, D to move up, left, down, and right respectively. "
						+ "The user on the right (User 2) uses the arrow keys to move.</p></html>");
			}
		});
		
		loadMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionLabel.setText("<html><P>The program also features a "
						+ "selection of maps that contain various layouts of obstacles. "
						+ "Players are able to select their preferred maps as well as change "
						+ "the speed of their vehicles prior to every match.</p></html> ");
			}
		});
		
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionLabel.setText("<html><P>This game was created as a group project for ECSE321 "
						+ "Introduction to Software Engineering, in fall 2013. "
						+ "The team members: Ze Qian Zhang, Dzmitry Murzich, Cheng Gong, Alonso Medina.</p></html>");
			}
		});
	}

	public void addBackBtnActionListener(ActionListener listener) {
		   back.addActionListener(listener);
	}
	
	public JComponent getMainComponent() {
	   return mainPanel;
	}
}