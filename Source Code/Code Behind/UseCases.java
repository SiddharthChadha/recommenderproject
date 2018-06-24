
public class UseCases {
	
	//Add a new user to an exiting project
	public static boolean AddUser(ProjectProfile project, String userName, int mobile, Address address, String[] interests) {
		//Generating a new UNIQUE id
		int uniqueId = SharedVariables.uniqueId;
		SharedVariables.uniqueId++;
		
		//Making a new user object
		UserProfile user = new UserProfile(name, uniqueId, mobile, address, interests);
		
		//Returns true if the user has been added to the project and false otherwise
		return ProjectProfile.addUserToProject(user);
	}


}
