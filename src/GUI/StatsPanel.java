package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import accounts.Login;
import accounts.Statistics;
import accounts.User;

/**
 * StatsPanel display the screen that contains the versus records and top ten records.
 * @author Ze
 * @version 1.0
 */
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
	private User user1;
	private User user2;
	
    /**
     * This panel displays two JTables with statistics of the players records.
     */
	public StatsPanel() {
		
		//initialize and format components
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
		
		//add components to panel
		mainPanel.add(title);
		mainPanel.add(topTenTitle);
		mainPanel.add(versusTitle);
		mainPanel.add(back);
	    mainPanel.setOpaque(true);
	    mainPanel.setBackground(Color.BLACK);
		
	    //set the location in layout for all components
		Dimension size = new Dimension(100,25);
		topTenTitle.setBounds(150, 140, 200,25);
		versusTitle.setBounds(600, 140, 200, 25);
		back.setBounds(350, 450, 200, size.height);
		title.setBounds(385, 25, 200, 100);
	}
	
	/**
 	* This method updates both JTables to reflect the current stats (with the currents Users)
 	*/
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

	/**
 	* Adds ActionListener to the back JButton, changes displayed card to MainPanel.
 	* @param listener contains action to be taken by button.
 	*/
	public void addBackBtnActionListener(ActionListener listener) {
		   back.addActionListener(listener);
	}
	
	/**
 	* Returns the main component of this JPanel
 	* @return Return the main Component(a JPanel) of JPanel
 	*/
	public JComponent getMainComponent() {
	   return mainPanel;
	}
}
