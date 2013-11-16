package gameplay;

import gameplay.Tuple;
import gameplay.Level;

public class Player {

	public int xPos, yPos;
	public String direction = "UP";
	public int velocity = 1;
	public int color;
	public Tuple tColor;

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


	//Default constructor : player position at middle - (400,300), direction UP
	public Player() {
		this.xPos = 400;
		this.yPos = 300;
		this.color = RED;
		this.tColor = REDPLAYERCOLORS;
	}

	//Constructor that allows to specify all parameters
	public Player(int xPos, int yPos, int velocity, String direction, int color) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.velocity = velocity;
		this.direction = direction;
		this.color = color;
		if (color == RED) 			{ this.tColor = REDPLAYERCOLORS; } 
		else if (color == BLUE) 	{ this.tColor = BLUEPLAYERCOLORS; } 
		else if (color == YELLOW) 	{ this.tColor = YELLOWPLAYERCOLORS; } 
		else if (color == GREEN) 	{ this.tColor = GREENPLAYERCOLORS; }
		else 						{ this.tColor = REDPLAYERCOLORS; } 
	}

	//Constructor that allows to specify xPos, yPos and has default speed and direction
	public Player(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.color = RED;
		this.tColor = REDPLAYERCOLORS;
	}

	//Constructor that allows to specify xPos, yPos, and direction with default velocity and color
	public Player(int xPos, int yPos, String direction) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.direction = direction;
		this.color = RED;
		this.tColor = REDPLAYERCOLORS;
	}

	//Constructor that allows to specify xPos, yPos, color, and velocity with default direction
	public Player(int xPos, int yPos, int velocity, int color) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.velocity = velocity;
		this.color = color;
		if (color == RED) 			{ this.tColor = REDPLAYERCOLORS; } 
		else if (color == BLUE) 	{ this.tColor = BLUEPLAYERCOLORS; } 
		else if (color == YELLOW) 	{ this.tColor = YELLOWPLAYERCOLORS; } 
		else if (color == GREEN) 	{ this.tColor = GREENPLAYERCOLORS; }
		else 						{ this.tColor = REDPLAYERCOLORS; } 
	}

	//Constructor that allows to specify xPos, yPos, color, and direction with default velocity
	public Player(int xPos, int yPos, String direction, int color) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.direction = direction;
		this.color = color;
		if (color == RED) 			{ this.tColor = REDPLAYERCOLORS; } 
		else if (color == BLUE) 	{ this.tColor = BLUEPLAYERCOLORS; } 
		else if (color == YELLOW) 	{ this.tColor = YELLOWPLAYERCOLORS; } 
		else if (color == GREEN) 	{ this.tColor = GREENPLAYERCOLORS; }
		else 						{ this.tColor = REDPLAYERCOLORS; } 
	}
	
	/*TODO Update the method to make the color on the inside darker - glow effect*/
	public void update(Level level) {
		//move
		this.xPos = this.getNextPos()[0];
		this.yPos = this.getNextPos()[1];
		//set colors
		level.pixels[xPos][yPos] = tColor.color;
		/*level.pixels[xPos][yPos - 1] = tColor.color; 
		level.pixels[xPos][yPos + 1] = tColor.color; 
		level.pixels[xPos + 1][yPos] = tColor.color;  
		level.pixels[xPos - 1][yPos] = tColor.color; 
		level.pixels[xPos + 1][yPos - 1] = tColor.color;  
		level.pixels[xPos - 1][yPos - 1] = tColor.color; 
		level.pixels[xPos + 1][yPos + 1] = tColor.color; 
		level.pixels[xPos - 1][yPos + 1] = tColor.color; */
		//set collisions
		//level.collisions[xPos][yPos] = true;		
	}
	
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