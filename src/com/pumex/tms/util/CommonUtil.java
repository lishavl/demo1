package com.pumex.tms.util;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

/**
 * Class for common methods
 * 
 * @Author JINSHAD P.T.
 * @Date 17/06/2016
 */

public class CommonUtil {

	/*
	 * Method to get application url
	 * 
	 * @return nothing
	 */
	public static String getAppURL(HttpServletRequest request) {
		String url = null;
		try {
			URI requestUri = new URI(request.getRequestURL().toString());
			url = new URI(requestUri.getScheme(), requestUri.getAuthority(),
					request.getContextPath(), null, null).toString() + "/";

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

}
