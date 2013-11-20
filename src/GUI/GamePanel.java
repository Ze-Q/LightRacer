package GUI;

import gameplay.Player;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class GamePanel {
	public JPanel mainPanel = new JPanel();

	public int p1color;
	public int p2color;
	public int sp;
	public int mapNumber;
	
	public JLabel title = new JLabel("<html> <h1>Current Score: " + MainWindow.score.getP1() + " - " +  MainWindow.score.getP2() + "</h1> </html>");
	protected JLabel mapLabel;
	protected DefaultComboBoxModel map;
	protected JComboBox maps;
	protected JLabel color1Label;
	protected DefaultComboBoxModel color1;
	protected JComboBox player1Color;
	protected JLabel color2Label;
	protected DefaultComboBoxModel color2;
	protected JComboBox player2Color;
	protected JLabel speedLabel;
	protected JTextField speed;
	public JButton set;
	public JButton start;
	public JButton abort;
	public JButton ret;
	public JLabel actionLabel;
	
    private String successSound = "src/res/sfx/success.wav";
	private String errorSound = "src/res/sfx/error.wav";
	private AudioInputStream audioInputStream;
	private Clip successClip;
	private Clip errorClip;

	public GamePanel() {
		
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(successSound).getAbsoluteFile());
			successClip = AudioSystem.getClip();
			successClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(errorSound).getAbsoluteFile());
			errorClip = AudioSystem.getClip();
			errorClip.open(audioInputStream);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		mainPanel.setLayout(null);

		speedLabel = new JLabel("Set Speed (3-10)" + ": ");
		speedLabel.setLabelFor(speed);
		speedLabel.setForeground(Color.WHITE);
		mapLabel = new JLabel("Choose map" + ": ");
		mapLabel.setForeground(Color.WHITE);
		map = new DefaultComboBoxModel();
		File[] mapFiles;
		File dir;
		dir = new File("./src/res/maps");
		mapFiles = dir.listFiles();
		int mapsFound = mapFiles.length;
		for(int i = 0; i < mapsFound; i++){
			map.addElement(mapFiles[i].getName());
		}
		maps = new JComboBox(map);
		maps.setBackground(Color.WHITE);
		maps.setForeground(Color.BLACK);
		color1Label = new JLabel("Player 1" + ": ");
		color1Label.setForeground(Color.WHITE);
		color1 = new DefaultComboBoxModel();
		color2Label = new JLabel("Player 2" + ": ");
		color2Label.setForeground(Color.WHITE);
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
		player1Color.setBackground(Color.WHITE);
		player1Color.setForeground(Color.BLACK);
		player2Color = new JComboBox(color2);
		player2Color.setBackground(Color.WHITE);
		player2Color.setForeground(Color.black);
		speed = new JTextField(10);
		start = new JButton("Start Game");
		start.setForeground(Color.WHITE);
		start.setBackground(Color.DARK_GRAY);
		start.setOpaque(true);
		start.setBorderPainted(false);
		start.setVisible(false);
		abort = new JButton("Abort Game");
		abort.setForeground(Color.WHITE);
		abort.setBackground(Color.DARK_GRAY);
		abort.setOpaque(true);
		abort.setBorderPainted(false);
		set = new JButton("Start Round!");
		set.setForeground(Color.WHITE);
		set.setBackground(Color.DARK_GRAY);
		set.setOpaque(true);
		set.setBorderPainted(false);
		actionLabel = new JLabel("");
		ret = new JButton("Return to Main Menu");
		ret.setForeground(Color.WHITE);
		ret.setBackground(Color.DARK_GRAY);
		ret.setVisible(false);
		ret.setOpaque(true);
		ret.setBorderPainted(false);
		title.setForeground(Color.WHITE);

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
		mainPanel.add(ret);
	    mainPanel.setOpaque(true);
	    mainPanel.setBackground(Color.BLACK);

		Dimension size = new Dimension(100,25);	
		title.setBounds(340, 25, 400, 100);
		abort.setBounds(375, 450, 150, size.height);
		start.setBounds(400, 400, size.width, size.height);
		mapLabel.setBounds(350, 125, size.width, size.height);
		maps.setBounds(450, 125, size.width+15, size.height);
		color1Label.setBounds(225, 225, 75, size.height);
		player1Color.setBounds(325, 225, size.width, size.height);
		color2Label.setBounds(450, 225, 75, size.height);
		player2Color.setBounds(550, 225, size.width, size.height);
		speedLabel.setBounds(335, 175, size.width+15, size.height);
		speed.setBounds(450, 175, size.width, size.height);
		set.setBounds(400, 350, size.width, size.height);
		actionLabel.setBounds(400, 275, 400, 75);
		ret.setBounds(375, 450, 150, size.height);
		
		set.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
					playSound(errorClip);
					actionLabel.setText("Choose different colors.");
					actionLabel.setBounds(385, 250, 400, 75);
					actionLabel.setForeground(Color.RED);
					start.setEnabled(false);
				}
				else if(spd > 10 || spd < 3){
					playSound(errorClip);
					actionLabel.setText("Invalid speed.");
					actionLabel.setBounds(410, 250, 400, 75);
					actionLabel.setForeground(Color.RED);
					start.setEnabled(false);
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
					actionLabel.setForeground(Color.WHITE);
					actionLabel.setBounds(400, 275, 400, 75);
					actionLabel.setText("<html>" + "Map: " + chosenMap + "<br>" +  "Speed: " + sp + "<br>" + "Player 1:" + colorPlayer1 + "<br>" + "Player 2:" + colorPlayer2 + "</html>");
					playSound(successClip);
					start.doClick();
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
	
	public void addRetBtnActionListener(ActionListener listener) {
		   ret.addActionListener(listener);
	}
	
	public JComponent getMainComponent() {
	   return mainPanel;
	}
	
	private void playSound(Clip clip) {
		clip.setFramePosition(0);
		clip.start();
		while (clip.getFramePosition() != clip.getFrameLength()) {
			//wait until clip has been played
		}
		clip.stop();
	}
}
