package com.pumex.tms.forum.bean;

/**
 * Bean class for forum
 * @Author JOSSINA JOSE.
 *
 */
import java.util.Date;
import java.util.List;

import com.pumex.tms.models.ForumDetails;

public class ForumBean {

	private long id;
	private String categoryname;
	private long parentid;
	private String subject;
	private String description;
	private String filepath;
	private long userid;
	private long categoryid;
	private long noofposts;
	private long noofthread;
	private String postby;
	private Date postedon;
	private ForumBean postbydetails;
	private long replies;
	private long views;
	private long likes;
	private long likestatus;
	private long threadid;
	private ForumDetails forumdetails;

	public ForumBean(String categoryname, String subject, Date postedon,
			long likes, long userid) {
		super();
		this.categoryname = categoryname;
		this.subject = subject;
		this.postedon = postedon;
		this.likes = likes;
		this.userid = userid;
	}
	
	public ForumBean(long id, String categoryname, String subject, Date postedon,
			long likes, long userid) {
		super();
		this.id = id;
		this.categoryname = categoryname;
		this.subject = subject;
		this.postedon = postedon;
		this.likes = likes;
		this.userid = userid;
	}
	

	public ForumBean(long id,long parentid,long categoryid,long userid, String categoryname, 
			String description, String filepath,  long likes,String postby, Date postedon) {
			
		super();
		this.id = id;
		this.parentid = parentid;
		this.categoryid = categoryid;
		this.userid = userid;
		this.categoryname = categoryname;
		this.description = description;
		this.filepath = filepath;
		this.likes = likes;
		this.postby = postby;
		this.postedon = postedon;
	}

	public ForumBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForumBean(long id, String categoryname, long parentid,
			List<ForumBean> subcategory) {
		super();
		this.id = id;
		this.categoryname = categoryname;
		this.parentid = parentid;
		this.subcategory = subcategory;
	}

	public ForumBean(long id, String categoryname, long parentid) {
		super();
		this.id = id;
		this.categoryname = categoryname;
		this.parentid = parentid;
	}
	

	public ForumBean(String subject, long categoryid,String categoryname) {
		super();
		this.subject = subject;
		this.categoryid = categoryid;
		this.categoryname = categoryname;
	}

	public ForumBean(long id, long parentid, String subject,
			String description, String filepath, long categoryid) {
		super();
		this.id = id;
		this.parentid = parentid;
		this.subject = subject;
		this.description = description;
		this.filepath = filepath;
		this.categoryid = categoryid;
	}

	public ForumBean(String postby, String subject, Date postedon) {
		super();
		this.subject = subject;
		this.postby = postby;
		this.postedon = postedon;
	}
	public ForumBean(long threadid,long categoryid,String categoryname,String postby, String subject, Date postedon,long parentid) {
		super();
		
		this.threadid =threadid;
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.subject = subject;
		this.postby = postby;
		this.postedon = postedon;
		this.parentid = parentid;
	}


	public ForumBean(long id, long categoryid, long parentid, String postby,
			String subject, String description, String filepath, Date postedon,
			long views) {

		super();
		this.id = id;
		this.parentid = parentid;
		this.subject = subject;
		this.description = description;
		this.filepath = filepath;
		this.categoryid = categoryid;
		this.postby = postby;
		this.postedon = postedon;
		this.views = views;
	}
	public ForumBean(long id, long categoryid, long parentid,String categoryname, String postby,
			String subject, String description, String filepath, Date postedon,
			long views) {

		super();
		this.id = id;
		this.categoryid = categoryid;
		this.parentid = parentid;
		this.categoryname = categoryname;
		this.postby = postby;
		this.subject = subject;
		this.description = description;
		this.filepath = filepath;
		this.postedon = postedon;
		this.views = views;
	}

	public long getReplies() {
		return replies;
	}

	public void setReplies(long replies) {
		this.replies = replies;
	}

	public long getViews() {
		return views;
	}

	public void setViews(long views) {
		this.views = views;
	}

	public Date getPostedon() {
		return postedon;
	}

	public void setPostedon(Date postedon) {
		this.postedon = postedon;
	}

	public ForumBean getPostbydetails() {
		return postbydetails;
	}

	public void setPostbydetails(ForumBean postbydetails) {
		this.postbydetails = postbydetails;
	}

	public String getPostby() {
		return postby;
	}

	public void setPostby(String postby) {
		this.postby = postby;
	}

	public long getNoofthread() {
		return noofthread;
	}

	public void setNoofthread(long noofthread) {
		this.noofthread = noofthread;
	}

	private String lastpostby;

	public long getNoofposts() {
		return noofposts;
	}

	public void setNoofposts(long noofposts) {
		this.noofposts = noofposts;
	}

	public String getLastpostby() {
		return lastpostby;
	}

	public void setLastpostby(String lastpostby) {
		this.lastpostby = lastpostby;
	}

	public long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
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

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	private List<ForumBean> subcategory;

	public List<ForumBean> getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(List<ForumBean> subcategory) {
		this.subcategory = subcategory;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
    
	public long getThreadid() {
		return threadid;
	}

	public void setThreadid(long threadid) {
		this.threadid = threadid;
	}

	public long getParentid() {
		return parentid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}
	

	public ForumDetails getForumdetails() {
		return forumdetails;
	}

	public void setForumdetails(ForumDetails forumdetails) {
		this.forumdetails = forumdetails;
	}
	

	public long getLikestatus() {
		return likestatus;
	}

	public void setLikestatus(long likestatus) {
		this.likestatus = likestatus;
	}

	@Override
	public String toString() {
		return "ForumBean [id=" + id + ", categoryname=" + categoryname
				+ ", parentid=" + parentid + ", subcategory=" + subcategory
				+ "]";
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

}
