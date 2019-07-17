/**
 * @Author Jinshad P.T.
 * @Date 26/08/2016
 */

package com.pumex.tms.usermanagement.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.Options;
import com.pumex.tms.models.Role;
import com.pumex.tms.models.RoleOptions;

@Transactional
@Repository("userAccessDao")
public class UserAccessDaoImp extends AbstractDao<Long, RoleOptions> implements
		UserAccessDao {

	@Override
	public Object[] loadOptions(long roleId) throws Exception {

		Object[] lst = new Object[2];

		lst[1] = getSession()
				.createQuery(
						"select new com.pumex.tms.usermanagement.bean.UserAccessBean(option.id,option.name,option.menuName,1)"
								+ " from RoleOptions where role.id=:rl")
				.setLong("rl", roleId).list();

		lst[0] = getSession().createQuery(
				"select new com.pumex.tms.usermanagement.bean.UserAccessBean(id,name,menuName)"
						+ " from Options").list();

		return lst;

	}

	@Override
	public void saveUserAccess(Long[] options, long roleId) throws Exception {

		getSession().createQuery("delete from RoleOptions where role.id=:rid")
				.setLong("rid", roleId).executeUpdate();

		for (Long long1 : options) {
			persist(new RoleOptions(new Role(roleId), new Options(long1)));
		}

	}

	@Override
	public boolean isValidAccess(String action, String roleIDs)
			throws Exception {

		long count = (long) getSession()
				.createQuery(
						"select count(id) from RoleOptions where option.action=:act and  role.id in "
								+ roleIDs).setString("act", action)
				.uniqueResult();

		if (count > 0)
			return true;

		return false;

	}

	@Override
	public List<Options> getAvailableOptions(String roleIDs) throws Exception {

		return getSession()
				.createQuery(
						"select distinct new com.pumex.tms.models.Options(option.menuName,option.action,option.menuGroup)"
								+ " from RoleOptions where role.id in "
								+ roleIDs
								+ " and option.status=1 order by option.order asc")
				.list();

	}

}
