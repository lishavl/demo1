package com.pumex.tms.trainingmanagement.dao;

/**
 * @Author JOSSINA JOSE.
 * @Date 20/10/2016
 */
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.DepartmentDetails;

@Transactional
@Repository("departmentDao")
public class DepartmentDaoImp extends AbstractDao<Long, DepartmentDetails>
		implements DepartmentDao {

	@Override
	public void saveDepartmentDetails(DepartmentDetails departmentdetails)
			throws Exception {
		saveOrUpdate(departmentdetails);
		// TODO Auto-generated method stub

	}

	@Override
	public List getAllDepartmentDetails() throws Exception {
		// TODO Auto-generated method stub
		return getSession().createQuery("from DepartmentDetails order by department").list();

	}

	@Override
	public DepartmentDetails departmentDetailsForEdit(long id) throws Exception {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	public void deleteDepartment(long id) throws Exception {
		// TODO Auto-generated method stub
		delete(getByKey(id));
	}

	@Override
	public List fetchAllAttendeeNamesByDeptId(long deptid) throws Exception {
		// TODO Auto-generated method stub
		return getSession().createQuery("select new com.pumex.tms.usermanagement.bean.Register(a.userId,concat(a.firstName,' ',a.lastName) as attendeename) from UserDetails a join a.roles b where b.role.id = 3 and  a.department.id=:deptid").setLong("deptid", deptid).list();
	}

	@Override
	public List fetchAttendeeListByDeptIdForAll() throws Exception {
		// TODO Auto-generated method stub
		return getSession().createQuery("select new com.pumex.tms.usermanagement.bean.Register(a.userId,concat(a.firstName,' ',a.lastName) as attendeename) from UserDetails a join a.roles b where b.role.id = 3").list();
	}

}
