package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class HelpPanel {
	
	private JPanel mainPanel = new JPanel();

	private JLabel title = new JLabel("Help");
	private JLabel gameRules;
	private JLabel controls;
	private JLabel loadMap;
	private JLabel about;
	private JButton back;

	public HelpPanel() {
		
		mainPanel.setLayout(null);
		back = new JButton("Return to Main Menu");
		
		gameRules = new JLabel("Light Racer features a login system in which users "
				+ "can register a new account as well as login with their existing username. "
				+ "This allows for an extensive statistics system which tracks "
				+ "the results of matches between opponents. Upon the completion "
				+ "of every 3 round long game, relevant statistics are displayed. "
				+ "Moreover, players can access their lifetime statistics and view "
				+ "the top 10 players of all time on a dedicated screen that is "
				+ "accessible from the main menu.");
		
		controls = new JLabel("Players control their vehicles using the same keyboard "
				+ "similar to many popular old arcade racing games. The user on the left (User 1) "
				+ "uses W, A, S, D to move up, left, down, and right respectively. "
				+ "The user on the right (User 2) uses the arrow keys to move.");
		
		loadMap = new JLabel("The program also features a "
				+ "selection of maps that contain various layouts of obstacles. "
				+ "Players are able to select their preferred maps as well as change "
				+ "the speed of their vehicles prior to every match. ");

		about = new JLabel ("This game was created as a group project for ECSE321 "
				+ "Introduction to Software Engineering, in fall 2013. "
				+ "The team members: Ze Qian Zhang, Dzmitry Murzich, Cheng Gong, Alonso Medina.");
		
		mainPanel.add(title);
		mainPanel.add(back);
		
		Dimension size = new Dimension(100,25);
		back.setBounds(350, 450, 200, size.height);
		title.setBounds(415, 50, size.width, size.height);
	}

	public void addBackBtnActionListener(ActionListener listener) {
		   back.addActionListener(listener);
	}
	
	public JComponent getMainComponent() {
	   return mainPanel;
	}
}