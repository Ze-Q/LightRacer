package GUI;

import accounts.*;
import crud.CSVHandler;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class CreateAccountPanel {
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("<html> <h1>Create Account</h1> </html>");

	private String usernameField = "Username";
	private String passwordField = "Password";
	protected JPasswordField password = new JPasswordField(10);
	protected JTextField username = new JTextField(10);
	protected JLabel actionLabel;
	private JButton createAccount;
	private JButton back;
	private JLabel textFieldLabel;
	private JLabel passwordFieldLabel;

	private Sound sound = new Sound();

    /**
     * This class contains the panel in which a user can create a new account
     * Main parameters:
     * @param password			stores the input password
     * @param username			stores the input username
     * @param actionLabel		displays error message if creation was unsuccessful and success message if creation was successful
     * @param sound				Sound object handling sound output
     */
	public CreateAccountPanel() {
		
		mainPanel.setLayout(null);
		
		createAccount = new JButton("Create New Account");
		createAccount.setForeground(Color.WHITE);
		createAccount.setBackground(Color.DARK_GRAY);
		createAccount.setOpaque(true);
		createAccount.setBorderPainted(false);
		back = new JButton("Back");
		back.setForeground(Color.WHITE);
		back.setBackground(Color.DARK_GRAY);
		back.setOpaque(true);
		back.setBorderPainted(false);
		textFieldLabel = new JLabel(usernameField + ": ");
		textFieldLabel.setForeground(Color.WHITE);
		textFieldLabel.setLabelFor(username);
		passwordFieldLabel = new JLabel(passwordField + ": ");
		passwordFieldLabel.setForeground(Color.WHITE);
		passwordFieldLabel.setLabelFor(password);
		actionLabel = new JLabel("");
		actionLabel.setForeground(Color.WHITE);
		title.setForeground(Color.WHITE);

		mainPanel.add(title);
		mainPanel.add(createAccount);
		mainPanel.add(back);
		mainPanel.add(textFieldLabel);
		mainPanel.add(passwordFieldLabel);
		mainPanel.add(actionLabel);
		mainPanel.add(username);
		mainPanel.add(password);
	    mainPanel.setOpaque(true);
	    mainPanel.setBackground(Color.BLACK);

		Dimension size = new Dimension(100, 25);
		title.setBounds(370, 25, 200, 100);
		createAccount.setBounds(350, 400, 200, size.height);
		back.setBounds(400, 450, size.width, size.height);
		textFieldLabel.setBounds(350, 150, size.width, size.height);
		passwordFieldLabel.setBounds(350, 200, size.width, size.height);
		username.setBounds(450, 150, size.width, size.height);
		password.setBounds(450, 200, size.width, size.height);
		actionLabel.setBounds(300, 225, 400, size.height + 150);

		createAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String enteredUsername = username.getText();
				String enteredPassword = new String(password.getPassword());

				boolean success = CreateAccount.addUser(enteredUsername,
						enteredPassword);

				if (!success) {
					actionLabel.setForeground(Color.RED);
					sound.playSound(sound.errorClip);
					String errorMessage = "";
					try {
						if (CSVHandler.isExist(enteredUsername))
							errorMessage = errorMessage + "Username Already Exists.<br>";
						errorMessage = errorMessage + CSVHandler.isValidUsername(enteredUsername);
						errorMessage = errorMessage + CSVHandler.isValidPassword(enteredPassword);
						actionLabel.setText("<html>" + errorMessage + "</html>");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					actionLabel.setForeground(Color.WHITE);
					actionLabel.setBounds(355, 225, 400, 175);
					sound.playSound(sound.successClip);
					actionLabel.setText("Your Account Has Been Created.");

				}
			}
		});

	}

	/**
 	* Adds ActionListener to back JButton, changes displayed card to IntroPanel
 	*/
	public void addBackBtnActionListener(ActionListener listener) {
		back.addActionListener(listener);
	}
	
	/**
 	* Returns the main component of this JPanel
 	* @return Main Component of JPanel
 	*/
	public JComponent getMainComponent() {
		return mainPanel;
	}

}
