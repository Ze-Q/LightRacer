package unitTests;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import crud.StatsFileSystem;
import accounts.Statistics;
import accounts.User;


public class StatsTests {

	public void generateTestArray() {
		StatsFileSystem.generateNewStatsArray(25);
		StatsFileSystem.statsArray[0][1]="Z";
		StatsFileSystem.statsArray[0][2]="A";
		StatsFileSystem.statsArray[0][3]="C";
		StatsFileSystem.statsArray[0][4]="D";
		
		StatsFileSystem.statsArray[1][0]="Z";
		StatsFileSystem.statsArray[2][0]="A";
		StatsFileSystem.statsArray[3][0]="C";
		StatsFileSystem.statsArray[4][0]="D";
		
		StatsFileSystem.statsArray[1][1]="0";
		StatsFileSystem.statsArray[1][2]="2";
		StatsFileSystem.statsArray[1][3]="3";
		StatsFileSystem.statsArray[1][4]="4";
		
		StatsFileSystem.statsArray[2][1]="5";
		StatsFileSystem.statsArray[2][2]="0";
		StatsFileSystem.statsArray[2][3]="7";
		StatsFileSystem.statsArray[2][4]="8";
		
		StatsFileSystem.statsArray[3][1]="9";
		StatsFileSystem.statsArray[3][2]="8";
		StatsFileSystem.statsArray[3][3]="0";
		StatsFileSystem.statsArray[3][4]="6";
		
		StatsFileSystem.statsArray[4][1]="5";
		StatsFileSystem.statsArray[4][2]="4";
		StatsFileSystem.statsArray[4][3]="3";
		StatsFileSystem.statsArray[4][4]="0";
	}
	
	
	//@Test
	public void readFile() throws IOException {
		boolean successful = StatsFileSystem.readStatsFromFile(4);
		StatsFileSystem.printStatsArray(20);
		assertTrue("The file does not exist!", successful);		
	}
	
	//@Test
	public void writeTest() throws IOException {
		generateTestArray();
		boolean doesStatsExist = StatsFileSystem.writeStatsToFile();
		assertTrue("Created the file!", doesStatsExist);
	}
	
	//@Test
	public void testStatsArray() {
		generateTestArray();
		StatsFileSystem.printStatsArray(20);
	}
	
	//@Test
	public void testUpdateVersusRecords() {
		User user1 = new User("A");
		User user2 = new User("D");
		
		StatsFileSystem.readStatsFromFile(4);
		StatsFileSystem.printStatsArray(20);
		Statistics.readVersusFromFile(user1, user2);
		
		System.out.println(user1.getUsername() +" won "+user1.getVersusWins() + " times against " + user2.getUsername());
		System.out.println(user2.getUsername() +" won "+user2.getVersusWins() + " times against " + user1.getUsername());
	}
	
	//@Test
	public void testUpdateStatsFile() {
		User user1 = new User("A");
		User user2 = new User("D");
		
		StatsFileSystem.readStatsFromFile(4);
		StatsFileSystem.printStatsArray(20);
		Statistics.readVersusFromFile(user1, user2);
		user1.increaseVersusWins();
		user2.increaseVersusWins();
		
		Statistics.updateStats(user1, user2);
		StatsFileSystem.readStatsFromFile(4);
		StatsFileSystem.printStatsArray(20);
	}
	
	
	//@Test
	public void testAddNewUser() {
		User user1 = new User("L");
		StatsFileSystem.readStatsFromFile(4);
		StatsFileSystem.printStatsArray(20);
		Statistics.addNewUser(user1);
		Statistics.updateStatsFile();
		
		StatsFileSystem.readStatsFromFile(4);
		StatsFileSystem.printStatsArray(20);
	}
	
	//@Test
	public void testAddNewUserToBlankFile() {
		User user1 = new User("L");
		User user2 = new User("O");
		User user3 = new User("P");
		StatsFileSystem.readStatsFromFile(4);
		StatsFileSystem.printStatsArray(20);
		Statistics.addNewUser(user1);
		Statistics.addNewUser(user2);
		Statistics.addNewUser(user3);
		Statistics.updateStatsFile();
		
		StatsFileSystem.readStatsFromFile(4);
		StatsFileSystem.printStatsArray(20);
	}
	
	@Test
	public void testTopTen() {
		Statistics.readStatsFile(20);
		Statistics.getTopTen();
	}
	
}
