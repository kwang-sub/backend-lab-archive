package com.spring.shop.signOut;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.shop.member.MemberDAO;

@Controller
public class SignOutController {
	
	@Autowired
	MemberDAO mDAO;
	
	@ResponseBody
	@PostMapping("/signOut")
	public int signOut(HttpServletRequest req) {
		
		int result = mDAO.signOut(req);
		
		req.setAttribute("contentPage", "main.jsp");
		
		return result;
	}
}
