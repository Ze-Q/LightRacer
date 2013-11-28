package accounts;

import java.io.IOException;
/**
 * This class contains all the methods used to verify login.
 * @author Cheng Gong
 *
 */
public class Login {

	private static final Login INSTANCE = new Login();

	private Login() {

	}
	
	/**
	 * 
	 * @return loginObject				This method returns the loginObject (Singleton design)
	 */
	public static Login getInstance() {
		return INSTANCE;
	}

	private User userOne = null;
	private User userTwo = null;

	/**
	 * This method return the user one
	 * @return userOne
	 */
	public User getUserOne() {
		return this.userOne;
	}

	/**
	 * This method return the user two
	 * @return userTwo
	 */
	public User getUserTwo() {
		return this.userTwo;
	}

	/**
	 * This method set the user one
	 * @param user
	 */
	public void setUserOne(User user) {
		this.userOne = user;
	}

	/**
	 * This method set the user two
	 * @param user
	 */
	public void setUserTwo(User user) {
		this.userTwo = user;
	}

	/**
	 * This method get the username one
	 * @return usernameOne
	 */
	public String getUserNameOne() {
		return userOne.getUsername();
	}

	/**
	 * This method get the username two
	 * @return usernameTwo
	 */
	public String getUserNameTwo() {
		return userTwo.getUsername();
	}

	/**
	 * This method is used to identify whether userOne is logged in
	 * @return isLoggedIn
	 */
	public boolean userOneLogin() {
		return !(userOne == null);
	}

	/**
	 * This method is used to identify whether userTwo is logged in
	 * @return isLoggedIn
	 */
	public boolean userTwoLogin() {
		return !(userTwo == null);
	}

	/**
	 * This method verifies whether one user is logged in
	 * @param username
	 * @return isLoggedIn
	 */
	public boolean checkLogedin(String username) {
		boolean alreadyLogin = false;
		if (userOne == null && userOne == null)
			return alreadyLogin;
		else if (userOne != null) {
			alreadyLogin = userOne.getUsername().equals(username);
		} else {
			alreadyLogin = userTwo.getUsername().equals(username);
		}

		return alreadyLogin;
	}

	/**
	 * This method checks whether an username and password are valid
	 * @param username				the username entered by the user
	 * @param password				the password entered by the password
	 * @return isValid				whether the useranme and password is a valid combination
	 */
	public boolean isValidUserInfo(String username, String password) {
		boolean isValid = false;
		try {
			isValid = (crud.CSVHandler.isValidCredential(username, password));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isValid;
	}

	/**
	 * This method is used to login userOne
	 * @param username					the username entered by the user
	 * @param password					the password entered by the password
	 * @return success					whether login is successful
	 */
	public boolean loginUserOne(String username, String password) {
		boolean isValid;
		try {
			isValid = (crud.CSVHandler.isValidCredential(username, password));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isValid = false;
		}
		if (isValid) {
			User thisUser = new User(username);
			setUserOne(thisUser);
		}
		return isValid;
	}

	/**
	 * This method is used to login userTwo
	 * @param username					the username entered by the user
	 * @param password					the password entered by the password
	 * @return success					whether login is successful
	 */
	public boolean loginUserTwo(String username, String password) {
		boolean isValid;
		try {
			isValid = (crud.CSVHandler.isValidCredential(username, password));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isValid = false;
		}
		if (isValid) {
			User thisUser = new User(username);
			setUserTwo(thisUser);
		}
		return isValid;
	}

}
