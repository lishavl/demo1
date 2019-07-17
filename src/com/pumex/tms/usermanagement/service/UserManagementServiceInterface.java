package com.pumex.tms.usermanagement.service;

import java.util.List;
import java.util.Set;

import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserRole;
import com.pumex.tms.models.UserSkills;
import com.pumex.tms.usermanagement.bean.Register;
import com.pumex.tms.usermanagement.bean.UserBean;

public interface UserManagementServiceInterface {

	public List<UserDetails> getAllUsers(long id) throws Exception;

	public UserDetails getUserById(long id) throws Exception;

	public void updateUser(UserDetails userdetails, String[] skill) throws Exception;

	public void deleteUser(long id) throws Exception;

	public Register editUser(long id) throws Exception;

	public void blockUser(long id, String reason) throws Exception;

	public List<UserSkills> getSkills(String text) throws Exception;

	public long isEmailExist(String email) throws Exception;

	public long userRegistration(Register register, String[] skill, Set<UserRole> userroles) throws Exception;

	public List<String> getSkills(long id) throws Exception;
	
	public void activateUser(long userId) throws Exception;

	public void unblockUser(long id) throws Exception ;
	
	public List<UserBean> getAllUserNamesByRoleId(long roleId) throws Exception;


}
