package kr.co.billiejoe.review.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.billiejoe.member.model.vo.Member;

import kr.co.billiejoe.common.model.vo.Pagination;
import kr.co.billiejoe.review.model.vo.RecentReview;
import kr.co.billiejoe.review.model.vo.Review;

@Repository
public class ReviewDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 전체 후기글 수 조회 DAO
	 * @return
	 */
	public int getListCount() {
		return sqlSession.selectOne("reviewMapper.getAllListCount");
	}
	
	/** 내가 작성한 전체 후기글 수 조회 DAO
	 * @return pagination
	 */
	public Pagination getListCount(Member loginMember) {
		return sqlSession.selectOne("reviewMapper.getListCount", loginMember.getMemberNo());
	}

	/** 내가 작성한 후기글 목록 조회 DAO
	 * @param pagination
	 * @return reviewList
	 */
	public List<Review> selectReviewList(Pagination pagination, Member loginMember) {
		
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("reviewMapper.selectReviewList", loginMember.getMemberNo(), rowBounds);
	}

	/** 내가 작성한 후기글 상세 조회 DAO
	 * @param placeNo
	 * @return review
	 */
	public Review selectReview(int reviewNo) {
		return sqlSession.selectOne("reviewMapper.selectReview", reviewNo);
	}

	/** 후기 작성 DB 삽입 DAO
	 * @param review
	 * @return reviewNo
	 */
	public int insertReview(Review review) {
		return sqlSession.insert("reviewMapper.insertReview", review);
	}

	/** 후기 수정 DAO
	 * @param review
	 * @return result
	 */
	public int updateReview(Review review) {
		return sqlSession.update("reviewMapper.updateReview", review);
	}

	/** 후기 삭제 DAO
	 * @param reviewNo
	 * @return result
	 */
	public int deleteReview(int reviewNo) {
		return sqlSession.update("reviewMapper.deleteReview", reviewNo);
	}

	/**
	 * 메인용 후기 조회 DAO
	 * @param reviewPagination
	 * @return
	 */
	public List<RecentReview> selectMainReviewList(int limit) {
		return sqlSession.selectList("reviewMapper.selectMainReviewList", limit);
	}
}
