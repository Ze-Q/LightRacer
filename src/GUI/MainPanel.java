package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MainPanel {
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("<html> <h1>Main Menu</h1> </html>");
	private JButton newGame;
	private JButton stats;
	private JButton help;
	private JButton logout;
	private JButton exit;

	public MainPanel() {
		
		mainPanel.setLayout(null);
		newGame = new JButton("New Game");
		newGame.setForeground(Color.WHITE);
		newGame.setBackground(Color.DARK_GRAY);
		logout = new JButton("Logout");
		logout.setForeground(Color.WHITE);
		logout.setBackground(Color.DARK_GRAY);
		exit = new JButton ("Exit");
		exit.setForeground(Color.WHITE);
		exit.setBackground(Color.DARK_GRAY);
		stats = new JButton ("Statistics");
		stats.setForeground(Color.WHITE);
		stats.setBackground(Color.DARK_GRAY);
		help = new JButton ("Help");
		help.setForeground(Color.WHITE);
		help.setBackground(Color.DARK_GRAY);
		title.setForeground(Color.WHITE);
		
		mainPanel.add(title);
		mainPanel.add(newGame);
		mainPanel.add(logout);
		mainPanel.add(exit);
		mainPanel.add(stats);
		mainPanel.add(help);
	    mainPanel.setOpaque(true);
	    mainPanel.setBackground(Color.BLACK);
		
		Dimension size = new Dimension(100,25);
		title.setBounds(385, 25, 200, 100);
		newGame.setBounds(400, 175, size.width, size.height);
		stats.setBounds(400, 225, size.width, size.height);
		help.setBounds(400, 275, size.width, size.height);
		logout.setBounds(400, 325, size.width, size.height);
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
	
	public void addLogoutBtnActionListener(ActionListener listener) {
		logout.addActionListener(listener);
	}
	
	public JComponent getMainComponent() {
		return mainPanel;
	}
}