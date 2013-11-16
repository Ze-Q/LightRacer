package unitTests;

import org.junit.Test;

import accounts.CreateAccount;

public class CreateAccountTests {
	@Test
	public void testCreateAccount() {
		CreateAccount.addUser("Ze", "1234567890");
	}
}
