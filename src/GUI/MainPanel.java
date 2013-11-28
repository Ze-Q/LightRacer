package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This panel contains the main menu with access to GamePanel, HelpPanel, StatsPanel and LogoutPanel as well as exiting the program entirely.
 * @author Alonso Medina
 * @version 1.0
 */
class MainPanel {
	
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("<html> <h1>Main Menu</h1> </html>");
	private JButton newGame;
	private JButton stats;
	private JButton help;
	private JButton logout;
	private JButton exit;

	/**
	 * This panel contains the main menu with access to GamePanel, HelpPanel, StatsPanel and LogoutPanel as well as exiting the program entirely.
	 */
	public MainPanel() {
		
		//initialize and format components
		mainPanel.setLayout(null);
		newGame = new JButton("New Game");
		newGame.setForeground(Color.WHITE);
		newGame.setBackground(Color.DARK_GRAY);
		newGame.setOpaque(true);
		newGame.setBorderPainted(false);
		logout = new JButton("Logout");
		logout.setForeground(Color.WHITE);
		logout.setBackground(Color.DARK_GRAY);
		logout.setOpaque(true);
		logout.setBorderPainted(false);
		exit = new JButton ("Exit");
		exit.setForeground(Color.WHITE);
		exit.setBackground(Color.DARK_GRAY);
		exit.setOpaque(true);
		exit.setBorderPainted(false);
		stats = new JButton ("Statistics");
		stats.setForeground(Color.WHITE);
		stats.setBackground(Color.DARK_GRAY);
		stats.setOpaque(true);
		stats.setBorderPainted(false);
		help = new JButton ("Help");
		help.setForeground(Color.WHITE);
		help.setBackground(Color.DARK_GRAY);
		help.setOpaque(true);
		help.setBorderPainted(false);
		title.setForeground(Color.WHITE);
		
		//add components to panel
		mainPanel.add(title);
		mainPanel.add(newGame);
		mainPanel.add(logout);
		mainPanel.add(exit);
		mainPanel.add(stats);
		mainPanel.add(help);
	    mainPanel.setOpaque(true);
	    mainPanel.setBackground(Color.BLACK);
		
	    //set the location in layout for all components
		Dimension size = new Dimension(100,25);
		title.setBounds(385, 25, 200, 100);
		newGame.setBounds(400-5, 175, size.width+10, size.height);
		stats.setBounds(400-2, 225, size.width+5, size.height);
		help.setBounds(400, 275, size.width, size.height);
		logout.setBounds(400, 325, size.width, size.height);
		exit.setBounds(400, 450, size.width, size.height);
		
		//quit program
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.sound.backgroundClip.stop();
				Window win = SwingUtilities.getWindowAncestor(mainPanel);
				win.dispose();
				System.exit(0);
			}
		});
	}
	
	/**
 	* Adds ActionListener to newGame JButton, changes displayed card to GamePanel.
	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addNewGameBtnActionListener(ActionListener listener) {
		newGame.addActionListener(listener);
	}
	
	/**
 	* Adds ActionListener to stats JButton, changes displayed card to StatsPanel.
 	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addStatsBtnActionListener(ActionListener listener) {
		stats.addActionListener(listener);
	}
	
	/**
 	* Adds ActionListener to help JButton, changes displayed card to HelpPanel.
 	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addHelpBtnActionListener(ActionListener listener) {
		help.addActionListener(listener);
	}
	
	/**
 	* Adds ActionListener to logout JButton, changes displayed card to LogoutPanel.
 	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addLogoutBtnActionListener(ActionListener listener) {
		logout.addActionListener(listener);
	}
	
	/**
 	* Returns the main component of this JPanel.
 	* @return Main Component of JPanel.
 	*/
	public JComponent getMainComponent() {
		return mainPanel;
	}
}
