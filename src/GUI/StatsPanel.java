package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class StatsPanel {
	private JPanel mainPanel = new JPanel();

	private JLabel title = new JLabel("Statistics");
	private JLabel statsText;
	private JButton back;

	public StatsPanel() {
		
		mainPanel.setLayout(null);
		statsText = new JLabel("THIS IS WHERE THE STATS GO");
		back = new JButton("Return to Main Menu");

		mainPanel.add(title);
		mainPanel.add(statsText);
		mainPanel.add(back);
		
		Dimension size = new Dimension(100,25);
		statsText.setBounds(350, 250, 200, size.height);
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