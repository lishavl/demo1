package com.pumex.tms.usermanagement.service;

/*
 *** @Author Reshma Manoj ***
 */

import java.util.List;

import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserSkills;
import com.pumex.tms.usermanagement.bean.Register;

public interface RegisterServiceInterface {

	public long isEmailExist(String email)throws Exception;

	public UserDetails userRegistration(Register register, String encryptedpwd) throws Exception;

}
