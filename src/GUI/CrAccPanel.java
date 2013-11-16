package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import accounts.CreateAccount;

public class CrAccPanel {
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("Create Account");
	
    protected static final String usernameField = "username";
    protected static final String passwordField = "password";
    
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
	
    public CrAccPanel() {

        mainPanel.setLayout(null);
        
        crAcc = new JButton("Create New Account");
        back = new JButton("Back");
        
        textFieldLabel = new JLabel(usernameField + ": ");
        textFieldLabel.setLabelFor(username);
        passwordFieldLabel = new JLabel(passwordField + ": ");
        passwordFieldLabel.setLabelFor(password);
        actionLabel = new JLabel("");
        
		mainPanel.add(title);
		mainPanel.add(crAcc);
		mainPanel.add(back);
		mainPanel.add(textFieldLabel);
		mainPanel.add(passwordFieldLabel);
		mainPanel.add(actionLabel);
		mainPanel.add(username);
		mainPanel.add(password);

		Dimension size = new Dimension(100,25);
		title.setBounds(400, 50, size.width, size.height);
		crAcc.setBounds(350, 350, 200, size.height);
		back.setBounds(400, 450, size.width, size.height);
		textFieldLabel.setBounds(350, 150, size.width, size.height);
		passwordFieldLabel.setBounds(350, 200, size.width, size.height);
		username.setBounds(450, 150, size.width, size.height);
		password.setBounds(450, 200, size.width, size.height);
		actionLabel.setBounds(350, 250, 200, size.height);
  
    }
	
	public void addCrtAccBtnActionListener(ActionListener listener) {
		String thisUsername = username.getText();
		String thisPassword = new String (password.getPassword());
		
		boolean success = true;
		if(success){
			crAcc.addActionListener(listener);
			crud.CSVHandler.addUser(thisUsername, thisPassword);
		}
		else{
			crAcc.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actionLabel.setText("Unsuccessful Account Creation.");
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