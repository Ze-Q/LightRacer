package GUI;

import gameplay.Player;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

/**
 * GamePanel class contains the menu which will display all the game options to the players and starts a new game round.
 * @author Alonso Medina
 * @version 1.0
 */
public class GamePanel {
	
	private JPanel mainPanel = new JPanel();
	/** Int used to store the selected color for player one. */
	public int p1color;
	/** Int used to store the selected color for player two. */
	public int p2color;
	/** Int used to store the selected speed for this round. */
	public int sp;
	/** Int used to store the selected map for this round. */
	public int mapNumber;
	/** JLabel object which displays the current score. */
	public JLabel title = new JLabel("<html> <h1></h1> </html>");
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
	protected JTextField speed;
	/** JButton object which verifies the selected settings are valid and then automatically calls start otherwise displays an error. */
	public JButton set;
	/** JButton object which is automatically called by set if settings are valid, creates a new Game object based on the selected settings. */
	public JButton start;
	/** JButton object which aborts the current match. */
	public JButton abort;
	/** JButton object displayed at the end of the match, changes panel to MainPanel. */
	public JButton ret;
	/** JLabel object which is used to display error messages or verification message. */
	public JLabel actionLabel;
	
	private Sound sound = new Sound();

    /**
     * GamePanel class contains the menu which will display all the game options to the players and starts a new game round.
     */
	public GamePanel() {
		
		mainPanel.setLayout(null);

		//initialize and format components
		speedLabel = new JLabel("Set Speed (1-10)" + ": ");
		speedLabel.setLabelFor(speed);
		speedLabel.setForeground(Color.WHITE);
		mapLabel = new JLabel("Choose map" + ": ");
		mapLabel.setForeground(Color.WHITE);
		map = new DefaultComboBoxModel();
		File[] mapFiles;
		File dir;
		dir = new File("./res/image");
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

		//add components to panel
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

	    //set the location in layout for all components
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
		set.setBounds(400-15, 350, size.width+30, size.height);
		actionLabel.setBounds(400, 275, 400, 75);
		ret.setBounds(375, 450, 150+40, size.height);
		
		//this method verifies the validity of the selected settings
		set.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String colorPlayer1 = player1Color.getSelectedItem().toString();
				String colorPlayer2 = player2Color.getSelectedItem().toString();
				String chosenMap = maps.getSelectedItem().toString();
				int spd;
				//verify speed is an integer
				try {
					spd = Integer.parseInt(speed.getText());
				} catch (Exception ex) {
					spd = -1;
				}
				//verify different colors
				if(colorPlayer1.equals(colorPlayer2)){
					sound.playSound(sound.errorClip);
					actionLabel.setText("Choose different colors.");
					actionLabel.setBounds(385, 250, 400, 75);
					actionLabel.setForeground(Color.RED);
				}
				//verify valid speed
				else if(spd > 10 || spd < 1){
					sound.playSound(sound.errorClip);
					actionLabel.setText("Invalid speed.");
					actionLabel.setBounds(410, 250, 400, 75);
					actionLabel.setForeground(Color.RED);
				}
				//if all settings are valid, stores in relevant variables, displays selected settings back to the user as confirmation and automatically start game
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
					sp = spd + 2;
					mapNumber = Character.getNumericValue((chosenMap.charAt(3)));
					actionLabel.setForeground(Color.WHITE);
					actionLabel.setBounds(400, 275, 400, 75);
					actionLabel.setText("<html>" + "Map: " + chosenMap + "<br>" +  "Speed: " + spd + "<br>" + "Player 1:" + colorPlayer1 + "<br>" + "Player 2:" + colorPlayer2 + "</html>");
					actionLabel.paintImmediately(actionLabel.getVisibleRect());
					start.doClick();
				}
			}
		});
	
	}

	/**
 	* Adds ActionListener to abort JButton, discards the stats for the current match and changes displayed card to MainPanels
 	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addAbortBtnActionListener(ActionListener listener) {
		   abort.addActionListener(listener);
	}
	
	/**
 	* Adds ActionListener to start JButton, creates a new Game() object with the desired settings.
 	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addStartBtnActionListener(ActionListener listener) {
		   start.addActionListener(listener);
	}
	
	/**
 	* Adds ActionListener to ret JButton, changes displayed card to MainPanel.
 	* @param listener        ActionListener object that contains action to be taken by button.
 	*/
	public void addRetBtnActionListener(ActionListener listener) {
		   ret.addActionListener(listener);
	}
	
	/**
 	* Returns the main component of this JPanel.
 	* @return Main Component of JPanel.
 	*/
	public JComponent getMainComponent() {
	   return mainPanel;
	}
}
