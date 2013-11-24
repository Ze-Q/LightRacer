package accounts;

import java.io.IOException;

public class Login {

	private static final Login INSTANCE = new Login();

	private Login() {

	}

	public static Login getInstance() {
		return INSTANCE;
	}

	private User userOne = null;
	private User userTwo = null;

	public User getUserOne() {
		return this.userOne;
	}

	public User getUserTwo() {
		return this.userTwo;
	}

	public void setUserOne(User user) {
		this.userOne = user;
	}

	public void setUserTwo(User user) {
		this.userTwo = user;
	}

	public void setUserNameOne(String username) {
		userOne.setUsername(username);
	}

	public void setUserNameTwo(String username) {
		userTwo.setUsername(username);
	}

	public String getUserNameOne() {
		return userOne.getUsername();
	}

	public String getUserNameTwo() {
		return userTwo.getUsername();
	}

	public User getNotLogin() {
		if (userOne == null)
			return userOne;
		else
			return userTwo;
	}
	
	public boolean userTwoLogin(){
		return !(userTwo==null);
	}
	
	public boolean userOneLogin(){
		return !(userOne==null);
	}
	
	public boolean checkLogedin (String username){
		boolean alreadyLogin = false;
		if(userOne == null && userOne == null) return alreadyLogin;
		else if(userOne!=null){
			alreadyLogin = userOne.getUsername().equals(username);
		}
		else{
			alreadyLogin = userTwo.getUsername().equals(username);
		}
			
		
		return alreadyLogin;
	}

	public boolean login(String username, String password) {
		boolean isValid;
		try {
			isValid = (crud.CSVHandler.isValidCredential(username, password));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isValid = false;
		}
		return isValid;
	}

}
