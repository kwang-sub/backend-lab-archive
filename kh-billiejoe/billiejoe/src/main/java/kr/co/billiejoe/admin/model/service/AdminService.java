package kr.co.billiejoe.admin.model.service;

import java.util.List;
import java.util.Map;

import kr.co.billiejoe.member.model.vo.Member;
import kr.co.billiejoe.common.model.vo.Pagination;
import kr.co.billiejoe.place.model.vo.Place;
import kr.co.billiejoe.place.model.vo.Report;
import kr.co.billiejoe.review.model.vo.Review;

public interface AdminService {

	/** 관리자 로그인 Service
	 * @param inputMember
	 * @return loginMember
	 */
	public abstract Member login(Member inputMember);

	public abstract List<Member> selectMemberList(Pagination pg);

	public abstract Pagination getMemberPg(Pagination pg);

	public abstract Pagination getMemberPg(Pagination pg, Map<String, String> search);

	public abstract List<Member> selectMemberList(Pagination pg, Map<String, String> search);

	public abstract int deleteMember(int memberNo);

	public abstract Pagination getUnRegMemberPg(Pagination pg);

	public abstract List<Member> selectUnRegMemberList(Pagination pg);

	public abstract Pagination getUnRegMemberPg(Pagination pg, Map<String, String> search);

	public abstract List<Member> selectUnRegMemberList(Pagination pg, Map<String, String> search);

	public abstract Pagination getReportListCount(Pagination pg);

	public abstract List<Report> selectReportList(Pagination pg);

	public abstract int reportCheck(int reviewNo);


	/** 관리자 게시판 총 후기 개수
	 * @param pg
	 * @return pagination
	 */
	public abstract Pagination getAdminReviewListCount(Pagination pg);

	
	
	/** 관리자 게시판 총 후기 목록
	 * @param pg
	 * @return adminReviewList
	 */
	public abstract List<Review> selectAdminReviewList(Pagination pg);
	
	/** 내가 작성한 후기 상세조회 Service
	 * @param placeNo
	 * @return reviewView
	 */
	Review selectAdminReview(int reviewNo);
	
	/** 후기글 삭제 Service
	 * @param reviewNo
	 * @return result
	 */
	
	int deleteReview(int reviewNo);
	public abstract Pagination getPagination(Pagination pg);

	public abstract List<Place> selectPlaceList(Pagination pagination);

	public abstract int updateStatus(Place place);

	public abstract Place placeDetailView(int placeNo);

	public abstract Pagination getPagination2(Pagination pg, int placeNo);

	public abstract List<Review> selectReviewListPlace(Pagination pagination, int placeNo);

	public abstract Review addReview(int placeNo);

	public abstract List<Integer> selectunMemberNo();

}
