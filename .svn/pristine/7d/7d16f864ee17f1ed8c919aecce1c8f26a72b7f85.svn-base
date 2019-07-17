/*
 * @Author Reshma Manoj
 */

package com.pumex.tms.trainingmanagement.controller;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.pumex.tms.models.TrainingCategory;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserSkills;
import com.pumex.tms.trainingmanagement.bean.TrainingCategoryBean;
import com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean;
import com.pumex.tms.trainingmanagement.service.TrainingCategoryServiceInterface;
import com.pumex.tms.trainingmanagement.service.TrainingScheduleServiceInterface;
import com.pumex.tms.usermanagement.bean.Register;
import com.pumex.tms.util.Response;

@Controller
public class TrainingScheduleController {

	@Autowired
	TrainingScheduleServiceInterface trainingScheduleServiceInterface;

	@Autowired
	TrainingCategoryServiceInterface trainingCategoryServiceInterface;

	// ------------------Fetch Main Category-------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/fetchmaincategory/", method = RequestMethod.GET)
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
	// ---------------------------------------------------

	@RequestMapping(value = "/fetchsubcategory/{maincategory}", method = RequestMethod.GET)
	public ResponseEntity<List<TrainingCategory>> fetchSubCategory(
			@PathVariable("maincategory") long maincategory, HttpSession session)
			throws Exception {

		List<TrainingCategory> training = trainingCategoryServiceInterface
				.FetchSubCategory(maincategory);
		if (training.isEmpty()) {
			return new ResponseEntity<List<TrainingCategory>>(
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TrainingCategory>>(training,
				HttpStatus.OK);
	}

	// ------------------Fetch Trainers-------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/fetchtrainers/{text}", method = RequestMethod.GET)
	public ResponseEntity<List<Register>> fetchUserSkills(
			@PathVariable("text") String text, HttpSession session)
			throws Exception {
		List<Register> trainers = trainingScheduleServiceInterface
				.FetchTrainers(text);
		if (trainers.isEmpty()) {
			return new ResponseEntity<List<Register>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Register>>(trainers, HttpStatus.OK);
	}

	// ------------------Fetch Attendees-------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/fetchattendees/{text}", method = RequestMethod.GET)
	public ResponseEntity<List<Register>> fetchAttendees(
			@PathVariable("text") String text, HttpSession session)
			throws Exception {
		List<Register> attendees = trainingScheduleServiceInterface
				.FetchAttendees(text);
		if (attendees.isEmpty()) {
			return new ResponseEntity<List<Register>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Register>>(attendees, HttpStatus.OK);
	}

	// ------------------Schedule Training-------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/trainingsschedule", method = RequestMethod.POST)
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

	// ------------------Fetch All Training Schedule-------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/fetchscheduledtrainings/", method = RequestMethod.GET)
	public ResponseEntity<List<TrainingTopic>> listAllTrainingSchedules()
			throws Exception {

		List<TrainingTopic> schedules = trainingScheduleServiceInterface
				.getAllTrainingSchedules();
		if (schedules.isEmpty()) {
			return new ResponseEntity<List<TrainingTopic>>(
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TrainingTopic>>(schedules, HttpStatus.OK);
	}

	// ------------------Fetch Training Schedule By Id-------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/fetchtrainingschedulesbyid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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

	// ------------------Delete Training Schedules-------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/deleteSchedule/{id}", method = RequestMethod.GET)
	public @ResponseBody Response deleteSchedules(@PathVariable("id") long id)
			throws Exception {
		trainingScheduleServiceInterface.deleteSchedule(id);
		return new Response(1, "successfully delete Training schedules!!!!!");
	}

	// ------------------Update Training Schedules-------------------
	// --------------------------------------------------------------

	@RequestMapping(value = "/updatetrainingschedulesss/{id}", method = RequestMethod.PUT)
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

}