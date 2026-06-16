package edu.kh.yeowoori.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.yeowoori.board.model.service.HomeBoardService;
import edu.kh.yeowoori.board.model.vo.Board;
import edu.kh.yeowoori.board.model.vo.Notice;

@WebServlet("/homeBoard")
public class HomeBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = null; //요청 위임 객체 저장 참조 변수
		
		HomeBoardService service = new HomeBoardService();
		
		int type = 0;
		
		try {
			
			List<Board> boardList = null;
			List<Board> qBoardList = null;
			List<Board> withBoardList = null;
			
			if(request.getParameter("area")==null) {
				
				boardList = service.selectHomeTrip();
				
				type = 2;
				qBoardList = service.selectHomeOther(type);
				
				type= 3;
				withBoardList = service.selectHomeOther(type);
				
				
			}else {
				int area = Integer.parseInt(request.getParameter("area"));
				
				boardList = service.selectHomeBoard(area);
				//System.out.println(boardList);
				
				type = 2;
				qBoardList = service.selectHomeQuestion(area, type);
				
				type = 3;
				withBoardList = service.selectHomeQuestion(area, type);
			}

			if(boardList !=null) {
				request.getSession().setAttribute("boardList", boardList);
			}
			if(qBoardList != null ) {
				request.getSession().setAttribute("qBoardList", qBoardList);
			}
			if(qBoardList != null ) {
				request.getSession().setAttribute("withBoardList", withBoardList);
			}
			
			List<Notice> noticeList = service.selectHomeNotice();
			//System.out.println(noticeList);
			
			if(noticeList != null) {
				request.getSession().setAttribute("noticeList", noticeList);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		view = request.getRequestDispatcher("/WEB-INF/views/board/homeBoard.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
