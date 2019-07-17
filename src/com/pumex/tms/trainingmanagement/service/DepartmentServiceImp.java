package com.pumex.tms.trainingmanagement.service;
/**
 * @Author JOSSINA JOSE.
 * @Date 20/10/2016
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.models.DepartmentDetails;
import com.pumex.tms.trainingmanagement.dao.DepartmentDao;

@Service("departmentservice")
public class DepartmentServiceImp implements DepartmentService{
	
	@Autowired
	DepartmentDao dao;

	@Override
	public void saveDepartmentDetails(DepartmentDetails departmentdetails)
			throws Exception {
		
		dao.saveDepartmentDetails(departmentdetails);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List fetchAllDepartmentDetails() throws Exception {
		// TODO Auto-generated method stub
		return dao.getAllDepartmentDetails();
	}

	@Override
	public DepartmentDetails departmentDetailsForEdit(long id) throws Exception {
		// TODO Auto-generated method stub
		return dao.departmentDetailsForEdit(id);
	}

	@Override
	public void deleteDepartment(long id) throws Exception {
		dao.deleteDepartment(id);
		// TODO Auto-generated method stub
	}

	@Override
	public List fetchAttendeeListByDeptId(long deptid) throws Exception {
		// TODO Auto-generated method stub
		return dao.fetchAllAttendeeNamesByDeptId(deptid);
	}

	@Override
	public List fetchAttendeeListByDeptIdForAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.fetchAttendeeListByDeptIdForAll();
	}

	
	
	
	
	

}
