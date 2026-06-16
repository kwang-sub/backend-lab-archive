package com.spring.shop.member;

public interface MemberMapper {
	public abstract MemberVO loginCheck(MemberVO vo);

	public abstract int signUp(MemberVO mem);

	public abstract int edit(MemberVO mem);

	public abstract int signOut(int mi_no);

	public abstract int checkId(String mi_id);
}
