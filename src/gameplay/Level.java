package gameplay;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameplay.*;
import gameplay.Player;


public class Level {


	public int width, height;
	public int[][] pixels; 
	public boolean[][] collisions;
	public String path = "/map.png";

	public Level (int w, int h) {
		this.width = w;
		this.height = h;
		pixels = new int[width][height];
		collisions = new boolean[width][height];
	}


	/* Default map layout :
	 * Fill the pixels matrix with solid color #131717
	 * Fill the border pixels with a different color #191F0C */
	public void defaultMapLayout() {
		for(int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++){
				//background 
				pixels[x][y] = 0x131717;
				//background does not cause collisions
				collisions[x][y] = false;
				//border
				if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
					pixels[x][y] = 0xFFFFFF;
					//border causes collisions
					collisions[x][y] = true;
				}
			}
		}
	}
	
	public void loadLevel() {
		try {
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			System.out.println(image.getRGB(1, 1));
			for(int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++){
					pixels[x][y] = image.getRGB(x, y);
					if (image.getRGB(x, y) != -16777216) {
						collisions[x][y] = true;
					}
					else {
						collisions[x][y] = false;
					}
					if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
						collisions[x][y] = true;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Clear the map by filling it with black color */
	public void clear() {
		for(int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++){
				pixels[x][y] = 0;
			}
		}
	}
	
	/* Update player positions and check for collisions */
	public void update(Game game, Player player1, Player player2) {
		for (int i = 1; i <= player1.velocity; i++) {
			if (player1.direction == "UP") {
				if (collisions[player1.xPos][player1.yPos - 1]) {
					game.collision(player2); 
				}
				else { 
					player1.yPos--; 
					player1.update(this); 
				}
			}
			else if (player1.direction == "DOWN") {
				if (collisions[player1.xPos][player1.yPos + 1]) { 
					game.collision(player2);
				}
				else { 
					player1.yPos++; 
					player1.update(this);  
				}
			}
			else if (player1.direction == "LEFT") {
				if (collisions[player1.xPos - 1][player1.yPos]) {
					game.collision(player2);
				}
				else { 
					player1.xPos--; 
					player1.update(this); 
				}
			}
			else if (player1.direction == "RIGHT") {
				if (collisions[player1.xPos + 1][player1.yPos]) {
					game.collision(player2);
				}
				else { 
					player1.xPos++; 
					player1.update(this);  
				}
			}
		}
		for (int i = 1; i <= player2.velocity; i++) {
			if (player2.direction == "UP") {
				if (collisions[player2.xPos][player2.yPos - 1]) { 
					game.collision(player1); 
				}
				else { 
					player2.yPos--; 
					player2.update(this);  
				}
			}
			else if (player2.direction == "DOWN") {
				if (collisions[player2.xPos][player2.yPos + 1]) { 
					game.collision(player1); 
				}
				else { 
					player2.yPos++; 
					player2.update(this); 
				}
			}
			else if (player2.direction == "LEFT") {
				if (collisions[player2.xPos - 1][player2.yPos]) { 
					game.collision(player1); 
				}
				else { 
					player2.xPos--; 
					player2.update(this); 
				}
			}
			else if (player2.direction == "RIGHT") {
				if (collisions[player2.xPos + 1][player2.yPos]) { 
					game.collision(player1); 
				}
				else { 
					player2.xPos++; 
					player2.update(this);  
				}
			}
		}
	}


}