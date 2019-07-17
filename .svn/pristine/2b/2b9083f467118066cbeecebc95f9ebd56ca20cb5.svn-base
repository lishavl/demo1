package com.pumex.tms.forum.service;

/**
 * @Author JOSSINA JOSE.
 *
 */
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.forum.bean.ForumBean;
import com.pumex.tms.forum.dao.ForumDao;
import com.pumex.tms.models.ForumDetails;
import com.pumex.tms.models.ForumUserLikes;
import com.pumex.tms.models.TrainingCategory;
import com.pumex.tms.models.UserDetails;

@Service("forumservice")
public class ForumServiceImp implements ForumService{
	
	@Autowired
	ForumDao dao;

	
	/*
	 * Method for fetch categories for forum	 * 
	 * @return List of Categories
	 */
	@Override
	public List getAllCategoriesForForum() throws Exception {
		// TODO Auto-generated method stub
		return dao.getAllCategoriesForForum();
	}
   
	/*
	 * Method for fetch categories for forum	 * 
	 * @return List of Categories
	 */
	@Override
	public List<ForumBean> FetchAllCategoriesForForum() throws Exception {
		// TODO Auto-generated method stub
		return dao.FetchAllCategoriesForForum();
	}
    
	/*
	 * Method for save or update thread and reply
	 * 
	 * @return status as response
	 */
	@Override
	public void addNewThread(ForumBean forum,long id) throws Exception {
		Date date=new Date();
		Timestamp timestamp = new Timestamp(date.getTime());//instead of date put your converted date
		Timestamp myTimeStamp= timestamp;
		UserDetails user=new UserDetails();
		TrainingCategory category=new TrainingCategory();
		category.setId(forum.getCategoryid());
		user.setUserId(id);
		
		ForumDetails details;
		if(forum.getId()!=0){
			details=dao.getById(forum.getId());
		}
		else
		details=new ForumDetails();
		
		details.setUser(user);
		details.setCategory(category);
		details.setSubject(forum.getSubject());
		details.setDescription(forum.getDescription());
		if(forum.getParentid()==0){
			details.setParentid(0);
		}else{
			details.setParentid(forum.getParentid());
			
		}
		details.setUpload_file_name(forum.getFilepath());
		details.setPostedon(timestamp);
		
		dao.saveThread(details);
		forum.setId(details.getId());
		
		
		// TODO Auto-generated method stub
		
	}
    
	/*
	 * Method fetch threads
	 * 
	 * @return  list of threads
	 */
	@Override
	public List getThreads(long categoryid,long id) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAllThreads(categoryid,id);
	}
    
	/*
	 * Method fetch replies
	 * 
	 * @return  list of replies as ForumBean
	 */
	@Override
	public List getReplyList(long parentid, long editcategoryid,long userid)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.getAllReplies(parentid,editcategoryid,userid);
	}
	
	/*
	 * Method fetch reply/thread for edit
	 * 
	 * @return   reply or thread as  ForumBean object
	 */
	@Override
	public ForumBean getThreads(long replyid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getReplyDataForEdit(replyid);
		
	}
    
	/*
	 * Method set view count
	 * 
	 * @return status as response
	 */
	
	@Override
	public void setviewStatus(long parentid, long editcategoryid) throws Exception {
		dao.seViewStatus(parentid,editcategoryid);
		// TODO Auto-generated method stub
		
	}
    
	/*
	 * Method delete reply 
	 * 
	 * @return status
	 */
	@Override
	public void deleteReplyByAdmin(long replyid) throws Exception {
		dao.deleteReplyForAdmin(replyid);
		// TODO Auto-generated method stub
		
	}
    
	
	/*
	 * Method delete thread 
	 * 
	 * @return status
	 */
	@Override
	public void deleteThreadByAdmin(long threadid) throws Exception {
		dao.deleteThreadForAdmin(threadid);
		// TODO Auto-generated method stub
		
	}
	/*
	 * Method for thread details for admin
	 * 
	 * @return response as ForumBean
	 */
	@Override
	public ForumBean fetchThreadDetailsForDashboard(long threadid)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.getThreadDetailsForDashboard(threadid);
	}
    
	/*
	 * Method for  forum search *
	 * 
	 * @return List of Thread and reply list as ForumBean
	 */
	@Override
	public List forumSearchData(String searchtext) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAllForumSearchData(searchtext);
	}
    
	/*
	 * Method set like count
	 * 
	 * @return status as response
	 */
	@Override
	public void setLikeStatus(long replyid,long userid) throws Exception {
		
		ForumDetails details = new ForumDetails();
	     details.setId(replyid);
		 UserDetails user =new UserDetails();
		 user.setUserId(userid);
		
		dao.setLikeStatus(replyid,details,user);
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Method for decrement like count
	 * 
	 * @return status as response
	 */
	@Override
	public void setDislikeStatus(long replyid, long userid) throws Exception {
		// TODO Auto-generated method stub
		dao.setDislikeStatus(replyid,userid);
	}


}
