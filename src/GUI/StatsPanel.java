package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import accounts.Login;
import accounts.Statistics;
import accounts.User;

class StatsPanel {
	private JPanel mainPanel = new JPanel();

	private JLabel title = new JLabel("Statistics");
	private JLabel topTenTitle;
	private JLabel versusTitle;
	private JButton back;
	private JTable topTenTable;
	private JTable versusTable;
	private boolean isVersusTableInitialized=false;
	
	User user1;
	User user2;
	
	public StatsPanel() {
		
		mainPanel.setLayout(null);
		topTenTitle = new JLabel("Top Ten Records");
		versusTitle = new JLabel("Versus Records");
		back = new JButton("Return to Main Menu");
		topTenTable = generateTopTenTable();
		
		mainPanel.add(title);
		mainPanel.add(topTenTitle);
		mainPanel.add(topTenTable);
		mainPanel.add(versusTitle);
		mainPanel.add(back);
		
		Dimension size = new Dimension(100,25);
		topTenTitle.setBounds(150, 140, 200,25);
		topTenTable.setBounds(150, 160, 150, 175);
		topTenTable.setBackground(Color.LIGHT_GRAY);
		versusTitle.setBounds(600, 140, 200, 25);
		back.setBounds(350, 450, 200, size.height);
		title.setBounds(415, 50, size.width, size.height);
	}
	
	public void updateStatsPanel() {
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