package com.pumex.tms.forum.service;
/**
 * @Author JOSSINA JOSE.
 *
 */
import java.util.List;

import com.pumex.tms.forum.bean.ForumBean;

public interface ForumService {

	List getAllCategoriesForForum() throws Exception;

	List<ForumBean> FetchAllCategoriesForForum() throws Exception;

	void addNewThread(ForumBean forum, long id) throws Exception;

	List getThreads(long categoryid, long id) throws Exception;

	List getReplyList(long parentid, long editcategoryid,long userid) throws Exception;

	ForumBean getThreads(long replyid) throws Exception;

	void setviewStatus(long parentid, long editcategoryid) throws Exception;

	void deleteReplyByAdmin(long replyid) throws Exception;

	void deleteThreadByAdmin(long threadid) throws Exception;

	ForumBean fetchThreadDetailsForDashboard(long threadid) throws Exception;

	List forumSearchData(String searchtext) throws Exception;

	void setLikeStatus(long replyid,long userid) throws Exception;

	void setDislikeStatus(long replyid, long userid) throws Exception;

}
