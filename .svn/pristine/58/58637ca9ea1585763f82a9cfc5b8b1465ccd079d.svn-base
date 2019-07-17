package com.pumex.tms.usermanagement.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.configurations.Encryption;
import com.pumex.tms.models.DepartmentDetails;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserRole;
import com.pumex.tms.models.UserSkills;
import com.pumex.tms.usermanagement.bean.Register;
import com.pumex.tms.usermanagement.bean.UserBean;
import com.pumex.tms.usermanagement.dao.UserManagementDao;

@Service("userservice")
public class UserManagementServiceImpl implements
		UserManagementServiceInterface {

	@Autowired
	UserManagementDao userManagementDao;

	public List<UserDetails> getAllUsers(long id) throws Exception {
		// TODO Auto-generated method stub
		return userManagementDao.getAllUsers(id);
	}

	public UserDetails getUserById(long id) throws Exception {
		UserDetails user = userManagementDao.editUser(id);
		return user;
	}

	public void updateUser(UserDetails user, String[] skill) throws Exception {
		long id = user.getUserId();
		userManagementDao.deleteSkill(id);
		userManagementDao.updateUser(user, skill);
		// TODO Auto-generated method stub

	}

	public void deleteUser(long id) throws Exception {
		// TODO Auto-generated method stub
		userManagementDao.DeleteUser(id);
	}

	public Register editUser(long id) throws Exception {
		UserDetails userDetails = userManagementDao.editUser(id);

		Register register = new Register();
		register.setUser_id(userDetails.getUserId());
		register.setFirstName(userDetails.getFirstName());
		register.setLastName(userDetails.getLastName());
		register.setAddress(userDetails.getAddress());
		register.setPhoneNumber(userDetails.getMobile());
		register.setEmail(userDetails.getEmail());
		String pwd = userDetails.getPassword();
		String password = Encryption.decodeAndDecrypt(pwd);
		register.setPassword(password);
		register.setConfirmPassword(password);
		register.setDob(userManagementDao.getdob(id));
		register.setSkills(userManagementDao.getSkills(id));
		register.setGender(userDetails.getGender());
		register.setRoles(userDetails.getRoles());
		if(userDetails.getDepartment()!=null){
			register.setDepartment(userDetails.getDepartment().getId());
			register.setDepartmentname(userDetails.getDepartment().getDepartment());
		}

		Set<UserRole> userroles = new HashSet<UserRole>();
		userroles = userDetails.getRoles();
		Iterator iterator = (Iterator) userroles.iterator();
		// check values
		while (iterator.hasNext()) {
			// System.out.println("Value: "+iterator.next());
			UserRole role = (UserRole) iterator.next();
			String role1 = role.getRole().getUserRole();
			if (role1.equals("Trainer")) {
				register.setRole1(2);
			}
			if (role1.equals("Attendee")) {
				register.setRole2(3);
			}
		}
		// TODO Auto-generated method stub
		return register;
	}

	@Override
	public void blockUser(long id, String reason) throws Exception {
		userManagementDao.BlockUser(id, reason);
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserSkills> getSkills(String text) throws Exception {
		// TODO Auto-generated method stub
		return userManagementDao.getSkills(text);
	}

	@Override
	public long isEmailExist(String email) throws Exception {
		// TODO Auto-generated method stub
		return userManagementDao.isEmailExist(email);
	}

	@Override
	public long userRegistration(Register register, String[] skill,
			Set<UserRole> userroles) throws Exception {
		// TODO Auto-generated method stub
		UserDetails userdetails = new UserDetails();

		if (register.getLastName() == null) {
			userdetails.setLastName("");
		}

		else {
			userdetails.setLastName(register.getLastName());

		}
		userdetails.setAddress(register.getAddress());

		String dob = register.getDob();

		SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date date = dt.parse(dob);
		java.sql.Date sqlDate = new Date(date.getTime());
		userdetails.setDob(sqlDate);
		userdetails.setEmail(register.getEmail());
		userdetails.setFirstName(register.getFirstName());
		userdetails.setGender(register.getGender());
		userdetails.setMobile(register.getPhoneNumber());
		userdetails.setDepartment(new DepartmentDetails(register.getDepartment()));
		String pwd = register.getPassword();
		String encryptedpwd = Encryption.encryptAndEncode(pwd);
		userdetails.setPassword(encryptedpwd);
		userdetails.setStatus(1);
		return userManagementDao
				.userRegistration(userdetails, skill, userroles);
	}

	@Override
	public List<String> getSkills(long id) throws Exception {
		// TODO Auto-generated method stub
		return userManagementDao.getSkills(id);
	}

	@Override
	public void activateUser(long userId) throws Exception {
		userManagementDao.activateUser(userId);
		// TODO Auto-generated method stub

	}

	@Override
	public void unblockUser(long id) throws Exception {
		// TODO Auto-generated method stub
		
		userManagementDao.unBlockUser(id);

	}

	@Override
	public List<UserBean> getAllUserNamesByRoleId(long roleId)
			throws Exception {
		// TODO Auto-generated method stub
		return userManagementDao.getAllUserNamesByRoleId(roleId);
	}
}
