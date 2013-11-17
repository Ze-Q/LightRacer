package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import accounts.Login;
import accounts.User;

public class LoginPanelTwo {
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("Login Player 2");

	protected static final String usernameField = "username";
	protected static final String passwordField = "password";
	protected static final String loginString = "Login";

	public final int WIDTH = 900;
	public final int HEIGHT = WIDTH * 9 / 16;
	public final int SCALE = 1;

	protected static JPasswordField password2 = new JPasswordField(10);
	protected static JTextField username2 = new JTextField(10);
	protected static JLabel actionLabel;
	private JButton login;
	private JButton back;
	public static  JButton cont;
	private JLabel usernameLabel;
	private JLabel passwordLabel;

	private Login loginObject = Login.getInstance();

	public LoginPanelTwo() {

		mainPanel.setLayout(null);

		login = new JButton("Login");
		back = new JButton("Back");
		cont = new JButton("Continue");
		cont.setVisible(false);
		usernameLabel = new JLabel(usernameField + ": ");
		usernameLabel.setLabelFor(username2);
		passwordLabel = new JLabel(passwordField + ": ");
		passwordLabel.setLabelFor(password2);
		actionLabel = new JLabel("");

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

		Dimension size = new Dimension(100, 25);
		title.setBounds(415, 50, 100, size.height);
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
				boolean success = loginObject.login(enteredUsername,
						enteredPassword);
				boolean loggedIn = loginObject.checkLogedin(enteredUsername);
				if (!success) {
					actionLabel.setText("Unsuccessful Login.");
				} else if (loggedIn) {
					actionLabel.setText(enteredUsername
							+ ", you are already logged in!");
				} else {
					cont.setVisible(true);
					username2.setEditable(false);
					password2.setEditable(false);
					User newUser = new User(enteredUsername);
					loginObject.setUserTwo(newUser);
					actionLabel.setText("Welcome! "
							+ loginObject.getUserNameTwo());
					

				}
			}
		});

	}

	public void addContinueBtnActionListener(ActionListener listener) {
		cont.addActionListener(listener);
	}

	public void addBackBtnActionListener(ActionListener listener) {
		back.addActionListener(listener);
	}

	public JComponent getMainComponent() {
		return mainPanel;
	}
}
