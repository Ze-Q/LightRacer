package GUI;

import accounts.Login;
import accounts.User;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class LoginPanelOne {
	
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("Login Player 1");
	
    protected static final String usernameField = "username";
    protected static final String passwordField = "password";
    protected static final String loginString = "Login";
    
    public final int WIDTH = 900;
    public final int HEIGHT = WIDTH * 9 / 16;
    public final int SCALE = 1;

   	protected static JPasswordField password1 = new JPasswordField(10);
    protected static JTextField username1 = new JTextField(10);
    protected static JLabel actionLabel;
    public static JButton cont;
    private  static JButton login;
    private JButton back;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private String successSound = "src/res/sfx/success.wav";
	private String errorSound = "src/res/sfx/error.wav";
	private AudioInputStream audioInputStream;
	private Clip successClip;
	private Clip errorClip;
    
    private Login loginObject = Login.getInstance(); 
	
    public LoginPanelOne() {
    	
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
        back = new JButton("Back");
        cont = new JButton("Continue");
        cont.setVisible(false);
        usernameLabel = new JLabel(usernameField + ": ");
        usernameLabel.setLabelFor(username1);
        passwordLabel = new JLabel(passwordField + ": ");
        passwordLabel.setLabelFor(password1);
        actionLabel = new JLabel("");
        
		mainPanel.add(title);
		mainPanel.add(login);
		mainPanel.add(back);
		mainPanel.add(usernameLabel);
		mainPanel.add(passwordLabel);
		mainPanel.add(actionLabel);
		mainPanel.add(username1);
		mainPanel.add(password1);
		mainPanel.add(actionLabel);
		mainPanel.add(cont);

		Dimension size = new Dimension(100,25);
		title.setBounds(415, 50, 100, size.height);
		login.setBounds(400, 350, size.width, size.height);
		back.setBounds(400, 400, size.width, size.height);
		cont.setBounds(400, 450, size.width, size.height);
		usernameLabel.setBounds(375, 150, size.width, size.height);
		passwordLabel.setBounds(375, 200, size.width, size.height);
		username1.setBounds(450, 150, size.width, size.height);
		password1.setBounds(450, 200, size.width, size.height);
		actionLabel.setBounds(380, 250, 200, size.height);
		
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String enteredUsername = username1.getText();
				String enteredPassword = new String (password1.getPassword());
				boolean success = loginObject.login(enteredUsername, enteredPassword);
				boolean loggedIn = loginObject.checkLogedin(enteredUsername);
				if(!success){
					playSound(errorClip);
					actionLabel.setText("Unsuccessful Login.");
				}
				else if(loggedIn){
					playSound(errorClip);
					actionLabel.setText(enteredUsername + ", you are already logged in!");
				
				}
				else{
					playSound(successClip);
					cont.setVisible(true);
					username1.setEditable(false);
					password1.setEditable(false);
					User newUser = new User(enteredUsername);
					loginObject.setUserOne(newUser);
					actionLabel.setText("Welcome! " + loginObject.getUserNameOne());
					
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
