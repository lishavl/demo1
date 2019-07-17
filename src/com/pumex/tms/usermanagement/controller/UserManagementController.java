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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.pumex.tms.configurations.Encryption;
import com.pumex.tms.models.DepartmentDetails;
import com.pumex.tms.models.Role;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserRole;
import com.pumex.tms.models.UserSkills;
import com.pumex.tms.usermanagement.bean.Register;
import com.pumex.tms.usermanagement.service.UpdateProfileServiceInterface;
import com.pumex.tms.usermanagement.service.UserManagementServiceInterface;
import com.pumex.tms.util.AppConstants;
import com.pumex.tms.util.Response;

@Controller
public class UserManagementController {

	@Autowired
	UserManagementServiceInterface userManagementServiceInterface;

	@Autowired
	UpdateProfileServiceInterface updateProfileServiceInterface;

	// ------------------List All Users-------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<UserDetails>> listAllUsers(HttpSession session)
			throws Exception {
		long id = (long) session.getAttribute("userId");

		List<UserDetails> users = userManagementServiceInterface
				.getAllUsers(id);
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDetails>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserDetails>>(users, HttpStatus.OK);
	}

	// -------------------ADD NEW USER-------------------
	// -----------------------------------------------------------

	@RequestMapping(value = "/saveUser/", method = RequestMethod.POST)
	public @ResponseBody Response saveUser(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "data") String jsonData) throws Exception {

		Gson gson = new Gson();
		Register register = gson.fromJson(jsonData, Register.class);

		String[] skill = register.getSkillsets();

		String pwd = register.getPassword();
		String cpwd = register.getConfirmPassword();

		int role1 = register.getRole1();
		int role2 = register.getRole2();

		Set<UserRole> userroles = new HashSet<UserRole>();
		UserRole userrole = new UserRole();
		UserRole user = new UserRole();
		Role role = new Role();
		Role roles = new Role();
		try {
			if (role1 != 0) {
				role.setId(role1);
				userrole.setRole(role);
				userroles.add(userrole);
			}
			if (role2 != 0) {
				roles.setId(role2);
				user.setRole(roles);
				userroles.add(user);
			}
		} catch (Exception e) {
			if (role2 != 0) {
				roles.setId(role2);
				user.setRole(roles);
				userroles.add(user);
			}
		}

		if (pwd.equals(cpwd)) {
			String email = register.getEmail();
			long count = userManagementServiceInterface.isEmailExist(email);
			if (count == 0) {
				long userid = userManagementServiceInterface.userRegistration(
						register, skill, userroles);

				String abcd = Long.toString(userid);
				if (file != null) {
					// String extension =
					// FilenameUtils.getExtension(file.getOriginalFilename());

					File convFile = new File(AppConstants.FILES_PATH
							+ "profileimages/" + abcd + ".jpeg");
					convFile.createNewFile();

					FileOutputStream fos = new FileOutputStream(convFile);
					fos.write(file.getBytes());
					fos.close();
				}
				return new Response(1, "User Registration Successfull!!!");
			} else {
				return new Response(2, "This Emailid Already Exists!!!");
			}
		} else {
			return new Response(3, "Password Mismatch!!!");
		}
	}

	// ------------------Update Users-------------------
	// -------------------------------------------------

	@RequestMapping(value = "/updateuser/", method = RequestMethod.POST)
	public @ResponseBody Response updateUser(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "data") String jsonData) throws Exception {

		Gson gson = new Gson();
		Register register = gson.fromJson(jsonData, Register.class);

		long userid = register.getUser_id();

		UserDetails userdetails = userManagementServiceInterface
				.getUserById(userid);

		System.out.println("UserId:" + userid);

		String pwd = register.getPassword();
		String cpwd = register.getConfirmPassword();

		String encryptedpwd = Encryption.encryptAndEncode(pwd);

		int role1 = register.getRole1();
		int role2 = register.getRole2();

		Set<UserRole> userroles = new HashSet<UserRole>();
		UserRole userrole = new UserRole();
		UserRole user = new UserRole();
		Role role = new Role();
		Role roles = new Role();
		try {
			if (role1 != 0) {
				role.setId(role1);
				userrole.setRole(role);
				userroles.add(userrole);
			}
			if (role2 != 0) {
				roles.setId(role2);
				user.setRole(roles);
				userroles.add(user);
			}
		} catch (Exception e) {
			if (role2 != 0) {
				roles.setId(role2);
				user.setRole(roles);
				userroles.add(user);
			}
		}

		if (pwd.equals(cpwd)) {

			if (register.getLastName() == null) {
				userdetails.setLastName("");
			} else {
				userdetails.setLastName(register.getLastName());
			}
			userdetails.setFirstName(register.getFirstName());
			userdetails.setEmail(register.getEmail());
			userdetails.setMobile(register.getPhoneNumber());
			userdetails.setAddress(register.getAddress());
			userdetails.setGender(register.getGender());
			userdetails.setPassword(encryptedpwd);
			userdetails.setRoles(userroles);
			userdetails.setDepartment(new DepartmentDetails(register
					.getDepartment()));
			String dob = register.getDob();
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
			java.util.Date date = dt.parse(dob);
			java.sql.Date sqlDate = new Date(date.getTime());
			userdetails.setDob(sqlDate);

			String[] skill = register.getSkillsets();

			userManagementServiceInterface.updateUser(userdetails, skill);

			String abcd = Long.toString(userid);
			if (file != null) {
				// String extension =
				// FilenameUtils.getExtension(file.getOriginalFilename());

				File convFile = new File(AppConstants.FILES_PATH
						+ "profileimages/" + abcd + ".jpeg");
				convFile.createNewFile();

				FileOutputStream fos = new FileOutputStream(convFile);
				fos.write(file.getBytes());
				fos.close();
			}
			return new Response(1, "success");
		} else {
			return new Response(2, "Password Mismatch");
		}
	}

	// ------------------Delete User-------------------
	// -------------------------------------------------

	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Response deleteUser(@PathVariable("id") long id)
			throws Exception {
		System.out.println("Fetching & Deleting User with id " + id);
		userManagementServiceInterface.deleteUser(id);
		return new Response(1, "success");
	}

	// ------------------Fetch User By Id-------------------
	// -------------------------------------------------
	@RequestMapping(value = "/fetchuser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Register> getUser(@PathVariable("id") long id)
			throws Exception {
		Register register = userManagementServiceInterface.editUser(id);
		
		File convFile = new File(AppConstants.FILES_PATH + "profileimages/"
				+ id + ".jpeg");

		if (!convFile.exists()) {
			register.setImageExist(false);
		} else {
			register.setImageExist(true);
		}
		
		if (register == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Register>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Register>(register, HttpStatus.OK);

	}

	// ------------------Block User By Id-------------------
	// -------------------------------------------------

	@RequestMapping(value = "/blockuser/{id}/{blockingmessage}", method = RequestMethod.GET)
	public @ResponseBody Response BlockUser(@PathVariable("id") long id,
			@PathVariable("blockingmessage") String blockingmessage)
			throws Exception {
		userManagementServiceInterface.blockUser(id, blockingmessage);
		return new Response(1, "success");
	}

	// ------------------UnBlock User By Id-------------------
	// -------------------------------------------------

	@RequestMapping(value = "/unblockuser/{id}", method = RequestMethod.GET)
	public @ResponseBody Response UnblockUser(@PathVariable("id") long id)
			throws Exception {
		userManagementServiceInterface.unblockUser(id);
		return new Response(1, "success");
	}

	// -------------------FETCHING SKILLS-------------------
	// ------------------------------------------------------

	@RequestMapping(value = "/fetchuserskills/{text}", method = RequestMethod.GET)
	public ResponseEntity<List<UserSkills>> fetchUserSkills(
			@PathVariable("text") String text, HttpSession session)
			throws Exception {
		List<UserSkills> skills = userManagementServiceInterface
				.getSkills(text);
		if (skills.isEmpty()) {
			return new ResponseEntity<List<UserSkills>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserSkills>>(skills, HttpStatus.OK);
	}

	// ------------------Delete User Image-------------------
	// -------------------------------------------------
	@RequestMapping(value = "/deleteuserimage/{id}", method = RequestMethod.GET)
	public @ResponseBody boolean deleteUserImage(@PathVariable("id") long id)
			throws Exception {
		Register register = userManagementServiceInterface.editUser(id);

		File convFile = new File(AppConstants.FILES_PATH + "profileimages/"
				+ id + ".jpeg");

		if (convFile.exists()) {
			convFile.delete();
		}
		return true;
	}

	@RequestMapping(value = "/activateuser/{secretKey}", method = RequestMethod.GET)
	public @ResponseBody boolean activateUser(
			@PathVariable(value = "secretKey") String encryptedUserName)
			throws Exception {

		try {
			userManagementServiceInterface.activateUser(Long
					.parseLong(Encryption.decodeAndDecrypt(encryptedUserName)));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping(value = "/isvalidresetpassword/{secretKey}", method = RequestMethod.GET)
	public @ResponseBody long isValidResetPassword(Model model,
			@PathVariable(value = "secretKey") String encryptedUserName)
			throws Exception {
		try {
			UserDetails user = userManagementServiceInterface.getUserById(Long
					.parseLong(Encryption.decodeAndDecrypt(encryptedUserName)));
			if (user != null) {
				return user.getUserId();
			}
		} catch (Exception e) {
		}
		return 0;
	}

	@RequestMapping(value = "/resetpassword/", method = RequestMethod.POST)
	public @ResponseBody Response ChangePassword(
			@RequestBody Register register, HttpSession session)
			throws Exception {

		if (register.getUser_id() > 0
				&& register.getNewPassword().equals(
						register.getConfirmPassword())) {
			String newdbpwd = Encryption.encryptAndEncode(register
					.getNewPassword());

			updateProfileServiceInterface.ChangePassword(register.getUser_id(),
					newdbpwd);
			return new Response(1, "Password Reset Success");
		} else if (register.getUser_id() > 0) {
			return new Response(2, "Confirm password is different");
		} else {
			return new Response(3, "Invalid user");
		}

	}

}
