package com.pumex.tms.models;
/**
 * @Author JOSSINA JOSE.
 *12/10/2016
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pumex.tms.util.AppConstants;

@Entity
@Table(name = AppConstants.TABLE_NAMES.COMPANY_DETAILS)
public class CompanyDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4191915574763528345L;
	
	@Id
	@GeneratedValue
	@Column(name = "company_id", columnDefinition = "bigint(15)")
	private long id;
	
	@Column(name = "company_name", length = 50)
	private String companyname;
	
	@Column(name = "address", length = 100)
	private String address;
	
	@Column(name = "contact_person", length = 50)
	private String contactperson;
	
	@Column(name = "mobile", length = 15)
	private String mobile;
	
	@Column(name = "phn2", length = 15)
	private String phn2;
	

	@Column(name = "email_id", length = 50)
	private String email;
	

	@Column(name = "website", length = 50)
	private String website;
	
	@Column(name = "logo_path", columnDefinition = "varchar(150)")
	private String logo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhn2() {
		return phn2;
	}

	public void setPhn2(String phn2) {
		this.phn2 = phn2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CompanyDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyDetails(long id) {
		super();
		this.id = id;
	}
    
	

}
