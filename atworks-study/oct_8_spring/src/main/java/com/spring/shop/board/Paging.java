package com.spring.shop.board;

import java.util.HashMap;
import java.util.Map;

public class Paging {
	
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
}
