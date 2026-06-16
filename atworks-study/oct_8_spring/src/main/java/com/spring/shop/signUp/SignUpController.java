package com.spring.shop.signUp;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.shop.member.MemberDAO;

@Controller
public class SignUpController {
	
	@Autowired
	MemberDAO mDAO;
	
	@GetMapping("/signUp")
	public String singUpPage(HttpServletRequest req) {
		
		req.setAttribute("contentPage", "member/signUp.jsp");
		
		return "home";
	}
	
	@PostMapping("/signUp")
	public String signIn(HttpServletRequest req) {
		
		mDAO.signUp(req); 
		
		req.setAttribute("contentPage", "main.jsp");
		return "home";
	}
	
	@PostMapping("/checkId")
	@ResponseBody
	public Map<String, Object> checkId(HttpServletRequest req){
		
		int result = mDAO.checkId(req);
		Map<String, Object>res = new HashMap<String, Object>();
		res.put("result", result);
		return res;
	}
}
