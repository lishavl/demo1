package com.pumex.tms.company.dao;

/**
 * @Author JOSSINA JOSE.
 *
 */
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.company.bean.CompanyBean;
import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.CompanyDetails;

@Transactional
@Repository
public class CompanyDaoImp  extends AbstractDao<Long, CompanyDetails> implements
CompanyDao {
	
	/*
	 * Method to update or save company details
	 * 
	 * @return status as response
	 */
	@Override
	public void saveCompanyDetails(CompanyDetails details) throws Exception {
		// TODO Auto-generated method stub
		
		saveOrUpdate(details);
	}

	@Override
	public CompanyBean getCompanyDetails() throws Exception {
		// TODO Auto-generated method stub
		return (CompanyBean) getSession().createQuery("select new com.pumex.tms.company.bean.CompanyBean(id,companyname,address,contactperson,mobile,phn2,email,website,logo)from CompanyDetails").uniqueResult();
	}
	
	
	
	/*
	 * Method to get company details for edit
	 * 
	 * @return CompanyBean
	 */
	@Override
	public CompanyDetails getById(long id) throws Exception {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

}
