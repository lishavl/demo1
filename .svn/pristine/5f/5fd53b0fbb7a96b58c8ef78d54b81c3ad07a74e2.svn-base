package com.pumex.tms.company.service;

/**
 * @Author JOSSINA JOSE.
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.company.bean.CompanyBean;
import com.pumex.tms.company.dao.CompanyDao;
import com.pumex.tms.models.CompanyDetails;
import com.pumex.tms.models.ForumDetails;

@Service("companyservice")
public class CompanyServiceImp implements CompanyService {
	
	@Autowired
	CompanyDao dao;
	
	/*
	 * Method to update or save company details
	 * 
	 * @return status as response
	 */
	@Override
	public void saveCompanyDetails(CompanyBean companydetails) throws Exception {
		// TODO Auto-generated method stub
		CompanyDetails details;
		if(companydetails.getId()!=0){
			details=dao.getById(companydetails.getId());
		}
		else
			details=new CompanyDetails();
			
		details.setCompanyname(companydetails.getCompanyname());
		details.setAddress(companydetails.getAddress());
		details.setContactperson(companydetails.getContactperson());
		details.setMobile(companydetails.getMobile());
		details.setPhn2(companydetails.getPhn2());
		details.setEmail(companydetails.getEmail());
		details.setWebsite(companydetails.getWebsite());
		details.setLogo(companydetails.getLogo());
		dao.saveCompanyDetails(details);
	}
	
	/*
	 * Method to get company details for edit
	 * 
	 * @return CompanyBean
	 */
	@Override
	public CompanyBean getCompanyDetails() throws Exception {
		// TODO Auto-generated method stub
		return dao.getCompanyDetails();
	}

}
