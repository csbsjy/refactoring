package com.refactoring.fcm.handler;


import com.refactoring.fcm.DTO.user.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service
public class SaveLoginIdHandler extends SavedRequestAwareAuthenticationSuccessHandler
{

	 private static final Logger logger = LoggerFactory.getLogger(SaveLoginIdHandler.class);
	static final String REQUEST_PARAM_NAME = "_remember_username";
	static final String COOKIE_NAME = "saved_username";


	 @Override
	 public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	   Authentication authentication) throws IOException, ServletException {

		 Account user = ((Account) authentication.getPrincipal());
		if(request.getParameter("remember")==null){
			//기억하기 체크 안되어 있으면 쿠키 해제
			response.addCookie(new Cookie(COOKIE_NAME,""));
		}
		else{
			//기억하기 체크 되어있으면 쿠키 추가
			response.addCookie(new Cookie(COOKIE_NAME,user.getId()));
		}


		 String usertype = user.getType();
		 if(usertype.equals("ADMIN"))
			 new DefaultRedirectStrategy().sendRedirect(request, response, "/admin");
		 if(usertype.equals("MANAGER"))
			 new DefaultRedirectStrategy().sendRedirect(request, response, "/manager");
		 if(usertype.equals("TRAINER"))
			 new DefaultRedirectStrategy().sendRedirect(request, response, "/trainer");
		 if(usertype.equals("MEMBER"))
			 new DefaultRedirectStrategy().sendRedirect(request, response, "/member");

	 }
}
