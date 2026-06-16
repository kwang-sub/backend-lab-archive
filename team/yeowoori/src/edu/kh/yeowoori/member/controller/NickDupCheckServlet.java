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


@WebServlet("/member/nickDupCheck")
public class NickDupCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 닉네임 중복 검사 팝업창으로 요청 위임
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher view 
			= request.getRequestDispatcher("/WEB-INF/views/member/nickDupCheck.jsp");
		
		view.forward(request, response);
	}

	// DB에 같은 닉네임 있는지 중복 검사 후 결과 반환
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// POST 방식 전달됨 -> 문자 인코딩을 UTF-8로 변환
		request.setCharacterEncoding("UTF-8");
		
		String nickname = request.getParameter("nickname");
		
		try {
			
			int result = new MemberService().nickDupCheck(nickname);
			
			
			// redirect시 데이터가 유지될 수 있도록 Session 사용
			HttpSession session = request.getSession();
			
			session.setAttribute("result", result);
			session.setAttribute("nickname", nickname);
			
			// get방식으로 /member/idDupCheck 주소를 재요청
			response.sendRedirect("nickDupCheck");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "닉네임 중복 검사 과정에서 문제가 발생했습니다.");
			RequestDispatcher view 
				= request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");
			
			view.forward(request, response);
		}
	}

}
