package gameplay;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import gameplay.Keyboard;
import gameplay.Level;
import gameplay.Player;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import accounts.Login;
import accounts.Statistics;
import GUI.GamePanel;
import GUI.MainWindow;
import GUI.Sound;

/**
 * Game class contains the main game loop and displays the game window.
 * @author Dzmitry Murzich
 * @version 1.0
 */
public class Game extends Canvas implements Runnable {

        private static final long serialVersionUID = 1L;
        private final int WIDTH = 900;
        private final int HEIGHT = WIDTH * 9 / 16 ;
        private final int SCALE = 1;
        private final static String TITLE = "Light Racer";
        private final double FPS = 25.0;
        
        private JFrame frame;
        private Thread thread;
        private boolean running = false;
        private boolean firstRun = true;
        private boolean secondRun = false;
        private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);   
        private Level level;
        private Player player1, player2;
        private int player1Color;
        private int player2Color;
        private int speedSetting;
        private int mapNumber;
        
        private static Sound sound = new Sound();
        
        /** Boolean used for keeping track of pausing while in-game.  */
        public boolean resume;
        /** Keyboard object, handles everything related to user input  */
        public Keyboard key;
        /** Score object, contains current score between 2 players	*/
        public Score curScore;
        /** GamePanel object, panel that is presented to the user when he/she is trying to create a new game */
        public GamePanel curPanel;

        /**
         * Game class contains the main game loop and displays the game window.
         * @param gamePanel 		GamePanel object from MainWindow, used to update various panel parameters
         * @param score				Score object from MainWindow, used to keep track of player scores
         * @param player1Color		Integer representing player1 color, set by user in GamePanel 
         * @param player2Color 		Integer representing player2 color, set by user in GamePanel
         * @param speed				Integer representing speed setting, set by user in GamePanel
         * @param mapNumber			Integer representing map setting, set by user in GamePanel
         */
        public Game(GamePanel gamePanel , Score score, int player1Color, int player2Color, int speed, int mapNumber) {
        	
        		this.player1Color = player1Color;
                this.player2Color = player2Color;
                this.speedSetting = speed;
                this.mapNumber = mapNumber;
                this.curScore = score;
                this.curPanel = gamePanel;
        	
        		//set window size
                Dimension screenSize = new Dimension(WIDTH*SCALE, HEIGHT*SCALE);
                setPreferredSize(screenSize);

                //create 2 players
                player1 = new Player(10, 501, speedSetting, "UP", this.player1Color);
                player2 = new Player(890, 5, speedSetting, "DOWN", this.player2Color);

                level = new Level(WIDTH, HEIGHT);
                frame = new JFrame();
                
                key = new Keyboard();
                addKeyListener(key);
                
                frame.setResizable(false);
                frame.setTitle(TITLE);
                frame.add(this);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                start();
        }


        private synchronized void start() {
                running = true;
                level.clear();
                level.setLevel(mapNumber);
                thread = new Thread(this, "Display");
                thread.start();
        }
        
        
        private synchronized void stop() {
        	frame.setVisible(false);
    		frame.dispose();
        	running = false;
            try {
                    thread.join();
            } catch (InterruptedException ie) {
                    ie.printStackTrace();
            }          
        }

        
        	
       	/**
       	* Main game loop, handles logic related to calls to methods responsible for variable updates and rendering.
       	*/
		public void run() {
                int frames = 0;
                int updates = 0;
                double deltaTime = 0.0;
                final double nanoSecondsPerUpdate = 1000000000.0 / FPS;
                long lastTime = System.nanoTime();
                long timer = System.currentTimeMillis();
                requestFocus();
                while (running) {
                		long currentTime = System.nanoTime();
                        deltaTime += (currentTime - lastTime) / nanoSecondsPerUpdate;
                        lastTime = currentTime;

                        // Limit to 25 updates per second
                        while (deltaTime >= 1.0) {
                        		level.update(this, player1, player2);
                        		//update player direction and check for pause
                        		key.update();
                        		if (key.pause) {
                        			try {
                        				resume = false;
                        				sound.ingameClip.stop();
										Pause pause = new Pause(this);
                        				while (!resume) {
                        					thread.sleep(10);
                        				    deltaTime = deltaTime - FPS/100.0;
                        				}
                        				sound.ingameClip.start();
                        			} catch (InterruptedException e) {
                        				e.printStackTrace();
                        			}
                           		}                  
                        		if (key.up && player2.direction != "DOWN") { player2.direction = "UP"; }
                        		else if (key.down && player2.direction != "UP") { player2.direction = "DOWN"; }
                        		else if (key.left && player2.direction != "RIGHT") { player2.direction = "LEFT"; }
                        		else if (key.right && player2.direction != "LEFT") { player2.direction = "RIGHT"; }
                        		if (key.w && player1.direction != "DOWN") { player1.direction = "UP"; }
                        		else if (key.s && player1.direction != "UP") { player1.direction = "DOWN"; }
                        		else if (key.a && player1.direction != "RIGHT") { player1.direction = "LEFT"; }
                        		else if (key.d && player1.direction != "LEFT") { player1.direction = "RIGHT"; }
                                render();
                                frames++;
                                updates++;
                                deltaTime--;
                                if (secondRun) {
                                	long curT = System.currentTimeMillis();
                                	sound.playSound(sound.countdownClip);
                    				sound.ingameClip.setFramePosition(0);
                    				sound.ingameClip.start();
                                	long newT = System.currentTimeMillis();
                                	long deltaT = newT - curT;
                                	deltaTime = deltaTime - FPS*deltaT/1000;
                                	secondRun = false;
                                }
                                if (firstRun) {
                                	firstRun = false;
                                	secondRun = true;
                                }
                        }

                  

                        // Display FPS and UPS once per second
                        if (System.currentTimeMillis() - timer > 1000) {
                                timer += 1000;
                                frame.setTitle(TITLE + " | FPS " + frames + " | UPS " + updates);
                                frames = 0;
                                updates = 0;
                        }
           
                }
        }


        /**
       	* Updates BufferedImage that stores level snapshot and renders it to screen.
        */
        public void render() {
                BufferStrategy strategy = getBufferStrategy();
                if (strategy == null) {
                        //use 2 buffer frames
                        createBufferStrategy(3);
                        return;
                }

                player1.update(level);
                player2.update(level);

                //convert level's int[][] values to BufferedImage
                for (int y = 0; y < HEIGHT; y++) {
                        for (int x = 0; x < WIDTH; x++) {
                                image.setRGB(x, y, level.pixels[x][y]);
                        }
                }
                
                Graphics g = strategy.getDrawGraphics();
                
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                g.dispose();
                
                strategy.show();
        }
        
        /**
         * Handles logic and operations for when end of round conditions are met.
         * @param result 		String containing result of the round.
         */
        public void endRound(String result) {
                String roundResult;
                if (result.equals("Draw")) {
                        roundResult = result;
                }
                else {
                        roundResult = result + " has won this round!";
                }
                sound.ingameClip.stop();
                frame.setTitle(TITLE + " | " + roundResult);
                sound.announceRoundWinner(result);
                //player 1 reached match winning condition - game is over
                if (curScore.getPlayer1Score() == 2) {
                	sound.announceGameWinner(player1.getColor());
                	MainWindow.sound.backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
                	curPanel.title.setText("<html> <h1>" + Login.getInstance().getUserOne().getUsername() +  " has won!</h1> </html>");
                	curPanel.title.setBounds(350, 25, 400, 100);
                	curPanel.set.setVisible(false);
                	curPanel.abort.setVisible(false);
                	curPanel.ret.setVisible(true);
                	//update statistics
                	Login.getInstance().getUserOne().increaseVersusWins();
                	Statistics.getInstance().updateStats(Login.getInstance().getUserOne(), Login.getInstance().getUserTwo());
                	Statistics.getInstance().updateStatsFile();
                }
                //player 2 reached match winning condition - game is over
                else if (curScore.getPlayer2Score() == 2) {
                	sound.announceGameWinner(player2.getColor());
                	MainWindow.sound.backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
                	curPanel.title.setText("<html> <h1>" + Login.getInstance().getUserTwo().getUsername() +  " has won!</h1> </html>");
                	curPanel.title.setBounds(350, 25, 400, 100);
                	curPanel.actionLabel.setText("");
                	curPanel.set.setVisible(false);
                	curPanel.abort.setVisible(false);
                	curPanel.ret.setVisible(true);
                	//update statistics
                	Login.getInstance().getUserTwo().increaseVersusWins();
                	Statistics.getInstance().updateStats(Login.getInstance().getUserOne(), Login.getInstance().getUserTwo());
                	Statistics.getInstance().updateStatsFile();
                }
                //none of the players won 2 rounds yet
                else {
                	curPanel.title.setText("<html> <h1>" + Login.getInstance().getUserOne().getUsername() + " " +
                							curScore.getPlayer1Score() + " - " +  curScore.getPlayer2Score() + " " + 
                							Login.getInstance().getUserTwo().getUsername() + "</h1> </html>");
                	curPanel.title.paintImmediately(curPanel.title.getVisibleRect());
                }
                MainWindow.sound.backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
                stop();
        }

}
