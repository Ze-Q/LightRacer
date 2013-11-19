package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class IntroPanel {
	
	private static final Dimension MAIN_SIZE = new Dimension(MainWindow.WIDTH, MainWindow.HEIGHT);
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("<html> <h1>Light Racer</h1> </html>");
	private JButton login;
	private JButton createAccount;
	private JButton exit;

	public IntroPanel() {
		
		mainPanel.setLayout(null);
		login = new JButton("Login");
		login.setForeground(Color.WHITE);
		login.setBackground(Color.DARK_GRAY);
		login.setOpaque(true);
		login.setBorderPainted(false);
		createAccount = new JButton("Create Account");
		createAccount.setForeground(Color.WHITE);
		createAccount.setBackground(Color.DARK_GRAY);
		createAccount.setOpaque(true);
		createAccount.setBorderPainted(false);
		exit = new JButton ("Exit");
		exit.setForeground(Color.WHITE);
		exit.setBackground(Color.DARK_GRAY);
		exit.setOpaque(true);
		exit.setBorderPainted(false);
		title.setForeground(Color.WHITE);

		mainPanel.add(title);
		mainPanel.add(login);
		mainPanel.add(createAccount);
		mainPanel.add(exit);
	    mainPanel.setPreferredSize(MAIN_SIZE);
	    mainPanel.setOpaque(true);
	    mainPanel.setBackground(Color.BLACK);
		
		Dimension size = new Dimension(100,25);
		title.setBounds(385, 25, 200, 100);
		login.setBounds(400, 200, size.width, size.height);
		createAccount.setBounds(375, 250, 150, size.height);
		exit.setBounds(400, 450, size.width, size.height);
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.backgroundClip.stop();
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