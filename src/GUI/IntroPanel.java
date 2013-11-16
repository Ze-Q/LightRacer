package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class IntroPanel {
	
	private static final Dimension MAIN_SIZE = new Dimension(MainWindow.WIDTH, MainWindow.HEIGHT);
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("Intro Menu");
	private JButton login;
	private JButton createAccount;
	private JButton exit;

	public IntroPanel() {
		
		mainPanel.setLayout(null);
		login = new JButton("Login");
		createAccount = new JButton("Create Account");
		exit = new JButton ("Exit");

		mainPanel.add(title);
		mainPanel.add(login);
		mainPanel.add(createAccount);
		mainPanel.add(exit);
	    mainPanel.setPreferredSize(MAIN_SIZE);
		
		Dimension size = new Dimension(100,25);
		title.setBounds(415, 50, size.width, size.height);
		login.setBounds(400, 150, size.width, size.height);
		createAccount.setBounds(375, 250, 150, size.height);
		exit.setBounds(400, 350, size.width, size.height);
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window win = SwingUtilities.getWindowAncestor(mainPanel);
				win.dispose();
			}
		});
	}
	public void addLoginBtnActionListener(ActionListener listener) {
		login.addActionListener(listener);
	}

	public void addCreateAccBtnActionListener(ActionListener listener) {
		createAccount.addActionListener(listener);
	}
	
	public JComponent getMainComponent() {
		return mainPanel;
	}
}