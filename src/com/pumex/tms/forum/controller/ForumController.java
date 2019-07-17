package com.pumex.tms.forum.controller;

/**
 * @Author JOSSINA JOSE.
 *
 */

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.pumex.tms.forum.bean.ForumBean;
import com.pumex.tms.forum.service.ForumService;
import com.pumex.tms.models.TrainingCategory;
import com.pumex.tms.onlinetest.bean.TestReportBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;
import com.pumex.tms.trainingmanagement.bean.TrainingMaterialBean;
import com.pumex.tms.util.AppConstants;
import com.pumex.tms.util.Response;

@Controller
public class ForumController {

	@Autowired
	ForumService service;

	/*
	 * Method for fetch categories for forum *
	 * 
	 * @return List of Categories
	 */
	@RequestMapping(value = "/fetchallcategoriesforforum/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List FetchAllCatergoriesForForum(HttpSession session)
			throws Exception {

		List categories = service.FetchAllCategoriesForForum();
		return categories;
	}

	/*
	 * Method for forum search *
	 * 
	 * @return List of Thread and reply list as ForumBean
	 */

	@RequestMapping(value = "/forumsearch//{searchtext}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List ForumSearch(
			@PathVariable("searchtext") String searchtext) throws Exception {
		List forumsearch = service.forumSearchData(searchtext);
		return forumsearch;
	}

	/*
	 * Method for save or update thread and reply
	 * 
	 * @return status as response
	 */
	@RequestMapping(value = "/saveNewThread/", method = RequestMethod.POST)
	public @ResponseBody Response SaveNewThread(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "data") String jsonData, HttpSession session)
			throws Exception {

		Gson gson = new Gson();
		ForumBean forum = gson.fromJson(jsonData, ForumBean.class);
		long id = (long) session.getAttribute("userId");
		forum.setFilepath(file.getOriginalFilename());

		try {
			service.addNewThread(forum, id);
			
			String threadidid = Long.toString(forum.getId());
			
			if (file != null) {

				File convFile = new File(AppConstants.FILES_PATH + "forum/"
						+threadidid +"_"+file.getOriginalFilename());
				convFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(convFile);
				fos.write(file.getBytes());
				fos.close();
			}
			return new Response(1, "Add Material Successfull!!!");
		} catch (Exception e) {
			return new Response(2, "failed");
			// TODO: handle exception
		}

	}
	
	/*
	 * Method fetch threads
	 * 
	 * @return  list of threads
	 */
	@RequestMapping(value = "/fetchthread/{categoryid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List getAllUsers(
			@PathVariable("categoryid") long categoryid, HttpSession session)
			throws Exception {
		long id = (long) session.getAttribute("userId");
		List threadlist = service.getThreads(categoryid, id);
		return threadlist;
	}
    
	/*
	 * Method for fetch replies
	 * 
	 * @return  list of replies as ForumBean
	 */
	@RequestMapping(value = "/fetchreplylilst/{parentid}/{editcategoryid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List fetchReplyList(
			@PathVariable("parentid") long parentid,
			@PathVariable("editcategoryid") long editcategoryid,
			HttpSession session) throws Exception {
		long userid = (long) session.getAttribute("userId");
		List threadlist = service
				.getReplyList(parentid, editcategoryid, userid);
		return threadlist;
	}
	
	/*
	 * Method for fetch reply/thread for edit
	 * 
	 * @return   reply or thread as  ForumBean object
	 */
	@RequestMapping(value = "/editreply/{replyid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ForumBean getReplyForEdit(
			@PathVariable("replyid") long replyid, HttpSession session)
			throws Exception {
		ForumBean editreply = service.getThreads(replyid);
		return editreply;
	}
	 

	/*
	 * Method for set view count
	 * 
	 * @return status as response
	 */
	
	@RequestMapping(value = "/setviewstatus/{parentid}/{editcategoryid}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response SetViewStatus(
			@PathVariable("parentid") long parentid,
			@PathVariable("editcategoryid") long editcategoryid,
			HttpSession session) throws Exception {
		service.setviewStatus(parentid, editcategoryid);

		return new Response(1, "view  has been updated!!!");
	}
   
	
	/*
	 * Method for set like count
	 * 
	 * @return status as response
	 */
	
	@RequestMapping(value = "/likereplies/{replyid}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response SetReply(
			@PathVariable("replyid") long replyid, HttpSession session)
			throws Exception {
		long userid = (long) session.getAttribute("userId");
		service.setLikeStatus(replyid, userid);

		return new Response(1, "reply  has been updated!!!");
	}
   
	/*
	 * Method for set dislike count
	 * 
	 * @return status as response
	 */
	
	@RequestMapping(value = "/dislikereplies/{replyid}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response DislikeReplies(
			@PathVariable("replyid") long replyid, HttpSession session)
			throws Exception {
		long userid = (long) session.getAttribute("userId");

		service.setDislikeStatus(replyid, userid);

		return new Response(1, "reply  has been updated!!!");
	}
    
	/*
	 * Method  for set userid
	 * 
	 * @return userid
	 */
	
	@RequestMapping(value = "/getuserid/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody long GetUserId(HttpSession session) throws Exception {
		long id = (long) session.getAttribute("roleId");
		return id;
	}
    
	/*
	 * Method  for delete reply 
	 * 
	 * @return status
	 */
	@RequestMapping(value = "/deletereply/{replyid}", method = RequestMethod.DELETE)
	public @ResponseBody Response DeleteReply(
			@PathVariable("replyid") long replyid, HttpSession session)
			throws Exception {
		service.deleteReplyByAdmin(replyid);
		return new Response(1, "One reply has been deleted successfully!!!");
	}
    
	/*
	 * Method for delete thread 
	 * 
	 * @return status
	 */
	@RequestMapping(value = "/deletethread/{threadid}", method = RequestMethod.DELETE)
	public @ResponseBody Response DeleteThread(
			@PathVariable("threadid") long threadid, HttpSession session)
			throws Exception {
		service.deleteThreadByAdmin(threadid);
		return new Response(1, "One thread has been deleted successfully!!!");
	}
    
	/*
	 * Method for thread details for admin
	 * 
	 * @return response as ForumBean
	 */
	@RequestMapping(value = "/fetchdetailsfordashboard/{forumThreadId}", method = RequestMethod.GET)
	public @ResponseBody ForumBean GetDetailsForDashBoard(
			@PathVariable("forumThreadId") long threadid, HttpSession session)
			throws Exception {
		ForumBean threaddetails = service
				.fetchThreadDetailsForDashboard(threadid);
		return threaddetails;
	}
}
