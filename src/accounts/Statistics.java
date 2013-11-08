package accounts;

import crud.StatsFileSystem;

public class Statistics {

	//read stats.csv to prepare for stats inquiries
	public static void readStatsFile(int numberOfUsers) {
		StatsFileSystem.readStatsFromFile(numberOfUsers);
	}
	
	//read from stats.csv for versus records, and update the user objects' corresponding fields
	public static void readVersusFromFile(User user1, User user2) {
		String oneOverTwo = StatsFileSystem.searchForVersus(user1.getUsername(), user2.getUsername());
		String twoOverOne = StatsFileSystem.searchForVersus(user2.getUsername(), user1.getUsername());
		user1.setVersusWins(Integer.parseInt(oneOverTwo));
		user2.setVersusWins(Integer.parseInt(twoOverOne));
	}
	
	//update the statsArray, where all the info is temporarily stored in memory
	public static void updateStats (User user1, User user2) {
		StatsFileSystem.updateRecords(user1.getUsername(), user1.getVersusWins(), user2.getUsername(), user2.getVersusWins());
	}
	
	//must write to file before closing the game
	public static void updateStatsFile() {
		StatsFileSystem.writeStatsToFile();
	}
	
	//add a new user to the statsArray with the corresponding 0's
	public static void addNewUser(User user) {
		StatsFileSystem.addNewUserToArray (user.getUsername());
	}
	
	//get the top ten players as a list of User
	public static User[] getTopTen() {
		String[] usernames = getUsernamesAsArray();
		int[] totalWins = getTotalWinsAsArray();
		User[] users = generateUserArray(usernames, totalWins);
		
		sortUsers(users);
		
		User[] results = new User[10];
		for(int i=0; i<10; i++) {
			results[i]=users[i];
		}
	
		return results;
	}
	
	private static String[] getUsernamesAsArray() {
		String[] usernames = new String [StatsFileSystem.statsArray.length];
		for(int row=1; row<StatsFileSystem.statsArray.length; row++) {
			if (StatsFileSystem.statsArray[row][0]!=null){
				usernames[row]=StatsFileSystem.statsArray[row][0];
			}
			else{
				break;
			}
		}
		return usernames;
	}
	
	private static int[] getTotalWinsAsArray() {
		int[] totalWins = new int [StatsFileSystem.statsArray.length];
		int temporarySum=0;
		
		for(int row=1; row<StatsFileSystem.statsArray.length; row++) {
			if (StatsFileSystem.statsArray[row][0]!=null){
				for(int column=1; column<StatsFileSystem.statsArray.length; column++) {
					if (StatsFileSystem.statsArray[row][column]!=null){
						temporarySum += Integer.parseInt(StatsFileSystem.statsArray[row][column]);
					}
					else{
						break;
					}
				}
				totalWins[row]=temporarySum;
				temporarySum=0;
			}
			else{
				break;
			}
		}
		return totalWins;
	}
	
	private static User[] generateUserArray(String[] usernames, int[] totalWins) {
		User[] users = new User[usernames.length];
		for(int i=1; i<usernames.length&&usernames[i]!=null; i++) {
			users[i-1]=new User(usernames[i],totalWins[i]);
		}
		return users;
	}
	
	private static void sortUsers(User[] users) {
		int lastUserAt=0;
		for(int i=0; i<users.length; i++) {
			if(users[i]!=null){
				lastUserAt=i;
			}
			else{
				break;
			}
		}
		
		for(int i=0; i<=lastUserAt; i++) {
			for(int j=lastUserAt; j>i; j--){
				if(users[j].getTotalWins()>users[j-1].getTotalWins()){
					User temp = users[j-1];
					users[j-1] = users[j];
					users[j] = temp;
				}
				else{
					break;
				}
			}
		}
	}
}
