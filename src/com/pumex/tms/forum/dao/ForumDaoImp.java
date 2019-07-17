package com.pumex.tms.forum.dao;
/**
 * @Author JOSSINA JOSE.
 *
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.taglibs.standard.tag.common.fmt.SetLocaleSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.forum.bean.ForumBean;
import com.pumex.tms.models.ForumDetails;
import com.pumex.tms.models.ForumUserLikes;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.trainingmanagement.bean.TrainingCategoryBean;

@Transactional
@Repository
public class ForumDaoImp extends AbstractDao<Long, ForumDetails> implements
		ForumDao {

	
	/*
	 * Method for fetch categories for forum	 * 
	 * @return List of Categories as TrainingCategory
	 */
	@Override
	public List getAllCategoriesForForum() throws Exception {

		List lst = getSession().createQuery("from TrainingCategory").list();
		return lst;
		// TODO Auto-generated method stub
	}
	
	/*
	 * Method for fetch categories for forum	 * 
	 * @return List of Categories as ForumBean
	 */
	@Override
	public List<ForumBean> FetchAllCategoriesForForum() throws Exception {
		// TODO Auto-generated method stub
		List<ForumBean> list = getSession()
				.createQuery(
						"select new com.pumex.tms.forum.bean.ForumBean(a.id, a.category, a.parentId )from TrainingCategory a where a.parentId=0 and a.status =1 and a.isforumcategory =1 order by id desc")
				.list();

		for (ForumBean l : list) {
			if (l.getParentid() == 0) {

				ForumBean t = (ForumBean) getSession()
						.createQuery(
								"select new com.pumex.tms.forum.bean.ForumBean(concat(f.user.firstName,'',f.user.lastName)as name,f.subject,f.postedon ) from ForumDetails f where f.postedon = (select max(ff.postedon) from ForumDetails ff where ff.category.id =:categoryid)"

						).setLong("categoryid", l.getId()).uniqueResult();
				l.setPostbydetails(t);

				List<ForumBean> subcategory = getSession()
						.createQuery(
								"select new com.pumex.tms.forum.bean.ForumBean(a.id, a.category, a.parentId )from TrainingCategory a where a.parentId=:id and a.status =1 and a.isforumcategory =1")
						.setLong("id", l.getId()).list();
				for (ForumBean posts : subcategory) {

					long count = (long) getSession()
							.createQuery(
									"select count(id) from ForumDetails where category.id=:categoryid and parentid=0 ")
							.setLong("categoryid", posts.getId())
							.uniqueResult();
					posts.setNoofthread(count);
					long post = (long) getSession()
							.createQuery(
									"select count(id) from ForumDetails where category.id=:categoryid and parentid !=0 ")
							.setLong("categoryid", posts.getId())
							.uniqueResult();
					posts.setNoofposts(post);
					ForumBean name = (ForumBean) getSession()
							.createQuery(
									"select new com.pumex.tms.forum.bean.ForumBean(f.id,f.category.id,f.category.category,concat(f.user.firstName,' ',f.user.lastName)as name,f.subject,f.postedon,f.parentid ) from ForumDetails f where f.postedon = (select max(ff.postedon) from ForumDetails ff where ff.category.id =:categoryid)"

							).setLong("categoryid", posts.getId())
							.uniqueResult();
					posts.setPostbydetails(name);
				}
				l.setSubcategory(subcategory);
			}
		}

		return list;
	}
   
	/*
	 * Method for save or update thread and reply
	 * 
	 * @return status as response
	 */
	@Override
	public void saveThread(ForumDetails details) throws Exception {
		saveOrUpdate(details);
		// TODO Auto-generated method stub

	}
    
	/*
	 * Method fetch threads
	 * 
	 * @return  list of threads
	 */
	@Override
	public List getAllThreads(long categoryid, long id) throws Exception {
		// TODO Auto-generated method stub
		List<ForumBean> threadlist = getSession()
				.createQuery(
						"select new com.pumex.tms.forum.bean.ForumBean(id,category.id,parentid,category.category,concat(user.firstName,' ',user.lastName) as name,subject,description,upload_file_name,postedon,views )from ForumDetails where category.id = :categoryid and parentid=0 order by postedon desc")
				.setLong("categoryid", categoryid).list();

		for (ForumBean list : threadlist) {
			long count = (long) getSession()
					.createQuery(
							"select count(id) from ForumDetails where  category.id = :categoryid and parentid=:id")
					.setLong("categoryid", categoryid)
					.setLong("id", list.getId()).uniqueResult();
			list.setReplies(count);
			ForumBean name = (ForumBean) getSession()
					.createQuery(
							"select new com.pumex.tms.forum.bean.ForumBean(concat(f.user.firstName,' ',f.user.lastName)as name,f.subject,f.postedon ) from ForumDetails f where f.postedon = (select max(ff.postedon) "
									+ "from ForumDetails ff where ff.category.id =:categoryid and ff.parentid=:id)"

					).setLong("categoryid", categoryid)
					.setLong("id", list.getId()).uniqueResult();
			list.setPostbydetails(name);
		}
		return threadlist;
	}
	
	/*
	 * Method fetch replies
	 * 
	 * @return  list of replies as ForumBean
	 */
	@Override
	public List getAllReplies(long parentid, long editcategoryid,long userid)
			throws Exception {
		// TODO Auto-generated method stub
       
		
        List<ForumBean> forumdetails= getSession()
				.createQuery(
						"select new com.pumex.tms.forum.bean.ForumBean(id, parentid,category.id,user.userId,category.category,description,upload_file_name,likes,concat(user.firstName,' ',user.lastName) as name,postedon )from ForumDetails where  category.id = :categoryid and (parentid=:parentid or  id=:parentid)")
				.setLong("parentid", parentid)
				.setLong("categoryid", editcategoryid).list();
	
		for( ForumBean details:forumdetails){
			long count = (long) getSession()
					.createQuery(
							"select count(id) from ForumUserLikes where  user.userId=:id and forum.id=:forumid")
					.setLong("id", userid)
					.setLong("forumid",details.getId())
					.uniqueResult();
			details.setLikestatus(count);
		}
		return forumdetails;
	}

	/*
	 * Method fetch reply/thread for edit
	 * 
	 * @return   reply or thread as  ForumBean object
	 */
	@Override
	public ForumBean getReplyDataForEdit(long replyid) throws Exception {
		// TODO Auto-generated method stub
		return (ForumBean) getSession()
				.createQuery(
						"select new com.pumex.tms.forum.bean.ForumBean(id, parentid, subject,description,upload_file_name,category.id )from ForumDetails where id=:id")
				.setLong("id", replyid).uniqueResult();
	}
	
	/*
	 * Method for get data by id
	 * 
	 * @return ForumDetails
	 */
	@Override
	public ForumDetails getById(long id) throws Exception {
		// TODO Auto-generated method stub
		return getByKey(id);
	}
	
	/*
	 * Method set view count
	 * 
	 * @return status as response
	 */
	
	@Override
	public void seViewStatus(long parentid, long editcategoryid)
			throws Exception {
		getSession()
				.createQuery(
						"UPDATE ForumDetails SET views = views + 1 WHERE id = :parentid and  category.id = :categoryid")
				.setLong("parentid", parentid)
				.setLong("categoryid", editcategoryid).executeUpdate();

		// TODO Auto-generated method stub

	}
    
	/*
	 * Method delete reply 
	 * 
	 * @return status
	 */
	@Override
	public void deleteReplyForAdmin(long replyid) throws Exception {
		// TODO Auto-generated method stub
		delete(getByKey(replyid));
	}
    
	/*
	 * Method delete thread 
	 * 
	 * @return status
	 */
	@Override
	public void deleteThreadForAdmin(long threadid) throws Exception {
		getSession()
				.createQuery(
						" delete ForumDetails where (parentid=:threadid or  id=:threadid)")
				.setLong("threadid", threadid).executeUpdate();

	}
	/*
	 * Method for thread details for admin
	 * 
	 * @return response as ForumBean
	 */
	@Override
	public ForumBean getThreadDetailsForDashboard(long threadid)
			throws Exception {
		// TODO Auto-generated method stub
		return (ForumBean) getSession()
				.createQuery(
						"select new com.pumex.tms.forum.bean.ForumBean(subject,category.id,category.category )from ForumDetails where id=:threadid")
				.setLong("threadid", threadid).uniqueResult();

	}
	
	/*
	 * Method for  forum search *
	 * 
	 * @return List of Thread and reply list as ForumBean
	 */
	@Override
	public List getAllForumSearchData(String searchtext) throws Exception {

		List<ForumBean> forumList = null;

		List<Long> threadids = getSession().createQuery(
				" select  id from ForumDetails  where (subject like '%"
						+ searchtext + "%' or  description like '%"
						+ searchtext + "%') and parentid=0 ").list();

		List<Long> replyids;
		if (threadids != null && threadids.size() > 0) {
			replyids = getSession()
					.createQuery(
							" select parentid from ForumDetails  where  description like '%"
									+ searchtext
									+ "%'  and  parentid != 0 and parentid not in (:threadids)")
					.setParameterList("threadids", threadids).list();
		} else {
			replyids = getSession().createQuery(
					" select parentid from ForumDetails  where description like '%"
							+ searchtext + "%' and  parentid != 0").list();
		}

		if (threadids != null && threadids.size() > 0)
			forumList = getSession()
					.createQuery(
							"select new com.pumex.tms.forum.bean.ForumBean(id,category.id,parentid,category.category,concat(user.firstName,' ',user.lastName) as name,"
									+ "subject,description,upload_file_name,postedon,views )from ForumDetails where id in (:threadids)")
					.setParameterList("threadids", threadids).list();

		if (forumList == null)
			forumList = new ArrayList<ForumBean>();

		List rplyList = null;

		if (replyids != null && replyids.size() > 0)
			rplyList = getSession()
					.createQuery(
							"select new com.pumex.tms.forum.bean.ForumBean(id,category.id,parentid,category.category,concat(user.firstName,' ',user.lastName) as name,"
									+ "subject,description,upload_file_name,postedon,views )from ForumDetails where id in (:replyids)")
					.setParameterList("replyids", replyids).list();

		if (rplyList != null)
			forumList.addAll(rplyList);

		if (forumList != null && forumList.size() > 0) {
			for (ForumBean list : forumList) {
				long count = (long) getSession()
						.createQuery(
								"select count(id) from ForumDetails where  parentid=:id")
						.setLong("id", list.getId()).uniqueResult();
				list.setReplies(count);
				ForumBean name = (ForumBean) getSession()
						.createQuery(
								"select new com.pumex.tms.forum.bean.ForumBean(concat(f.user.firstName,' ',f.user.lastName)as name,f.subject,f.postedon ) from ForumDetails f where f.postedon = (select max(ff.postedon) "
										+ "from ForumDetails ff where ff.category.id =:categoryid and ff.parentid=:id)"

						).setLong("categoryid", list.getCategoryid())
						.setLong("id", list.getId()).uniqueResult();
				list.setPostbydetails(name);
			}
		}
		return forumList;
	}
    
	/*
	 * Method set like count
	 * 
	 * @return status as response
	 */
	
	@Override
	public void setLikeStatus(long replyid, ForumDetails details,
			UserDetails user) throws Exception {
		
        ForumUserLikes userlikes = new ForumUserLikes();
        userlikes.setForum(details);
        userlikes.setUser(user);
        
        getSession().save(userlikes);
		getSession()
				.createQuery(
						"UPDATE ForumDetails SET likes = likes + 1 WHERE id = :replyid")
				.setLong("replyid", replyid)
				.executeUpdate();
		// TODO Auto-generated method stub

	}
	/*
	 * Method for decrement like count
	 * 
	 * @return status as response
	 */
	@Override
	public void setDislikeStatus(long replyid, long userid) throws Exception {
		
		getSession()
		.createQuery(
				" delete ForumUserLikes where user.userId=:userid and forum.id=:replyid")
		.setLong("replyid", replyid).setLong("userid", userid).executeUpdate();

		getSession()
		.createQuery(
				"UPDATE ForumDetails SET likes = likes - 1 WHERE id = :replyid")
		.setLong("replyid", replyid)
		.executeUpdate();
// TODO Auto-generated method stub
	}

}
