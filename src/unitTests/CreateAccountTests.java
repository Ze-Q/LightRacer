package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import accounts.CreateAccount;

public class CreateAccountTests {
	
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
	
	@Test
	public void testCreateAccountWithValidUsernameValidPassword() {
		String username = "Ze";
		String password = "Dem@Us3R01";
		boolean success = CreateAccount.addUser(username, password);
		assertTrue(
				"testCreateAccountWithValidUsernameUnValidPassword failed! "
						+ "Username: " + username + "Password: " + password
						+ "are valid", success);
	}
}
