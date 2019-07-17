/**
 * @Author Jinshad P.T.
 * @Date 26/08/2016
 */

package com.pumex.tms.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.models.Options;
import com.pumex.tms.usermanagement.dao.UserAccessDao;

@Service("userAccessService")
public class UserAccessServiceImpl implements UserAccessService {

	@Autowired
	UserAccessDao userAccessDao;

	@Override
	public Object[] loadOptions(long roleId) throws Exception {

		return userAccessDao.loadOptions(roleId);

	}

	@Override
	public void saveUserAccess(Long[] options, long roleId) throws Exception {

		userAccessDao.saveUserAccess(options, roleId);

	}

	@Override
	public boolean isValidAccess(String action, String roleIDs)
			throws Exception {

		return userAccessDao.isValidAccess(action, roleIDs);

	}

	/*
	 * Method to get menu for role
	 */
	@Override
	public List<Options> getAvailableOptions(String roleIDs) throws Exception {

		return userAccessDao.getAvailableOptions(roleIDs);

	}

}
