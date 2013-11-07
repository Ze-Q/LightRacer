package accounts;

import crud.StatsFileSystem;


public class Statistics {
	
	public User[] getTopTen() {
		
		return null;
	}

	//read from stats.csv from versus records, and update the user objects' corresponding fields
	public static void updateVersusRecord(User user1, User user2) {
		
		String oneOverTwo = StatsFileSystem.searchForVersus(user1.getUsername(), user2.getUsername());
		String twoOverOne = StatsFileSystem.searchForVersus(user2.getUsername(), user1.getUsername());
		user1.setVersusWins(Integer.parseInt(oneOverTwo));
		user2.setVersusWins(Integer.parseInt(twoOverOne));
		
		//System.out.println("The strings are " + oneOverTwo + " and " + twoOverOne);
		//System.out.println("The integers are " + Integer.parseInt(oneOverTwo) + " and " + Integer.parseInt(twoOverOne));
	}
	
	public static void updateStatsFile(User user1, User user2) {
		
		StatsFileSystem.updateRecords(user1.getUsername(), user1.getVersusWins(), user2.getUsername(), user2.getVersusWins());
		StatsFileSystem.writeStatsToFile();
		
	}
}
