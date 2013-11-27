package gameplay;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

/**
 * Level class, loads maps from a specified folder and stores the selected map in an int 2d array. 
 * @author Dzmitry Murzich
 * @version 1.0
 *  */
public class Level {


	private int width, height;
	private BufferedImage[] maps;
	private String mapsPath = "./res/image";
	
	/**	2D array of integers, represents pixels of level */
	public int[][] pixels;
	
	/**
	 * Level class, loads maps from a specified folder and stores the selected map in an int 2d array. 
	 * @param w				width of the level
	 * @param h				height of the level
	 */
	public Level (int w, int h) {
		this.width = w;
		this.height = h;
		pixels = new int[width][height];
		File[] mapFiles;
		File dir;
		try {
			dir = new File(mapsPath);
			mapFiles = dir.listFiles();
			int mapsFound = mapFiles.length;
			//preload all maps
			maps = new BufferedImage[mapsFound];
			for (int i = 0; i < mapsFound; i++) {
				maps[i] = ImageIO.read(mapFiles[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	// Default map layout
	private void defaultMapLayout() {
		for(int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++){
				//background 
				pixels[x][y] = 0x131717;
				//border
				if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
					pixels[x][y] = 0xFFFFFF;
				}
			}
		}
	}
	
	/**
	 * Set level according to index that was passed to the method.
	 * @param mapIndex		Integer representing map number to load
	 *  */
	public void setLevel(int mapIndex) {
		BufferedImage image = maps[mapIndex];
		for(int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++){
				pixels[x][y] = image.getRGB(x, y);
				if (x < 3 || x > width - 4 || y < 3 || y > height - 4) {
					pixels[x][y] = 0xFFFFFF;
				}
			}
		}
		
	}

	/**
	 * Set all pixels to 0 - blank black map.
	 */
	public void clear() {
		for(int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++){
				pixels[x][y] = 0;
			}
		}
	}
	
	/**
	 * Handles player position updates and collision checking.
	 * @param game				Game object, required if collision was detected to handle end of round
	 * @param player1			Player 1 object, required to update player positions
	 * @param player2			Player 2 object, required to update player positions
	 */
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
			else if (pixels[player1.getNextPos()[0]][player1.getNextPos()[1]] != -16777216
						|| isOutOfBounds(player1)) {
				game.curScore.player2Won();	
				game.endRound(player2.getColor());
			}
			//Next position for player2 is a collision => player1 wins
			else if (pixels[player2.getNextPos()[0]][player2.getNextPos()[1]] != -16777216
						|| isOutOfBounds(player2)) {
				game.curScore.player1Won();
				game.endRound(player1.getColor());
			}
			else {
				player1.update(this);
				player2.update(this);
			}
		}
	}
	
	/**
	 * Checks if the next player position is out of map bounds.
	 * @param player	Player object that is checked for being out of bounds.
	 * @return boolean representing whether the player is out of bounds or not.
	 */
	public boolean isOutOfBounds(Player player) {
		boolean outOfBounds = false;
		if (player.getNextPos()[0] < 2 || player.getNextPos()[0] > this.width - 2) {
			outOfBounds = true;
		}
		else if(player.getNextPos()[1] < 2 || player.getNextPos()[1] > this.height - 2) {
			outOfBounds = true;
		}
		return outOfBounds;
	}
	


}
