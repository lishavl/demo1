package com.pumex.tms.configurations;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * This class intercepts all requests to server
 * 
 * @Author JINSHAD P.T.
 * @Date 10/05/2016
 */

public class RequestInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (request.getSession().getAttribute("userDetails") != null)
			return true;
		else {
			System.out.println("URL : " + request.getRequestURL().toString());
			System.out.println("Unauthorized User..!");
			throw new AuthenticationException("Unautherized access!!");
		}
	}
}