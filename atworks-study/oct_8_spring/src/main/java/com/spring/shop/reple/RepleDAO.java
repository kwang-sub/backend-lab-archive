package com.spring.shop.reple;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.member.MemberVO;
import com.spring.shop.notice.NoticeDAO;
import com.spring.shop.notice.NoticeMapper;

@Service
public class RepleDAO {

	@Autowired
	SqlSession ss;
	
	public Map<String, Object> getList(HttpServletRequest req) {
		// TODO Auto-generated method stub
		int ni_no = Integer.parseInt(req.getParameter("ni_no"));
		int curPage = Integer.parseInt(req.getParameter("curPage"));
		int pagePerCnt = Integer.parseInt(req.getParameter("pagePerCnt"));
		
		int total = ss.getMapper(RepleMapper.class).getTotal(ni_no);
		NoticeDAO nDAO = new NoticeDAO();
		Map<String, Object> page =  nDAO.generatePagingParam(total, pagePerCnt, curPage);
		page.put("ni_no", ni_no);
		List<RepleVO> rList = ss.getMapper(RepleMapper.class).getList(page);
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		res.put("list", rList);
		res.put("total", total);
		return res;
	}

	public Map<String, Object> insert(HttpServletRequest req) {
		
		int ni_no = Integer.parseInt(req.getParameter("ni_no"));
		String ri_content = req.getParameter("ri_content");
		MemberVO loginMember = (MemberVO)req.getSession().getAttribute("loginMember");
		
		RepleVO re = new RepleVO();
		
		
		re.setNi_no(ni_no);
		re.setRi_content(ri_content);
		re.setMi_id(loginMember.getMi_id());
		
		int result = ss.getMapper(RepleMapper.class).insert(re);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("result", result);
		
		return res;
	}

	public Map<String, Object> modify(HttpServletRequest req) {
		
		int ri_no = Integer.parseInt(req.getParameter("ri_no"));
		String ri_content = req.getParameter("ri_content");
		
		RepleVO re = new RepleVO();
		
		re.setRi_content(ri_content);
		re.setRi_no(ri_no);
		int result = ss.getMapper(RepleMapper.class).modify(re);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("result", result);
		
		return res;
	}

	public Map<String, Object> delect(HttpServletRequest req) {
		
		int ri_no = Integer.parseInt(req.getParameter("ri_no"));
		
		int result = ss.getMapper(RepleMapper.class).delect(ri_no);
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		res.put("result", result);
		
		return res;
	}

}
