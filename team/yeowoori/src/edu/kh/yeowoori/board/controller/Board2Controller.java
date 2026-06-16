package edu.kh.yeowoori.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.yeowoori.board.model.service.BoardService;
import edu.kh.yeowoori.board.model.service.SelectBoardService;
import edu.kh.yeowoori.board.model.service.UpdateBoardService;
import edu.kh.yeowoori.board.model.vo.Attachment;
import edu.kh.yeowoori.board.model.vo.Board;
import edu.kh.yeowoori.board.model.vo.Category;
import edu.kh.yeowoori.common.MyFileRenamePolicy;


@WebServlet("/board2/*")
public class Board2Controller extends HttpServlet {
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
		
		SelectBoardService service = new SelectBoardService();
		UpdateBoardService serv = new UpdateBoardService();
		
		try {
	
			int cp = request.getParameter("cp") == null ? 1 : Integer.parseInt(request.getParameter("cp"));
			int type = request.getParameter("type") == null ? 1 : Integer.parseInt(request.getParameter("type"));
		
			// 게시글 수정 화면 전환 Controller
			if(command.equals("updateForm")) {
				
				List<Category> category = service.selectCategoryList();
				int boardNo = Integer.parseInt(request.getParameter("boardNo")); //상세 조회할 게시글 번호
				Board board =service.selectBoard(boardNo);
				
				// 게시글 내용에 있는 <br> -> /r/n으로 변경 (원래는 service에 작성하는게 좋음!)
				board.setBoardContent(board.getBoardContent().replaceAll("<br>", "\r\n"));
				
				request.setAttribute("category", category);
				request.setAttribute("board", board);
				
				path = "/WEB-INF/views/board/boardUpdate.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}
			
			//게시글 수정 Controller
			else if(command.equals("update")) {
				
				//MultipartRequest 
				int maxSize = 1024 * 1024 * 20;
				HttpSession session = request.getSession();
				String root = session.getServletContext().getRealPath("/");
				String filePath = "resources/img/";
				
				switch(type){
					case 1: filePath += "tripboard/"; break;
					case 2: filePath += "questionboard/"; break;
					case 3: filePath += "togetherboard/"; break;
				}
				MultipartRequest mpRequest = new MultipartRequest(request, root+filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
				//게시글 수정에 사용되는 파라미터(게시글 번호, 제목, 내용, 카테고리, 이미지)
				int boardNo = Integer.parseInt(mpRequest.getParameter("boardNo"));
				String boardTitle = mpRequest.getParameter("title");
				String boardContent = mpRequest.getParameter("content");
				int categoryCode = Integer.parseInt(mpRequest.getParameter("category"));
				int areaCode =  Integer.parseInt(mpRequest.getParameter("area"));
				
				Board board = new Board();
				board.setBoardNo(boardNo);
				board.setBoardTitle(boardTitle);
				board.setBoardContent(boardContent);
				board.setCategoryCode(categoryCode);
				board.setAreaCode(areaCode);
				
				List<Attachment> atList = new ArrayList<Attachment>();
				
				Enumeration<String> images = mpRequest.getFileNames();
				while(images.hasMoreElements()) {
					String name = images.nextElement();
					if(mpRequest.getFilesystemName(name)!= null) {
						Attachment at = new Attachment();
						at.setFilePath(filePath);
						at.setFileNm(mpRequest.getFilesystemName(name));
						at.setFileLevel(Integer.parseInt(name.substring(3)));
						at.setBoardNo(boardNo);
						atList.add(at);
					}
				}
				
//				System.out.println(board);
//				for(Attachment at : atList) {
//					System.out.println(at);
//				}
				
				
				int result = serv.updateBoard(board, atList);
				cp = Integer.parseInt(mpRequest.getParameter("cp"));
				
				if(result>0) {
					icon = "success";
					title = "게시글 수정 성공";
					path="../board/view?no="+boardNo+"&cp="+cp+"&type="+type+"&area="+board.getAreaCode();
				}else {
					icon = "error";
					title = "게시글 수정 실패";
					path = request.getHeader("referer");
				}
				
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				response.sendRedirect(path);
			}
			
			else if (command.equals("delete")) {
				
				//System.out.println(request.getParameter("area"));
				
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				
				int result = serv.deleteBoard(boardNo);
				if(result>0) {
					icon = "success";
					title = "게시글 삭제 성공";
					
					if(request.getParameter("area").equals("")) {
						if(type==1) {
							path = "../board/list?type=1";
						}else {
							path = "../board/list"+type+"?type="+type;
						}
					}else {
						int area = Integer.parseInt(request.getParameter("area"));
						if(type==1) {
							path = "../board/list?type=1&area="+area;
						}else {
							path = "../board/list"+type+"?type="+type+"&area="+area;
						}
					}
					
				}else {
					icon="error";
					title ="게시글 삭제 실패";
					path = "/yeowoori/homeBoard";
				}
				HttpSession session = request.getSession();
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				response.sendRedirect(path);

			}
			
			else if(command.equals("like/board")) {
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				int memberNo = 0;
				if(request.getParameter("memberNo").equals("")) {
					icon ="error";
					title="로그인 후 이용해주세요.";
					
				}else {
					memberNo = Integer.parseInt(request.getParameter("memberNo"));
					int result = serv.checkLike(boardNo, memberNo);
					if(result==0) {
						result = serv.updateLike(boardNo, memberNo);
						if(result>0) {
							icon ="success";
							title="게시글 좋아요 성공";
							text = "마이페이지에서 좋아하는 게시글을 확인해보세요.";
						}
					}else {
						icon ="warning";
						title="이미 좋아요한 게시글입니다.";
						text = "마이페이지에서 좋아하는 게시글을 확인해보세요.";
					}
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				session.setAttribute("text", text);
				
				path = request.getHeader("referer");
				response.sendRedirect(path);
				
			}
			else if(command.equals("like/comment")) {
				
				int commentNo = Integer.parseInt(request.getParameter("commentNo"));
				
				int memberNo = 0;
				if(request.getParameter("memberNo").equals("")) {
					icon ="error";
					title="로그인 후 이용해주세요.";
					
				}else {
					memberNo = Integer.parseInt(request.getParameter("memberNo"));
					int result = serv.checkLikeComment(commentNo, memberNo);
					if(result==0) {
						result = serv.updateLikeComment(commentNo, memberNo);
						if(result>0) {
							icon ="success";
							title="댓글 좋아요 성공";
						}
					}else {
						icon ="warning";
						title="이미 좋아요한 댓글입니다.";
					}
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				
				path = request.getHeader("referer");
				response.sendRedirect(path);
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
