package GUI;

import gameplay.Game;
import gameplay.Score;

import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.Clip;
import javax.swing.*;

import accounts.Login;
import accounts.User;

/**
 * MainWindow class contains the main frame which is used to display all the menu screens. 
 * @author Alonso Medina
 * @version 1.0
 */
public class MainWindow {
		
	private Login loginObject = Login.getInstance(); 
	
	private static final String INTRO = "intro";
	private static final String CRACC = "cracc";
	private static final String LOGIN = "login1";
	private static final String LOGIN2 = "login2";
	private static final String MAIN = "main";
	private static final String LOGOUT = "logout";
	private static final String GAME = "game";
	private static final String STATS = "stats";
	private static final String HELP = "help";
	
	private CardLayout cardlayout = new CardLayout();
	private JPanel mainWindow = new JPanel(cardlayout);
	
	//initialize menu panels
	private IntroPanel introPanel = new IntroPanel();
	private CreateAccountPanel createAccountPanel = new CreateAccountPanel();
	private LoginPanelOne loginPanel1 = new LoginPanelOne();
	private LoginPanelTwo loginPanel2 = new LoginPanelTwo();
	private MainPanel mainPanel = new MainPanel();
	private GamePanel gamePanel = new GamePanel();
	private StatsPanel statsPanel = new StatsPanel();
	private HelpPanel helpPanel = new HelpPanel();
	private LogoutPanel logoutPanel = new LogoutPanel();
	
	/** Sound object used to play background music and button sounds. */
	public static Sound sound = new Sound();
	private Score score = new Score();
	
