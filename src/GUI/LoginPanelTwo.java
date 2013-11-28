package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import accounts.Login;

/**
 * This class contains the panel in which a user can login as player two.
 * @author Alonso Medina
 * @author Cheng Gong
 * @version 1.0
 */
class LoginPanelTwo {
	
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("<html> <h1>Login Player 2</h1></html>");
	private String usernameField = "Username";
	private String passwordField = "Password";
	/** JPasswordField object used to store the password of player two. */
	protected static JPasswordField password2 = new JPasswordField(10);
	/** JTextField object used to store the username of player two. */
	protected static JTextField username2 = new JTextField(10);
	/** JLabel object used to display error messages or welcome message. */
	protected static JLabel actionLabel;
	private JButton cont;
	private JButton login;
	private JButton back;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
    
	private Sound sound = new Sound();

	private Login loginObject = Login.getInstance();

    /**
     * This class contains the panel in which a user can login as player two.
     */
	public LoginPanelTwo() {

		mainPanel.setLayout(null);

		//initialize and format components
		login = new JButton("Login");
		login.setForeground(Color.WHITE);
		login.setBackground(Color.DARK_GRAY);
		login.setOpaque(true);
		login.setBorderPainted(false);
		back = new JButton("Back");
		back.setForeground(Color.WHITE);
		back.setBackground(Color.DARK_GRAY);
		back.setOpaque(true);
		back.setBorderPainted(false);
		cont = new JButton("Continue");
		cont.setForeground(Color.WHITE);
		cont.setBackground(Color.DARK_GRAY);
		cont.setOpaque(true);
		cont.setBorderPainted(false);
		cont.setVisible(false);
		usernameLabel = new JLabel(usernameField + ": ");
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setLabelFor(username2);
		passwordLabel = new JLabel(passwordField + ": ");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setLabelFor(password2);
		actionLabel = new JLabel("");
		actionLabel.setForeground(Color.WHITE);
		title.setForeground(Color.WHITE);

		//add components to panel
		mainPanel.add(title);
		mainPanel.add(login);
		mainPanel.add(back);
		mainPanel.add(usernameLabel);
		mainPanel.add(passwordLabel);
		mainPanel.add(actionLabel);
		mainPanel.add(username2);
		mainPanel.add(password2);
		mainPanel.add(actionLabel);
		mainPanel.add(cont);
	    mainPanel.setOpaque(true);
	    mainPanel.setBackground(Color.BLACK);

	    //set the location in layout for all components
		Dimension size = new Dimension(100, 25);
		title.setBounds(375, 50, 200, 50);
		login.setBounds(400, 350, size.width, size.height);
		back.setBounds(400, 400, size.width, size.height);
		cont.setBounds(400, 450, size.width, size.height);
		usernameLabel.setBounds(375, 150, size.width, size.height);
		passwordLabel.setBounds(375, 200, size.width, size.height);
		username2.setBounds(450, 150, size.width, size.height);
		password2.setBounds(450, 200, size.width, size.height);
		actionLabel.setBounds(380, 250, 200, size.height);

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String enteredUsername = username2.getText();
				String enteredPassword = new String(password2.getPassword());
				
				//verify user info
				boolean success = loginObject.isValidUserInfo(enteredUsername, enteredPassword);
				//verify if user is logged in
				boolean loggedIn = loginObject.checkLogedin(enteredUsername);
				
				//display error message
				if (!success) {
					actionLabel.setForeground(Color.RED);
					sound.playSound(sound.errorClip);
					actionLabel.setText("Unsuccessful Login.");
					actionLabel.setBounds(395, 250, 200, 25);
				} 
				//display already logged in message
				else if (loggedIn) {
					actionLabel.setForeground(Color.RED);
					sound.playSound(sound.errorClip);
					actionLabel.setText(enteredUsername + ", you are already logged in!");
				} 
				//display welcome message
				else {
					actionLabel.setForeground(Color.WHITE);
					actionLabel.setBounds(400, 250, 200, 25);
					username2.setEditable(false);
					password2.setEditable(false);
					loginObject.loginUserTwo(enteredUsername, enteredPassword);
					actionLabel.setText("Welcome " + loginObject.getUserNameTwo() + "!");
					actionLabel.paintImmediately(actionLabel.getVisibleRect());
					sound.playSound(sound.successClip);
					cont.doClick();
				}
			}
		});
	}

	/**
 	* Adds ActionListener to cont JButton, changes displayed card to MainPanel.
 	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addContinueBtnActionListener(ActionListener listener) {
		cont.addActionListener(listener);
	}

	/**
 	* Adds ActionListener to back JButton, changes displayed card to IntroPanel.
 	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addBackBtnActionListener(ActionListener listener) {
		back.addActionListener(listener);
	}

	/**
 	* Returns the main component of this JPanel.
 	* @return Main Component of JPanel.
 	*/
	public JComponent getMainComponent() {
		return mainPanel;
	}
	
}
