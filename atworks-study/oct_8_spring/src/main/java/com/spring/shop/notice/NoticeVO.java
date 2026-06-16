package com.spring.shop.notice;

import lombok.Data;

@Data
public class NoticeVO {
	
	private int ni_no;
	private String ni_title;
	private String ni_content;
	private String ni_insert_dt;
	private String ni_modifi_dt;
	private int mi_id;
}
