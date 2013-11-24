package accounts;
import crud.CSVHandler;

public class CreateAccount {
	
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
