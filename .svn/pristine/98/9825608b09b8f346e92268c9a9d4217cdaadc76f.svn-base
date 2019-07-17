package com.pumex.tms.trainingmanagement.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pumex.tms.configurations.Encryption;
import com.pumex.tms.configurations.Mailer;
import com.pumex.tms.models.TrainingCategory;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserRole;
import com.pumex.tms.notifications.bean.NotificationBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean;
import com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean;
import com.pumex.tms.trainingmanagement.service.TrainerTrainingListServiceInterface;
import com.pumex.tms.trainingmanagement.service.TrainingCategoryServiceInterface;
import com.pumex.tms.trainingmanagement.service.TrainingScheduleServiceInterface;
import com.pumex.tms.usermanagement.bean.FeedBackBean;
import com.pumex.tms.usermanagement.bean.Register;
import com.pumex.tms.util.AppConstants;
import com.pumex.tms.util.CommonUtil;
import com.pumex.tms.util.Response;

@Controller
public class TrainerTrainingListController {

	@Autowired
	TrainerTrainingListServiceInterface trainerTrainingListServiceInterface;

	@Autowired
	TrainingScheduleServiceInterface trainingScheduleServiceInterface;

	@Autowired
	TrainingCategoryServiceInterface trainingCategoryServiceInterface;
	
	

	// ------------------Fetch User Roles -------------------
	// ------------------------------------------------------

	@RequestMapping(value = "/fetchuserroles/", method = RequestMethod.GET)
	public @ResponseBody long fetchUserRoles(HttpSession session)
			throws Exception {

		long roleId = 0;

		if ((boolean) session.getAttribute("isAdmin")) {
			roleId = 1;
		}
		if ((boolean) session.getAttribute("isTrainer")
				&& !(boolean) session.getAttribute("isAttendee")) {
			roleId = 2;
		}
		if ((boolean) session.getAttribute("isAttendee")
				&& !(boolean) session.getAttribute("isTrainer")) {
			roleId = 3;
		}

		if ((boolean) session.getAttribute("isAttendee")
				&& (boolean) session.getAttribute("isTrainer")) {
			roleId = 4;
		}
		return roleId;

	}

	// ------------------Fetch Upcoming Training Schedules-------------------
	// ----------------------------------------------------------------------

