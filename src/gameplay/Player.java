package gameplay;

import gameplay.Tuple;
import gameplay.Level;

/**
 * Player class contains parameters related to players - position, color, direction, speed - and helper methods related to player logic 
 * @author Dzmitry Murzich
 * @version 1.0
 *  */
public class Player {

	private int xPos, yPos;
	private int color;
	private Tuple tColor;
	
	public String direction = "UP";
	public int velocity = 1;

	public final static int RED = 0xFF0707;
	public final static int RED_GLOW = 0xFF7777;
	public final static int BLUE = 0x2164FF;
	public final static int BLUE_GLOW = 0x85AAFF;
	public final static int YELLOW = 0xFFD407;
	public final static int YELLOW_GLOW = 0xFFE087;
	public final static int GREEN = 0x07FF26;
	public final static int GREEN_GLOW = 0x77FF87;

	public final static Tuple REDPLAYERCOLORS = new Tuple(RED, RED_GLOW);
	public final static Tuple BLUEPLAYERCOLORS = new Tuple(BLUE, BLUE_GLOW);
	public final static Tuple YELLOWPLAYERCOLORS = new Tuple(YELLOW, YELLOW_GLOW);
	public final static Tuple GREENPLAYERCOLORS = new Tuple(GREEN, GREEN_GLOW);


	/**
	 * Player constructor
	 * Takes no arguments as input, default parameter values
	 */
	public Player() {
		this.xPos = 400;
		this.yPos = 300;
		this.color = RED;
		this.tColor = REDPLAYERCOLORS;
	}

	/**
	 * Player object constructor
	 * @param xPos					integer representing initial horizontal position of a player 
	 * @param yPos					integer representing initial vertical position of a player 
	 * @param velocity				integer representing initial player speed
	 * @param direction				String representing initial player direction
	 * @param color					integer representing player color
	 */
	public Player(int xPos, int yPos, int velocity, String direction, int color) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.velocity = velocity;
		this.direction = direction;
		this.color = color;
		if (color == RED) { 
			this.tColor = REDPLAYERCOLORS; 
		} 
		else if (color == BLUE) { 
			this.tColor = BLUEPLAYERCOLORS; 
		} 
		else if (color == YELLOW) { 
			this.tColor = YELLOWPLAYERCOLORS;
		} 
		else if (color == GREEN) { 
			this.tColor = GREENPLAYERCOLORS;
		}
		else { 
			this.tColor = REDPLAYERCOLORS;
		} 
	}

	/**
	 * Player object constructor
	 * @param xPos					integer representing initial horizontal position of a player 
	 * @param yPos					integer representing initial vertical position of a player
	 * other parameters are set to default values
	 */
	public Player(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.color = RED;
		this.tColor = REDPLAYERCOLORS;
	}

	/**
	 * Player object constructor
	 * @param xPos					integer representing initial horizontal position of a player 
	 * @param yPos					integer representing initial vertical position of a player
	 * @param direction				String representing initial player direction
	 * other parameters are set to default values
	 */
	public Player(int xPos, int yPos, String direction) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.direction = direction;
		this.color = RED;
		this.tColor = REDPLAYERCOLORS;
	}

	/**
	 * Player object constructor
	 * @param xPos					integer representing initial horizontal position of a player 
	 * @param yPos					integer representing initial vertical position of a player
	 * @param velocity				integer representing initial player speed
	 * @param color					integer representing player color
	 * other parameters are set to default values
	 */
	public Player(int xPos, int yPos, int velocity, int color) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.velocity = velocity;
		this.color = color;
		if (color == RED) { 
			this.tColor = REDPLAYERCOLORS;
		} 
		else if (color == BLUE) { 
			this.tColor = BLUEPLAYERCOLORS;
		} 
		else if (color == YELLOW) { 
			this.tColor = YELLOWPLAYERCOLORS;
		} 
		else if (color == GREEN) { 
			this.tColor = GREENPLAYERCOLORS;
		}
		else { 
			this.tColor = REDPLAYERCOLORS;
		} 
	}

	/**
	 * Player object constructor
	 * @param xPos					integer representing initial horizontal position of a player 
	 * @param yPos					integer representing initial vertical position of a player
	 * @param direction				String representing initial player direction
	 * @param color					integer representing player color
	 * other parameters are set to default values
	 */
	public Player(int xPos, int yPos, String direction, int color) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.direction = direction;
		this.color = color;
		if (color == RED) { 
			this.tColor = REDPLAYERCOLORS;
		} 
		else if (color == BLUE) { 
			this.tColor = BLUEPLAYERCOLORS;
		} 
		else if (color == YELLOW) { 
			this.tColor = YELLOWPLAYERCOLORS;
		} 
		else if (color == GREEN) { 
			this.tColor = GREENPLAYERCOLORS;
		}
		else { 
			this.tColor = REDPLAYERCOLORS;
		} 
	}
	
	/** 
	 * Updates player position in Level object
	 * @param level				required to update player position in Level object
	 */
	public void update(Level level) {
		//move
		this.xPos = this.getNextPos()[0];
		this.yPos = this.getNextPos()[1];
		//set colors
		level.pixels[xPos][yPos] = tColor.color;	
	}
	
	/**
	 * Returns player color
	 * @return	String representing player color
	 */
	public String getColor() {
		String color;
		if (this.color == Player.RED) {
			color = "Red";
		}
		else if (this.color == Player.BLUE) {
			color = "Blue";
		}
		else if (this.color == Player.YELLOW) {
			color = "Yellow";
		}
		else {
			color = "Green";
		}
		return color;
	}
	
	
	/** 
	 * Calculates and returns the next position of a player
	 * @return Next position of a player as an array of 2 integers of form [x, y]
	 */
	public int[] getNextPos() {
		int[] nextPos = new int[2];
		nextPos[0] = this.xPos;
		nextPos[1] = this.yPos;
		
		if (this.direction == "UP") {
			nextPos[1]--;
		}
		else if (this.direction == "DOWN") {
			nextPos[1]++;
		}
		else if (this.direction == "LEFT") {
			nextPos[0]--;
		}
		else if (this.direction == "RIGHT") {
			nextPos[0]++;
		}

		return nextPos;
	}
	

	
}
