package com.pumex.tms.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.pumex.tms.util.AppConstants;

/**
 * @Author JOSSINA JOSE.
 * @Date 07/09/2016
 */

@Entity
@Table(name = AppConstants.TABLE_NAMES.TOPICS)
public class ForumDetails implements Serializable {

	private static final long serialVersionUID = 486532332688930776L;

	@Id
	@GeneratedValue
	@Column(name = "topic_id")
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ForumDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@OneToOne
	@JoinColumn(name = "user_id", columnDefinition = "bigint(15)")
	private UserDetails user;

	@OneToOne
	@JoinColumn(name = "category_id", columnDefinition = "bigint(15)")
	private TrainingCategory category;

	@Column(name = "subject", length = 1000)
	private String subject;

	@Column(name = "description", length = 1000)
	private String description;

	@Column(name = "file_path", columnDefinition = "varchar(150)")
	private String upload_file_name;

	@Column(name = "posted_on")
	private Timestamp postedon;

	@Column(name = "likes")
	private long likes;

	@Column(name = "views")
	private long views;

	@Column(name = "parent_id")
	private long parentid;

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public TrainingCategory getCategory() {
		return category;
	}

	public void setCategory(TrainingCategory category) {
		this.category = category;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpload_file_name() {
		return upload_file_name;
	}

	public void setUpload_file_name(String upload_file_name) {
		this.upload_file_name = upload_file_name;
	}

	public Timestamp getPostedon() {
		return postedon;
	}

	public void setPostedon(Timestamp postedon) {
		this.postedon = postedon;
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

	public long getViews() {
		return views;
	}

	public void setViews(long views) {
		this.views = views;
	}

	public long getParentid() {
		return parentid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}


}
