package com.pumex.tms.usermanagement.dao;

/*
 *** @Author Reshma Manoj ***
 */

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.Role;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserRole;
import com.pumex.tms.models.UserSkills;

@Transactional
@Repository("productDao")
public class RegisterDaoImpl extends AbstractDao <Long, UserDetails> implements RegisterDaoInterface{
	
	public UserDetails userRegistration(UserDetails userdetails) throws Exception {
		// TODO Auto-generated method stub
		
		Set<UserRole> userroles = new HashSet<UserRole>();
		UserRole userrole = new UserRole();
		Role role = new Role();
		role.setId(3);
		userrole.setRole(role);
		userroles.add(userrole);
		userdetails.setRoles(userroles);
		persist(userdetails);
		return userdetails;
	}
	
	@Override
	public long isEmailExist(String email) throws Exception {
		// TODO Auto-generated method stub
		long count = (Long) getSession()
				.createQuery(
						"select count(userId) from UserDetails where email=:email")
				.setString("email", email).uniqueResult();
		return count;
	}
	

}
