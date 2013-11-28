package unitTests;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import crud.StatsFileSystem;
import accounts.Statistics;
import accounts.User;

//integration test between Statistics and StatsFileSystem
public class StatsTests {

	public void generateTestArray() {
		StatsFileSystem statsFileSystem = StatsFileSystem.getInstance();	
		statsFileSystem.getStatsArray()[0][1]="Z";
		statsFileSystem.getStatsArray()[0][2]="A";
		statsFileSystem.getStatsArray()[0][3]="C";
		statsFileSystem.getStatsArray()[0][4]="D";
		
		statsFileSystem.getStatsArray()[1][0]="Z";
		statsFileSystem.getStatsArray()[2][0]="A";
		statsFileSystem.getStatsArray()[3][0]="C";
		statsFileSystem.getStatsArray()[4][0]="D";
		
		statsFileSystem.getStatsArray()[1][1]="0";
		statsFileSystem.getStatsArray()[1][2]="2";
		statsFileSystem.getStatsArray()[1][3]="3";
		statsFileSystem.getStatsArray()[1][4]="4";
		
		statsFileSystem.getStatsArray()[2][1]="5";
		statsFileSystem.getStatsArray()[2][2]="0";
		statsFileSystem.getStatsArray()[2][3]="7";
		statsFileSystem.getStatsArray()[2][4]="8";
		
		statsFileSystem.getStatsArray()[3][1]="9";
		statsFileSystem.getStatsArray()[3][2]="8";
		statsFileSystem.getStatsArray()[3][3]="0";
		statsFileSystem.getStatsArray()[3][4]="6";
		
		statsFileSystem.getStatsArray()[4][1]="5";
		statsFileSystem.getStatsArray()[4][2]="4";
		statsFileSystem.getStatsArray()[4][3]="3";
		statsFileSystem.getStatsArray()[4][4]="0";
	}
	
	StatsFileSystem statsFileSystem = StatsFileSystem.getInstance();
	Statistics statistics = Statistics.getInstance();
	
	//@Test
	public void testUpdateStatsFile() {
		User user1 = new User("A");
		User user2 = new User("D");
		
		statistics.readStatsFile();
		statsFileSystem.printStatsArray();
		statistics.readVersusFromFile(user1, user2);
		user1.increaseVersusWins();
		user2.increaseVersusWins();
		
		statistics.updateStats(user1, user2);
		statistics.updateStatsFile();
		statistics.readStatsFile();
		statsFileSystem.printStatsArray();
	}
	
	
	//@Test
	public void testAddNewUser() {
		User user1 = new User("F");
		statistics.readStatsFile();
		statsFileSystem.printStatsArray();
		statistics.addNewUser(user1);
		statistics.updateStatsFile();
		
		statsFileSystem.readStatsFromFile();
		statsFileSystem.printStatsArray();
	}
	
	//@Test
	public void testAddNewUserToBlankFile() {
		User user1 = new User("J");
		User user2 = new User("K");
		User user3 = new User("L");
		statistics.readStatsFile();
		statsFileSystem.printStatsArray();
		statistics.addNewUser(user1);
		statistics.addNewUser(user2);
		statistics.addNewUser(user3);
		statistics.updateStatsFile();
		
		statistics.readStatsFile();
		statsFileSystem.printStatsArray();
	}
	
	//@Test
	public void testTopTen() {
		statistics.readStatsFile();
		statsFileSystem.printStatsArray();
		
		User temp = new User("test");
		temp.printUserList(statistics.getTopTen());
		
	}
	
}
