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
import edu.kh.yeowoori.board.model.vo.Attachment;
import edu.kh.yeowoori.board.model.vo.Board;
import edu.kh.yeowoori.common.MyFileRenamePolicy;
import edu.kh.yeowoori.member.model.vo.Member;

@WebServlet("/boardwrite/*")
public class BoardWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
    BoardService service = new BoardService();
    String icon = null;
    String title = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/board/boardwrite.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();//요청주소
		String contextPath = request.getContextPath();//최상위 주소
		String command = uri.substring((contextPath+"/boardWrite/").length());
		
		String path = null;//응답화면 경로
		RequestDispatcher view = null; // 요청 위임 객체 저장 참조변수
		int cp = request.getParameter("cp") == "" ? 1 : Integer.parseInt(request.getParameter("cp"));
		try {
			
			if(command.equals("write")) {
//				type, title, category,area, img0, img1, img2, memberNo
				int boardType = Integer.parseInt(request.getParameter("type"));
				HttpSession session = request.getSession();
				int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
				int maxSize = 1024*1024*20;
				String root = session.getServletContext().getRealPath("/");
				
				String filePath = "resources/img";
				switch(boardType) {//게시판 별로 경로 지정
				case 1: filePath +="/tripboard/"; break;
				case 2: filePath +="/questionboard/"; break;
				case 3: filePath +="/togetherboard/"; break;
				}
				MultipartRequest mpRequest = new MultipartRequest(request, root+filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
				List<Attachment> atList = new ArrayList<Attachment>();
				Enumeration<String> images = mpRequest.getFileNames();
				
				while(images.hasMoreElements()) {
					String name = images.nextElement();
					if(mpRequest.getFilesystemName(name) != null) {
						Attachment at = new Attachment();
						at.setFilePath(filePath);
						at.setFileNm(mpRequest.getFilesystemName(name));
						at.setFileLevel(Integer.parseInt(name.substring("img".length())));
						atList.add(at);
					}
				}
				
				for(Attachment a : atList) {
					System.out.println(a);
				}
			
//				type, title, category,area, img0, img1, img2, memberNo,content
				String boardTitle = mpRequest.getParameter("title");
				String boardContent = mpRequest.getParameter("content");
				int categoryCode = Integer.parseInt(mpRequest.getParameter("category"));
				int area = Integer.parseInt(mpRequest.getParameter("area"));
				
				
				
				Board board = new Board();
				board.setAreaCode(area);
				board.setCategoryCode(categoryCode);
				board.setBoardContent(boardContent);
				board.setBoardTitle(boardTitle);
				board.setMemberNo(memberNo);
				int result = 0;
				if(boardType==4) {
					result = service.insertNoticeBoard(board);
				}else {
					
					result = service.insertBoard(board, atList, boardType);
				}
				
				System.out.println("result : " + result);
				
				if(result > 0) {
					
					icon = "success";
					title = "게시글 등록성공";
//				게시글 디테일 페이지 확정되면 수정필수!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					if(boardType==1) {
						
						path = request.getContextPath()+"/board/list?type="+boardType;
					}else {
						path = request.getContextPath()+"/board/list"+boardType+"?type="+boardType;
						
					}
				}else {
					
					icon = "error";
					title = "게시글 등록 실패";
					path = request.getHeader("referer");
				}
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				
				System.out.println(path);
				
				response.sendRedirect(path);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
