package com.pumex.tms.usermanagement.service;

/*
 *** @Author Reshma Manoj ***
 */


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserRole;
import com.pumex.tms.usermanagement.dao.LoginDaoInterface;


@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginServiceInterface {
	
	@Autowired
	LoginDaoInterface loginDaoInterface;
	
	public int checkUserAuthentication(String email, String password) throws Exception {
		return loginDaoInterface.checkUserAuthentication(email,password);
	}

	public UserDetails getUserFromEmail(String email) throws Exception {
		return loginDaoInterface.getUserFromEmail(email);
	}
	
	public UserRole getUserRole(long user_id) throws Exception {
		return loginDaoInterface.getUserRole(user_id);
	}

	@Override
	public String getPassword(String email) throws Exception {
		// TODO Auto-generated method stub
		return loginDaoInterface.getPassword(email);
	}
	
	@Override
	public boolean checkUserExist(String email) throws Exception {
		// TODO Auto-generated method stub
		return loginDaoInterface.checkUserExist(email);
	}

}
