package com.spring.shop.notice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.board.BoardMapper;
import com.spring.shop.member.MemberDAO;
import com.spring.shop.member.MemberVO;

@Service
public class NoticeDAO {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);
	
	@Autowired
	SqlSession ss;

	public Map<String, Object> getList(HttpServletRequest req) {
		
		//페이징하기 위한 정보 받기
		int curPage = Integer.parseInt(req.getParameter("curPage")) ;
		int pagePerCnt = Integer.parseInt(req.getParameter("pagePerCnt"));
		int search_key_int =0;
		Map<String, Object> page = new HashMap<String, Object>();
		
		//페이지 검색어 받아서 하기
		String search_type = req.getParameter("search_type");
		String search_key = req.getParameter("search_key");
		if(search_type != null) {
			
			if(search_type.equals("ni_no")) {
				search_key_int = Integer.parseInt(search_key);
			}
		}
		
		page.put("search_type", search_type);
		page.put("search_key", search_key);
		page.put("search_key_int", search_key_int);
		
		
		
		Map<String,  Object> rst = new HashMap<String, Object>();
		
		int total = ss.getMapper(NoticeMapper.class).getTotal(page);
		
		page = generatePagingParam(total, pagePerCnt, curPage);
		page.put("search_type", search_type);
		page.put("search_key", search_key);
		page.put("search_key_int", search_key_int);
		
		
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = ss.getMapper(NoticeMapper.class).getList(page);
		
		rst.put("list", list);
		
		rst.put("total", total);
		
		return rst;
		
	}
	
	public Map<String, Object> generatePagingParam(float totalCnt, float pagePerCnt, int curPage) {
		Map<String, Object> pageValue = new HashMap<String, Object>();
		int pageCount = (int) Math.ceil(totalCnt / pagePerCnt);
		pageValue.put("S_OFFSET", pagePerCnt * (curPage - 1));
		pageValue.put("E_OFFSET", pagePerCnt * (curPage - 1) + pagePerCnt);
		pageValue.put("CUR_PAGE", curPage);
		pageValue.put("PAGE_CNT", pageCount);
		pageValue.put("TOTAL_CNT", totalCnt);

		return pageValue;
	}

	public void insertNotice(HttpServletRequest req) {
		
		MemberVO loginMember = (MemberVO)req.getSession().getAttribute("loginMember");
		NoticeVO insertNotice = new NoticeVO();
		
		
		insertNotice.setNi_content(req.getParameter("ni_content"));
		insertNotice.setNi_title(req.getParameter("ni_title"));
		insertNotice.setMi_id(loginMember.getMi_no());
		
		int result = ss.getMapper(NoticeMapper.class).write(insertNotice);
	    
		
	}

	public void view(HttpServletRequest req) {
		
		int ni_no = Integer.parseInt(req.getParameter("ni_no"));
		
		NoticeVO notice = ss.getMapper(NoticeMapper.class).getView(ni_no);
		req.setAttribute("notice", notice);
		
	}

	public void modify(HttpServletRequest req) {
		
		NoticeVO notice = new NoticeVO();
		
		notice.setNi_content(req.getParameter("ni_content"));
		notice.setNi_title(req.getParameter("ni_title"));
		notice.setNi_no(Integer.parseInt(req.getParameter("ni_no")));
		
		int result = ss.getMapper(NoticeMapper.class).modify(notice);
		
		if(result>0) {
			view(req);
		};
		
	}

	public void delete(HttpServletRequest req) {
		
		int ni_no = Integer.parseInt(req.getParameter("ni_no"));
		
		ss.getMapper(NoticeMapper.class).delete(ni_no);
		
	}
}
