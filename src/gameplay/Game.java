package gameplay;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import gameplay.Keyboard;
import gameplay.Level;
import gameplay.Player;

import javax.swing.JFrame;
import javax.swing.JPanel;

import accounts.Login;
import accounts.Statistics;
import GUI.GamePanel;
import GUI.MainWindow;


//TODO Game Over screens displaying who won round/game and handle new round

public class Game extends Canvas implements Runnable {

        private static final long serialVersionUID = 1L;
        private final int WIDTH = 900;
        private final int HEIGHT = WIDTH * 9 / 16 ;
        private final int SCALE = 1;
        private final static String TITLE = "Light Racer";
        
        private JFrame frame;
        private Thread thread;
        private boolean running = false;
        private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);   
        private Level level;
        private Player player1, player2;
        private int player1Color;
        private int player2Color;
        private int speedSetting;
        private int mapNumber;
        
        public boolean resume;
        public Keyboard key;
        public Score curScore;
        public GamePanel curPanel;

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
                player1 = new Player(100, 450, speedSetting, "UP", this.player1Color);
                player2 = new Player(800, 56, speedSetting, "DOWN", this.player2Color);

                level = new Level(WIDTH, HEIGHT);
                frame = new JFrame();
                
                key = new Keyboard();
                addKeyListener(key);
                
                frame.setResizable(false);
                frame.setTitle(TITLE);
                frame.add(this);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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



        //Main game loop
        public void run() {
                int frames = 0;
                int updates = 0;
                double deltaTime = 0.0;
                final double nanoSecondsPerUpdate = 1000000000.0 / 25.0;
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
                        				Pause pause = new Pause(this);
                        				while (!resume) {
                        					thread.sleep(10);
                        				    deltaTime = deltaTime - 0.25;
                        				}
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


        //Called 25 times a second
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
        
        //Called when collision is detected
        public void endRound(String result) {
                String roundResult;
                if (result.equals("Draw")) {
                        roundResult = result;
                }
                else {
                        roundResult = result + " has won this round!";
                }
                frame.setTitle(TITLE + " | " + roundResult);
                
                if (MainWindow.score.getP1() == 2) {
                	curPanel.title.setText("Player 1 has won!");
                	curPanel.set.setVisible(false);
                	curPanel.abort.setVisible(false);
                	curPanel.ret.setVisible(true);
                	curPanel.start.setVisible(false);
                	
                	Login.getInstance().getUserOne().increaseVersusWins();
                	Statistics.getInstance().updateStats(Login.getInstance().getUserOne(), Login.getInstance().getUserTwo());
                	Statistics.getInstance().updateStatsFile();
                }
                
                else if (MainWindow.score.getP2() == 2) {
                	curPanel.title.setText("Player 2 has won!");
                	curPanel.set.setVisible(false);
                	curPanel.abort.setVisible(false);
                	curPanel.ret.setVisible(true);
                	curPanel.start.setVisible(false);
                	
                	Login.getInstance().getUserTwo().increaseVersusWins();
                	Statistics.getInstance().updateStats(Login.getInstance().getUserOne(), Login.getInstance().getUserTwo());
                	Statistics.getInstance().updateStatsFile();
                }
                
                else {
                	curPanel.title.setText("Current Score: " + MainWindow.score.getP1() + " - " +  MainWindow.score.getP2());
                	curPanel.title.setVisible(false);
                	curPanel.title.setVisible(true);
                	curPanel.mainPanel.remove(curPanel.title);
                	curPanel.mainPanel.add(curPanel.title);
                }
                stop();
        }
}
