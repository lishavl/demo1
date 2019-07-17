package com.pumex.tms.usermanagement.service;

/*
 *** @Author Reshma Manoj ***
 */

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserSkills;
import com.pumex.tms.usermanagement.bean.Register;
import com.pumex.tms.usermanagement.dao.RegisterDaoInterface;

@Service("registerservice")
public class RegisterServiceImpl implements RegisterServiceInterface {

	@Autowired
	RegisterDaoInterface registerDaoInterface;

	public UserDetails userRegistration(Register register, String encryptedpwd)
			throws Exception {

		UserDetails userdetails = new UserDetails();

		if (register.getLastName() == null) {
			userdetails.setLastName("");
		}

		else {
			userdetails.setLastName(register.getLastName());
		}

		userdetails.setFirstName(register.getFirstName());
		userdetails.setEmail(register.getEmail());
		userdetails.setPassword(encryptedpwd);
		userdetails.setStatus(0);
		userdetails = registerDaoInterface.userRegistration(userdetails);
		return userdetails;
	}

	@Override
	public long isEmailExist(String email) throws Exception {
		// TODO Auto-generated method stub
		return registerDaoInterface.isEmailExist(email);
	}

}
