package accounts;

import java.io.IOException;

public class Login {
	
	private static Login login = null;
	
	private User userOne = null;
	private User userTwo = null;
	
	private Login(){
		this.userOne = userOne;
		this.userTwo = userTwo;
	}
	
	public User getUserOne() {
		return this.userOne;
	}
	
	public User getUserTwo() {
		return this.userTwo;
	}
	
	public void setUserOne(User user) {
		this.userOne = user;
	}
	
	public void serUseTwo(User user) {
		this.userTwo = user;
	}
	
	public User getNotLogin(){
		if (userOne == null) return userOne;
		else return userTwo;
	}
	
	public static synchronized Login getLogin(){
		if(login == null){
			login = new Login();
		}
		return login;
	}
	
	public boolean login(String username, String password){
		boolean isValid;
		try {
			isValid = crud.CSVHandler.isValidCredential(username, password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isValid = false;
		}
		return isValid;
	}
	
}