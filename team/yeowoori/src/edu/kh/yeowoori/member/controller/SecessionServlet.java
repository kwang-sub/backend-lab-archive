package edu.kh.yeowoori.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.yeowoori.member.model.service.MemberService;
import edu.kh.yeowoori.member.model.vo.Member;

@WebServlet("/member/secession")
public class SecessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/secession.jsp")
		.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
		
		try {
			int result = new MemberService().secession(memberNo);
		
			// 탈퇴 성공 시 -> 메인 페이지로 이동
			// 실패 시 -> 회원 탈퇴 페이지로 이동
			String path = null;
			
			String icon = null;
			String title = null;
			String text = null;
			
			if(result > 0) { // 성공
				icon = "success";
				title = "회원 탈퇴 성공";
				text = "이용해 주셔서 감사합니다.";
				
				path = request.getContextPath()+"/homeBoard"; // 메인페이지 요청 주소
				session.invalidate();
						
			} else {
				icon = "error";
				title = "회원 탈퇴 실패";
				text = "";
				
				path = "secession"; // 회원 탈퇴 페이지 요청 주소
			}
			
			// - 탈퇴 성공 시 이전에 얻어온 세션이 무효화 되기 떄문에
			// 새롭게 세션을 가져옴
			// - 탈퇴 실패 시 이전 세션을 그대로 얻어옴
			
			session = request.getSession();
			
			session.setAttribute("icon", icon);
			session.setAttribute("title", title);
			session.setAttribute("text", text);
			
			response.sendRedirect(path);
		
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
