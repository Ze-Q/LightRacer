package accounts;

/**
 * The User class is responsible to contain the info of one User.
 * @author Ze
 *
 */
public class User {
	private String username;
	private int totalWins;
	private int versusWins;
	
	/**
	 * Initialize a new User object with only an username.
	 * @param username A string which is the username of the User object.
	 */
	public User (String username) {
		this.username=username;
	}
	
	/**
	 * Constructor used only for calculating top ten players.
	 * <p>Initialize a new User object with the username and the number of total wins.
	 * @param username A string which is the username of the User object.
	 * @param totalWins An int which is the total wins of the User.
	 */
	public User (String username, int totalWins) {
		this.username=username;
		this.totalWins=totalWins;
	}
	
	/**
	 * Get the username of the User object.
	 * @return return a string which is the username of the User.
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * Get the number of total wins of the User object.
	 * @return return an int which is the total wins of the User.
	 */
	public int getTotalWins() {
		return this.totalWins;
	}
	
	/**
	 * Get the number of versus wins (against the other User) of the User object
	 * @return return an int which is the versus wins of the User.
	 */
	public int getVersusWins() {
		return this.versusWins;
	}
	
	/**
	 * Set a new username for the User object.
	 * @param newUsername Accepts a String which is the new username.
	 */
	public void setUsername(String newUsername) {
		this.username=newUsername;
	}
	
	/**
	 * Set a new total wins for the User object.
	 * @param newTotalWins Accepts an int which is the new totalWins.
	 */
	public void setTotalWins(int newTotalWins) {
		this.totalWins=newTotalWins;
	}
	
	/**
	 * Set a new number of versus wins for the User object.
	 * @param newVersusWins Accepts an int which is the new versusWins.
	 */
	public void setVersusWins(int newVersusWins) {
		this.versusWins=newVersusWins;
	}
	
	/**
	 * Increase the number of total wins by one.
	 */
	public void increaseTotalWins() {
		this.totalWins++;
	}
	
	/**
	 * Increase the number of total wins by one.
	 */
	public void increaseVersusWins() {
		this.versusWins++;
	}
	
	/**
	 * For testing purpose only. Print User info (username and total wins) to the stdout. 
	 */
	public void printUser() {
		System.out.println("The user " + this.getUsername() + " has a total win of " + this.getTotalWins() +" !");
	}
	
	/**
	 * For testing purpose only. Print a list of User (username and total wins) to the stdout. 
	 * @param users
	 */
	public void printUserList(User[] users) {
		for(int i=0; i<users.length&&users[i]!=null; i++) {
			users[i].printUser();
		}
	}
}