	@RequestMapping(value = "/fetchmytraininglist/", method = RequestMethod.GET)
	public ResponseEntity<List<TrainerTrainingListBean>> listMyTrainingSchedules(
			HttpSession session) throws Exception {

		long trainerid = (long) session.getAttribute("userId");
		long roleId = (long) session.getAttribute("roleId");

		List<TrainerTrainingListBean> traininglist = null;

		if (roleId == 1) {
			traininglist = trainerTrainingListServiceInterface
					.getAllTrainings();
		}

		else if (roleId == 2) {
			traininglist = trainerTrainingListServiceInterface
					.getMyTrainingList(trainerid);
		}

		else if (roleId == 3) {

			traininglist = trainerTrainingListServiceInterface
					.getAttendeeTrainingList(trainerid);
		}

		if (traininglist.isEmpty() || traininglist == null) {
			return new ResponseEntity<List<TrainerTrainingListBean>>(
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TrainerTrainingListBean>>(traininglist,
				HttpStatus.OK);
	}
    
	// ------------------Fetch Completed Training Schedules by date-------------------
		// ----------------------------------------------------------------------

		@RequestMapping(value = "/searchtopicbydateforcompletedtraining/{startDate}/{endDate}", method = RequestMethod.GET)
		public ResponseEntity<List<TrainerTrainingListBean>> searchTrainingSchedulesByCriteria(
				@PathVariable("startDate")String strtdate,@PathVariable("endDate") String enddte,HttpSession session) throws Exception {
			
			long trainerid = (long) session.getAttribute("userId");
			long roleId = (long) session.getAttribute("roleId");
    
			List<TrainerTrainingListBean> traininglist = null;
			
			if (strtdate != null && strtdate.equals("none")) {
				strtdate = "";
			}
			if (enddte != null && enddte.equals("none")) {
				enddte = "";
			}
            
			
			if (roleId == 1) {
				traininglist = trainerTrainingListServiceInterface
						.getAllTrainingsBydate(strtdate,enddte );
			}

			else if (roleId == 2) {
				traininglist = trainerTrainingListServiceInterface
						.getMyTrainingListByDate(trainerid,strtdate,enddte);
			}

			else if (roleId == 3) {

				traininglist = trainerTrainingListServiceInterface
						.getAttendeeTrainingListByDate(trainerid,strtdate,enddte);
			}

			if (traininglist.isEmpty() || traininglist == null) {
				return new ResponseEntity<List<TrainerTrainingListBean>>(
						HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<TrainerTrainingListBean>>(traininglist,
					HttpStatus.OK);
		}
		
		// ------------------Fetch Upcoming Training Schedules by date-------------------
				// ----------------------------------------------------------------------

				@RequestMapping(value = "/searchtopicbydateforupcomingtraining/{startDate}/{endDate}", method = RequestMethod.GET)
				public ResponseEntity<List<TrainerTrainingListBean>> getUpcomingTrainingByDate(
						@PathVariable("startDate")String strtdate,@PathVariable("endDate") String enddte,HttpSession session) throws Exception {
					
					long trainerid = (long) session.getAttribute("userId");
					long roleId = (long) session.getAttribute("roleId");
		    
					List<TrainerTrainingListBean> traininglist = null;
					
					if (strtdate != null && strtdate.equals("none")) {
						strtdate = "";
					}
					if (enddte != null && enddte.equals("none")) {
						enddte = "";
					}
		            
					
					if (roleId == 1) {
						traininglist = trainerTrainingListServiceInterface
								.getAllUpcomingTrainingsBydate(strtdate,enddte );
					}

					else if (roleId == 2) {
						traininglist = trainerTrainingListServiceInterface
								.getUpcomingTrngForTrainerByDate(trainerid,strtdate,enddte);
					}

					else if (roleId == 3) {

						traininglist = trainerTrainingListServiceInterface
								.getUpcmingAttendeeTrainingListByDate(trainerid,strtdate,enddte);
					}

					if (traininglist.isEmpty() || traininglist == null) {
						return new ResponseEntity<List<TrainerTrainingListBean>>(
								HttpStatus.NO_CONTENT);
					}
					return new ResponseEntity<List<TrainerTrainingListBean>>(traininglist,
							HttpStatus.OK);
				}
		
	// ------------------Fetch Completed Training Schedules-------------------
	// -----------------------------------------------------------------------

	@RequestMapping(value = "/fetchcompletedtraininglist/", method = RequestMethod.GET)
	public ResponseEntity<List<TrainerTrainingListBean>> listCompletedTrainingSchedules(
			HttpSession session) throws Exception {

		long trainerid = (long) session.getAttribute("userId");
		long roleId = (long) session.getAttribute("roleId");

		List<TrainerTrainingListBean> traininglist = null;

		if (roleId == 1) {
			traininglist = trainerTrainingListServiceInterface
					.getAllCompletedTrainings();
		}

		else if (roleId == 2) {

			traininglist = trainerTrainingListServiceInterface
					.getCompletedTrainingList(trainerid);
		}

		else if (roleId == 3) {

			traininglist = trainerTrainingListServiceInterface
					.getAttendeesCompletedTrainingList(trainerid);

		}

		if (traininglist.isEmpty()) {
			return new ResponseEntity<List<TrainerTrainingListBean>>(
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TrainerTrainingListBean>>(traininglist,
				HttpStatus.OK);
	}

	// ------------------Fetch Attendee List-------------------
	// -----------------------------------------------------------

	@RequestMapping(value = "/fetchattendeesbytrainer/{topicid}", method = RequestMethod.GET)
	public ResponseEntity<List<Register>> fetchAllAttendees(
			HttpSession session, @PathVariable("topicid") long topicid)
			throws Exception {

		List<Register> attendeelist = trainerTrainingListServiceInterface
				.fetchAllAttendees(topicid);
		if (attendeelist.isEmpty()) {
			return new ResponseEntity<List<Register>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Register>>(attendeelist, HttpStatus.OK);

	}

	// ------------------Add New Attendees-------------------
	// ------------------------------------------------------

	@RequestMapping(value = "/addattedeesbytrainer/{attendeelist}/{topicid}", method = RequestMethod.POST)
	public @ResponseBody Response addMainCategory(
			@PathVariable("attendeelist") long[] attendees,
			@PathVariable("topicid") long topicid, HttpSession session)
			throws Exception {
		trainerTrainingListServiceInterface.addAttendee(attendees, topicid);
		return new Response(1, "New Category Is Added!!!");
	}

	// ------------------Fetch Training Details By Id-------------------
	// -----------------------------------------------------------------

	@RequestMapping(value = "/fetchparticipatedattendees/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<TrainerTrainingListBean>> getDetails(
			@PathVariable("id") long id, HttpSession session) throws Exception {
		long trainerId = (long) session.getAttribute("userId");
		List<TrainerTrainingListBean> trainerTrainingListBean = trainerTrainingListServiceInterface
				.getTrainingDetails(id, trainerId);
		if (trainerTrainingListBean == null) {
			return new ResponseEntity<List<TrainerTrainingListBean>>(
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<TrainerTrainingListBean>>(
				trainerTrainingListBean, HttpStatus.OK);
	}

	// ------------------Schedule Training -------------------
	// -------------------------------------------------------

	@RequestMapping(value = "/trainingschedules", method = RequestMethod.POST)
	public @ResponseBody Response scheduleTraining(
			@RequestBody TrainingScheduleBean trainingScheduleBean,
			HttpSession session) throws Exception {

		String maincategory = trainingScheduleBean.getMaincategory();
		String subCategory = trainingScheduleBean.getSubCategory();

		String topic = trainingScheduleBean.getTopic();
		String sdate = trainingScheduleBean.getStartDateTime();
		String edate = trainingScheduleBean.getEndDateTime();

		long count = trainingScheduleServiceInterface.getTraining(topic, sdate,
				edate);
		long Categoryid;
		long SubCategoryid = 0;

		if (count == 0) {
			if (maincategory.equals("0")) {
				String categoryname = trainingScheduleBean.getCategoryName();
				Categoryid = trainingScheduleServiceInterface
						.saveCategory(categoryname);
				if (!subCategory.equals("")) {
					String subCategoryName = trainingScheduleBean
							.getSubCategoryName();
					SubCategoryid = trainingScheduleServiceInterface
							.saveSubCategory(subCategoryName, Categoryid);
				} else {
					SubCategoryid = 0;
				}
			} else {
				Categoryid = Long.parseLong(maincategory);
				if (!subCategory.equals("")) {
					if (subCategory.equals("0")) {
						String subCategoryName = trainingScheduleBean
								.getSubCategoryName();
						SubCategoryid = trainingScheduleServiceInterface
								.saveSubCategory(subCategoryName, Categoryid);
					} else {
						SubCategoryid = Long.parseLong(subCategory);
					}
				} else {
					SubCategoryid = 0;
				}

			}

			long userId = (long) session.getAttribute("userId");
			long roleId = (long) session.getAttribute("roleId");

			int length = trainingScheduleBean.getTrainerlist().length + 1;
			long[] trainers = new long[length];

			long[] trainerlist = trainingScheduleBean.getTrainerlist();
			long[] attendeelist = trainingScheduleBean.getAttendeelist();

			if (roleId == 1) {

				if (SubCategoryid == 0) {

					trainingScheduleServiceInterface.scheduleTraining(
							trainingScheduleBean, Categoryid, trainerlist,
							attendeelist);

				} else {

					trainingScheduleServiceInterface.scheduleTraining(
							trainingScheduleBean, SubCategoryid, trainerlist,
							attendeelist);
				}
			}

			else if (roleId == 2) {
				trainers[0] = userId;
				for (int i = 0; i < trainerlist.length; i++) {
					for (int j = 1; j < trainers.length; j++) {
						trainers[j] = trainerlist[i];
					}
				}

				if (SubCategoryid == 0) {

					trainingScheduleServiceInterface.scheduleTraining(
							trainingScheduleBean, Categoryid, trainers,
							attendeelist);

				} else {

					trainingScheduleServiceInterface.scheduleTraining(
							trainingScheduleBean, SubCategoryid, trainers,
							attendeelist);
				}

			}

			return new Response(1, "New Training is Scheduled Successfully!!!");
		} else {
			return new Response(2,
					"This Training Program is Already Scheduled!!!");
		}
	}

	// ------------------Fetch Main Category-------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/fetchmaincategorybytrainer/", method = RequestMethod.GET)
	public ResponseEntity<List<TrainingCategory>> fetchCategory()
			throws Exception {
		List<TrainingCategory> training = trainingCategoryServiceInterface
				.FetchMainCategory();
		if (training.isEmpty()) {
			return new ResponseEntity<List<TrainingCategory>>(
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TrainingCategory>>(training,
				HttpStatus.OK);
	}

	// ------------------Fetch Sub Category-------------------
	// -------------------------------------------------------

	@RequestMapping(value = "/fetchsubcategorybytrainer/{maincategory}", method = RequestMethod.GET)
	public ResponseEntity<List<TrainingCategory>> fetchSubCategory(
			@PathVariable("maincategory") String maincategory,
			HttpSession session) throws Exception {
		long parentid = trainingScheduleServiceInterface
				.getParentID(maincategory);
		List<TrainingCategory> training = trainingCategoryServiceInterface
				.FetchSubCategory(parentid);
		if (training.isEmpty()) {
			return new ResponseEntity<List<TrainingCategory>>(
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TrainingCategory>>(training,
				HttpStatus.OK);
	}

	// ------------------Fetch Trainers-------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/fetchtrainersbytrainer/{text}", method = RequestMethod.GET)
	public ResponseEntity<List<Register>> fetchUserSkills(
			@PathVariable("text") String text, HttpSession session)
			throws Exception {

		long roleId = (long) session.getAttribute("roleId");
		long userId = (long) session.getAttribute("userId");

		List<Register> trainers = null;
		if (roleId == 1) {
			trainers = trainingScheduleServiceInterface.FetchTrainers(text);
		} else if (roleId == 2) {

			trainers = trainingScheduleServiceInterface.FetchTrainers(text,
					userId);

		}
		if (trainers.isEmpty()) {
			return new ResponseEntity<List<Register>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Register>>(trainers, HttpStatus.OK);
	}

	// ------------------Fetch Attendees-------------------
	// ----------------------------------------------------

	@RequestMapping(value = "/fetchattendeebytrainers/{text}/{deptid}", method = RequestMethod.GET)
	public ResponseEntity<List<Register>> fetchAttendee(
			@PathVariable("text") String text,
			@PathVariable("deptid") long deptid, HttpSession session)
			throws Exception {

		List<Register> trainers;
		if ((deptid == 0)) {
			trainers = trainingScheduleServiceInterface.FetchAttendee(text);
		} else {
			trainers = trainingScheduleServiceInterface.FetchAttendee(text,
					deptid);
		}
		if (trainers.isEmpty()) {
			return new ResponseEntity<List<Register>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Register>>(trainers, HttpStatus.OK);
	}

	// ------------------Fetch Feedback List-------------------
	// --------------------------------------------------------

	@RequestMapping(value = "/feedbacklistfortrainer/{attendeeid}/{topicid}", method = RequestMethod.GET)
	public ResponseEntity<FeedBackBean> getFeedback(
			@PathVariable("attendeeid") long attendeeid,
			@PathVariable("topicid") long topicid, HttpSession session)
			throws Exception {
		long trainerId = (long) session.getAttribute("userId");
		FeedBackBean attendeeFeedback = trainerTrainingListServiceInterface
				.getFeedBack(attendeeid, trainerId, topicid);
		if (attendeeFeedback == null) {
			return new ResponseEntity<FeedBackBean>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FeedBackBean>(attendeeFeedback, HttpStatus.OK);

	}

	// ------------------Fetch Expected Attendee List-------------------
	// -----------------------------------------------------------------

	@RequestMapping(value = "/fetchexpectedattedees/{topicid}", method = RequestMethod.GET)
	public ResponseEntity<List<FeedBackBean>> getExpectedAttendees(
			@PathVariable("topicid") long topicid, HttpSession session)
			throws Exception {

		List expectedattendee = trainerTrainingListServiceInterface
				.getAttendees(topicid);
		if (expectedattendee == null) {
			return new ResponseEntity<List<FeedBackBean>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<FeedBackBean>>(expectedattendee,
				HttpStatus.OK);

	}

	// ------------------Delete Training Schedules-------------------
	// --------------------------------------------------------------

	@RequestMapping(value = "/deletetrainingschedule/{id}", method = RequestMethod.GET)
	public @ResponseBody Response deleteSchedules(@PathVariable("id") long id)
			throws Exception {
		trainingScheduleServiceInterface.deleteSchedule(id);
		return new Response(1, "successfully delete Training schedules!!!!!");
	}

	// ------------------Fetch Training Schedule By Id-------------------
	// ------------------------------------------------------------------

	@RequestMapping(value = "/retrievetrainingschedulesbyid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TrainingScheduleBean> getSchedules(
			@PathVariable("id") long id) throws Exception {
		// System.out.println("Fetch Training Schedule with id" + id);
		TrainingScheduleBean trainingScheduleBean = trainingScheduleServiceInterface
				.getScheduleById(id);
		if (trainingScheduleBean == null) {
			// System.out.println("Training schedule with id " + id +
			// " not found");
			return new ResponseEntity<TrainingScheduleBean>(
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TrainingScheduleBean>(trainingScheduleBean,
				HttpStatus.OK);

	}

	// ------------------ Update Training Schedules -------------------
	// ----------------------------------------------------------------

	@RequestMapping(value = "/updatetrainingschedules/{id}", method = RequestMethod.PUT)
	public @ResponseBody Response updateCategory(@PathVariable("id") long id,
			@Valid @RequestBody TrainingScheduleBean trainingScheduleBean)
			throws Exception {

		// return new Response(1, "success");

		String maincategory = trainingScheduleBean.getMaincategory();
		String subCategory = trainingScheduleBean.getSubCategory();

		long Categoryid;
		long SubCategoryid = 0;

		if (maincategory.equals("0")) {
			String categoryname = trainingScheduleBean.getCategoryName();
			Categoryid = trainingScheduleServiceInterface
					.saveCategory(categoryname);
			if (!subCategory.equals("")) {
				String subCategoryName = trainingScheduleBean
						.getSubCategoryName();
				SubCategoryid = trainingScheduleServiceInterface
						.saveSubCategory(subCategoryName, Categoryid);
			} else {
				SubCategoryid = 0;
			}
		} else {
			Categoryid = Long.parseLong(maincategory);
			if (null != subCategory) {
				if (subCategory.equals("0")) {
					String subCategoryName = trainingScheduleBean
							.getSubCategoryName();
					SubCategoryid = trainingScheduleServiceInterface
							.saveSubCategory(subCategoryName, Categoryid);
				} else {
					SubCategoryid = Long.parseLong(subCategory);
				}
			} else {
				SubCategoryid = 0;
			}

		}

		long[] trainerlist = trainingScheduleBean.getTrainerlist();
		long[] attendeelist = trainingScheduleBean.getAttendeelist();

		if (SubCategoryid == 0) {

			trainingScheduleServiceInterface
					.updateSchedule(trainingScheduleBean, Categoryid,
							trainerlist, attendeelist);
		} else {
			trainingScheduleServiceInterface.updateSchedule(
					trainingScheduleBean, SubCategoryid, trainerlist,
					attendeelist);
		}
		return new Response(1, "Training Scheduled Updated Successfully!!!");
	}

	// ------------------Fetch All Trainer List For Attendee Feedback
	// View-------------------
	// -----------------------------------------------------------------

	@RequestMapping(value = "/fetchalltrainersforfeedback/{topicid}", method = RequestMethod.GET)
	public ResponseEntity<List<FeedBackBean>> getAllTrainers(
			@PathVariable("topicid") long topicid, HttpSession session)
			throws Exception {

		long id = ((long) session.getAttribute("userId"));

		List trainers = trainerTrainingListServiceInterface.getAllTrainers(
				topicid, id);
		if (trainers == null) {
			return new ResponseEntity<List<FeedBackBean>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<FeedBackBean>>(trainers, HttpStatus.OK);

	}

	// ------------------Send Mail Notification For Attendees-------------------
	// -------------------------------------------------------------------------

	@RequestMapping(value = "/sendingmailforattendees/{topicid}", method = RequestMethod.POST)
	public @ResponseBody Response sendMailForTraining(
			@PathVariable("topicid") long topicid, HttpSession session,
			HttpServletRequest request) throws Exception {

		List<NotificationBean> todaystraininglist = trainerTrainingListServiceInterface
				.getTrainingDetails(topicid);

		if (!todaystraininglist.isEmpty()) {

			for (NotificationBean test : todaystraininglist) {
				boolean mail = false;
				String message = "Hi " + test.getFirstName() + " "
						+ test.getLastName() + ",<br>" + "<br>"
						+ test.getTopicname() + " has been scheduled on "
						+ test.getSdate() + "."
						+ " Please consider this as a reminder. <br><br>"
						+ "Thanks & Regards" + "<br><br>" + "Team TMS";
				Mailer.sendMail(test.getEmail(), "Training Reminder", message);

				System.out.println(message);
			}

		}

		trainerTrainingListServiceInterface.setEmailStatus(topicid);

		return new Response(1, "success");
	}

	/*
	 * Method to get all training programs for Calendar
	 * 
	 * @return list of training programs
	 */
	@RequestMapping(value = "/fetchalltrainingprogramsforcalendar/", method = RequestMethod.GET)
	public @ResponseBody List<TrainerTrainingListBean> getAllTrainingList(
			HttpSession session) throws Exception {

		List lst = trainerTrainingListServiceInterface.getAllTrainingList(
				(long) session.getAttribute("userId"),
				(long) session.getAttribute("roleId"));

		return lst;
	}

}
