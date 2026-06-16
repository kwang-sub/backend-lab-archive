package edu.kh.yeowoori.member.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.yeowoori.common.MyFileRenamePolicy;
import edu.kh.yeowoori.member.model.service.MemberService;
import edu.kh.yeowoori.member.model.vo.Member;


@WebServlet("/member/updateMyPage")
public class UpdateMyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/updateMyPage.jsp");
		view.forward(request, response);
		request.setCharacterEncoding("UTF-8");
				
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// MultipartRequest 객체를 만들기 위한 값 준비
		int maxSize = 1024 * 1024 * 20; // 20MB 용량 제한
		
		// 실제 서버 저장 경로 + 웹상 접근 경로
		String root = session.getServletContext().getRealPath("/"); // WebContent의 실제 경로
		String filePath = "resources/img/profile-img"; // 
		
		MultipartRequest mpRequest 
			= new MultipartRequest(request, root + filePath, maxSize, 
								"UTF-8", new MyFileRenamePolicy() );
		// MultipartRequest 생성과 동시에 업로드된 파일 일 지정된 경로에 저장된다
	
		int memberNo = loginMember.getMemberNo(); // 회원 번호 
		String memberNickname = mpRequest.getParameter("nickName");
		String memberContent = mpRequest.getParameter("intro");
		String memberProfile = loginMember.getMemberProfile(); // 이전 프로필 이미지 경로
		
		
		Member member = new Member();
		member.setMemberNo(memberNo);
		member.setMemberNickname(memberNickname);
		member.setMemberContent(memberContent);
		
		Enumeration<String> images = mpRequest.getFileNames();
		
		if(images.hasMoreElements()) { // 다음 name 속성 값이 있으면
			
			String name = images.nextElement(); // name 하나 얻어오기
			System.out.println("name : " + name);
			System.out.println("변경 전 : " + mpRequest.getOriginalFileName(name));
			System.out.println("변경 후  : " + mpRequest.getFilesystemName(name));
			
			// 업로드된 파일이 있을 때
			if(mpRequest.getFilesystemName(name) != null) {
				memberProfile = "/" + filePath + "/" + mpRequest.getFilesystemName(name);
				member.setMemberProfile(memberProfile);
			}
			
		}
		
		try {
			int result = new MemberService().memberUpdate(member) ;
			
			String icon = null;
			String title = null;
			String text = null;
			
			
			if(result > 0) { 
				icon = "success";
				title = "회원 정보 수정 성공";
				text = "회원 정보가 수정되었습니다.";
			
				loginMember.setMemberNickname(memberNickname);
				loginMember.setMemberProfile(memberProfile);
				loginMember.setMemberContent(memberContent);
				
				
			}else { 
				icon = "error";
				title = "회원 정보 수정 실패";
				text = "회원 정보 수정 중 문제가 발생했습니다.\n문제가 지속될 경우 고객센터 문의 바랍니다.";
			}
			
			session.setAttribute("icon", icon);
			session.setAttribute("title", title);
			session.setAttribute("text", text);
			
			
			
			
			response.sendRedirect("myPage/my");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "회원 정보 수정 과정에서 오류 발생");
			
			RequestDispatcher view 
				= request.getRequestDispatcher("WEB-INF/views/common/error.jsp");
			
			view.forward(request, response);
		}
	}

}
