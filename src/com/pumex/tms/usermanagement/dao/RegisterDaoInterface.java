package com.pumex.tms.usermanagement.dao;

/*
 *** @Author Reshma Manoj ***
 */

import java.util.List;

import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserSkills;

public interface RegisterDaoInterface {

	public UserDetails userRegistration(UserDetails userdetails) throws Exception;

	public long isEmailExist(String email) throws Exception;
}
