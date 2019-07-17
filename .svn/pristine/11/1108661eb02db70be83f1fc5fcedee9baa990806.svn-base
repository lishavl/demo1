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
 * @Date 15/06/2016
 */

@Entity
@Table(name = AppConstants.TABLE_NAMES.USER_SKILLS)
public class UserSkills implements Serializable {

	private static final long serialVersionUID = 1491715889659855553L;

	public UserSkills() {
		super();
	}

	public UserSkills(long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "skill_id", columnDefinition = "bigint(15)")
	private long id;

	@Column(name = "skill", length = 50)
	private String skill;
	
	@OneToOne
	@JoinColumn(name = "user_id", columnDefinition = "bigint(15)")
	private UserDetails user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

}
