package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MainPanel {
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("Main Menu");
	private JButton newGame;
	private JButton stats;
	private JButton help;
	private JButton settings;
	private JButton logout;
	private JButton exit;

	public MainPanel() {
		
		mainPanel.setLayout(null);
		newGame = new JButton("New Game");
		logout = new JButton("Logout");
		exit = new JButton ("Exit");
		stats = new JButton ("Statistics");
		help = new JButton ("Help");
		settings = new JButton ("Settings");

		mainPanel.add(title);
		mainPanel.add(newGame);
		mainPanel.add(logout);
		mainPanel.add(exit);
		mainPanel.add(stats);
		mainPanel.add(help);
		mainPanel.add(settings);
		
		Dimension size = new Dimension(100,25);
		title.setBounds(415, 10, 100, size.height);
		newGame.setBounds(400, 100, size.width, size.height);
		stats.setBounds(400, 150, size.width, size.height);
		help.setBounds(400, 200, size.width, size.height);
		settings.setBounds(400, 250, size.width, size.height);
		logout.setBounds(400, 300, size.width, size.height);
		exit.setBounds(400, 450, size.width, size.height);
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window win = SwingUtilities.getWindowAncestor(mainPanel);
				win.dispose();
			}
		});
	}
	public void addNewGameBtnActionListener(ActionListener listener) {
		newGame.addActionListener(listener);
	}
	
	public void addStatsBtnActionListener(ActionListener listener) {
		stats.addActionListener(listener);
	}
	
	public void addHelpBtnActionListener(ActionListener listener) {
		help.addActionListener(listener);
	}
	
	public void addSettBtnActionListener(ActionListener listener) {
		settings.addActionListener(listener);
	}

	public void addLogoutBtnActionListener(ActionListener listener) {
		logout.addActionListener(listener);
	}
	
	public JComponent getMainComponent() {
		return mainPanel;
	}
}