package game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import game.controllers.Keyboard;
import game.levels.Level;
import game.player.Player;

import javax.swing.JFrame;

//TODO optimize for more FPS, preferably through SCALE

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public final int WIDTH = 900;
	public final int HEIGHT = WIDTH * 9 / 16 ;  
	public final int SCALE = 1;

	private final static String TITLE = "Light Racer Prototype";

	private JFrame frame;
	private Thread thread;
	private Keyboard key;

	public boolean running = false;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);	

	public Level level;
	public Player player1, player2;

	public Game() {
		//set window size
		Dimension screenSize = new Dimension(WIDTH*SCALE, HEIGHT*SCALE);
		setPreferredSize(screenSize);

		//create 2 players
		player1 = new Player(100, 450, 5, "UP", Player.YELLOW);
		player2 = new Player(800, 56, 5, "DOWN", Player.GREEN);

		level = new Level(WIDTH, HEIGHT);
		frame = new JFrame();
		
		key = new Keyboard();
		addKeyListener(key);

	}


	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
		level.clear();
		level.loadLevel();
	}
	
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	//Main game loop
	public void run() {
		int frames = 0;
		int updates = 0;
		double deltaTime = 0.0;
		final double nanoSecondsPerUpdate = 1000000000.0 / 20.0;
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		requestFocus();
		while (running) {
			long currentTime = System.nanoTime();
			deltaTime += (currentTime - lastTime) / nanoSecondsPerUpdate;
			lastTime = currentTime;

			// Limit to 20 updates per second
			while (deltaTime >= 1.0) {
				update();
				render();
				frames++;
				updates++;
				deltaTime--;
			}

			// Don't put limit on rendering yet
			render();
			frames++;

			// Display FPS and UPS once per second
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(TITLE + " | FPS " + frames + " | UPS " + updates); 
				frames = 0;
				updates = 0;
			}
		}
	}

	//Called a set number of times per second
	public void update() {
		//update player position
		level.update(this, player1, player2);
		//update player direction
		key.update();
		if (key.up && player2.direction != "DOWN") { player2.direction = "UP"; }
		if (key.down && player2.direction != "UP") { player2.direction = "DOWN"; }
		if (key.left && player2.direction != "RIGHT") { player2.direction = "LEFT"; }
		if (key.right && player2.direction != "LEFT") { player2.direction = "RIGHT"; }
		if (key.w && player1.direction != "DOWN") { player1.direction = "UP"; }
		if (key.s && player1.direction != "UP") { player1.direction = "DOWN"; }
		if (key.a && player1.direction != "RIGHT") { player1.direction = "LEFT"; }
		if (key.d && player1.direction != "LEFT") { player1.direction = "RIGHT"; }
	}

	//Called unlimited times a second
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
	
	//TODO Game Over screen displaying who won
	//TODO Collision explosion effect
	//Called when collision is detected
	public void collision(Player winningPlayer) {
		String result;
		if (winningPlayer.color == Player.RED) {
			result = "Red Player won!";
		}
		else if (winningPlayer.color == Player.BLUE) {
			result = "Blue Player won!";
		}
		else if (winningPlayer.color == Player.YELLOW) {
			result = "Yellow Player won!";
		}
		else {
			result = "Green Player won!";
		}
		frame.setTitle(TITLE + " | " + result);
		System.out.println(result);
		stop();
	}


	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(TITLE);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}


}