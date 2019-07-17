package com.pumex.tms.usermanagement.dao;

/*
 *** @Author Reshma Manoj ***
 */


import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserRole;

public interface LoginDaoInterface {

	int checkUserAuthentication(String email, String password) throws Exception;

	UserDetails getUserFromEmail(String email) throws Exception;

	UserRole getUserRole(long user_id) throws Exception;

	String getPassword(String email)throws Exception;
	
	boolean checkUserExist(String email) throws Exception;

}
