package com.pumex.tms.usermanagement.bean;

import java.util.List;

public class UserAccessBean {

	private long optionId;
	private String optionName;
	private String menuName;
	private int available;
	private long roleId;
	private Long[] optionIDs;
	private List data;

	public UserAccessBean() {
		super();
	}

	public UserAccessBean(long optionId, String optionName, int available) {
		super();
		this.optionId = optionId;
		this.optionName = optionName;
		this.available = available;
	}
	
	public UserAccessBean(long optionId, String optionName, String menuName, int available) {
		super();
		this.optionId = optionId;
		this.optionName = optionName;
		this.menuName = menuName;
		this.available = available;
	}

	public UserAccessBean(long optionId, String optionName, String menuName) {
		super();
		this.optionId = optionId;
		this.optionName = optionName;
		this.menuName = menuName;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public Long[] getOptionIDs() {
		return optionIDs;
	}

	public void setOptionIDs(Long[] optionIDs) {
		this.optionIDs = optionIDs;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getOptionId() {
		return optionId;
	}

	public void setOptionId(long optionId) {
		this.optionId = optionId;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
