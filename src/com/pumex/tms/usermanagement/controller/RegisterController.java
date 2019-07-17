package com.pumex.tms.usermanagement.controller;

/*
 *** @Author Reshma Manoj ***
 */


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pumex.tms.configurations.Encryption;
import com.pumex.tms.configurations.Mailer;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.usermanagement.bean.Register;
import com.pumex.tms.usermanagement.service.RegisterServiceInterface;
import com.pumex.tms.util.CommonUtil;
import com.pumex.tms.util.Response;

@Controller
public class RegisterController {

	@Autowired
	RegisterServiceInterface registerServiceInterface;

	// -------------------ATTENDEE REGISTRATION-------------------
	// -----------------------------------------------------------

	@Async
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody Response registerUser(
			@RequestParam(value = "data") String jsonData,
			HttpServletRequest request) throws Exception {

		Gson gson = new Gson();
		Register register = gson.fromJson(jsonData, Register.class);

		String pwd = register.getPassword();

		String encryptedpwd = Encryption.encryptAndEncode(pwd);

		String email = register.getEmail();
		long count = registerServiceInterface.isEmailExist(email);
		
		if (count == 0) {
			UserDetails user = registerServiceInterface.userRegistration(
					register, encryptedpwd);
		
			String message = "Hi "
					+ user.getFirstName()
					+ " "
					+ user.getLastName()
					+ ",<br>"
					+ "Please click on the below link to activate your account. <br><br>"
					+ CommonUtil.getAppURL(request)
					+ "#/secure/activeaccount/secretKey="
					+ Encryption.encryptAndEncode(user.getUserId() + "")
					+ "<br><br> Thanks & Regards" + "<br>" + "Team TMS";
			Mailer.sendMail(user.getEmail(), "Account Activation", message);

			return new Response(1, "User Registration Successfull!!!");
		} else {
			return new Response(2, "This Emailid Already Exists!!!");
		}
	}
}
