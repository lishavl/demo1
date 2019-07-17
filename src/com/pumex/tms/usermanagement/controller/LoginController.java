package com.pumex.tms.usermanagement.controller;

/*
 *** @Author Reshma Manoj ***
 */


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pumex.tms.configurations.Encryption;
import com.pumex.tms.configurations.Mailer;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.models.UserRole;
import com.pumex.tms.notifications.service.NotificationServiceInterface;
import com.pumex.tms.usermanagement.bean.Login;
import com.pumex.tms.usermanagement.service.LoginServiceInterface;
import com.pumex.tms.util.AppConstants;
import com.pumex.tms.util.CommonUtil;
import com.pumex.tms.util.Response;

@Controller
public class LoginController {

	@Autowired
	LoginServiceInterface loginServiceInterface;

	@Autowired
	NotificationServiceInterface notificationServiceInterface;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Response userLogin(@RequestBody Login login,
			HttpSession session) throws Exception {

		String email = login.getEmail();
		String pwd = Encryption.encryptAndEncode(login.getPassword());

				int status = loginServiceInterface.checkUserAuthentication(
						email, pwd);

				UserDetails user = loginServiceInterface
						.getUserFromEmail(email);

				if (status == AppConstants.USER_STATUSES.VALID) {

					boolean isAdmin = false, isTrainer = false, isAttendee = false;

					long roleId = 3;
					
					for (UserRole rol : user.getRoles()) {
						if (rol.getRole().getId() < roleId)
							roleId = rol.getRole().getId();

						if (rol.getRole().getId() == AppConstants.USER_ROLES.ADMIN_ROLE)
							isAdmin = true;
						else if (rol.getRole().getId() == AppConstants.USER_ROLES.TRAINER_ROLE)
							isTrainer = true;
						else if (rol.getRole().getId() == AppConstants.USER_ROLES.ATTENDEE_ROLE)
							isAttendee = true;
					}

					session.setAttribute("isAdmin", isAdmin);
					session.setAttribute("isTrainer", isTrainer);
					session.setAttribute("isAttendee", isAttendee);

					session.setAttribute("userDetails", user);
					session.setAttribute("userId", user.getUserId());
					session.setAttribute("roleId", roleId);

					return new Response(1, "Success", roleId);

				} else if (status == AppConstants.USER_STATUSES.INVALID) {
					return new Response(0, "Invalid Emailid or Password");
					
				} else if (status == AppConstants.USER_STATUSES.BLOCKED) {
					String blckreason = user.getBlockingReason();
					return new Response(2, "Your Account Has Been Blocked",
							blckreason);
					
				} else {
					return new Response(3, "Error While Login");
				}
	}

	@RequestMapping(value = "/requestforresetpassword", method = RequestMethod.POST)
	public @ResponseBody boolean fetchAllOptions(HttpServletRequest request,
			@RequestBody String email) throws Exception {

		if (loginServiceInterface.checkUserExist(email)) {

			UserDetails user = loginServiceInterface.getUserFromEmail(email);
			String message = "Hi "
					+ user.getFirstName()
					+ " "
					+ user.getLastName()
					+ ",<br><br>"
					+ "Please click on the below link to change your password. <br><br>Link : "
					+ CommonUtil.getAppURL(request) + "#/"
					+ "secure/resetpassword/secretKey="
					+ Encryption.encryptAndEncode(user.getUserId()+"") + "<br> <br> Regards,<br>TMS Team";
//			System.out.println(message);
			Mailer.sendMail(user.getEmail(), "Reset Password", message);

			return true;
		} else {
			return false;
		}

	}

}
