package resources;

public enum UserResources {
	
	AddUserAPI("/api/users"),
	GetUserAPI("/api/users"),
	UpdateUserAPI("/api/users/{id}"),
	DeleteUserAPI("/api/users/{id}");
	private String apiresource;
	
	
	private UserResources(String resource) {
		this.apiresource = resource;
	}

	public String getResource()
	{
		return apiresource;
	}

}
