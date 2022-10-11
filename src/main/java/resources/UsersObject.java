package resources;

import pojo.Users;

public class UsersObject {
	
	public static Users setUsers(String name, String job)
	{
		Users user= new Users();
		user.setName(name);
		user.setJob(job);
		
		return user;
	}

}
