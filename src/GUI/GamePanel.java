package GUI;

import gameplay.Player;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

class GamePanel {
	private JPanel mainPanel = new JPanel();

	public int p1color;
	public int p2color;
	public int sp;
	public int mapNumber;
	
	private JLabel title = new JLabel("New Game Menu");
	private JLabel mapLabel;
	private DefaultComboBoxModel map;
	private JComboBox maps;
	private JLabel color1Label;
	private DefaultComboBoxModel color1;
	private JComboBox player1Color;
	private JLabel color2Label;
	private DefaultComboBoxModel color2;
	private JComboBox player2Color;
	private JLabel speedLabel;
	private JTextField speed;
	private JButton set;
	private JButton start;
	private JButton abort;
	private JLabel actionLabel;

	public GamePanel() {
		
		mainPanel.setLayout(null);

		speedLabel = new JLabel("Set Speed (2-10)" + ": ");
		speedLabel.setLabelFor(speed);
		mapLabel = new JLabel("Choose map" + ": ");
		map = new DefaultComboBoxModel();
		File[] mapFiles;
		File dir;
		dir = new File("./src/res");
		mapFiles = dir.listFiles();
		int mapsFound = mapFiles.length;
		for(int i = 0; i < mapsFound; i++){
			map.addElement(mapFiles[i].getName());
		}
		maps = new JComboBox(map);
		color1Label = new JLabel("Player 1" + ": ");
		color1 = new DefaultComboBoxModel();
		color2Label = new JLabel("Player 2" + ": ");
		color2 = new DefaultComboBoxModel();
		color1.addElement("Red");
		color1.addElement("Blue");
		color1.addElement("Green");
		color1.addElement("Yellow");
		color2.addElement("Red");
		color2.addElement("Blue");
		color2.addElement("Green");
		color2.addElement("Yellow");
		player1Color = new JComboBox(color1);
		player2Color = new JComboBox(color2);
		speed = new JTextField(10);
		start = new JButton("Start Game");
		abort = new JButton("Abort Game");
		set = new JButton("Set");
		actionLabel = new JLabel("THIS IS THE ACTION LABEL");

		mainPanel.add(title);
		mainPanel.add(start);
		mainPanel.add(abort);
		mainPanel.add(speedLabel);
		mainPanel.add(speed);
		mainPanel.add(set);
		mainPanel.add(mapLabel);
		mainPanel.add(maps);
		mainPanel.add(color1Label);
		mainPanel.add(player1Color);
		mainPanel.add(color2Label);
		mainPanel.add(player2Color);
		mainPanel.add(actionLabel);

		Dimension size = new Dimension(100,25);	
		abort.setBounds(375, 450, 150, size.height);
		title.setBounds(400, 50, size.width, size.height);
		start.setBounds(400, 400, size.width, size.height);
		mapLabel.setBounds(350, 100, size.width, size.height);
		maps.setBounds(450, 100, size.width, size.height);
		color1Label.setBounds(225, 200, 75, size.height);
		player1Color.setBounds(325, 200, size.width, size.height);
		color2Label.setBounds(450, 200, 75, size.height);
		player2Color.setBounds(550, 200, size.width, size.height);
		speedLabel.setBounds(350, 150, size.width, size.height);
		speed.setBounds(450, 150, size.width, size.height);
		set.setBounds(400, 350, size.width, size.height);
		actionLabel.setBounds(250, 250, 400, size.height);
		
		set.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean success = true;
				String colorPlayer1 = player1Color.getSelectedItem().toString();
				String colorPlayer2 = player2Color.getSelectedItem().toString();
				String chosenMap = maps.getSelectedItem().toString();
				int spd;
				try {
					spd = Integer.parseInt(speed.getText());
				} catch (Exception ex) {
					spd = -1;
				}
				if(colorPlayer1.equals(colorPlayer2)){
					actionLabel.setText("Choose different colors.");
				}
				else if(spd > 10 || spd < 2){
					actionLabel.setText("Invalid speed.");
				}
				else { 
					if(colorPlayer1.equals("Red")){
						p1color = Player.RED;
					}
					if(colorPlayer1.equals("Blue")){
						p1color = Player.BLUE;
					}
					if(colorPlayer1.equals("Green")){
						p1color = Player.GREEN;
					}
					if(colorPlayer1.equals("Yellow")){
						p1color = Player.YELLOW;
					}
					if(colorPlayer2.equals("Red")){
						p2color = Player.RED;
					}
					if(colorPlayer2.equals("Blue")){
						p2color = Player.BLUE;
					}
					if(colorPlayer2.equals("Green")){
						p2color = Player.GREEN;
					}
					if(colorPlayer2.equals("Yellow")){
						p2color = Player.YELLOW;
					}
					sp = spd;
					mapNumber = Character.getNumericValue((chosenMap.charAt(3)));
					actionLabel.setText("" + mapNumber);
				}
			}
		});
	
	}

	public void addAbortBtnActionListener(ActionListener listener) {
		   abort.addActionListener(listener);
	}
	
	public void addStartBtnActionListener(ActionListener listener) {
		   start.addActionListener(listener);
	}
	
	public JComponent getMainComponent() {
	   return mainPanel;
	}
}
