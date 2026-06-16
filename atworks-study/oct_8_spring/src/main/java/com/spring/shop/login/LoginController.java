package com.spring.shop.login;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.shop.member.MemberDAO;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	MemberDAO mDAO;
	
	@GetMapping("/login")
	public String login(HttpServletRequest req) {
		
		req.setAttribute("contentPage", "member/login.jsp");
		return "home";
				
	}
	
	@PostMapping("/login")
	public String logincheck(HttpServletRequest req) {
		
		mDAO.loginCheck(req);
		req.setAttribute("contentPage", "main.jsp");
		return "home";
	}
	
	@GetMapping("/logOut") 
	public String logOut(HttpServletRequest req) {
		
		req.getSession().invalidate();
		
		req.setAttribute("contentPage", "main.jsp");
		return "home";
	}
}
