package accounts;

import crud.StatsFileSystem;

public class Statistics {
	private static final Statistics INSTANCE = new Statistics();
	private static StatsFileSystem statsFileSystem = StatsFileSystem.getInstance();
	
	private Statistics() {
	}
	
	public static Statistics getInstance() { 
        return INSTANCE;
    }
	
	//read stats.csv to prepare for stats inquiries
	public void readStatsFile() {
		statsFileSystem.readStatsFromFile();
	}
	
	//must write to file before closing the game
	public void updateStatsFile() {
		statsFileSystem.writeStatsToFile();
	}
		
	
	//read from stats.csv for versus records, and update the user objects' corresponding fields
	public void readVersusFromFile(User user1, User user2) {
		String oneOverTwo = statsFileSystem.searchForVersus(user1.getUsername(), user2.getUsername());
		String twoOverOne = statsFileSystem.searchForVersus(user2.getUsername(), user1.getUsername());
		user1.setVersusWins(Integer.parseInt(oneOverTwo));
		user2.setVersusWins(Integer.parseInt(twoOverOne));
	}
	
	//update the statsArray, where all the info is still temporarily stored in memory
	public void updateStats (User user1, User user2) {
		statsFileSystem.updateRecords(user1.getUsername(), user1.getVersusWins(), user2.getUsername(), user2.getVersusWins());
	}
	
	//add a new user to the statsArray with the corresponding 0's
	public void addNewUser(User user) {
		statsFileSystem.addNewUserToArray (user.getUsername());
	}
	
	//get the top ten players as a list of User
	public User[] getTopTen() {
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
	
	private String[] getUsernamesAsArray() {
		String[] usernames = new String [statsFileSystem.getStatsArray().length];
		for(int row=1; row<statsFileSystem.getStatsArray().length; row++) {
			if (statsFileSystem.getStatsArray()[row][0]!=null){
				usernames[row]=statsFileSystem.getStatsArray()[row][0];
			}
			else{
				break;
			}
		}
		return usernames;
	}
	
	private int[] getTotalWinsAsArray() {
		int[] totalWins = new int [statsFileSystem.getStatsArray().length];
		int temporarySum=0;
		
		for(int row=1; row<statsFileSystem.getStatsArray().length; row++) {
			if (statsFileSystem.getStatsArray()[row][0]!=null){
				for(int column=1; column<statsFileSystem.getStatsArray().length; column++) {
					if (statsFileSystem.getStatsArray()[row][column]!=null){
						temporarySum += Integer.parseInt(statsFileSystem.getStatsArray()[row][column]);
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
	
	private User[] generateUserArray(String[] usernames, int[] totalWins) {
		User[] users = new User[usernames.length];
		for(int i=1; i<usernames.length&&usernames[i]!=null; i++) {
			users[i-1]=new User(usernames[i],totalWins[i]);
		}
		return users;
	}
	
	private void sortUsers(User[] users) {
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
			}
		}
	}
}
