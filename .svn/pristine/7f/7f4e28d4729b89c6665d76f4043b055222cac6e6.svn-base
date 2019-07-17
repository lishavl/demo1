package com.pumex.tms.usermanagement.dao;

import java.util.List;

import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserSkills;
import com.pumex.tms.usermanagement.bean.Register;

public interface UpdateProfileDaoInterface {

	UserDetails editUser(long userId) throws Exception;

	String getdob(long userId) throws Exception;

	List<String> getSkills(long userId) throws Exception;

	UserDetails getUserById(long id) throws Exception;

	void updateUser(UserDetails userdetails, String[] skill) throws Exception;

	void deleteSkill(long id) throws Exception;

	List<UserSkills> getSkills(String text) throws Exception;

	String getPassword(long userId) throws Exception;

	void ChangePassword(long userId, String newpassword) throws Exception;

	UserDetails getUserDetails(long id) throws Exception;

}
