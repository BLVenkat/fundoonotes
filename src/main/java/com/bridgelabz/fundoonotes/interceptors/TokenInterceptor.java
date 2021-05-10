package com.bridgelabz.fundoonotes.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bridgelabz.fundoonotes.exception.FundooException;
import com.bridgelabz.fundoonotes.utils.TokenService;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	TokenService tokenService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//System.out.println(request.getRequestURI());
		
		String token = request.getHeader("token");
		//System.out.println(token);
		
		if(request.getRequestURI().contains("swagger") || request.getRequestURI().contains("user")) {
			return true;
		}
		
		if (token == null) {
			//response.sendError(404, "Bad Request. Header does not have token.");
			throw new FundooException(HttpStatus.BAD_REQUEST.value(),"token is not present in header");
		//	return false;
		}

		tokenService.decodeToken(token);
		return true;
	}
}
