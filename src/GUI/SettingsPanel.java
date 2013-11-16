package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SettingsPanel {
	private JPanel mainPanel = new JPanel();

	private JLabel title = new JLabel("Settings");
	private JLabel settText;
	private JButton back;

	public SettingsPanel() {
		
		mainPanel.setLayout(null);
		settText = new JLabel("THIS IS WHERE THE SETTINGS GO");
		back = new JButton("Return to Main Menu");

		mainPanel.add(title);
		mainPanel.add(settText);
		mainPanel.add(back);
		
		Dimension size = new Dimension(100,25);
		settText.setBounds(350, 250, 200, size.height);
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