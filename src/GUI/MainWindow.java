package GUI;

import gameplay.Score;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import accounts.Login;
import accounts.User;

public class MainWindow {
	public static final int WIDTH = 900;
	public static final int HEIGHT = WIDTH * 9 / 16 ;
	
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
	private String successSound = "src/res/sfx/success.wav";
	private String errorSound = "src/res/sfx/error.wav";
	private String backSound = "src/res/sfx/back.wav";
	private String gameSound = "src/res/sfx/startgame.wav";
	private String backgroundSound = "src/res/sfx/background.wav";
	private AudioInputStream audioInputStream;
	private Clip successClip;
	private Clip errorClip;
	private Clip backClip;
	private Clip gameClip;
	public static Clip backgroundClip;
	
	private IntroPanel introPanel = new IntroPanel();
	private CrAccPanel crAccPanel = new CrAccPanel();
	private LoginPanelOne loginPanel1 = new LoginPanelOne();
	private LoginPanelTwo loginPanel2 = new LoginPanelTwo();
	private MainPanel mainPanel = new MainPanel();
	public GamePanel gamePanel = new GamePanel();
	private StatsPanel statsPanel = new StatsPanel();
	private HelpPanel helpPanel = new HelpPanel();
	private LogoutPanel logoutPanel = new LogoutPanel();
	
	public static Score score = new Score();
	
	public MainWindow() {
		
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(successSound).getAbsoluteFile());
			successClip = AudioSystem.getClip();
			successClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(errorSound).getAbsoluteFile());
			errorClip = AudioSystem.getClip();
			errorClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(backSound).getAbsoluteFile());
			backClip = AudioSystem.getClip();
			backClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(gameSound).getAbsoluteFile());
			gameClip = AudioSystem.getClip();
			gameClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(backgroundSound).getAbsoluteFile());
			backgroundClip = AudioSystem.getClip();
			backgroundClip.open(audioInputStream);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		mainWindow.add(introPanel.getMainComponent(), INTRO);
		mainWindow.add(crAccPanel.getMainComponent(), CRACC);
		mainWindow.add(loginPanel1.getMainComponent(), LOGIN);
		mainWindow.add(loginPanel2.getMainComponent(), LOGIN2);
		mainWindow.add(mainPanel.getMainComponent(), MAIN);
		mainWindow.add(gamePanel.getMainComponent(), GAME);
		mainWindow.add(statsPanel.getMainComponent(), STATS);
		mainWindow.add(helpPanel.getMainComponent(), HELP);
		mainWindow.add(logoutPanel.getMainComponent(), LOGOUT);
		
		introPanel.addLoginBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(successClip);
				if(loginObject.userOneLogin()){
					cardlayout.show(mainWindow, LOGIN2);
				}
				else{
					cardlayout.show(mainWindow, LOGIN);
				}
			}
		});
		
		introPanel.addCreateAccBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(successClip);
				cardlayout.show(mainWindow, CRACC);
			}
		});
				
		crAccPanel.addBackBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(backClip);
				cardlayout.show(mainWindow, INTRO);
			}
		});
		
		loginPanel1.addContinueBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(successClip);
				LoginPanelOne.username1.setText("");
				LoginPanelOne.password1.setText("");
				LoginPanelOne.actionLabel.setText("");
				LoginPanelOne.cont.setVisible(false);
				if(loginObject.userTwoLogin()){
					cardlayout.show(mainWindow, MAIN);
				}
				else{
					cardlayout.show(mainWindow, LOGIN2);
				}
			}
		});
		
		loginPanel1.addBackBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(backClip);
				cardlayout.show(mainWindow, INTRO);
			}
		});
		
		loginPanel2.addContinueBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(successClip);
				LoginPanelTwo.username2.setText("");
				LoginPanelTwo.password2.setText("");
				LoginPanelTwo.actionLabel.setText("");
				LoginPanelTwo.cont.setVisible(false);
				cardlayout.show(mainWindow, MAIN);
			}
		});
		
		loginPanel2.addBackBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(backClip);
				if(loginObject.userOneLogin()){
					cardlayout.show(mainWindow, INTRO);
				}
				else{
					cardlayout.show(mainWindow, LOGIN);
				}
				
			}
		});

		mainPanel.addNewGameBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(successClip);
				cardlayout.show(mainWindow, GAME);
				gamePanel.set.setVisible(true);
				gamePanel.abort.setVisible(true);
				statsPanel.updateStatsPanel();
			}
		});
		
		mainPanel.addStatsBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(successClip);
				cardlayout.show(mainWindow, STATS);
				statsPanel.updateStatsPanel();
			}
		});
		
		mainPanel.addHelpBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(successClip);
				cardlayout.show(mainWindow, HELP);
			}
		});
				
		mainPanel.addLogoutBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(successClip);
				cardlayout.show(mainWindow, LOGOUT);
			}
		});
		
		logoutPanel.addPlayerOneActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				playSound(successClip);
				User newUser = null;
				loginObject.setUserOne(newUser);
				LoginPanelOne.username1.setEditable(true);
				LoginPanelOne.password1.setEditable(true);
				cardlayout.show(mainWindow, LOGIN);
			}
		});
		
		logoutPanel.addPlayerTwoActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(successClip);
				User newUser = null;
				loginObject.setUserTwo(newUser);
				LoginPanelTwo.username2.setEditable(true);
				LoginPanelTwo.password2.setEditable(true);
				cardlayout.show(mainWindow, LOGIN2);
			}
		});
		
		logoutPanel.addCancelActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(backClip);
				cardlayout.show(mainWindow, MAIN);
			}
		});
		
		
		logoutPanel.addBothActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(successClip);
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
			@Override
			public void actionPerformed(ActionEvent e) {
				backgroundClip.stop();
				playSound(gameClip);
				gameplay.Game startGame = new gameplay.Game(gamePanel,score, gamePanel.p1color, gamePanel.p2color, gamePanel.sp, gamePanel.mapNumber);
			}
		});

		gamePanel.addAbortBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(backClip);
				gamePanel.speed.setText("");
				gamePanel.start.setEnabled(false);
				gamePanel.actionLabel.setText("");
				gamePanel.ret.setVisible(false);
				gamePanel.title.setText("<html> <h1>Current score: 0 - 0</h1> </html>S");
				score.p1 = 0;
				score.p2 = 0;
				cardlayout.show(mainWindow, MAIN);
			}
		});
		
		gamePanel.addRetBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(backClip);
				gamePanel.speed.setText("");
				gamePanel.start.setEnabled(false);
				gamePanel.actionLabel.setText("");
				gamePanel.ret.setVisible(false);
				gamePanel.title.setText("<html> <h1>Current score: 0 - 0</h1> </html>");
				score.p1 = 0;
				score.p2 = 0;
				cardlayout.show(mainWindow, MAIN);
			}
		});
		
		statsPanel.addBackBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(backClip);
				cardlayout.show(mainWindow, MAIN);
			}
		});
		
		helpPanel.addBackBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(backClip);
				cardlayout.show(mainWindow, MAIN);
			}
		});
	}
	

	private JComponent getMainComponent() {
		return mainWindow;
	}

	private static void createAndShowUI() {
		JFrame frame = new JFrame("Light Racer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MainWindow().getMainComponent());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		backgroundClip.loop(0);
	}
	
	private void playSound(Clip clip) {
		clip.setFramePosition(0);
		clip.start();
		while (clip.getFramePosition() != clip.getFrameLength()) {
			//wait until clip has been played
		}
		clip.stop();
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				createAndShowUI();
			}
		});
	}
}
