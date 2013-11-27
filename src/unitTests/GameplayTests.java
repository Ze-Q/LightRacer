package unitTests;

import static org.junit.Assert.*;
import gameplay.Level;
import gameplay.Player;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

public class GameplayTests {

	//test getNextPos() method in Player.java
	@Test
	public void testNextPlayerPosistion() {
		Player player1 = new Player(20, 20, "UP");
		Player player2 = new Player(20, 20, "DOWN");
		Player player3 = new Player(20, 20, "RIGHT");
		Player player4 = new Player(20, 20, "LEFT");
		int[] player1nextPos = {20,19};
		int[] player2nextPos = {20,21};
		int[] player3nextPos = {21,20};
		int[] player4nextPos = {19,20};
		
		assertTrue("player1 position is not correct, test failed!", Arrays.equals(player1.getNextPos(), player1nextPos));
		assertTrue("player2 position is not correct, test failed!", Arrays.equals(player2.getNextPos(), player2nextPos));
		assertTrue("player3 position is not correct, test failed!", Arrays.equals(player3.getNextPos(), player3nextPos));
		assertTrue("player4 position is not correct, test failed!", Arrays.equals(player4.getNextPos(), player4nextPos));
	}
	
	
	//test getColor() method in Player.java
	@Test
	public void testGetColor() {
		Player player1 = new Player(20, 20, "UP", 0xFF0707);
		Player player2 = new Player(20, 20, "DOWN", 0x2164FF);
		Player player3 = new Player(20, 20, "RIGHT", 0xFFD407);
		Player player4 = new Player(20, 20, "LEFT", 0x07FF26);
		String red = "Red";
		String yellow = "Yellow";
		String blue = "Blue";
		String green = "Green";
		
		assertTrue("player1 color is not correct, test failed!", red.equals(player1.getColor()));
		assertTrue("player2 color is not correct, test failed!", blue.equals(player2.getColor()));
		assertTrue("player3 color is not correct, test failed!", yellow.equals(player3.getColor()));
		assertTrue("player4 color is not correct, test failed!", green.equals(player4.getColor()));
	}
	
	
	//test isOutOfBounds() method in Level.java
	@Test
	public void testIsOutOfBounds() {
		Level level = new Level(10, 10);
		Player player1 = new Player(2, 2, "UP");
		Player player2 = new Player(2, 2, "DOWN");
		Player player3 = new Player(2, 2, "LEFT");
		Player player4 = new Player(2, 2, "RIGHT");
		Player player5 = new Player(8, 8, "UP");
		Player player6 = new Player(8, 8, "DOWN");
		Player player7 = new Player(8, 8, "LEFT");
		Player player8 = new Player(8, 8, "RIGHT");
		
		assertTrue("player1 position is detected as not out of bounds, test failed!", level.isOutOfBounds(player1) == true);
		assertTrue("player2 position is detected as out of bounds, test failed!", level.isOutOfBounds(player2) == false);
		assertTrue("player3 position is detected as not out of bounds, test failed!", level.isOutOfBounds(player3) == true);
		assertTrue("player4 position is detected as out of bounds, test failed!", level.isOutOfBounds(player4) == false);
		assertTrue("player5 position is detected as out of bounds, test failed!", level.isOutOfBounds(player5) == false);
		assertTrue("player6 position is detected as not out of bounds, test failed!", level.isOutOfBounds(player6) == true);
		assertTrue("player7 position is detected as out of bounds, test failed!", level.isOutOfBounds(player7) == false);
		assertTrue("player8 position is detected as not out of bounds, test failed!", level.isOutOfBounds(player8) == true);
	}
	
}
