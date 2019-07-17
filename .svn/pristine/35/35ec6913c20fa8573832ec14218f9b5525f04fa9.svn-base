package com.pumex.tms.usermanagement.dao;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.Role;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserRole;
import com.pumex.tms.models.UserSkills;
import com.pumex.tms.usermanagement.bean.Register;
import com.pumex.tms.usermanagement.bean.UserBean;

/**
 * @Author JINSHAD P.T.
 * @Date 12/01/2016
 */
@Transactional
@Repository("userDao")
public class UserManagementDaoImpl extends AbstractDao<Long, UserDetails>
		implements UserManagementDao {

	public List<UserDetails> getAllUsers(long id) throws Exception {
		// TODO Auto-generated method stub
		List list = getSession()
				.createQuery(
						"from UserDetails where status != -1 and userId != :id order by firstName asc,lastName  asc")
				.setLong("id", id).list();
		return list;
	}

	public UserDetails editUser(long id) throws Exception {

		return (UserDetails) getSession()
				.createQuery(" from UserDetails where userId = :id")
				.setLong("id", id).uniqueResult();
		// TODO Auto-generated method stub
	}

	public void updateUser(UserDetails User, String[] skill) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < skill.length; i++) {
			UserSkills userskills = new UserSkills();
			userskills.setUser(User);
			userskills.setSkill(skill[i]);
			getSession().save(userskills);
		}

		update(User);
	}

	public void DeleteUser(long id) throws Exception {
		// TODO Auto-generated method stub
		getSession().createQuery(" delete UserSkills where user.userId = :id")
				.setLong("id", id).executeUpdate();

		/*
		 * getSession().createQuery(" delete UserRole where id = :id")
		 * .setLong("id", id).executeUpdate();
		 */

		getSession()
				.createQuery(
						" update UserDetails set status = -1 where userId = :id")
				.setLong("id", id).executeUpdate();

	}

	@Override
	public void BlockUser(long id, String reason) throws Exception {
		// TODO Auto-generated method stub
		getSession()
				.createQuery(
						" update UserDetails set status = 0,blockingReason = :reason where userId = :id")
				.setLong("id", id).setString("reason", reason).executeUpdate();
	}

	@Override
	public List<UserSkills> getSkills(String text) {
		// TODO Auto-generated method stub
		return getSession().createQuery(
				"select distinct skill from UserSkills where skill like '%"
						+ text + "%'").list();
	}

	@Override
	public long isEmailExist(String email) throws Exception {
		// TODO Auto-generated method stub
		long count = (Long) getSession()
				.createQuery(
						"select count(userId) from UserDetails where email=:email and status =1")
				.setString("email", email).uniqueResult();

		System.out.println("count---->" + count);

		return count;
	}

	@Override
	public long userRegistration(UserDetails userdetails, String[] skill,
			Set<UserRole> userroles) throws Exception {
		// TODO Auto-generated method stub
		userdetails.setRoles(userroles);
		persist(userdetails);
		long userId = userdetails.getUserId();

		for (int i = 0; i < skill.length; i++) {

			UserSkills userskills = new UserSkills();
			userskills.setUser(userdetails);
			userskills.setSkill(skill[i]);
			getSession().save(userskills);
		}

		return userId;

	}

	@Override
	public List<String> getSkills(long id) throws Exception {
		// TODO Auto-generated method stub
		return getSession()
				.createQuery(
						"select distinct skill from UserSkills where user.userId = :id")
				.setLong("id", id).list();
	}

	@Override
	public String getdob(long id) throws Exception {
		// TODO Auto-generated method stub
		String date = "";
		Date dob = (Date) getSession()
				.createQuery("select dob from UserDetails where userId = :id")
				.setLong("id", id).uniqueResult();
		if (null != dob) {
			date = dob.toString();
		}

		return date;
	}

	@Override
	public void deleteSkill(long id) throws Exception {
		// TODO Auto-generated method stub
		getSession().createQuery(" delete UserSkills where user.userId = :id")
				.setLong("id", id).executeUpdate();
	}

	@Override
	public void activateUser(long userId) throws Exception {
		// TODO Auto-generated method stub
		getSession()
				.createQuery(
						" update UserDetails set status = 1 where userId = :id")
				.setLong("id", userId).executeUpdate();
	}

	@Override
	public void unBlockUser(long id) throws Exception {
		// TODO Auto-generated method stub

		getSession()
				.createQuery(
						" update UserDetails set status = 1,blockingReason = 'NULL' where userId = :id")
				.setLong("id", id).executeUpdate();

	}

	@Override
	public List<UserBean> getAllUserNamesByRoleId(long roleId) throws Exception {
		// TODO Auto-generated method stub

		List lst = getSession()
				.createQuery(
						"select new com.pumex.tms.usermanagement.bean.UserBean(a.userId,a.firstName,a.lastName,a.address,a.email) "
								+ "from UserDetails a join a.roles b where b.role.id =:rolId and a.status = 1 "
								+ "group by a.userId order by a.firstName,a.lastName asc")
				.setLong("rolId", roleId).list();

		return lst;

	}

}
