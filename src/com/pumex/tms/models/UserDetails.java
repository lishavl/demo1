package com.pumex.tms.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pumex.tms.util.AppConstants;

/**
 * @Author JINSHAD P.T.
 * @Date 15/06/2016
 */

@Entity
@Table(name = AppConstants.TABLE_NAMES.USER_DETAILS)
public class UserDetails implements Serializable {

	private static final long serialVersionUID = -2052325011395588775L;

	public UserDetails(long userId) {
		super();
		this.userId = userId;
	}

	public UserDetails() {
		super();
	}

	@Id
	@GeneratedValue
	@Column(name = "user_id", columnDefinition = "bigint(15)")
	private long userId;

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(name = "gender")
	private char gender;

	@Column(name = "dob", nullable = true)
	private Date dob;

	@Column(name = "mobile", length = 15)
	private String mobile;

	@Column(name = "address", length = 100)
	private String address;

	@Column(name = "email_id", length = 50)
	private String email;

	@Column(name = "password", length = 100)
	private String password;

	@Column(name = "blocking_reason", length = 100)
	private String blockingReason;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role_map", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<UserRole> roles;

	@OneToOne
	@JoinColumn(name = "dept_id", columnDefinition = "bigint(15)")
	private DepartmentDetails department;


//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "user_skills_map", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "skill_id") })
//	private Set<UserSkills> skills;


	@Column(name = "status", columnDefinition = "int(1) default '1'", nullable = false)
	private int status;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBlockingReason() {
		return blockingReason;
	}

	public void setBlockingReason(String blockingReason) {
		this.blockingReason = blockingReason;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
    
	public DepartmentDetails getDepartment() {
		return department;
	}
	
	public void setDepartment(DepartmentDetails department) {
		this.department = department;
	}
}
