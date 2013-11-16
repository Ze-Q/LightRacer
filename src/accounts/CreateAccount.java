package accounts;
import crud.CSVHandler;

public class CreateAccount {
	//TODO generate proper error message?
	public static boolean addUser(String username, String password){
		boolean succesful = false;
		boolean isValid = CSVHandler.validateCredential(username,password);
		if(isValid) {
			succesful = true;
			CSVHandler.addUser(username, password);
		}
		return succesful;
	}
}