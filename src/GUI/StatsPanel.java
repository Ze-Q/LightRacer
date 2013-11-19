package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import accounts.Login;
import accounts.Statistics;
import accounts.User;

class StatsPanel {
	private JPanel mainPanel = new JPanel();

	private JLabel title = new JLabel("<html> <h1>Statistics</h1> </html>");
	private JLabel topTenTitle;
	private JLabel versusTitle;
	private JButton back;
	private JTable topTenTable;
	private JTable versusTable;
	private boolean isVersusTableInitialized=false;
	private boolean isTopTenTableInitialized=false;
	
	User user1;
	User user2;
	
	public StatsPanel() {
		
		mainPanel.setLayout(null);
		topTenTitle = new JLabel("Top Ten Records");
		topTenTitle.setForeground(Color.WHITE);
		topTenTitle.setBackground(Color.DARK_GRAY);
		versusTitle = new JLabel("Versus Records");
		versusTitle.setForeground(Color.WHITE);
		versusTitle.setBackground(Color.DARK_GRAY);
		back = new JButton("Return to Main Menu");
		back.setForeground(Color.WHITE);
		back.setBackground(Color.DARK_GRAY);
		back.setOpaque(true);
		back.setBorderPainted(false);
		title.setForeground(Color.WHITE);
		
		mainPanel.add(title);
		mainPanel.add(topTenTitle);
		mainPanel.add(versusTitle);
		mainPanel.add(back);
	    mainPanel.setOpaque(true);
	    mainPanel.setBackground(Color.BLACK);
		
		Dimension size = new Dimension(100,25);
		topTenTitle.setBounds(150, 140, 200,25);
		versusTitle.setBounds(600, 140, 200, 25);
		back.setBounds(350, 450, 200, size.height);
		title.setBounds(385, 25, 200, 100);
	}
	
	public void updateStatsPanel() {
		//update versus table
		user1 = Login.getInstance().getUserOne();
		user2 = Login.getInstance().getUserTwo();
		
		if(isVersusTableInitialized==true) {
			mainPanel.remove(versusTable);
		}
		
		versusTable = generateVersusTable();
		mainPanel.add(versusTable);
		isVersusTableInitialized=true;
		versusTable.setBounds(600, 160, 150, 45);
		versusTable.setBackground(Color.LIGHT_GRAY);
		versusTable.setEnabled(false);
		title.setForeground(Color.WHITE);
		
		//update top ten table
		if(isTopTenTableInitialized==true) {
			mainPanel.remove(topTenTable);
		}
		
		topTenTable=generateTopTenTable();
		mainPanel.add(topTenTable);
		isTopTenTableInitialized=true;
		topTenTable.setBounds(150, 160, 150, 175);
		topTenTable.setBackground(Color.LIGHT_GRAY);
		topTenTable.setEnabled(false);
		title.setForeground(Color.WHITE);
	}
	
	private JTable generateTopTenTable () {
		Statistics statistics = Statistics.getInstance();
		User[] results = statistics.getTopTen();
		
		String[] columnNames = {"Username", "Total Wins"};
		Object[][] data = new String[11][2];
		data[0][0] = "Username";
		data[0][1] = "Total Wins";
		for(int i=0; i<10&&results[i]!=null; i++) {
			data[i+1][0] = results[i].getUsername();
			data[i+1][1] = Integer.toString(results[i].getTotalWins());
		}
		
		JTable topTenTable = new JTable(data, columnNames);
		return topTenTable;
	}
	
	private JTable generateVersusTable() {
		Statistics statistics = Statistics.getInstance();
		statistics.readVersusFromFile(user1, user2);;
		
		String[] columnNames = {"Username", "Versus Wins"};
		Object[][] data = new String[3][2];
		
		data[0][0] = "Username";
		data[0][1] = "Total Wins";
		data[1][0] = user1.getUsername();
		data[1][1] = Integer.toString(user1.getVersusWins());
		data[2][0] = user2.getUsername();
		data[2][1] = Integer.toString(user2.getVersusWins());;
		
		JTable versusTable = new JTable(data, columnNames);
		return versusTable;
	}

	public void addBackBtnActionListener(ActionListener listener) {
		   back.addActionListener(listener);
	}
	
	public JComponent getMainComponent() {
	   return mainPanel;
	}
}