package com.pumex.tms.models;
/**
 * @Author JOSSINA JOSE.
 * @Date 20/10/2016
 */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pumex.tms.util.AppConstants;

@Entity
@Table(name = AppConstants.TABLE_NAMES.DEPT_DETAILS)
public class DepartmentDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6114244492654968694L;
	
	@Id
	@GeneratedValue
	@Column(name="dept_id")
	private long id;
	
	@Column(name = "department", length = 1000)
	private String department;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public DepartmentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepartmentDetails(long id) {
		super();
		this.id = id;
	}
	
	

}
