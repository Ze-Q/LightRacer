package GUI;

import accounts.*;
import crud.CSVHandler;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class CrAccPanel {
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("<html> <h1>Create Account</h1> </html>");

	protected static final String usernameField = "Username";
	protected static final String passwordField = "Password";

	public final int WIDTH = 900;
	public final int HEIGHT = WIDTH * 9 / 16;
	public final int SCALE = 1;

	protected JPasswordField password = new JPasswordField(10);
	protected JTextField username = new JTextField(10);
	protected JLabel actionLabel;
	private JButton crAcc;
	private JButton back;
	private JLabel textFieldLabel;
	private JLabel passwordFieldLabel;
	private String successSound = "src/res/sfx/success.wav";
	private String errorSound = "src/res/sfx/error.wav";
	private AudioInputStream audioInputStream;
	private Clip successClip;
	private Clip errorClip;

	public CrAccPanel() {

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
		
		crAcc = new JButton("Create New Account");
		crAcc.setForeground(Color.WHITE);
		crAcc.setBackground(Color.DARK_GRAY);
		back = new JButton("Back");
		back.setForeground(Color.WHITE);
		back.setBackground(Color.DARK_GRAY);
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
		mainPanel.add(crAcc);
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
		crAcc.setBounds(350, 400, 200, size.height);
		back.setBounds(400, 450, size.width, size.height);
		textFieldLabel.setBounds(350, 150, size.width, size.height);
		passwordFieldLabel.setBounds(350, 200, size.width, size.height);
		username.setBounds(450, 150, size.width, size.height);
		password.setBounds(450, 200, size.width, size.height);
		actionLabel.setBounds(300, 225, 400, size.height + 150);

		crAcc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String enteredUsername = username.getText();
				String enteredPassword = new String(password.getPassword());

				boolean success = CreateAccount.addUser(enteredUsername,
						enteredPassword);

				if (!success) {
					actionLabel.setForeground(Color.RED);
					playSound(errorClip);
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
					playSound(successClip);
					actionLabel.setText("Your Account Has Been Created.");

				}
			}
		});

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
