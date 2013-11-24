/**
 * 
 * CSVHandlerTests.java
 * Purpose: Unit Tests CRUD operations for create account and login
 *
 * @author Cheng Gong
 * @version 1.0 11/22/13
 */

package unitTests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import crud.CSVHandler;

public class CSVHandlerTests {

	/**
	 * Test CRUD operation for create account
	 */

	@Test
	public void testIsExistsWithNotExistUsername() throws IOException {
		String username01 = "ECSE321";
		boolean isUserExists01 = CSVHandler.isExist(username01);
		assertTrue("testIsExistsWithNotExistUsername failed, " + username01
				+ " does not exist!", !isUserExists01);
	}

	@Test
	public void testIsExistsWithExistUsername() throws IOException {
		String username00 = "Demo01";
		boolean isUserExists00 = CSVHandler.isExist(username00);
		assertTrue("testIsExistsWithExistUsername failed, " + username00
				+ "exists!", isUserExists00);
	}

	@Test
	public void testAddUser() {
		String username = "Test";
		String password = "Testing";
		boolean success = CSVHandler.addUser(username, password);
		assertTrue("testAddUser passed, " + username + " cannot be added.",
				success);
	}

	@Test
	public void testIsValidUsernameWithValidUsername() {
		String username = "Demo01";
		String message = CSVHandler.isValidUsername(username);
		assertTrue(
				"testIsValidUsernameWithExistingUsername failed, this is a valid username!",
				message.equals(""));
	}

	@Test
	public void testIsValidUsernameWithUnvalidUsername() {
		String username = "Demo01@";
		String message = CSVHandler.isValidUsername(username);
		assertTrue(
				"testIsValidUsernameWithExistingUsername failed, this is a unvalid username!",
				message.equals("Username must only contains alphanumeric characters.<br>"));
	}

	@Test
	public void testIsValidPasswordWithValidPassword() {
		String password = "Dem@Us3R01";
		String message = CSVHandler.isValidPassword(password);
		assertTrue(
				"testIsValidPasswordWithValidPassword failed, this is a valid password!",
				message.equals(""));
	}

	@Test
	public void testIsValidPasswordWithUnalidPasswordWithLengthLessThan8() {
		String password = "123";
		String message = CSVHandler.isValidPassword(password);
		assertTrue(
				"testIsValidPasswordWithUnalidPasswordWithLengthLessThan8 failed, this is a unvalid password!",
				message.contains("Password minimum length is 8.<br>"));
	}

	@Test
	public void testIsValidPasswordWithNoDigit() {
		String password = "abc";
		String message = CSVHandler.isValidPassword(password);
		assertTrue(
				"testIsValidPasswordWithNoDigit failed, this is a unvalid password!",
				message.contains("Password must contain at least one digit.<br>"));
	}

	@Test
	public void testIsValidPasswordWithNoLowerCase() {
		String password = "ABC";
		String message = CSVHandler.isValidPassword(password);
		assertTrue(
				"testIsValidPasswordWithNoLowerCase failed, this is a unvalid password!",
				message.contains("Password must contain at least one lowercase character.<br>"));
	}

	@Test
	public void testIsValidPasswordWithNoUpperCase() {
		String password = "abc";
		String message = CSVHandler.isValidPassword(password);
		assertTrue(
				"testIsValidPasswordWithNoUpperCase failed, this is a unvalid password!",
				message.contains("Password must contain at least one uppercase character.<br>"));
	}

	@Test
	public void testIsValidPasswordWithNoNonAlphaNumeric() {
		String password = "abc";
		String message = CSVHandler.isValidPassword(password);
		assertTrue(
				"testIsValidPasswordWithNoNonAlphaNumeric failed, this is a unvalid password!",
				message.contains("Password must contain at least one non-alphanumeric character.<br>"));
	}

	/**
	 * Test CRUD operation for login
	 * 
	 * @throws IOException
	 */

	@Test
	public void testIsValidCredentialWithUnvalidUsernameUnvalidPassword() throws IOException {
		String username = "ECSE";
		String password = "Test";
		boolean isValid = CSVHandler.isValidCredential(username, password);
		assertTrue(
				"testIsValidCredentialWithUnvalidUsernameUnvalidPassword failed, this is a unvalid username!",
				!isValid);
	}

	@Test
	public void testIsValidCredentialWithValidUsernameUnvalidPassword()
			throws IOException {
		String username = "Demo01";
		String password = "Test";
		boolean isValid = CSVHandler.isValidCredential(username, password);
		assertTrue(
				"testIsValidCredentialWithUnvalidPassword failed, this is a unvalid password!",
				!isValid);
	}
	
	@Test
	public void testIsValidCredentialWithUnvalidUsernameValidPassword()
			throws IOException {
		String username = "ECSE";
		String password = "Dem@Us3R01";
		boolean isValid = CSVHandler.isValidCredential(username, password);
		assertTrue(
				"testIsValidCredentialWithUnvalidPassword failed, this is a unvalid username!",
				!isValid);
	}
	
	@Test
	public void testIsValidCredentialWithValidUsernameValidPassword()
			throws IOException {
		String username = "Demo01";
		String password = "Dem@Us3R01";
		boolean isValid = CSVHandler.isValidCredential(username, password);
		assertTrue(
				"testIsValidCredentialWithUnvalidPassword failed, this is a valid username/password!",
				isValid);
	}

}
