package com.pumex.tms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pumex.tms.util.AppConstants;

/**
 * @Author JINSHAD P.T.
 * @Date 25/08/2016
 */

@Entity
@Table(name = AppConstants.TABLE_NAMES.ROLE_OPTIONS)
public class RoleOptions implements Serializable {

	private static final long serialVersionUID = -862374529461275403L;

	public RoleOptions() {
		super();
	}

	public RoleOptions(long id) {
		super();
		this.id = id;
	}

	public RoleOptions(Role role, Options option) {
		super();
		this.role = role;
		this.option = option;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", columnDefinition = "bigint(4)")
	private long id;

	@OneToOne
	@JoinColumn(name = "role_id", columnDefinition = "bigint(2)")
	private Role role;

	@OneToOne
	@JoinColumn(name = "option_id", columnDefinition = "bigint(4)")
	private Options option;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Options getOption() {
		return option;
	}

	public void setOption(Options option) {
		this.option = option;
	}

}
