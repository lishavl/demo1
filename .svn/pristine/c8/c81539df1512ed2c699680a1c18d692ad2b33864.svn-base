package com.pumex.tms.usermanagement.dao;

/*
 *** @Author Reshma Manoj ***
 */


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserRole;
import com.pumex.tms.util.AppConstants;

@Transactional
@Repository("loginDao")
public class LoginDaoImpl extends AbstractDao<Long, UserDetails> implements
		LoginDaoInterface {

	public int checkUserAuthentication(String email, String password)
			throws Exception {

		long count = (Long) getSession()
				.createQuery(
						"select count(userId) from UserDetails where email=:email and password=:password")
				.setString("email", email).setString("password", password)
				.uniqueResult();

		if (count == 1) {

			int status = (Integer) getSession()
					.createQuery(
							"select status from UserDetails where email=:email and password=:password")
					.setString("email", email).setString("password", password)
					.uniqueResult();

			if (status == 1)
				return AppConstants.USER_STATUSES.VALID;
			else if (status == 0)
				return AppConstants.USER_STATUSES.BLOCKED;
			else
				return AppConstants.USER_STATUSES.INVALID;
		} else
			return AppConstants.USER_STATUSES.INVALID;
	}

	public UserDetails getUserFromEmail(String email) throws Exception {
		UserDetails user = null;
		try {
			user = (UserDetails) getSession()
					.createQuery("from UserDetails where email=:email")
					.setString("email", email).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		return user;
	}

	public UserRole getUserRole(long user_id) throws Exception {
		UserRole role = null;
		try {
			role = (UserRole) getSession()
					.createQuery("from UserRole where user_id=:user_id")
					.setLong("user_id", user_id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		return role;
	}
	
	@Override
	public String getPassword(String email) throws Exception {
		// TODO Auto-generated method stub
		String pwd = (String) getSession()
				.createQuery(
						"select password from UserDetails where email=:email")
				.setString("email", email).uniqueResult();
		return pwd;
	}
	
	@Override
	public boolean checkUserExist(String email) throws Exception {

		long count = (long) getSession()
				.createQuery(
						"select count(userId) from UserDetails where email=:email")
				.setString("email", email).uniqueResult();
		if (count > 0)
			return true;

		return false;
	}
	
}
