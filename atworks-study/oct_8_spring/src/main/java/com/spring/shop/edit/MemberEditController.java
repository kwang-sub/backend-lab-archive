package com.spring.shop.edit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.shop.member.MemberDAO;

@Controller
public class MemberEditController {
	
	@Autowired
	MemberDAO mDAO;
	
	@GetMapping("/edit")
	public String editPage(HttpServletRequest req) {
		
		req.setAttribute("contentPage", "member/edit.jsp");
		
		return "home";
	}
	
	@PostMapping("/edit")
	public String edit(HttpServletRequest req) {
		
		mDAO.edit(req);
		req.setAttribute("contentPage", "member/edit.jsp");
		
		
		return "home";
	}
}
