package com.pumex.tms.usermanagement.controller;

/**
 * @Author Jinshad P.T.
 * @Date 26/08/2016
 */

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pumex.tms.usermanagement.bean.UserAccessBean;
import com.pumex.tms.usermanagement.service.UserAccessService;
import com.pumex.tms.util.AppConstants;
import com.pumex.tms.util.Response;

@Controller
public class UserAccessController {

	@Autowired
	UserAccessService service;

	@RequestMapping(value = "/fetchalloptions/{roleId}", method = RequestMethod.GET)
	public @ResponseBody Object[] fetchAllOptions(
			@PathVariable("roleId") long roleId) throws Exception {
		return service.loadOptions(roleId);
	}

	@RequestMapping(value = "/saveuseraccess/", method = RequestMethod.POST)
	public @ResponseBody Response saveUserAccess(
			@RequestParam(value = "data") String jsonData) throws Exception {

		Gson gson = new Gson();
		UserAccessBean userAccess = gson.fromJson(jsonData,
				UserAccessBean.class);

		service.saveUserAccess(userAccess.getOptionIDs(),
				userAccess.getRoleId());

		return new Response(1, "success");
	}

	@RequestMapping(value = "/isvalidaccess/{action}", method = RequestMethod.GET)
	public @ResponseBody boolean isValidAccess(
			@PathVariable("action") String action, HttpSession session)
			throws Exception {

		if (session.getAttribute("roleId") != null) {

			String IDs = "(";

			if ((boolean) session.getAttribute("isAdmin")) {
				IDs += AppConstants.USER_ROLES.ADMIN_ROLE + ",";
			}
			if ((boolean) session.getAttribute("isTrainer")) {
				IDs += AppConstants.USER_ROLES.TRAINER_ROLE + ",";
			}
			if ((boolean) session.getAttribute("isAttendee")) {
				IDs += AppConstants.USER_ROLES.ATTENDEE_ROLE + ",";
			}
			IDs += "0)";

			return service.isValidAccess(action, IDs);
		}

		return false;
	}

}
