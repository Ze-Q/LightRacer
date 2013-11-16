package GUI;

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

    protected JPasswordField password = new JPasswordField(10);
    protected JTextField username = new JTextField(10);
    protected JLabel actionLabel;
    private JButton login;
    private JButton back;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
	
    public LoginPanelOne() {

        mainPanel.setLayout(null);
        
        login = new JButton("Login");
        back = new JButton("Back");
        usernameLabel = new JLabel(usernameField + ": ");
        usernameLabel.setLabelFor(username);
        passwordLabel = new JLabel(passwordField + ": ");
        passwordLabel.setLabelFor(password);
        actionLabel = new JLabel("");
        
		mainPanel.add(title);
		mainPanel.add(login);
		mainPanel.add(back);
		mainPanel.add(usernameLabel);
		mainPanel.add(passwordLabel);
		mainPanel.add(actionLabel);
		mainPanel.add(username);
		mainPanel.add(password);
		mainPanel.add(actionLabel);

		Dimension size = new Dimension(100,25);
		title.setBounds(415, 50, 100, size.height);
		login.setBounds(400, 350, size.width, size.height);
		back.setBounds(400, 450, size.width, size.height);
		usernameLabel.setBounds(375, 150, size.width, size.height);
		passwordLabel.setBounds(375, 200, size.width, size.height);
		username.setBounds(450, 150, size.width, size.height);
		password.setBounds(450, 200, size.width, size.height);
		actionLabel.setBounds(380, 250, 200, size.height);
  
    }
	
	public void addLoginBtnActionListener(ActionListener listener) {
		boolean success = true;
		if(success){
			login.addActionListener(listener);
		}
		else{
			login.addActionListener( new ActionListener() {
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