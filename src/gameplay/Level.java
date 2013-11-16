package gameplay;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import gameplay.*;


public class Level {


	public int width, height;
	public int[][] pixels; 
	//public boolean[][] collisions;
	public BufferedImage[] maps;

	public Level (int w, int h) {
		this.width = w;
		this.height = h;
		pixels = new int[width][height];
		//collisions = new boolean[width][height];
		File[] mapFiles;
		File dir;
		try {
			dir = new File("./src/res");
			mapFiles = dir.listFiles();
			int mapsFound = mapFiles.length;
			System.out.println("Found " + mapsFound + " maps in " + dir.toString() + " directory");
			//preload all maps
			maps = new BufferedImage[mapsFound];
			for (int i = 0; i < mapsFound; i++) {
				maps[i] = ImageIO.read(mapFiles[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
				//collisions[x][y] = false;
				//border
				if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
					pixels[x][y] = 0xFFFFFF;
					//border causes collisions
					//collisions[x][y] = true;
				}
			}
		}
	}
	
	public void setLevel(int mapIndex) {
		BufferedImage image = maps[mapIndex];
		System.out.println(image.getRGB(1, 1));	//Get background color for debugging
		for(int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++){
				pixels[x][y] = image.getRGB(x, y);
				if (image.getRGB(x, y) != -16777216) {
					//collisions[x][y] = true;
				}
				else {
					//collisions[x][y] = false;
				}
				if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
					//collisions[x][y] = true;
				}
			}
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
			//Next position for both players is same => draw
			if (Arrays.equals(player1.getNextPos(), player2.getNextPos())) {
				game.endRound("Draw");
			}
			//Next position for both players is a collision => draw
			else if (pixels[player1.getNextPos()[0]][player1.getNextPos()[1]] != -16777216 
						&& pixels[player2.getNextPos()[0]][player2.getNextPos()[1]] != -16777216) {
				game.endRound("Draw");
			}
			//Next position for player1 is a collision => player2 wins
			else if (pixels[player1.getNextPos()[0]][player1.getNextPos()[1]] != -16777216) {
				game.endRound(player2.getColor());
				game.score[1]++;
			}
			//Next position for player2 is a collision => player1 wins
			else if (pixels[player2.getNextPos()[0]][player2.getNextPos()[1]] != -16777216) {
				game.endRound(player1.getColor());
				game.score[0]++;
			}
			else {
				player1.update(this);
				player2.update(this);
			}
		}
		/*	
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
		}*/
		

	}
	


}