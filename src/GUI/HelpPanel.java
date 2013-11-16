package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class HelpPanel {
	
	private JPanel mainPanel = new JPanel();

	private JLabel title = new JLabel("Help");
	private JLabel helpText;
	private JButton back;

	public HelpPanel() {
		
		mainPanel.setLayout(null);
		helpText = new JLabel("THIS IS WHERE THE HELP GOES");
		back = new JButton("Return to Main Menu");

		mainPanel.add(title);
		mainPanel.add(helpText);
		mainPanel.add(back);
		
		Dimension size = new Dimension(100,25);
		helpText.setBounds(350, 250, 200, size.height);
		back.setBounds(350, 450, 200, size.height);
		title.setBounds(415, 50, size.width, size.height);
	}

	public void addBackBtnActionListener(ActionListener listener) {
		   back.addActionListener(listener);
	}
	
	public JComponent getMainComponent() {
	   return mainPanel;
	}
}