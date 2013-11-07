package unitTests;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import crud.StatsFileSystem;


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
	
	
	@Test
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
}
