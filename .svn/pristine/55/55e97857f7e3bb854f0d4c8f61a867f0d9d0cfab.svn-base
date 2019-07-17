package com.pumex.tms.usermanagement.service;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.models.DepartmentDetails;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserRole;
import com.pumex.tms.models.UserSkills;
import com.pumex.tms.usermanagement.bean.Register;
import com.pumex.tms.usermanagement.dao.UpdateProfileDaoInterface;

@Service("updateprofileservice")
public class UpdateProfileServiceImpl implements UpdateProfileServiceInterface {

	@Autowired
	UpdateProfileDaoInterface UpdateProfileDaoInterface;

	@Override
	public Register editUser(long userId) throws Exception {
		// TODO Auto-generated method stub
		UserDetails userDetails = UpdateProfileDaoInterface.editUser(userId);

		Register register = new Register();

		register.setUser_id(userDetails.getUserId());
		register.setFirstName(userDetails.getFirstName());
		register.setLastName(userDetails.getLastName());
		register.setAddress(userDetails.getAddress());
		register.setPhoneNumber(userDetails.getMobile());
		register.setEmail(userDetails.getEmail());
		register.setPassword(userDetails.getPassword());
		register.setConfirmPassword(userDetails.getPassword());

		register.setDob(UpdateProfileDaoInterface.getdob(userId));

		register.setSkills(UpdateProfileDaoInterface.getSkills(userId));

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
			System.out.println("role--->" + role);
			System.out.println("id--->" + role.getId());
			System.out.println("Roles--->" + role.getRole().getUserRole());
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
	public void updateUser(Register register, long id) throws Exception {

		// TODO Auto-generated method stub

		UserDetails userdetails = UpdateProfileDaoInterface.getUserById(id);

		if (register.getLastName() == null) {
			userdetails.setLastName("");
		} else {
			userdetails.setLastName(register.getLastName());
		}  
		
		
			userdetails.setFirstName(register.getFirstName());			
			userdetails.setEmail(register.getEmail());
			userdetails.setMobile(register.getPhoneNumber());
			userdetails.setAddress(register.getAddress());
			userdetails.setGender(register.getGender());
			
			DepartmentDetails department =new DepartmentDetails();
			department.setId(register.getDepartment());
			userdetails.setDepartment(department);
			
			java.sql.Date sqlDate = Date.valueOf(register.getDob().split("T")[0]);
			userdetails.setDob(sqlDate);
	

		String[] skill = register.getSkillsets();
		UpdateProfileDaoInterface.deleteSkill(id);
		UpdateProfileDaoInterface.updateUser(userdetails, skill);

	}

	@Override
	public List<UserSkills> getSkills(String text) throws Exception {
		// TODO Auto-generated method stub
		return UpdateProfileDaoInterface.getSkills(text);
	}

	@Override
	public String getPassword(long userId) throws Exception {
		// TODO Auto-generated method stub
		return UpdateProfileDaoInterface.getPassword(userId);
	}

	@Override
	public void ChangePassword(long userId, String newpassword)
			throws Exception {
		// TODO Auto-generated method stub
		UpdateProfileDaoInterface.ChangePassword(userId, newpassword);
	}

	@Override
	public UserDetails getUserDetails(long id) throws Exception {
		// TODO Auto-generated method stub
		return UpdateProfileDaoInterface.getUserDetails(id);
	}

}
