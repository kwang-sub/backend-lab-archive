package edu.kh.yeowoori.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.kh.yeowoori.board.model.service.CommentService;
import edu.kh.yeowoori.board.model.vo.Comment;

@WebServlet("/comment/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/comment/").length());
		
		try {
			CommentService service = new CommentService();
			
			if(command.equals("list")) {
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				List<Comment> list = service.selectList(boardNo);
				
				Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일 HH:mm").create();
				gson.toJson(list, response.getWriter());
				
			}
			
	
			
			// 댓글 삽입 Controller
			else if(command.equals("insertComment")) {
				int memberNo = Integer.parseInt(request.getParameter("memberNo"));
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				String commentContent = request.getParameter("commentContent");
				
				Comment comment = new Comment();

				comment.setMemberNo(memberNo);
				comment.setBoardNo(boardNo);
				comment.setCommentContent(commentContent);
				
				int result = service.insertComment(comment);
				response.getWriter().print(result);
				
			}
			
			
			// 댓글 수정 Controller
			else if(command.equals("updateComment")) {
				int commentNo = Integer.parseInt(request.getParameter("commentNo"));
				String commentContent = request.getParameter("commentContent");
				
				Comment comment = new Comment();

				comment.setCommentNo(commentNo);
				comment.setCommentContent(commentContent);
				

				int result = service.updateComment(comment);
				
				response.getWriter().print(result);
			}
			
			
			// 댓글 삭제 Controller
			else if(command.equals("deleteComment")) {
				int commentNo = Integer.parseInt(request.getParameter("commentNo"));
				
				int result = service.deleteComment(commentNo);
				
				response.getWriter().print(result);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
