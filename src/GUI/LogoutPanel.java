package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * LogoutPanel class contains the panel in which the users can logout.
 * @author Alonso Medina
 * @version 1.0
 */
class LogoutPanel {
	
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("<html> <h1>Choose player(s) to logout</h1> </html>");
	/** JButton object which is used to logout player one */
	protected static JButton playerOne;
	/** JButton object which is used to logout player two */
	protected static JButton playerTwo;
	private JButton both;
	private JButton cancel;

    /**
     * This class contains the panel in which the users can logout.
     */
	public LogoutPanel() {
		
		mainPanel.setLayout(null);
		playerOne = new JButton("Player 1");
		playerOne.setForeground(Color.WHITE);
		playerOne.setBackground(Color.DARK_GRAY);
		playerOne.setOpaque(true);
		playerOne.setBorderPainted(false);
		playerTwo = new JButton("Player 2");
		playerTwo.setForeground(Color.WHITE);
		playerTwo.setBackground(Color.DARK_GRAY);
		playerTwo.setOpaque(true);
		playerTwo.setBorderPainted(false);
		both = new JButton("Both");
		both.setForeground(Color.WHITE);
		both.setBackground(Color.DARK_GRAY);
		both.setOpaque(true);
		both.setBorderPainted(false);
		cancel = new JButton ("Cancel");
		cancel.setForeground(Color.WHITE);
		cancel.setBackground(Color.DARK_GRAY);
		cancel.setOpaque(true);
		cancel.setBorderPainted(false);
		title.setForeground(Color.WHITE);

		mainPanel.add(title);
		mainPanel.add(playerOne);
		mainPanel.add(playerTwo);
		mainPanel.add(both);
		mainPanel.add(cancel);
	    mainPanel.setOpaque(true);
	    mainPanel.setBackground(Color.BLACK);
		
		Dimension size = new Dimension(100,25);
		title.setBounds(315, 25, 400, 100);
		playerOne.setBounds(400, 200, size.width, size.height);
		playerTwo.setBounds(400, 250, size.width, size.height);
		both.setBounds(400, 300, size.width, size.height);
		cancel.setBounds(400, 450, size.width, size.height);
		
		
	}
	
	/**
 	* Adds ActionListener to playerOne JButton, logs out player one and changes displayed card to LoginPanelOne.
 	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addPlayerOneActionListener(ActionListener listener) {
		playerOne.addActionListener(listener);
	}

	/**
 	* Adds ActionListener to playerTwo JButton, logs out player two and changes displayed card to LoginPanelTwo.
 	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addPlayerTwoActionListener(ActionListener listener) {
		playerTwo.addActionListener(listener);
	}
	
	/**
 	* Adds ActionListener to both JButton, logs out both players and changes displayed card to LoginPanelOne.
 	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addBothActionListener(ActionListener listener) {
		both.addActionListener(listener);
	}
	
	/**
 	* Adds ActionListener to cancel JButton, changes displayed card to MainPanel.
 	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addCancelActionListener(ActionListener listener) {
		cancel.addActionListener(listener);
	}
	
	/**
 	* Returns the main component of this JPanel.
 	* @return Main Component of JPanel.
 	*/
	public JComponent getMainComponent() {
		return mainPanel;
	}
}
