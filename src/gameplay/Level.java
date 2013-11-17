package gameplay;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;


public class Level {


	public int width, height;
	public int[][] pixels; 
	public BufferedImage[] maps;

	public Level (int w, int h) {
		this.width = w;
		this.height = h;
		pixels = new int[width][height];
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
				//border
				if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
					pixels[x][y] = 0xFFFFFF;
				}
			}
		}
	}
	
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
			else if (pixels[player1.getNextPos()[0]][player1.getNextPos()[1]] != -16777216
						|| isOutOfBounds(player1)) {
				game.curScore.p2Won();	
				game.endRound(player2.getColor());
			}
			//Next position for player2 is a collision => player1 wins
			else if (pixels[player2.getNextPos()[0]][player2.getNextPos()[1]] != -16777216
						|| isOutOfBounds(player2)) {
				game.curScore.p1Won();
				game.endRound(player1.getColor());
			}
			else {
				player1.update(this);
				player2.update(this);
			}
		}
	}
	
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