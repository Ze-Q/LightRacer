package unitTests;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import crud.StatsFileSystem;
import accounts.Statistics;
import accounts.User;

//integration test between Statistics and StatsFileSystem
public class StatisticsTests {

	private void generateTestArray() {
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
	
	private void cleanUpArray(){
		for(int i=0; i<statsFileSystem.getStatsArray().length; i++) {
			for(int j=0; j<statsFileSystem.getStatsArray().length; j++) {
				statsFileSystem.getStatsArray()[i][j]=null;
			}
		}
		statsFileSystem.getStatsArray()[0][0]="0";
	}
	
	StatsFileSystem statsFileSystem = StatsFileSystem.getInstance();
	Statistics statistics = Statistics.getInstance();
	
	@Test
	public void testReadVersus() {
		User user1 = new User("Z");
		User user2 = new User("A");
		
		cleanUpArray();
		generateTestArray();
		statistics.readVersusFromFile(user1, user2);
		assertEquals(user1.getVersusWins(),2);
		assertEquals(user2.getVersusWins(),5);
	}
	
	
	@Test
	public void testUpdateStats() {
		User user1 = new User("Z");
		User user2 = new User("A");
		
		cleanUpArray();
		generateTestArray();
		user1.setVersusWins(20);
		user2.setVersusWins(50);
		statistics.updateStats(user1, user2);
		assertEquals(statsFileSystem.getStatsArray()[1][2],"20");
		assertEquals(statsFileSystem.getStatsArray()[2][1],"50");
	}
	
	@Test
	public void testAddNewUserToBlankFile() {
		User user1 = new User("J");
		cleanUpArray();
		generateTestArray();
		statistics.addNewUser(user1);
		assertEquals(statsFileSystem.getStatsArray()[5][0],"J");
	}
	
	@Test
	public void testTopTen() {
		cleanUpArray();
		generateTestArray();
		User[] topTen=statistics.getTopTen();
		assertEquals(topTen[0].getUsername(),"C");
		assertEquals(topTen[1].getUsername(),"A");
		assertEquals(topTen[2].getUsername(),"D");
		assertEquals(topTen[3].getUsername(),"Z");
	}
	
}
