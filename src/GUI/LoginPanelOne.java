package GUI;

import accounts.Login;
import accounts.User;

import java.awt.*;
import java.awt.event.*;

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

   	protected JPasswordField password1 = new JPasswordField(10);
    protected JTextField username1 = new JTextField(10);
    protected JLabel actionLabel;
    private JButton cont;
    private JButton login;
    private JButton back;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    
    private Login loginObject = Login.getInstance(); 
	
    public LoginPanelOne() {

        mainPanel.setLayout(null);
        
        login = new JButton("Login");
        back = new JButton("Back");
        cont = new JButton("Continue");
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
					actionLabel.setText("Unsuccessful Login.");
				}
				else if(loggedIn){
					actionLabel.setText(enteredUsername + ", you are already logged in!");
				}
				else{
					
					User newUser = new User(enteredUsername);
					loginObject.setUserOne(newUser);
					actionLabel.setText("Welcome! " + loginObject.getUserNameOne());
					
				}
			}
		});
    }
	
	public void addContinueBtnActionListener(ActionListener listener) {
		boolean success = true;
		if(success){
			cont.addActionListener(listener);
		}
		else{
			cont.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actionLabel.setText("Unsuccessful Login Attempt");
				}
			});
		}
	}
	
	public void addBackBtnActionListener(ActionListener listener) {
		   back.addActionListener(listener);
	}

	public JComponent getMainComponent() {
		return mainPanel;
	}
}
