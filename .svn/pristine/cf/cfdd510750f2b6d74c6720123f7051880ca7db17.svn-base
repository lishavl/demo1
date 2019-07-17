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
 * @Author Jossina Jose.
 * @Date 11/10/2016
 */

@Entity
@Table(name = AppConstants.TABLE_NAMES.FORUM_USER_LIKE)
public class ForumUserLikes implements Serializable {
	
	private static final long serialVersionUID = -852712404932069673L;

	@Id
	@GeneratedValue
	@Column(name = "like_id",columnDefinition = "bigint(15)")
	private long id;



	@OneToOne
	@JoinColumn(name = "forum_user_id", columnDefinition = "bigint(15)")
	private UserDetails user;

	@OneToOne
	@JoinColumn(name = "forum_id", columnDefinition = "bigint(15)")
	private ForumDetails forum;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public ForumDetails getForum() {
		return forum;
	}

	public void setForum(ForumDetails forum) {
		this.forum = forum;
	}
	public ForumUserLikes() {
		super();
		// TODO Auto-generated constructor stub
	}

}
