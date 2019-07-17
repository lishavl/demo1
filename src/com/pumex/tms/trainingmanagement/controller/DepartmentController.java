package com.pumex.tms.trainingmanagement.controller;

/**
 * @Author JOSSINA JOSE.
 * @Date 20/10/2016
 */
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.pumex.tms.company.bean.CompanyBean;
import com.pumex.tms.models.DepartmentDetails;
import com.pumex.tms.trainingmanagement.service.DepartmentService;
import com.pumex.tms.util.AppConstants;
import com.pumex.tms.util.Response;

@Controller
public class DepartmentController {

	@Autowired
	DepartmentService service;

	/*
	 * Method to update or save Department details
	 * 
	 * @return status as response
	 */
	@RequestMapping(value = "/savedepartmentdetails/", method = RequestMethod.POST)
	public @ResponseBody Response SaveDepartmentDetails(
			@RequestParam(value = "data") String jsonData, HttpSession session)
			throws Exception {

		Gson gson = new Gson();

		DepartmentDetails departmentdetails = gson.fromJson(jsonData,
				DepartmentDetails.class);
		try {
			service.saveDepartmentDetails(departmentdetails);
			return new Response(1, "Add Department Successfull!!!");

		} catch (Exception e) {
			return new Response(2, "Failed!!!");
			// TODO: handle exception
		}

	}

	/*
	 * Method for fetch All Department Details
	 * 
	 * @return list as TopicAttendeeBean
	 */
	@RequestMapping(value = "/fetchdepartmentdetails/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List fetchDepartmentDetails() throws Exception {
		
		List departmentlist = service.fetchAllDepartmentDetails();
		return departmentlist;
	}

	@RequestMapping(value = "/fetchforedit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DepartmentDetails fetchDepartmentDetailsForEdit(
			@PathVariable("id") long id) throws Exception {
		
		DepartmentDetails departmentlist = service.departmentDetailsForEdit(id);
		return departmentlist;
	}
	
	@RequestMapping(value = "/deletedepartment/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Response deleteDepartment(
			@PathVariable("id") long id) throws Exception {
		
		try {
			service.deleteDepartment(id);
			return new Response(1, "Department Deleted Successfullly");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new Response(2, "Failed to delete");
		}
	}
	@RequestMapping(value = "/fetchattendeesbydeptid/{deprtid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List fetchAttendeeListByDeptId( @PathVariable("deprtid") long deptid) throws Exception {
		List attendeelist;
		if(deptid !=0){
			
		 attendeelist = service.fetchAttendeeListByDeptId(deptid);
		}
		else{
			
	    attendeelist = service.fetchAttendeeListByDeptIdForAll();
		}
		
		return attendeelist;
	}


}
