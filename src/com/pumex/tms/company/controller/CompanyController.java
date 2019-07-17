package com.pumex.tms.company.controller;
/**
 * @Author JOSSINA JOSE.
 *
 */

import java.io.File;
import java.io.FileOutputStream;

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
import com.pumex.tms.company.service.CompanyService;
import com.pumex.tms.forum.bean.ForumBean;
import com.pumex.tms.util.AppConstants;
import com.pumex.tms.util.Response;

@Controller
public class CompanyController {

	@Autowired
	CompanyService service;
    
	/*
	 * Method to update or save company details
	 * 
	 * @return status as response
	 */
	@RequestMapping(value = "/saveCompanyProfile/", method = RequestMethod.POST)
	public @ResponseBody Response SaveCompanyDetails(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "data") String jsonData, HttpSession session)
			throws Exception {

		Gson gson = new Gson();
		CompanyBean companydetails = gson.fromJson(jsonData, CompanyBean.class);
		String abcd = Long.toString(companydetails.getId());
		
		if (file != null) {
			File convFile = new File(AppConstants.FILES_PATH + "logo/"
					+ "company_logo.jpeg");
			convFile.createNewFile();
			companydetails.setLogo("company_logo.jpeg");
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		}
		
        try {
        	service.saveCompanyDetails(companydetails);
        	return new Response(1, "Add CompanyDetails Successfull!!!");
			
		} catch (Exception e) {
			return new Response(2, "Failed!!!");
			// TODO: handle exception
		}

	}
	
	/*
	 * Method to get company details for edit
	 * 
	 * @return CompanyBean
	 */
	@RequestMapping(value = "/fetchcompanydetails/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CompanyBean getReplyForEdit(HttpSession session)
			throws Exception {
		CompanyBean details = service.getCompanyDetails();
		return details;
	}
}
