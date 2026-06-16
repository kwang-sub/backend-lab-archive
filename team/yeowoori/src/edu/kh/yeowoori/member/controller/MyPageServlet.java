package edu.kh.yeowoori.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.yeowoori.member.model.service.myPageService;
import edu.kh.yeowoori.member.model.vo.Member;


@WebServlet("/member/myPage/*")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();//요청주소
		String contextPath = request.getContextPath();//최상위 주소
		String command = uri.substring((contextPath+"/member/myPage/").length());
		
		String path = null;//응답화면 경로
		RequestDispatcher view = null; // 요청 위임 객체 저장 참조변수
		myPageService service = new myPageService();
		HttpSession session = request.getSession();
		
				
		Member loginMember = (Member)session.getAttribute("loginMember"); 
		
		try {
			if(command.equals("my")) {
				
				
				
				List<Member> myTrip =  service.getMyTrip(loginMember);
				List<Member> myQuestion = service.getMyQuestion(loginMember);
				List<Member> myTogether = service.getMyTogether(loginMember);
				int likeCount = service.getLikeCount(loginMember);
				request.setAttribute("myQuestion", myQuestion);
				request.setAttribute("myTrip", myTrip);
				request.setAttribute("myTogether", myTogether);
				request.setAttribute("likeCount", likeCount);
				path = "/WEB-INF/views/member/myPage.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}else if (command.equals("like")) {
				List<Member> myTrip =  service.getLikeTrip(loginMember);
				List<Member> myQuestion = service.getLikeQuestion(loginMember);
				List<Member> myTogether = service.getLikeTogether(loginMember);
				int likeCount = service.getLikeCount(loginMember);
				request.setAttribute("myQuestion", myQuestion);
				request.setAttribute("myTrip", myTrip);
				request.setAttribute("myTogether", myTogether);
				request.setAttribute("likeCount", likeCount);
				request.setAttribute("like", "like");
				path = "/WEB-INF/views/member/myPage.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}else if(command.equals("logout")) {
				session.removeAttribute("loginMember");
				
				
				String icon = "success";
				String title = "로그아웃 하셨습니다";
				
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				response.sendRedirect(request.getContextPath()+"/homeBoard");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
