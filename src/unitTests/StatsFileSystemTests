package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import accounts.User;
import crud.StatsFileSystem;

//this class contains reading and writing tests for StatsFileSystem.java
public class StatsFileSystemTests {

	StatsFileSystem statsFileSystem = StatsFileSystem.getInstance();
	
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
	
	@Test
	public void readFile() throws IOException {
		cleanUpArray();
		assertTrue(statsFileSystem.readStatsFromFile());
	}
	
	@Test
	public void writeTest() throws IOException {
		cleanUpArray();
		statsFileSystem.readStatsFromFile();
		boolean success = statsFileSystem.writeStatsToFile();
		assertTrue("Wrote the file!", success);
	}
	
	@Test
	public void searchVersusTest() throws IOException {
		cleanUpArray();
		generateTestArray();
		String result=statsFileSystem.searchForVersus("Z", "A");
		assertEquals(result,"2");
	}
	
	@Test
	public void testUpdateVersusRecords() {
		cleanUpArray();
		generateTestArray();
		statsFileSystem.updateRecords("Z", 5, "A", 10);
		assertEquals(statsFileSystem.getStatsArray()[1][2],"5");
		assertEquals(statsFileSystem.getStatsArray()[2][1],"10");
	}
	
	@Test
	public void testAddNewUser() {
		cleanUpArray();
		generateTestArray();
		statsFileSystem.addNewUserToArray("CCC");
		assertEquals(statsFileSystem.getStatsArray()[5][0],"CCC");
	}
		
}
