package com.spring.shop.notice;

import java.util.List;
import java.util.Map;

public interface NoticeMapper  {

	public abstract List<Map<String, Object>> getList(Map<String, Object> page);

	public abstract int getTotal(Map<String, Object> page);

	public abstract int write(NoticeVO insertNotice);

	public abstract NoticeVO getView(int ni_no);

	public abstract int modify(NoticeVO notice);

	public abstract void delete(int ni_no);

}
