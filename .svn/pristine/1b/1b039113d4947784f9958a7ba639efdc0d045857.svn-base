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
 * @Date 15/06/2016
 */

@Entity
@Table(name = AppConstants.TABLE_NAMES.ROLE)
public class Role  implements Serializable {

	private static final long serialVersionUID = 4592544793185045724L;

	public Role() {
		super();
	}

	public Role(long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "role_id",columnDefinition = "bigint(2)")
	private long id;

	@Column(name = "user_role", length = 30)
	private String userRole;

	@Column(name = "status", length = 1, nullable = false)
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
