package com.pumex.tms.trainingmanagement.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.pumex.tms.models.TrainingCategory;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.onlinetest.bean.TestResultBean;
import com.pumex.tms.trainingmanagement.bean.CategoryBean;
import com.pumex.tms.trainingmanagement.bean.TrainingCategoryBean;
import com.pumex.tms.trainingmanagement.service.TrainingCategoryServiceInterface;
import com.pumex.tms.trainingmanagement.service.TrainingScheduleServiceInterface;
import com.pumex.tms.usermanagement.bean.Register;
import com.pumex.tms.util.Response;

/**
 * @Author Reshma Manoj.
 * @Date 01/07/2016
 */

@Controller
public class TrainingCategoryController {

	@Autowired
	TrainingCategoryServiceInterface trainingCategoryServiceInterface;

	// ------------------Fetch Category-------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/fetchcategory/", method = RequestMethod.GET)
	public ResponseEntity<List<TrainingCategory>> fetchCategory()
			throws Exception {
		List<TrainingCategory> training = trainingCategoryServiceInterface
				.getCategory();
		if (training.isEmpty()) {
			return new ResponseEntity<List<TrainingCategory>>(
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TrainingCategory>>(training,
				HttpStatus.OK);
	}

	// -------------------Add New Category-------------------
	// --------------------------------------------------------

	@RequestMapping(value = "/addtrainingcategory/", method = RequestMethod.POST)
	public @ResponseBody Response addTrainingCategory(
			@RequestBody TrainingCategoryBean trainingCategory,
			HttpSession session) throws Exception {
		String category = trainingCategory.getCategory();
		long count = trainingCategoryServiceInterface.getCategory(category);
		// System.out.println("Count in Controller----->" + count);
		if (count == 0) {
			trainingCategoryServiceInterface.addCategory(trainingCategory);
			return new Response(1, "New Category Is Added!!!");
		} else {
			return new Response(2, "This Category Is Already Exist!!!");
		}
	}

	// -------------------Add New Category Under Main Category-------------------
	// --------------------------------------------------------------------------

	@RequestMapping(value = "/addsubcategory/{maincategory}", method = RequestMethod.POST)
	public @ResponseBody Response addMainCategory(
			@PathVariable("maincategory") long maincategory,
			@RequestBody TrainingCategoryBean trainingCategory,
			HttpSession session) throws Exception {
		String category = trainingCategory.getCategory();
		long count = trainingCategoryServiceInterface.getCategory(category);
		if (count == 0) {
			trainingCategoryServiceInterface.addSubCategory(trainingCategory,
					maincategory);
			return new Response(1, "New Category Is Added!!!");
		} else {
			return new Response(2, "This Category Is Already Exist!!!");
		}
	}

	// ------------------List All Categories-------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/fetchtrainingcategories/", method = RequestMethod.GET)
	public ResponseEntity<List<TrainingCategoryBean>>listAllTrainingCategories()
			throws Exception {
		List<TrainingCategoryBean> category = (List<TrainingCategoryBean> ) trainingCategoryServiceInterface.getAllCategories();
		
		if (!category.isEmpty()) {
		return new ResponseEntity<List<TrainingCategoryBean>>(category,
		HttpStatus.OK);
		}
		else{
		
		return new ResponseEntity<List<TrainingCategoryBean>>(
				HttpStatus.NO_CONTENT);
	}
		
	}

	// ------------------Delete Categories-------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/deletecategory/{id}", method = RequestMethod.GET)
	public @ResponseBody Response deleteUser(@PathVariable("id") long id)
			throws Exception {
		long count = trainingCategoryServiceInterface.getCategoryCount(id);
		if (count > 0) {
			trainingCategoryServiceInterface.deleteCategoryandsubcategory(id);
			return new Response(1, "successfully delete category and subcategories!!!!!");
		} else {
			trainingCategoryServiceInterface.deleteCategory(id);
			return new Response(2, "successfully delete category!!!!!");
		}

	}

	// ------------------Fetch Single Category-------------------
	// ---------------------------------------------------
	@RequestMapping(value = "/fetchsinglecategory/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TrainingCategory> getCategoryById(
			@PathVariable("id") long id) throws Exception {

		TrainingCategory category = trainingCategoryServiceInterface
				.editCategory(id);
		if (category == null) {
			
			return new ResponseEntity<TrainingCategory>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<TrainingCategory>(category, HttpStatus.OK);
	}

	// ------------------Update Training Category-------------------
	// -------------------------------------------------

	@RequestMapping(value = "/updatecategory/{id}/{category}", method = RequestMethod.PUT)
	public @ResponseBody Response updateCategory(@PathVariable("id") long id,
			@PathVariable("category") long category,
			@Valid @RequestBody TrainingCategoryBean trainingcategory)
			throws Exception {
		
//		System.out.println("Category In Controller---->"+category);
//		 System.out.println("Updating Category with id " + id);

		TrainingCategory training = trainingCategoryServiceInterface
				.editCategory(id);
		
		training.setCategory(trainingcategory.getCategory());
		training.setDescription(trainingcategory.getDescription());
		training.setParentId(category);
		training.setIstrainingcategory(trainingcategory.getIstrainingcategory());
		training.setIsforumcategory(trainingcategory.getIsforumcategory());
//		System.out.println("ParentId"+training.getParentId());
//		if (category != 0) {
//			
//		}
//		else{
//			
//			training.setParentId(0);
//		}
		trainingCategoryServiceInterface.updateCategory(training);
		return new Response(1, "success");
	}

}
