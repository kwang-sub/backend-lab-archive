package edu.kh.yeowoori.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.yeowoori.board.model.service.ReportService;

@WebServlet("/report/*")
public class ReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();  				 
		String contextPath = request.getContextPath();  	 
		
		String command = uri.substring( (contextPath + "/report/" ).length() ); 
		
		String path = null; 
		RequestDispatcher view = null; 
		HttpSession session = request.getSession();
		String icon =null;
		String title = null;
		String text = null;
		
		ReportService service = new ReportService();
		
		try {
			int cp = request.getParameter("cp") == null ? 1 : Integer.parseInt(request.getParameter("cp"));
			if(command.equals("board")) {
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				int memberNo = request.getParameter("memberNo")=="" ? 0 : Integer.parseInt(request.getParameter("memberNo"));
				int result = 0;
				if(boardNo>0 && memberNo>0) {
					
					result = service.reportBoard(boardNo, memberNo);
					if(result>0) {
						icon = "success";
						title = "신고가 접수되었습니다";
						text = "자세한 사항은 관리자에게 문의해주세요";
						path = request.getHeader("referer");
					}else {
						icon = "error";
						title = "신고 실패";
						path = request.getHeader("referer");
					}
				}else {
					icon = "error";
					title = "신고 실패 로그인 해주세요";
					path = request.getHeader("referer");
				}
				
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				response.sendRedirect(path);
			}
			if(command.equals("comment")) {
				int commentNo = Integer.parseInt(request.getParameter("commentNo"));
				int memberNo = request.getParameter("memberNo")=="" ? 0 :Integer.parseInt(request.getParameter("memberNo"));
				int result = 0;
				if(commentNo>0 && memberNo>0) {
					result = service.reportComment(commentNo, memberNo);
					if(result>0) {
						icon = "success";
						title = "신고가 접수되었습니다";
						text = "자세한 사항은 관리자에게 문의해주세요";
						path = request.getHeader("referer");
					}else {
						icon = "error";
						title = "신고 실패하셨습니다";
						path = request.getHeader("referer");
					}
				}else {
					icon = "error";
					title = "신고 실패 로그인 해주세요";
					path = request.getHeader("referer");
				}
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				session.setAttribute("text", text);
				response.sendRedirect(path);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
