package com.spring.shop.member;

import lombok.Data;


@Data
public class MemberVO {
	
	private int mi_no;
	private String mi_id;
	private String mi_pw;
	private int mi_mobile;
	private String mi_email;
	private String mi_gender;
	private String mi_insert_dt;
}
