/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.usermanagement.controller;

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

import com.google.gson.Gson;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.trainingmanagement.bean.TopicAttendeeBean;
import com.pumex.tms.usermanagement.bean.FeedBackBean;
import com.pumex.tms.usermanagement.service.FeedbackService;
import com.pumex.tms.util.Response;

@Controller
public class FeedBackController {

	@Autowired
	FeedbackService service;
    
	/*
	 * Method for fetch all feedback details
	 * 
	 * @return  list as Feedback bean
	 */
	@RequestMapping(value = "/fetchAllFeedBackList/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FeedBackBean>> listAllFeedbacks(HttpSession session)
			throws Exception {
		long id = ((long) session.getAttribute("userId"));
		List<FeedBackBean> tests = service.getAllFeedBackList(id);
		if (tests.isEmpty()) {
			return new ResponseEntity<List<FeedBackBean>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FeedBackBean>>(tests, HttpStatus.OK);
	}
   
	/*
	 * Method for fetch trainer name
	 * 
	 * @return  as Feedback bean
	 */
	@RequestMapping(value = "/fetchTrainerName/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FeedBackBean> fetchTrainerName(
			@PathVariable("id") long id, HttpSession session) throws Exception {
		/* long id = ((long) session.getAttribute("userId")); */
		FeedBackBean tests = service.getTrainerName(id);
		if (tests == null) {
			return new ResponseEntity<FeedBackBean>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FeedBackBean>(tests, HttpStatus.OK);

	}
   
	/*
	 * Method for update feedback
	 * 
	 * @return  status
	 */
	@RequestMapping(value = "/updatefeedback/", method = RequestMethod.POST)
	public @ResponseBody Response updateFeedbak(
			@RequestParam(value = "data") String jsonData, HttpSession session)
			throws Exception {
		
		Gson gson = new Gson();

		FeedBackBean feedback = gson.fromJson(jsonData, FeedBackBean.class);
		long id = ((long) session.getAttribute("userId"));
		try {
			service.updateFeedback(feedback, id);
			return new Response(1, "success");
			
		} catch (Exception e) {
			return new Response(2, "Failed");
			// TODO: handle exception
		}

	}
	
	/*
	 * Method for get feedback details
	 * 
	 * @return  feedback details as FeedBackBean
	 */
	@RequestMapping(value = "/viewfeedback/{topicid}/{trainerid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FeedBackBean> viewFeedBack(
			@PathVariable("topicid") long topicid,
			@PathVariable("trainerid") long trainerid, HttpSession session)
			throws Exception {
		long id = ((long) session.getAttribute("userId"));
		FeedBackBean tests = service.viewFeedback(id, topicid, trainerid);

		if (tests == null) {
			return new ResponseEntity<FeedBackBean>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FeedBackBean>(tests, HttpStatus.OK);

	}
    
	/*
	 * Method for fetch all topic names for admin feedback
	 * 
	 * @return  topic names as TrainingTopic list
	 */
	@RequestMapping(value = "/fetchalltopicnamesforadmin/", method = RequestMethod.GET)
	public ResponseEntity<List<TrainingTopic>> fetchTopicNames()
			throws Exception {
		List<TrainingTopic> topicnames = service.getAllTopicNames();
		if (topicnames.isEmpty()) {
			return new ResponseEntity<List<TrainingTopic>>(
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TrainingTopic>>(topicnames,
				HttpStatus.OK);
	}
     
	/*
	 * Method for fetch all trainer names for admin feedback
	 * 
	 * @return  trainer names as TopicTrainer list
	 */
	@RequestMapping(value = "/fetchalltrainernamesforadmin/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<TopicTrainer>> FetchTrainerNames(
			@PathVariable("id") long id) throws Exception {
		List<TopicTrainer> trainernames = service
				.getAllTrainerNamesForAdmin(id);
		if (trainernames.isEmpty()) {
			return new ResponseEntity<List<TopicTrainer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TopicTrainer>>(trainernames,
				HttpStatus.OK);
	}
   
	/*
	 * Method for fetch all attendee details  for admin feedback
	 * 
	 * @return  attendee  names as TopicAttendeeBean list
	 */
	@RequestMapping(value = "/fetchallattendeesforadminfeedback/{topicid}/{trinerid}", method = RequestMethod.GET)
	public ResponseEntity<List<TopicAttendeeBean>> fetchAllAttendeesForAdminFeedback(
			@PathVariable("topicid") long topicid,
			@PathVariable("trinerid") long trainerid) throws Exception {
		List<TopicAttendeeBean> attendeenames = service
				.getAllAttendeeForAdminFeedback(topicid, trainerid);
		if (attendeenames.isEmpty()) {
			return new ResponseEntity<List<TopicAttendeeBean>>(
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TopicAttendeeBean>>(attendeenames,
				HttpStatus.OK);
	}
    
	/*
	 * Method for fetch feedback  for admin feedback
	 * 
	 * @return  feedback   FeedBackBean
	 */
	@RequestMapping(value = "/fetchfeedbackforadmin/{attendeeid}/{topicid}/{trainerid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FeedBackBean> viewFeedBackForAdmin(
			@PathVariable("topicid") long topicid,
			@PathVariable("trainerid") long trainerid,
			@PathVariable("attendeeid") long attendeeid, HttpSession session)
			throws Exception {
		FeedBackBean tests = service.viewFeedback(attendeeid, topicid,
				trainerid);

		if (tests == null) {
			return new ResponseEntity<FeedBackBean>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FeedBackBean>(tests, HttpStatus.OK);

	}

}
