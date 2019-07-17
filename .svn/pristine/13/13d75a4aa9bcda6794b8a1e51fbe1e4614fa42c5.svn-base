package com.pumex.tms.usermanagement.service;

import java.util.List;

import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserSkills;
import com.pumex.tms.usermanagement.bean.Register;

public interface UpdateProfileServiceInterface {

	Register editUser(long userId) throws Exception;

	void updateUser(Register register, long id) throws Exception;

	List<UserSkills> getSkills(String text) throws Exception;

	String getPassword(long userId) throws Exception;

	void ChangePassword(long userId, String newpassword) throws Exception;

	UserDetails getUserDetails(long id) throws Exception;

}
