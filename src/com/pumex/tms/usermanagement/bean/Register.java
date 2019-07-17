package com.pumex.tms.usermanagement.bean;

/*
 *** @Author Reshma Manoj ***
 */

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.pumex.tms.models.UserRole;


public class Register {

	private long user_id;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private String dob;
	private char gender;
	private String photo;
	private String email;
	private String password;
	private String confirmPassword;
	private String[] skillsets;
	private String blockingReason;
	private File userPhoto;
	private List<String> skills;
	private Set<UserRole> roles;
	private int role1;
	private int role2;
	private String newPassword;
	private String oldPassword;
	private long department;
	private String departmentname;
	private boolean imageExist;

	
	


	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
	public Register(long user_id, String firstName) {
		super();
		this.user_id = user_id;
		this.firstName = firstName;
	}


	public Register(long user_id, String firstName, String lastName) {
		super();
		this.user_id = user_id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Register(long user_id, String firstName, String lastName,
			String address, String phoneNumber, String dob, char gender,
			String photo, String email, String password,
			String confirmPassword, String[] skillsets, String blockingReason,
			File userPhoto, List<String> skills, Set<UserRole> roles,
			int role1, int role2, String newPassword, String oldPassword) {
		super();
		this.user_id = user_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.gender = gender;
		this.photo = photo;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.skillsets = skillsets;
		this.blockingReason = blockingReason;
		this.userPhoto = userPhoto;
		this.skills = skills;
		this.roles = roles;
		this.role1 = role1;
		this.role2 = role2;
		this.newPassword = newPassword;
		this.oldPassword = oldPassword;
	}


	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public int getRole1() {
		return role1;
	}

	public void setRole1(int role1) {
		this.role1 = role1;
	}

	public int getRole2() {
		return role2;
	}

	public void setRole2(int role2) {
		this.role2 = role2;
	}

	public String getBlockingReason() {
		return blockingReason;
	}

	public void setBlockingReason(String blockingReason) {
		this.blockingReason = blockingReason;
	}	

	public File getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(File userPhoto) {
		this.userPhoto = userPhoto;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String[] getSkillsets() {
		return skillsets;
	}

	public void setSkillsets(String[] skillsets) {
		this.skillsets = skillsets;
	}
	

	public long getDepartment() {
		return department;
	}


	public void setDepartment(long department) {
		this.department = department;
	}
	public String getDepartmentname() {
		return departmentname;
	}


	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	
	

	public boolean isImageExist() {
		return imageExist;
	}


	public void setImageExist(boolean imageExist) {
		this.imageExist = imageExist;
	}


	@Override
	public String toString() {
		return "Register [user_id=" + user_id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", dob=" + dob + ", gender="
				+ gender + ", photo=" + photo + ", email=" + email
				+ ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", skillsets=" + Arrays.toString(skillsets)
				+ ", blockingReason=" + blockingReason + ", userPhoto="
				+ userPhoto + ", skills=" + skills + ", roles=" + roles
				+ ", role1=" + role1 + ", role2=" + role2 + ", newPassword="
				+ newPassword + ", oldPassword=" + oldPassword
				+ ", department=" + department + "]";
	}



}
