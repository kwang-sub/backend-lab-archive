package kr.co.billiejoe.member.model.service;

import kr.co.billiejoe.member.model.vo.Member;

public interface MailService {


	int selectmemberDetail(Member member);

	/** 임시 비밀번호로 업데이트
	 * @param member
	 * @return result
	 */
	int updateTempPw(Member member);

}
