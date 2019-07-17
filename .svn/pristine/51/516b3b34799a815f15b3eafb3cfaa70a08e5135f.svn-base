package com.pumex.tms.forum.dao;
/**
 * @Author JOSSINA JOSE.
 *
 */

import java.util.List;

import com.pumex.tms.forum.bean.ForumBean;
import com.pumex.tms.models.ForumDetails;
import com.pumex.tms.models.ForumUserLikes;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.trainingmanagement.bean.TrainingCategoryBean;

public interface ForumDao {

	List getAllCategoriesForForum() throws Exception;

	List<ForumBean> FetchAllCategoriesForForum() throws Exception;

	void saveThread(ForumDetails details) throws Exception;

	List getAllThreads(long categoryid,long id)throws Exception;

	List getAllReplies(long parentid, long editcategoryid,long userid) throws Exception;

	ForumBean getReplyDataForEdit(long replyid) throws Exception;

	ForumDetails getById(long id) throws Exception;

	void seViewStatus(long parentid, long editcategoryid)throws Exception;

	void deleteReplyForAdmin(long replyid) throws Exception;

	void deleteThreadForAdmin(long threadid) throws Exception;

	ForumBean getThreadDetailsForDashboard(long threadid) throws Exception;

	List getAllForumSearchData(String searchtext) throws Exception;

	void setLikeStatus(long replyid,ForumDetails details, UserDetails user) throws Exception;

	void setDislikeStatus(long replyid, long userid) throws Exception;

}
