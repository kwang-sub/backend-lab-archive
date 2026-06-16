package kr.co.billiejoe.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.billiejoe.member.model.vo.Member;

@Repository
public class MailDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int selectmemberDetail(Member member) {
		return sqlSession.selectOne("emailMapper.memberDetail", member);
	}

	
	/** 임시 비밀번호로 업데이트
	 * @param member
	 * @return result
	 */
	public int updateTempPw(Member member) {
		return sqlSession.update("emailMapper.updateTempPw", member);
	}

	

}
