package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * IntroPanel class contains introductory panel that displays when user first launches the program.
 * @author Alonso Medina
 * @version 1.0
 */
class IntroPanel {
	
    private final static int WIDTH = 900;
    private final static int HEIGHT = WIDTH * 9 / 16 ;
	private static final Dimension MAIN_SIZE = new Dimension(WIDTH, HEIGHT);
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("<html> <h1>Light Racer</h1> </html>");
	private JButton login;
	private JButton createAccount;
	private JButton exit;

    /**
     * IntroPanel class contains introductory panel that displays when user first launches the program.
     */
	public IntroPanel() {
		
		//initialize and format components
		mainPanel.setLayout(null);
		login = new JButton("Login");
		login.setForeground(Color.WHITE);
		login.setBackground(Color.DARK_GRAY);
		login.setOpaque(true);
		login.setBorderPainted(false);
		createAccount = new JButton("Create Account");
		createAccount.setForeground(Color.WHITE);
		createAccount.setBackground(Color.DARK_GRAY);
		createAccount.setOpaque(true);
		createAccount.setBorderPainted(false);
		exit = new JButton ("Exit");
		exit.setForeground(Color.WHITE);
		exit.setBackground(Color.DARK_GRAY);
		exit.setOpaque(true);
		exit.setBorderPainted(false);
		title.setForeground(Color.WHITE);

		//add components to panel
		mainPanel.add(title);
		mainPanel.add(login);
		mainPanel.add(createAccount);
		mainPanel.add(exit);
	    mainPanel.setPreferredSize(MAIN_SIZE);
	    mainPanel.setOpaque(true);
	    mainPanel.setBackground(Color.BLACK);
		
	    //set the location in layout for all components
		Dimension size = new Dimension(100,25);
		title.setBounds(385, 25, 200, 100);
		login.setBounds(400, 200, size.width, size.height);
		createAccount.setBounds(375, 250, 150, size.height);
		exit.setBounds(400, 450, size.width, size.height);
		
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
 	* Adds ActionListener to login JButton, changes displayed card to LoginPanelOne or LoginPanelTwo.
 	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addLoginBtnActionListener(ActionListener listener) {
		login.addActionListener(listener);
	}

	/**
 	* Adds ActionListener to createAccount JButton, changes displayed card to CreateAccountPanel.
 	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addCreateAccBtnActionListener(ActionListener listener) {
		createAccount.addActionListener(listener);
	}
	
	/**
 	* Returns the main component of this JPanel.
 	* @return Main Component of JPanel.
 	*/
	public JComponent getMainComponent() {
		return mainPanel;
	}
}
