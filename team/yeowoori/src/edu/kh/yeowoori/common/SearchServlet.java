package edu.kh.yeowoori.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.yeowoori.board.model.service.SearchService;
import edu.kh.yeowoori.board.model.service.SelectBoardService;
import edu.kh.yeowoori.board.model.vo.Board;
import edu.kh.yeowoori.board.model.vo.Pagination;

@WebServlet("/search/*")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI(); //요청 주소 				 
		String contextPath = request.getContextPath();  //최상위 주소	 
		
		String command = uri.substring( (contextPath + "/board2/" ).length() ); 
		//uri 에서 contextPath + "/board2/" 만큼을 앞에서부터 잘라낸 나머지를 command에 저장
		
		String path = null; //응답화면 주소 또는 경로
		RequestDispatcher view = null; //요청 위임 객체 저장 참조 변수
		
		String icon =null;
		String title = null;
		String text = null;
		
		String search = request.getParameter("search");
		int area = request.getParameter("area") == null ? 0: Integer.parseInt(request.getParameter("area")) ;
		SearchService service = new SearchService();
		SelectBoardService selectBoardService = new SelectBoardService();

		try {
			if(command.equals("all")) {
				int type = 0;
				int maxBoardNum = 0;
				
				List<Board> tripBoardList = null;
				List<Board> qBoardList = null;
				List<Board> withBoardList = null;
				
				if(area ==0) {
					maxBoardNum = 7;
					type = 1;
					tripBoardList = service.searchBoard(type, maxBoardNum, search);
					
					maxBoardNum = 6;
					type= 2;
					qBoardList = service.searchBoard(type, maxBoardNum, search);
					
					maxBoardNum = 6;
					type= 3;
					withBoardList = service.searchBoard(type, maxBoardNum, search);
					
				}else {
					maxBoardNum = 7;
					type =1;
					tripBoardList= service.searchAreaBoard(area, type, maxBoardNum, search);
					
					maxBoardNum = 6;
					type =2;
					qBoardList = service.searchAreaBoard(area, type, maxBoardNum, search);
					
					maxBoardNum = 6;
					type =3;
					withBoardList = service.searchAreaBoard(area, type, maxBoardNum, search);
				
				}

				request.setAttribute("tripBoardList", tripBoardList);
				request.setAttribute("qBoardList", qBoardList);
				request.setAttribute("withBoardList", withBoardList);
				request.setAttribute("search", search);

				view = request.getRequestDispatcher("/WEB-INF/views/board/search.jsp");
				view.forward(request, response);
			}
			
			else{
				
				Pagination pagination = selectBoardService.getPagination(1,0,area,0);
				
				if(command.equals("trip")) {
					
					pagination.setBoardType(1);
					pagination.setLimit(12);
					path = "/WEB-INF/views/board/boardList.jsp";
					
					
				}else if(command.equals("question")) {

					pagination.setBoardType(2);
					pagination.setLimit(10);
					path = "/WEB-INF/views/board/boardList2.jsp";
					
				}else if(command.equals("with")) {
					
					pagination.setBoardType(3);
					pagination.setLimit(10);
					path = "/WEB-INF/views/board/boardList3.jsp";
					
				}
				
				List<Board> boardList = service.selectBoardList(pagination, area, search);
				request.getSession().setAttribute("pagination", pagination);
				request.setAttribute("boardList", boardList);
				
				request.getRequestDispatcher(path).forward(request, response);
				
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
