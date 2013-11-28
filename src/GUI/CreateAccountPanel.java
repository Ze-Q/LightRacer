package GUI;

import accounts.*;
import crud.CSVHandler;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 * This class contains the panel in which a user can create a new account.
 * @author Alonso Medina
 * @author Cheng Gong
 * @version 1.0
 */
class CreateAccountPanel {
	
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("<html> <h1>Create Account</h1> </html>");

	private String usernameField = "Username";
	private String passwordField = "Password";
	private JPasswordField password = new JPasswordField(10);
	private JTextField username = new JTextField(10);
	private JLabel actionLabel;
	private JButton createAccount;
	private JButton back;
	private JLabel textFieldLabel;
	private JLabel passwordFieldLabel;

	private Sound sound = new Sound();

    /**
     * This class contains the panel in which a user can create a new account.
     */
	public CreateAccountPanel() {
		
		mainPanel.setLayout(null);
		
		//initialize and format components
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

		//add components to panel
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

	    //set the location in layout for all components
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

				//call to CreateAccount class in accounts package
				boolean success = CreateAccount.addUser(enteredUsername, enteredPassword);

				//check for relevant errors and display error message
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
						e1.printStackTrace();
					}
				//display success message
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
