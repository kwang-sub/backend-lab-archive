package kr.co.billiejoe.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.billiejoe.member.model.vo.Member;
import kr.co.billiejoe.common.model.vo.Pagination;
import kr.co.billiejoe.place.model.vo.Place;
import kr.co.billiejoe.place.model.vo.Report;
import kr.co.billiejoe.review.model.vo.Review;

@Repository
public class AdminDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 관리자 로그인 DAO
	 * @param memberEmail
	 * @return loginMember
	 */
	public Member login(String memberEmail) {
		return sqlSession.selectOne("adminMapper.login", memberEmail);
	}

	/**총회원수 조회
	 * @return
	 */
	public int getListCount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("adminMapper.getListCount",null);
	}

	/**회원목록 조회
	 * @param pg
	 * @return
	 */
	public List<Member> selectMemberList(Pagination pg) {
		int offset = (pg.getCurrentPage()-1)*pg.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pg.getLimit());
		
		return sqlSession.selectList("adminMapper.selectMemberList", pg, rowBounds);
	}

	public int getListCount(Map<String, String> search) {
		return sqlSession.selectOne("adminMapper.getSearchListCount", search);
	}

	public List<Member> selectMemberList(Pagination pg, Map<String, String> search) {
		int offset = (pg.getCurrentPage()-1)*pg.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pg.getLimit());
		return sqlSession.selectList("adminMapper.getSearchList", search, rowBounds);
	}

	/**탈퇴하는 멤버 정보 조회
	 * @param memberNo
	 * @return
	 */
	public Member selectDeleteMember(int memberNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("adminMapper.selectDeleteMember", memberNo);
	}

	public int insertUnreg(Member member) {
		// TODO Auto-generated method stub
		return sqlSession.insert("adminMapper.insertUnreg",member);
	}

	

	/** 탈퇴한 회원수 조회
	 * @return
	 */
	public int getUnRegListCount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("adminMapper.getUnRegListCount",null);
	}

	public List<Member> selectUnRegMemberList(Pagination pg) {
		int offset = (pg.getCurrentPage()-1)*pg.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pg.getLimit());
		
		return sqlSession.selectList("adminMapper.selectUnRegMemberList", pg, rowBounds);
	}

	public int getUnRegListCount(Map<String, String> search) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("adminMapper.getSearchUnRegListCount", search);
	}

	public List<Member> selectUnRegMemberList(Pagination pg, Map<String, String> search) {
		int offset = (pg.getCurrentPage()-1)*pg.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pg.getLimit());
		return sqlSession.selectList("adminMapper.getSearchUnRegList", search, rowBounds);
	}

	public int getReportListCount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("adminMapper.getReportListCount",null);
	}

	public List<Report> selectReportList(Pagination pg) {
		int offset = (pg.getCurrentPage()-1)*pg.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pg.getLimit());
		return sqlSession.selectList("adminMapper.selectReportList",pg, rowBounds);
	}

	/**신고 처리하기
	 * @param reviewNo
	 * @return
	 */
	public int reportCheck(int reviewNo) {
		// TODO Auto-generated method stub
		return sqlSession.update("adminMapper.reportCheck",reviewNo);
	}


	/** 관리자 게시판 총 후기 개수
	 * @return pg
	 */
	public int getAdminReviewListCount() {
		return sqlSession.selectOne("adminMapper.getAdminReviewListCount", null);
	}

	/** 관리자 게시판 총 후기 목록
	 * @param pg
	 * @return selectAdminReviewList
	 */
	public List<Review> selectAdminReviewList(Pagination pg) {
		int offset = (pg.getCurrentPage()-1)*pg.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pg.getLimit());
		return sqlSession.selectList("adminMapper.selectAdminReviewList",pg, rowBounds);
	}
	
	/** 관리자 후기 상세조회
	 * @param reviewNo
	 * @return review
	 */
	public Review selectAdminReview(int reviewNo) {
		return sqlSession.selectOne("adminMapper.selectAdminReview", reviewNo);
	}
	
	/** 후기 삭제 DAO
	 * @param reviewNo
	 * @return result
	 */
	public int deleteReview(int reviewNo) {
		return sqlSession.update("reviewMapper.deleteReview", reviewNo);
	}

	/** 전체 게시글 수 조회
	 * @return
	 */
	public int getBoardListCount() {
		return sqlSession.selectOne("adminMapper.getBoardListCount", null);
	}

	
	// 전체 게시글 목록 조회
	public List<Place> selectPlaceList(Pagination pagination) {
		int offset = (pagination.getCurrentPage() -1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		// offset 만큼 건너 뛰고, limit만큼의 행을 얻어옴
		
		return sqlSession.selectList("adminMapper.selectPlaceList",null , rowBounds);
		
	}

	public int updateStatus(Place place) {
		return sqlSession.update("adminMapper.updateStatus", place);
	}

	public Place placeDetailView(int placeNo) {
		return sqlSession.selectOne("adminMapper.placeDetailView", placeNo);
	}

	public Pagination getListCountPlace(int placeNo) {
		return sqlSession.selectOne("adminMapper.placeListCount", placeNo);
	}

	public List<Review> selectReviewListPlace(Pagination pagination, int placeNo) {
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		return sqlSession.selectList("reviewMapper.placeReviewList", placeNo, rowBounds);
	}

	public Review addReview(int placeNo) {
		return sqlSession.selectOne("reviewMapper.addReview", placeNo);
	}

	public List<Integer> selectunMemberNo() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("adminMapper.selectunMemberNo");
	}
	
}
