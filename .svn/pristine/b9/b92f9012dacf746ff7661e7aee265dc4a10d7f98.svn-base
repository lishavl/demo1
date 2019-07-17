package com.pumex.tms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pumex.tms.util.AppConstants;

/**
 * @Author JINSHAD P.T.
 * @Date 25/08/2016
 */

@Entity
@Table(name = AppConstants.TABLE_NAMES.OPTIONS)
public class Options implements Serializable {

	private static final long serialVersionUID = -8475982058671810913L;

	public Options() {
		super();
	}

	public Options(long id) {
		super();
		this.id = id;
	}

	public Options(String menuName, String action, long menuGroup) {
		super();
		this.menuName = menuName;
		this.action = action;
		this.setMenuGroup(menuGroup);
	}

	@Id
	@GeneratedValue
	@Column(name = "option_id", columnDefinition = "bigint(4)")
	private long id;

	@Column(name = "option_name", length = 60)
	private String name;

	@Column(name = "menu_name", length = 60)
	private String menuName;

	@Column(name = "action", length = 60)
	private String action;

	@Column(name = "menu_order", columnDefinition = "int(3) default 1")
	private int order;

	@Column(name = "menu_group", columnDefinition = "bigint(5) default 1")
	private long menuGroup;

	@Column(name = "status", length = 1, nullable = false)
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public long getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(long menuGroup) {
		this.menuGroup = menuGroup;
	}

}
