package com.spring.shop.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberDAO {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);

	
	@Autowired
	SqlSession ss;
	
	/**로그인 기능
	 * @param req
	 */
	public void loginCheck(HttpServletRequest req) {
		MemberVO m = new MemberVO();
		m.setMi_id(req.getParameter("mi_id"));
		m.setMi_pw(req.getParameter("mi_pw"));
		
		MemberVO loginMember = ss.getMapper(MemberMapper.class).loginCheck(m);
		req.setAttribute("MSG", "하하하하");
		logger.info("로그인 완료한 객체 " + loginMember);
		
		req.getSession().setAttribute("loginMember", loginMember);
		
		
	}

	/**회원가입
	 * @param req
	 */
	@Transactional
	public void signUp(HttpServletRequest req) {
		MemberVO mem = new  MemberVO();
		mem.setMi_email(req.getParameter("mi_email"));
		mem.setMi_gender(req.getParameter("gender"));
		mem.setMi_id(req.getParameter("mi_id"));
		mem.setMi_mobile(Integer.parseInt(req.getParameter("mi_mobile")) );
		mem.setMi_pw(req.getParameter("mi_pw"));
		logger.info("회원가입 정보확인" + mem);
		
		int result = ss.getMapper(MemberMapper.class).signUp(mem);
		logger.info("삽입 결과 " + result);
	}

	/**수정
	 * @param req
	 */
	public void edit(HttpServletRequest req) {
		
		MemberVO mem = new MemberVO();
		mem.setMi_email(req.getParameter("mi_email"));
		mem.setMi_id(req.getParameter("mi_id"));
		mem.setMi_mobile(Integer.parseInt(req.getParameter("mi_mobile")) );
		mem.setMi_pw(req.getParameter("mi_pw"));
		mem.setMi_no(Integer.parseInt(req.getParameter("mi_no")) );
		logger.info("수정할 회원 정보 : " + mem);
		
		int result = ss.getMapper(MemberMapper.class).edit(mem);
		logger.info("회원정보 수정 결과 갑 : "+result);
	}
	
	
	@Transactional
	public int signOut(HttpServletRequest req) {
		
		int mi_no = Integer.parseInt(req.getParameter("mi_no"));
		
		logger.info("탈퇴할 회원 번호" + mi_no);
		
		int result = ss.getMapper(MemberMapper.class).signOut(mi_no);
		logger.info("탈퇴 결과값" + result);
		if(result>0) {
			req.getSession().invalidate();
		}
		
		return result;
		
	}

	public int checkId(HttpServletRequest req) {
		
		String mi_id = req.getParameter("mi_id");
		int result = ss.getMapper(MemberMapper.class).checkId(mi_id);
		
		return result;
	}


}
