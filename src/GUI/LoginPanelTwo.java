package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import accounts.Login;
import accounts.User;

public class LoginPanelTwo {
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("<html> <h1>Login Player 2</h1></html>");

	protected static final String usernameField = "Username";
	protected static final String passwordField = "Password";
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
    private String successSound = "src/res/sfx/success.wav";
	private String errorSound = "src/res/sfx/error.wav";
	private AudioInputStream audioInputStream;
	private Clip successClip;
	private Clip errorClip;

	private Login loginObject = Login.getInstance();

	public LoginPanelTwo() {
		
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(successSound).getAbsoluteFile());
			successClip = AudioSystem.getClip();
			successClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(errorSound).getAbsoluteFile());
			errorClip = AudioSystem.getClip();
			errorClip.open(audioInputStream);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		mainPanel.setLayout(null);

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
				boolean success = loginObject.login(enteredUsername,
						enteredPassword);
				boolean loggedIn = loginObject.checkLogedin(enteredUsername);
				if (!success) {
					actionLabel.setForeground(Color.RED);
					playSound(errorClip);
					actionLabel.setText("Unsuccessful Login.");
					actionLabel.setBounds(395, 250, 200, 25);
				} 
				else if (loggedIn) {
					actionLabel.setForeground(Color.RED);
					playSound(errorClip);
					actionLabel.setText(enteredUsername + ", you are already logged in!");
				} 
				else {
					actionLabel.setForeground(Color.WHITE);
					actionLabel.setBounds(400, 250, 200, 25);
					//cont.setVisible(true);
					username2.setEditable(false);
					password2.setEditable(false);
					User newUser = new User(enteredUsername);
					loginObject.setUserTwo(newUser);
					actionLabel.setText("Welcome " + loginObject.getUserNameTwo() + "!");
					playSound(successClip);
					cont.doClick();
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
	
	private void playSound(Clip clip) {
		clip.setFramePosition(0);
		clip.start();
		while (clip.getFramePosition() != clip.getFrameLength()) {
			//wait until clip has been played
		}
		clip.stop();
	}
}
