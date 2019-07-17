/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.trainingmanagement.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pumex.tms.configurations.Encryption;
import com.pumex.tms.configurations.Mailer;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.onlinetest.bean.TestReportBean;
import com.pumex.tms.onlinetest.bean.TestResultBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;
import com.pumex.tms.trainingmanagement.bean.TrainingCategoryBean;
import com.pumex.tms.usermanagement.bean.FeedBackBean;
import com.pumex.tms.usermanagement.service.FeedbackService;
import com.pumex.tms.util.CommonUtil;
import com.pumex.tms.util.Response;

@Controller
public class UpcomingTrainingController {

	@Autowired
	FeedbackService service;
    

	/*
	 * Method for fetch All UpComing Training Details
	 * 
	 * @return  list as FeedBackBean
	 */
	@RequestMapping(value = "/fetchAllUpcomingTrainingList/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FeedBackBean>> listAllUpcomingTrainings(
			HttpSession session) throws Exception {
		long id = ((long) session.getAttribute("userId"));
		List<FeedBackBean> upcomingtrainings = service
				.getAllUpcomingTrainings(id);
		if (upcomingtrainings.isEmpty()) {
			return new ResponseEntity<List<FeedBackBean>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FeedBackBean>>(upcomingtrainings,
				HttpStatus.OK);
	}
	
	/*
	 * Method for fetch All Completed Training Details
	 * 
	 * @return  list as FeedBackBean
	 */
	@RequestMapping(value = "/fetchAllCompleted/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FeedBackBean>> ListAllCompletedTrainings(
			HttpSession session) throws Exception {
		long id = ((long) session.getAttribute("userId"));
		List<FeedBackBean> completedtrainings = service
				.getAllCompletedTrainings(id);
		if (completedtrainings.isEmpty()) {
			return new ResponseEntity<List<FeedBackBean>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FeedBackBean>>(completedtrainings,
				HttpStatus.OK);
	}
    
	/*
	 * Method for fetch Attendee Test Result Details
	 * 
	 * @return  list as TestReportBean
	 */
	@RequestMapping(value = "/fetchAttendeeTestResult/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TestReportBean> fetchAllAttendeeTestResult(
			HttpSession session) throws Exception {
		long id = ((long) session.getAttribute("userId"));
		TestReportBean reportBean = new TestReportBean();
		List<TestResultBean> upcomingtrainings = service
				.getAllTestResult(id);
		long countpassnumber=0;
		long countfailednumber=0;
		
		for(TestResultBean test:upcomingtrainings){
			if(test.getMark()>=test.getPassMark()){
				countpassnumber++;
			}
			else{
				countfailednumber++;
			}
		}
		reportBean.setTestresult(upcomingtrainings);
		reportBean.setNopass(countpassnumber);
		reportBean.setNofail(countfailednumber);
		if (upcomingtrainings.isEmpty()) {
			return new ResponseEntity<TestReportBean>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<TestReportBean>(reportBean,
				HttpStatus.OK);
	}
    
	/*
	 * Method for Request for training and send mail to the trainer 
	 * 
	 * @return  status as response
	 */
	@RequestMapping(value = "/showrequestfortraining/{topicid}", method = RequestMethod.POST)
	public @ResponseBody Response sendMailForTraining(
			@PathVariable("topicid") long topicid,
			 HttpSession session,
			HttpServletRequest request) throws Exception {
		long id = ((long) session.getAttribute("userId"));
		List <TrainerTopicBean> trainerlist=service.getAllTrainerNamesForMail(topicid);
		
		
		
		UserDetails user = (UserDetails) session.getAttribute("userDetails");
		String adminmail="admin@tms.com";
		for(TrainerTopicBean test:trainerlist){
		boolean mail = false;
		String message = "Hi "
				+ test.getName()
				+ ",<br>"
				+ "<br>"
				+ "<br>"
				+ user.getFirstName()
				+ " "
				+ user.getLastName()
				+ " has been shown interest for your "
				+ test.getTopic()
				+ " training on "
				+ test.getDate()
				+ " <br>"
				+ " <br>"
				+ " <br>"
				+ "Please click on the below link to activate your account. <br>"
				+ " <br>"
				+ " <br>"
				+ " <br>"
				
				+ CommonUtil.getAppURL(request) + "#/"
				+ "addattendee/interested/secretKey="
				+ Encryption.encryptAndEncode(id + "") + "/topicid="
				+ Encryption.encryptAndEncode(topicid + "") + "/trainerid="
				+ Encryption.encryptAndEncode(test.getTrainerid() + "") + "<br>"
				+ " <br>"
				+ " <br>"
				+ " <br>"
				+ "Thank You"

				+ "<br>" + "Regards " + user.getFirstName() + " "
				+ user.getLastName();
		Mailer.sendMail(
				test.getMail(),adminmail,"Attendee details for training",message);
		System.out.println(message);
		}

		try {
			
			service.setStatusAftrMail(id, topicid);
			return new Response(1, "success");

		} catch (Exception e) {
			return new Response(2, "Sending Failed");
		}
	}
    
	/*
	 * Method for fetch interested attendee details for trainer from mail url
	 * 
	 * @return  response  as FeedBackBean
	 */
	@RequestMapping(value = "/fetchAllInterestedAttendees/{userid}/{topicid}/{trainerid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FeedBackBean> listAllInterestedAttendeeList(
			HttpSession session, HttpServletRequest request,
			@PathVariable("userid") String encryptedUserId,
			@PathVariable("topicid") String encryptedTopicId,
			@PathVariable("trainerid") String encryptedTrainerId)
			throws Exception {
		String[] parts = encryptedUserId.split("=");
		String p = parts[1];
		long id = Long.parseLong(Encryption.decodeAndDecrypt(p));

		String[] topicids = encryptedTopicId.split("=");
		long topicid = Long.parseLong(Encryption.decodeAndDecrypt(topicids[1]));

		String[] trainerids = encryptedTrainerId.split("=");
		long trainerid = Long.parseLong(Encryption
				.decodeAndDecrypt(trainerids[1]));


		FeedBackBean fdbck = new FeedBackBean();

		UserDetails AttendeeDetails = service.getAttendeeDetails(id);
		TrainingTopic topics = service.getAllTopicDetails(topicid);

		fdbck.setId(AttendeeDetails.getUserId());
		fdbck.setTopicid(topics.getId());
		fdbck.setStartDateTime(topics.getStartDateTime());
		fdbck.setTopicname(topics.getTopic());
		fdbck.setAttendeefn(AttendeeDetails.getFirstName());
		fdbck.setAttendeeln(AttendeeDetails.getLastName());
		if (fdbck == null) {
			return new ResponseEntity<FeedBackBean>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<FeedBackBean>(fdbck, HttpStatus.OK);

	}
	
	/*
	 * Method for add interested attendee details 
	 * 
	 * @return  status as response
	 */
	@RequestMapping(value = "/addinterestedattendee/{topicid}/{userid}", method = RequestMethod.POST)
	public @ResponseBody Response addInterestedAttendee(
			@PathVariable("topicid") long topicid,
			@PathVariable("userid") long userid, HttpSession session)
			throws Exception {
		 long count=service.isAttendeeExist(topicid,userid);
		 if(count==0){
			 
			 service.addAttendeeDetails(topicid, userid);
			 return new Response(1, "Add Interested Attendee Success!!!");
		 }
		 else{
			 return new Response(2, "Attendee already added!!!");
		 }
	}
}
