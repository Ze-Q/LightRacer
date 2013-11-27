package accounts;

import crud.StatsFileSystem;

/**
 * Statistics keep track of the battle records of all users.
 * @author Ze
 *	@version 1.0
 */
public class Statistics {
	private static final Statistics INSTANCE = new Statistics();
	private static StatsFileSystem statsFileSystem = StatsFileSystem.getInstance();
	
	private Statistics() {
	}
	
	/**
	 * The getter for singleton design, it makes sure that there is only one instance of this object.
	 * @return return the only instance of the Statistics object
	 */
	public static Statistics getInstance() { 
        return INSTANCE;
    }
	
	/**
	 * Read stats.csv to prepare for stats inquiries.
	 */
	public void readStatsFile() {
		statsFileSystem.readStatsFromFile();
	}
	
	/**
	 * Write the stats info in memory to the file.
	 */
	public void updateStatsFile() {
		statsFileSystem.writeStatsToFile();
	}
		
	
	/**
	 * Read from stats.csv for versus records, and update the user objects' corresponding fields.
	 * @param user1 Takes in an User object which corresponds to user1.
	 * @param user2 Takes in an User object which corresponds to user2.
	 */
	public void readVersusFromFile(User user1, User user2) {
		String oneOverTwo = statsFileSystem.searchForVersus(user1.getUsername(), user2.getUsername());
		String twoOverOne = statsFileSystem.searchForVersus(user2.getUsername(), user1.getUsername());
		user1.setVersusWins(Integer.parseInt(oneOverTwo));
		user2.setVersusWins(Integer.parseInt(twoOverOne));
	}
	
	/**
	 * Update the statsArray, where all the info is still temporarily stored in memory.
	 * This method extracts the versus records from the User objects and update them in the statsArray.
	 * 
	 * @param user1 Takes in an User object which corresponds to user1.
	 * @param user2 Takes in an User object which corresponds to user2.
	 */
	public void updateStats (User user1, User user2) {
		statsFileSystem.updateRecords(user1.getUsername(), user1.getVersusWins(), user2.getUsername(), user2.getVersusWins());
	}
	
	/**
	 * add a new user to the statsArray with the corresponding 0's
	 * @param user Takes in an User object which corresponds to the new user.
	 */
	public void addNewUser(User user) {
		statsFileSystem.addNewUserToArray (user.getUsername());
	}
	
	/**
	 * This method gets the top ten players (with the highest number of wins) as a list of User.
	 * @return This method returns an array of size ten, which contains the top ten players.
	 */
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
