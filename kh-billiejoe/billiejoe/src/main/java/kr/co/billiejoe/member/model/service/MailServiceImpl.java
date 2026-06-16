package kr.co.billiejoe.member.model.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.billiejoe.member.model.dao.MailDAO;
import kr.co.billiejoe.member.model.vo.Member;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	private BCryptPasswordEncoder bc;
	
	@Autowired
	private MailDAO dao;

	@Override
	public int selectmemberDetail(Member member) {
		return dao.selectmemberDetail(member);
	}

	// 임시 비밀번호로 업데이트
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateTempPw(Member member) {
		
		String encPw = bc.encode(member.getMemberPw()); // 비밀번호 암호화
		
		member.setMemberPw(encPw); // 암호화된 비밀번호를 member에 추가
		
		return dao.updateTempPw(member);
	}

	
}
