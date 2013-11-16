package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow {
	public static final int WIDTH = 900;
	public static final int HEIGHT = WIDTH * 9 / 16 ;
	
	private static final String INTRO = "intro";
	private static final String CRACC = "cracc";
	private static final String LOGIN = "login1";
	private static final String LOGIN2 = "login2";
	private static final String MAIN = "main";
	private static final String GAME = "game";
	private static final String STATS = "stats";
	private static final String HELP = "help";
	private static final String SETT = "setting";
	
	private CardLayout cardlayout = new CardLayout();
	private JPanel mainWindow = new JPanel(cardlayout);
	
	private IntroPanel introPanel = new IntroPanel();
	private CrAccPanel crAccPanel = new CrAccPanel();
	private LoginPanelOne loginPanel1 = new LoginPanelOne();
	private LoginPanelTwo loginPanel2 = new LoginPanelTwo();
	private MainPanel mainPanel = new MainPanel();
	private GamePanel gamePanel = new GamePanel();
	private StatsPanel statsPanel = new StatsPanel();
	private HelpPanel helpPanel = new HelpPanel();
	private SettingsPanel settPanel = new SettingsPanel();

	public MainWindow() {
		
		mainWindow.add(introPanel.getMainComponent(), INTRO);
		mainWindow.add(crAccPanel.getMainComponent(), CRACC);
		mainWindow.add(loginPanel1.getMainComponent(), LOGIN);
		mainWindow.add(loginPanel2.getMainComponent(), LOGIN2);
		mainWindow.add(mainPanel.getMainComponent(), MAIN);
		mainWindow.add(gamePanel.getMainComponent(), GAME);
		mainWindow.add(statsPanel.getMainComponent(), STATS);
		mainWindow.add(helpPanel.getMainComponent(), HELP);
		mainWindow.add(settPanel.getMainComponent(), SETT);
		
		introPanel.addLoginBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, LOGIN);
			}
		});
		
		introPanel.addCreateAccBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, CRACC);
			}
		});
		
		crAccPanel.addCrtAccBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, INTRO);
			}
		});
		
		crAccPanel.addBackBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, INTRO);
			}
		});
		
		loginPanel1.addLoginBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, LOGIN2);
			}
		});
		
		loginPanel1.addBackBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, INTRO);
			}
		});
		
		loginPanel2.addBackBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, LOGIN);
			}
		});
		
		loginPanel2.addLoginBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, MAIN);
			}
		});

		mainPanel.addNewGameBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, GAME);
			}
		});
		
		mainPanel.addStatsBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, STATS);
			}
		});
		
		mainPanel.addHelpBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, HELP);
			}
		});
		
		mainPanel.addSettBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, SETT);
			}
		});
		
		mainPanel.addLogoutBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, INTRO);
			}
		});

		gamePanel.addAbortBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, MAIN);
			}
		});
		
		statsPanel.addBackBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, MAIN);
			}
		});
		
		helpPanel.addBackBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, MAIN);
			}
		});
		
		settPanel.addBackBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainWindow, MAIN);
			}
		});
	}
	

	private JComponent getMainComponent() {
		return mainWindow;
	}

	private static void createAndShowUI() {
		JFrame frame = new JFrame("MainWindow");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MainWindow().getMainComponent());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				createAndShowUI();
			}
		});
	}
}