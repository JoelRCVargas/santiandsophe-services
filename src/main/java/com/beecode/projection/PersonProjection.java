package com.beecode.projection;

public interface PersonProjection {
	
	Long getId();
	String getFirstName();
	String getLastName();
	String getAddress();
	String getCity();
	String getPhone();
	int getAge();
	UserProjection getUser();
	
	public interface UserProjection{
		Long getId();
		String getEmail();
	}
}
