package com.pumex.tms.usermanagement.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.pumex.tms.configurations.Encryption;
import com.pumex.tms.models.Role;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserRole;
import com.pumex.tms.models.UserSkills;
import com.pumex.tms.usermanagement.bean.Register;
import com.pumex.tms.usermanagement.service.UpdateProfileServiceInterface;
import com.pumex.tms.util.AppConstants;
import com.pumex.tms.util.Response;

@Controller
public class UpdateProfileController {

	@Autowired
	UpdateProfileServiceInterface updateProfileServiceInterface;

	// ------------------Fetch User By Id-------------------
	// -------------------------------------------------
	@RequestMapping(value = "/updateprofile/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Register> getUser(HttpSession session)
			throws Exception {

		long userId = (long) session.getAttribute("userId");
		Register register = updateProfileServiceInterface.editUser(userId);
		
		File convFile = new File(AppConstants.FILES_PATH + "profileimages/"
				+ userId + ".jpeg");

		if (!convFile.exists()) {
			register.setImageExist(false);
		} else {
			register.setImageExist(true);
		}
		if (register == null) {
//			System.out.println("User with id " + userId + " not found");
			return new ResponseEntity<Register>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Register>(register, HttpStatus.OK);

	}

	// ------------------Update Users-------------------
	// -------------------------------------------------
	
	@RequestMapping(value = "/updateuserprofile/", method = RequestMethod.POST)
	public @ResponseBody Response updateUser(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "data") String jsonData,HttpSession session) throws Exception {
//		System.out.println(jsonData);
		Gson gson = new Gson();
		
		Register register = gson.fromJson(jsonData, Register.class);
		long id = register.getUser_id();
		String abcd = Long.toString(id);
		if (file != null) {

			File convFile = new File(AppConstants.FILES_PATH+"profileimages/"+abcd +".jpeg");
			convFile.createNewFile();
		   
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		}
		updateProfileServiceInterface.updateUser(register, id);
		
		UserDetails user = updateProfileServiceInterface.getUserDetails(id);
		
		session.setAttribute("userDetails", user);
		session.setAttribute("userId", user.getUserId());
		return new Response(1, "success");

	}

	// -------------------FETCHING SKILLS-------------------
	// ------------------------------------------------------

	@RequestMapping(value = "/fetchskillsforuser/{text}", method = RequestMethod.GET)
	public ResponseEntity<List<UserSkills>> fetchUserSkills(
			@PathVariable("text") String text, HttpSession session)
			throws Exception {
		List<UserSkills> skills = updateProfileServiceInterface.getSkills(text);
		if (skills.isEmpty()) {
			return new ResponseEntity<List<UserSkills>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserSkills>>(skills, HttpStatus.OK);
	}

	// ------------------Change Password-------------------
	// -------------------------------------------------

	@RequestMapping(value = "/changepassword/", method = RequestMethod.POST)
	public @ResponseBody Response ChangePassword(@Valid @RequestBody Register register,HttpSession session) throws Exception {
		long userId = (long) session.getAttribute("userId");
		String oldpassword = updateProfileServiceInterface.getPassword(userId);
		String olddbpwd = Encryption.decodeAndDecrypt(oldpassword);
		String oldpwd = register.getOldPassword();
		String newpassword =register.getNewPassword();
		String cpwd = register.getConfirmPassword();
		String newdbpwd = Encryption.encryptAndEncode(newpassword);
		
//		updateProfileServiceInterface.updateUser(register,userId);
		
		if(olddbpwd.equals(oldpwd)){
			if(newpassword.equals(cpwd)){
				updateProfileServiceInterface.ChangePassword(userId,newdbpwd);
				session.removeAttribute(userId+"");
				return new Response(1, "Password Update Success");				
			}else{
				return new Response(2, "Password Mismatch");
			}
			
		}
		else{
			return new Response(3, "Your Old Password is Worng");
		}
	}
	
	// ------------------Delete User Image-------------------
		// -------------------------------------------------
		@RequestMapping(value = "/deleteprofileimage/", method = RequestMethod.GET)
		public @ResponseBody boolean deleteUserImage(HttpSession session)
				throws Exception {
			
			long id = (long) session.getAttribute("userId");
			
			Register register = updateProfileServiceInterface.editUser(id);
			File convFile = new File(AppConstants.FILES_PATH + "profileimages/"
					+ id + ".jpeg");

			if (convFile.exists()) {
				convFile.delete();
			}
			return true;
		}

}