    /**
     *  MainWindow class contains the main JFrame which will display each panel and change between menus. 
     */
	public MainWindow() {
		
		//add panels to frame
		mainWindow.add(introPanel.getMainComponent(), INTRO);
		mainWindow.add(createAccountPanel.getMainComponent(), CRACC);
		mainWindow.add(loginPanel1.getMainComponent(), LOGIN);
		mainWindow.add(loginPanel2.getMainComponent(), LOGIN2);
		mainWindow.add(mainPanel.getMainComponent(), MAIN);
		mainWindow.add(gamePanel.getMainComponent(), GAME);
		mainWindow.add(statsPanel.getMainComponent(), STATS);
		mainWindow.add(helpPanel.getMainComponent(), HELP);
		mainWindow.add(logoutPanel.getMainComponent(), LOGOUT);
		
		introPanel.addLoginBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.successClip);
				if(loginObject.userOneLogin()){
					cardlayout.show(mainWindow, LOGIN2);
				}
				else{
					cardlayout.show(mainWindow, LOGIN);
				}
			}
		});
		
		introPanel.addCreateAccBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.successClip);
				cardlayout.show(mainWindow, CRACC);
			}
		});
				
		createAccountPanel.addBackBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.backClip);
				cardlayout.show(mainWindow, INTRO);
			}
		});
		
		loginPanel1.addContinueBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPanelOne.username1.setText("");
				LoginPanelOne.password1.setText("");
				LoginPanelOne.actionLabel.setText("");
				LoginPanelOne.actionLabel.paintImmediately(LoginPanelOne.actionLabel.getVisibleRect());
				if(loginObject.userTwoLogin()){
					cardlayout.show(mainWindow, MAIN);
				}
				else{
					cardlayout.show(mainWindow, LOGIN2);
				}
			}
		});
		
		loginPanel1.addBackBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.backClip);
				cardlayout.show(mainWindow, INTRO);
			}
		});
		
		loginPanel2.addContinueBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPanelTwo.username2.setText("");
				LoginPanelTwo.password2.setText("");
				LoginPanelTwo.actionLabel.setText("");
				LoginPanelTwo.actionLabel.paintImmediately(LoginPanelTwo.actionLabel.getVisibleRect());
				cardlayout.show(mainWindow, MAIN);
			}
		});
		
		loginPanel2.addBackBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.backClip);
				if(loginObject.userOneLogin()){
					cardlayout.show(mainWindow, INTRO);
				}
				else{
					cardlayout.show(mainWindow, LOGIN);
				}
				
			}
		});

		mainPanel.addNewGameBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.successClip);
				cardlayout.show(mainWindow, GAME);
				gamePanel.title.setText("<html> <h1>"+ loginObject.getUserOne().getUsername() + " 0 - 0 " +loginObject.getUserTwo().getUsername() +"</h1> </html>");
				gamePanel.set.setVisible(true);
				gamePanel.abort.setVisible(true);
				statsPanel.updateStatsPanel();
			}
		});
		
		mainPanel.addStatsBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.successClip);
				cardlayout.show(mainWindow, STATS);
				statsPanel.updateStatsPanel();
			}
		});
		
		mainPanel.addHelpBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.successClip);
				cardlayout.show(mainWindow, HELP);
			}
		});
				
		mainPanel.addLogoutBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.successClip);
				LogoutPanel.playerOne.setText(loginObject.getUserNameOne());
				LogoutPanel.playerTwo.setText(loginObject.getUserNameTwo());
				cardlayout.show(mainWindow, LOGOUT);
			}
		});
		
		logoutPanel.addPlayerOneActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.successClip);
				User newUser = null;
				loginObject.setUserOne(newUser);
				LoginPanelOne.username1.setEditable(true);
				LoginPanelOne.password1.setEditable(true);
				cardlayout.show(mainWindow, LOGIN);
			}
		});
		
		logoutPanel.addPlayerTwoActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.successClip);
				User newUser = null;
				loginObject.setUserTwo(newUser);
				LoginPanelTwo.username2.setEditable(true);
				LoginPanelTwo.password2.setEditable(true);
				cardlayout.show(mainWindow, LOGIN2);
			}
		});
		
		logoutPanel.addCancelActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.backClip);
				cardlayout.show(mainWindow, MAIN);
			}
		});
		
		
		logoutPanel.addBothActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.successClip);
				User newUser = null;
				loginObject.setUserOne(newUser);
				loginObject.setUserTwo(newUser);
				LoginPanelOne.username1.setEditable(true);
				LoginPanelOne.password1.setEditable(true);
				LoginPanelTwo.username2.setEditable(true);
				LoginPanelTwo.password2.setEditable(true);
				cardlayout.show(mainWindow, INTRO);
			}
		});
		
		gamePanel.addStartBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.backgroundClip.stop();
				sound.playSound(sound.gameClip);
				Game startGame = new Game(gamePanel,score, gamePanel.p1color, gamePanel.p2color, gamePanel.sp, gamePanel.mapNumber);
			}
		});

		gamePanel.addAbortBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.backClip);
				gamePanel.speed.setText("");
				gamePanel.actionLabel.setText("");
				gamePanel.ret.setVisible(false);
				gamePanel.title.setText("<html> <h1></h1> </html>");
				score.setPlayer1Score(0);
				score.setPlayer2Score(0);
				cardlayout.show(mainWindow, MAIN);
			}
		});
		
		gamePanel.addRetBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.backClip);
				gamePanel.speed.setText("");
				gamePanel.actionLabel.setText("");
				gamePanel.ret.setVisible(false);
				gamePanel.title.setText("<html> <h1></h1> </html>");
				score.setPlayer1Score(0);
				score.setPlayer2Score(0);
				cardlayout.show(mainWindow, MAIN);
			}
		});
		
		statsPanel.addBackBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.backClip);
				cardlayout.show(mainWindow, MAIN);
			}
		});
		
		helpPanel.addBackBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.backClip);
				cardlayout.show(mainWindow, MAIN);
			}
		});
	}
	
	/**
 	* Returns the main component of this JPanel.
 	* @return Main Component of JPanel.
 	*/
	private JComponent getMainComponent() {
		return mainWindow;
	}

	/**
 	* Initializes the GUI, creates and displays the parent frame and starts background music playback.
 	*/
	private static void createAndShowUI() {
		JFrame frame = new JFrame("Light Racer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MainWindow().getMainComponent());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		sound.backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
 	* Starts the program, calls createAndShowGUI().
 	*/
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				createAndShowUI();
			}
		});
	}
}
