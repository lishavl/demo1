package com.pumex.tms.usermanagement.dao;

import java.util.List;
import java.util.Set;

import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserRole;
import com.pumex.tms.models.UserSkills;
import com.pumex.tms.usermanagement.bean.Register;
import com.pumex.tms.usermanagement.bean.UserBean;

/**
 * @Author JINSHAD P.T.
 * @Date 12/01/2016
 */

public interface UserManagementDao {

	public List<UserDetails> getAllUsers(long id) throws Exception ;

	public UserDetails editUser(long id) throws Exception;

	public void updateUser(UserDetails user, String[] skill) throws Exception;

	public void DeleteUser(long id) throws Exception;

	public void BlockUser(long id, String reason) throws Exception;

	public List<UserSkills> getSkills(String text);

	public long isEmailExist(String email) throws Exception;

	public long userRegistration(UserDetails userdetails, String[] skill, Set<UserRole> userroles) throws Exception;

	public List<String> getSkills(long id) throws Exception;

	public String getdob(long id) throws Exception;

	public void deleteSkill(long id) throws Exception;
	
	public void activateUser(long userId) throws Exception;

	public void unBlockUser(long id) throws Exception;
	
	public List<UserBean> getAllUserNamesByRoleId(long roleId) throws Exception;

}
