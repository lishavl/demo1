package com.pumex.tms.company.service;

/**
 * @Author JOSSINA JOSE.
 *
 */
import com.pumex.tms.company.bean.CompanyBean;

public interface CompanyService {

	void saveCompanyDetails(CompanyBean companydetails) throws Exception;

	CompanyBean getCompanyDetails() throws Exception;

}
