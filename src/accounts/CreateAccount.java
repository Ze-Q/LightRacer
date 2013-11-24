
package accounts;
import crud.CSVHandler;
/**
 * This class handles create account.
 * @author Cheng Gong
 * @version 1.0
 */
public class CreateAccount {
	/**
	 * Add the user to userCSV
	 * @param username					the username entered by the user who is attempting to create an account
	 * @param password					the password entered by the user who is attempting to create an account
	 * @return success					whether account creation is successful.
	 */
	public static boolean addUser(String username, String password){
		boolean isValid = CSVHandler.validateCredential(username,password);
		if(isValid) {
			CSVHandler.addUser(username, password);
			Statistics statistics = Statistics.getInstance();
			statistics.addNewUser(new User(username));
			statistics.updateStatsFile();
		}
		return isValid;
	}
}
