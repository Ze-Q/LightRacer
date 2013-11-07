package accounts;

public class User {
	private String username;
	private int totalWins;
	private int versusWins;
	
	public User (String username) {
		this.username=username;
	}
	
	//constructor used for top ten rank
	public User (String username, int totalWins) {
		this.username=username;
		this.totalWins=totalWins;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public int getTotalWins() {
		return this.totalWins;
	}
	
	public int getVersusWins() {
		return this.versusWins;
	}
	
	public void setUsername(String newUsername) {
		this.username=newUsername;
	}
	
	public void setTotalWins(int newTotalWins) {
		this.totalWins=newTotalWins;
	}
	
	public void setVersusWins(int newVersusWins) {
		this.versusWins=newVersusWins;
	}
	
	public void increaseTotalWins() {
		this.totalWins++;
	}
	
	public void increaseVersusWins() {
		this.versusWins++;
	}
}
