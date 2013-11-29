package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import accounts.CreateAccount;
import accounts.Login;

public class AccountCRUDIntegrationTest {
	
	@Test
	public void testCreateAccountWithUnvalidUsernameUnvalidPassword() {
		String username = "Ze@";
		String password = "";
		boolean success = CreateAccount.addUser(username, password);
		assertTrue(
				"testCreateAccountWithUnvalidUsernameUnvalidPassword failed! "
						+ "Username: " + username + ", Password: " + password
						+ "are unvalid", !success);
	}
	
	@Test
	public void testCreateAccountWithUnvalidUsernameValidPassword() {
		String username = "Ze@";
		String password = "Dem@Us3R01";
		boolean success = CreateAccount.addUser(username, password);
		assertTrue(
				"testCreateAccountWithUnvalidUsernameValidPassword failed! "
						+ "Password: " + password
						+ "is unvalid", !success);
	}
	
	@Test
	public void testCreateAccountWithValidUsernameUnValidPassword() {
		String username = "Ze";
		String password = "";
		boolean success = CreateAccount.addUser(username, password);
		assertTrue(
				"testCreateAccountWithValidUsernameUnValidPassword failed! "
						+ "Username: " + username
						+ "is unvalid", !success);
	}
	
	private Login loginObject = Login.getInstance();
	@Test
	public void testIsValidUserInfoWithUnvalidUsernameUnvalidPassword() {
		String username = "ECSE";
		String password = "test";
		boolean isValid = loginObject.isValidUserInfo(username, password);
		assertTrue("testIsValidUserInfoWithUnvalidUsernameUnvalidPassword failed! " + "Username: " + username + " Password: " + password + " are invalid",!isValid);
	}
	
	@Test
	public void testIsValidUserInfoWithUnvalidUsernameValidPassword() {
		String username = "ECSE";
		String password = "Dem@Us3R01";
		boolean isValid = loginObject.isValidUserInfo(username, password);
		assertTrue("testIsValidUserInfoWithUnvalidUsernameValidPassword failed! " + "Username: " + username + " is invalid",!isValid);
	}
	
	@Test
	public void testIsValidUserInfoWithvalidUsernameUnvalidPassword() {
		String username = "Demo01";
		String password = "test";
		boolean isValid = loginObject.isValidUserInfo(username, password);
		assertTrue("testIsValidUserInfoWithvalidUsernameUnvalidPassword failed! " + " Password: " + password + " is invalid",!isValid);
	}
	
	@Test
	public void testIsValidUserInfoWithvalidUsernameValidPassword() {
		String username = "Demo01";
		String password = "Dem@Us3R01";
		boolean isValid = loginObject.isValidUserInfo(username, password);
		assertTrue("testIsValidUserInfoWithvalidUsernameValidPassword failed! " + "Username: "+username+" Password: " + password + " are invalid",isValid);
	}

	@Test
	public void testLoginUserOne() {
		String username = "Demo01";
		String password = "Dem@Us3R01";
		boolean success = loginObject.loginUserOne(username, password);
		assertTrue("testLoginUserOne failed!",success);
	}

	@Test
	public void testLoginUserTow() {
		String username = "Demo02";
		String password = "Dem@Us3R02";
		boolean success = loginObject.loginUserTwo(username, password);
		assertTrue("testLoginUserOne failed!",success);
	}

	@Test
	public void testCheckLogedinForLoginedUserOne() {
		String username = "Demo01";
		boolean isLoggedin = loginObject.checkLogedin(username);
		assertTrue("testCheckLogedinForLoginedUserTwo failed! " + username+ " is logged in!",isLoggedin);
	}
	
	@Test
	public void testCheckLogedinForLoginedUserWithNotLogedinUser() {
		String username = "Demo03";
		boolean isLoggedin = loginObject.checkLogedin(username);
		assertTrue("testCheckLogedinForLoginedUserTwo failed! " + username+ " is not logged in!",!isLoggedin);
	}
}


