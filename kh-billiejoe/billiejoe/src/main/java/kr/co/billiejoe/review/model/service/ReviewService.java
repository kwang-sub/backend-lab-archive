package kr.co.billiejoe.review.model.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import kr.co.billiejoe.member.model.vo.Member;

import kr.co.billiejoe.common.model.vo.Pagination;
import kr.co.billiejoe.review.model.vo.RecentReview;

import kr.co.billiejoe.review.model.vo.Review;

public interface ReviewService {
	
	/**
	 * 메인화면용 후기 글 수 Service
	 * @param pg
	 * @return
	 */
	Pagination getPagination(Pagination pg);
	
	/** 내가 작성한 전체 후기글 수 조회 Service
	 * @param pg
	 * @param loginMember 
	 * @return pagination
	 */
	Pagination getPagination(Pagination pg, Member loginMember);
	
	/** 내가 작성한 후기글 목록 조회 Service
	 * @param pagination
	 * @return reviewList
	 */
	List<Review> selectReviewList(Pagination pagination, Member loginMember);

	/** 내가 작성한 후기 상세조회 Service
	 * @param placeNo
	 * @return reviewView
	 */
	Review selectReview(int reviewNo);

	/** 후기 작성 DB 삽입 Service
	 * @param review
	 * @return reviewNo
	 */
	int insertReview(Review review);

	/** 후기글 수정용 상세 조회 Service
	 * @param reviewNo
	 * @return review
	 */
	Review selectUpdateReview(int reviewNo);

	/** 후기글 수정 Service
	 * @param review
	 * @return result
	 */
	int updateReview(Review review);

	/** 후기글 삭제 Service
	 * @param reviewNo
	 * @return result
	 */
	int deleteReview(int reviewNo);
	


	/**
	 * 메인용 리뷰 조회 Service
	 * @param reviewPagination
	 * @return
	 */
	List<RecentReview> selectMainReviewList(int limit);

}
