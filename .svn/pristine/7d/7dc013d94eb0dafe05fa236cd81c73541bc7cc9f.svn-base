package com.pumex.tms.usermanagement.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserSkills;
import com.pumex.tms.usermanagement.bean.Register;

@Transactional
@Repository("updateProfileDao")
public class UpdateProfileDaoImpl extends AbstractDao<Long, UserDetails> implements UpdateProfileDaoInterface {

	@Override
	public UserDetails editUser(long userId) throws Exception {
		// TODO Auto-generated method stub		
		return (UserDetails) getSession()
				.createQuery(" from UserDetails where userId = :id")
				.setLong("id", userId).uniqueResult();
	}

	@Override
	public String getdob(long userId) throws Exception {
		// TODO Auto-generated method stub
		String date = "";
		Date dob = (Date) getSession()
				.createQuery("select dob from UserDetails where userId = :id")
				.setLong("id", userId).uniqueResult();
		
		if(null != dob){
		 date = dob.toString();
		}
		
		return date;
	}

	@Override
	public List<String> getSkills(long userId) throws Exception {
		// TODO Auto-generated method stub
		return getSession()
				.createQuery(
						"select distinct skill from UserSkills where user.userId = :id")
				.setLong("id", userId).list();
	}

	@Override
	public UserDetails getUserById(long id) throws Exception {
		// TODO Auto-generated method stub
		return (UserDetails) getSession()
				.createQuery(" from UserDetails where userId = :id")
				.setLong("id", id).uniqueResult();
	}

	@Override
	public void updateUser(UserDetails userdetails, String[] skill)
			throws Exception {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < skill.length; i++) {
			UserSkills userskills = new UserSkills();
			userskills.setUser(userdetails);
			userskills.setSkill(skill[i]);
			getSession().save(userskills);
		}

		update(userdetails);
		
	}

	@Override
	public void deleteSkill(long id) throws Exception {
		// TODO Auto-generated method stub
		
		getSession().createQuery(" delete UserSkills where user.userId = :id")
		.setLong("id", id).executeUpdate();
		
	}

	@Override
	public List<UserSkills> getSkills(String text) throws Exception {
		return getSession().createQuery(
				"select distinct skill from UserSkills where skill like '%"
						+ text + "%'").list();
	}

	@Override
	public String getPassword(long userId) throws Exception {
		// TODO Auto-generated method stub
		
		String password =(String) getSession()
				.createQuery(" select password from UserDetails where userId = :id")
				.setLong("id", userId).uniqueResult();
		return password;
	}

	@Override
	public void ChangePassword(long userId,String newpassword) throws Exception {
		// TODO Auto-generated method stub
		getSession().createQuery(" update UserDetails set password = :newpassword where userId = :id")
		.setLong("id", userId).setString("newpassword", newpassword).executeUpdate();
		
	}

	@Override
	public UserDetails getUserDetails(long id) throws Exception {
		// TODO Auto-generated method stub
		return (UserDetails) getSession().createQuery("from UserDetails where userId = :id")
				.setLong("id", id).uniqueResult();
	}


}
