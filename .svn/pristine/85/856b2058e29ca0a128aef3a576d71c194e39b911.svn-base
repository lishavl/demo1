package com.pumex.tms.usermanagement.service;

/*
 *** @Author Reshma Manoj ***
 */


import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserRole;



public interface LoginServiceInterface {

	public UserDetails getUserFromEmail(String email) throws Exception;

	int checkUserAuthentication(String email, String password) throws Exception;

	public UserRole getUserRole(long user_id) throws Exception;

	public String getPassword(String email) throws Exception;
	
	boolean checkUserExist(String email) throws Exception;

	

}
