package edu.kh.yeowoori.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.yeowoori.member.model.service.MemberService;
import edu.kh.yeowoori.member.model.vo.Member;

@WebServlet("/member/signUp")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/signUp.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("id");
		String memberPw = request.getParameter("pwd1");
		
		String[] email = request.getParameterValues("email");
		
		String memberEmail = String.join("@", email);
		
		String memberNickname = request.getParameter("nickName");
	
	
		Member member = new Member(memberId, memberPw,  memberEmail, memberNickname);
		
		try {
			
			int result = new MemberService().signUp(member);
			 
			
			String icon = null;
			String title = null;
			String text = null;
			
			
			if(result > 0) { 
				icon = "success";
				title = "회원 가입 성공";
				text = "회원 가입을 환영합니다.";
			
			}else { 
				icon = "error";
				title = "회원 가입 실패";
				text = "회원 가입 중 문제가 발생했습니다.\n문제가 지속될 경우 고객센터 문의 바랍니다.";
			}

			HttpSession session = request.getSession();
			session.setAttribute("icon", icon);
			session.setAttribute("title", title);
			session.setAttribute("text", text);
			
			response.sendRedirect(request.getContextPath()+"/homeBoard");
		
		
		
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "회원 가입 과정에서 문제가 발생했습니다.");
			
			RequestDispatcher view 
				= request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");
			
			view.forward(request, response);
		}
				
	
	
	}

	
}
