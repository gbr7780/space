package com.space.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.space.config.auth.PrincipalDetails;
import com.space.member.constant.Area;
import com.space.member.service.MemberService;
/**
 * 로그인 성공 핸들러 
 * @author Jongha
 *
 */
@Service
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
    private MemberService memberService;
	
	/**
	 * 로그인 성공 메서드
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
			
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			Area area = principalDetails.getMember().getArea();
			String spaceUrl = principalDetails.getMember().getSpaceid();
			
			if(area != null) {
				response.sendRedirect("/space/"+spaceUrl+"");
			} else {
				response.sendRedirect("/members/login/addInfo");
			}
		
	}
 
}
