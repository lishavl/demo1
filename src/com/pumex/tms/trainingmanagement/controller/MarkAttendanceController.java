/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.trainingmanagement.controller;

/**
 * @Author JOSSINA JOSE.
 * @Date 20/07/2016
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pumex.tms.notifications.service.NotificationServiceInterface;
import com.pumex.tms.onlinetest.service.TestManagementService;
import com.pumex.tms.trainingmanagement.bean.TopicAttendeeBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;
import com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean;
import com.pumex.tms.trainingmanagement.service.MarkAttendaceService;
import com.pumex.tms.util.Response;

@Controller
public class MarkAttendanceController {

	@Autowired
	TestManagementService testManagementServiceInterface;

	@Autowired
	MarkAttendaceService service;
	
	@Autowired
	NotificationServiceInterface notificationServiceInterface;
	
	/*
	 * Method for fetch All Training names
	 * 
	 * @return  list as TrainerTopicBean
	 */
	@RequestMapping(value = "/fetchAllSessions/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List getAllTrainings(HttpSession session)
			throws Exception {
		long id = (long) session.getAttribute("userId");
		long roleid = (long) session.getAttribute("roleId");
		List<TrainerTopicBean> test1;
		List<TrainerTopicBean> test2;
		if (roleid == 2) {

			return test1 = service.getAllTopicNames(id);
		} else {
			return test2 = service.getAllTopicNamesForAdmin();
		}
	}
   
	/*
	 * Method for fetch All Attendee names
	 * 
	 * @return  list as TopicAttendeeBean
	 */
	@RequestMapping(value = "/fetchallUsers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List getAllUsers(@PathVariable("id") long id)
			throws Exception {
		List<TopicAttendeeBean> test = service.getAllAttendees(id);
		
		
		return test;
	}
	
	/*
	 * Method for check date for attendance
	 * 
	 * @return  status as response
	 */
	@RequestMapping(value = "/checkdateforattendance/{trainingid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response CheckDateForAttendance(@PathVariable("trainingid") long trainingid)
			throws Exception {
		 if(service.getDateForAttendance(trainingid)){
   		  return new Response(1, "Attendance has been added!!!");
   	     }
   	     else{
   		  return new Response(2, "Date for marking attendance has expired. Please contact administrator");
   	    }
	}
    
	/*
	 * Method for update attendance
	 * 
	 * @return  status as response
	 */
	@RequestMapping(value = "/addattendance/", method = RequestMethod.POST)
	public @ResponseBody Response addAttendance(
			@RequestParam(value = "data") String jsonData,HttpSession session) throws Exception {

		Gson gson = new Gson();
		TopicAttendeeBean topicattendee = gson.fromJson(jsonData,
				TopicAttendeeBean.class);
		long roleid = (long) session.getAttribute("roleId");
		Long[] usrids = topicattendee.getUserids();
		long testid = topicattendee.getTestid();

		List<Long> lists = Arrays.asList(usrids);
		List<TopicAttendeeBean> test = service.getAllAttendees(testid);
      if(roleid==1){
    	  if (usrids.length == 0) {
				List<Long> list = new ArrayList<>();
				for (TopicAttendeeBean temp : test) {
					list.add(temp.getId());
					service.CancelAttendanceForAllAttndees(list,testid);
					/*service.cancelTopicCompletionStatus(testid, temp.getId());*/
				}
			}

			else {
				service.addAttendance(lists, testid);
				notificationServiceInterface.getFeedbackList(testid);
				
			}
			return new Response(1, "Attendance has been added!!!");
    	  
      }else{
    	  if(service.getDateForAttendance(testid)){
    		  if (usrids.length == 0) {
    			  List<Long> list = new ArrayList<>();
    			  for (TopicAttendeeBean temp : test) {
    				  list.add(temp.getId());
    				  service.CancelAttendanceForAllAttndees(list,testid);
    				  /*service.cancelTopicCompletionStatus(testid, temp.getId());*/
    			  }
    		  }
    		  
    		  else {
    			  service.addAttendance(lists, testid);
    			  notificationServiceInterface.getFeedbackList(testid);
    			  
    		  }
    		  return new Response(1, "Attendance has been added!!!");
    		  
    	  }
    	  else{
    		  
    		  return new Response(2, "Date for marking attendance is expired. Please contact administrator");
    	  }
      }
		

	}

}
