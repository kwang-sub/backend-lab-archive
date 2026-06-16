package kr.co.billiejoe.admin.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.billiejoe.admin.model.dao.AdminDAO;
import kr.co.billiejoe.member.model.vo.Member;
import kr.co.billiejoe.common.model.vo.Pagination;
import kr.co.billiejoe.place.model.vo.Place;
import kr.co.billiejoe.place.model.vo.Report;
import kr.co.billiejoe.review.model.vo.Review;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDAO dao;
	
	// 암호화 객체 생성
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// 로그인 Service
	@Override
	public Member login(Member inputMember) {
		
		// 암호화
		String encPw = bCryptPasswordEncoder.encode(inputMember.getMemberPw());
		
		Member loginMember = dao.login(inputMember.getMemberEmail());
		
		System.out.println(loginMember);
		
		if(loginMember != null) {
			
			if( !bCryptPasswordEncoder.matches(inputMember.getMemberPw(), loginMember.getMemberPw()) ) {
				loginMember = null;
			
			}else {
				loginMember.setMemberPw(null); 
			}
		
		}	
		
		return loginMember;
	}

	/**회원목록조회
	 *
	 */
	@Override
	public List<Member> selectMemberList(Pagination pg) {
		return dao.selectMemberList(pg) ;
	}

	/**회원목록 페이징
	 *
	 */
	@Override
	public Pagination getMemberPg(Pagination pg) {
		int listCount = dao.getListCount();
		pg.setListCount(listCount);
		return pg;
	}

	/**검색된 회원수 구하기
	 *
	 */
	@Override
	public Pagination getMemberPg(Pagination pg, Map<String, String> search) {
		int listCount = dao.getListCount(search);
		pg.setListCount(listCount);
		return pg;
		
	}

	/**검색된 회원리스트 구하기
	 *
	 */
	@Override
	public List<Member> selectMemberList(Pagination pg, Map<String, String> search) {
		return dao.selectMemberList(pg,search);
	}

	/**회원 탈퇴 승인
	 *
	 */
	@Override
	public int deleteMember(int memberNo) {
		Member member = dao.selectDeleteMember(memberNo);
		int result = 0;
		if(member != null) {
			result = dao.insertUnreg(member);
			
		}
		return result;
	}

	/**탈퇴한 회원수 조회 
	 *
	 */
	@Override
	public Pagination getUnRegMemberPg(Pagination pg) {
		int listCount = dao.getUnRegListCount();
		pg.setListCount(listCount);
		return pg;
	}

	/**탈퇴한 회원 목록 조회
	 *
	 */
	@Override
	public List<Member> selectUnRegMemberList(Pagination pg) {
		// TODO Auto-generated method stub
		return dao.selectUnRegMemberList(pg);
	}

	/**검색된 탈퇴회원수 조회
	 *
	 */
	@Override
	public Pagination getUnRegMemberPg(Pagination pg, Map<String, String> search) {
		int listCount = dao.getUnRegListCount(search);
		System.out.println(listCount);
		pg.setListCount(listCount);
		return pg;
	}

	/**검색된 탈퇴회원 목록조회
	 *
	 */
	@Override
	public List<Member> selectUnRegMemberList(Pagination pg, Map<String, String> search) {
		// TODO Auto-generated method stub
		return dao.selectUnRegMemberList(pg,search);
	}

	/**신고글 수 구하기
	 *
	 */
	@Override
	public Pagination getReportListCount(Pagination pg) {
		int listCount = dao.getReportListCount();
		pg.setListCount(listCount);
		return pg;
	}

	/**신고글 목록조회
	 *
	 */
	@Override
	public List<Report> selectReportList(Pagination pg) {
		// TODO Auto-generated method stub
		return dao.selectReportList(pg);
	}

	/**신고처리하기
	 *
	 */
	@Override
	public int reportCheck(int reviewNo) {
		// TODO Auto-generated method stub
		return dao.reportCheck(reviewNo);
	}


	// 관리자 게시판 총 후기개수
	@Override
	public Pagination getAdminReviewListCount(Pagination pg) {
		int listCount = dao.getAdminReviewListCount();
		pg.setListCount(listCount);
		System.out.println("impl : " + listCount); // 9
		System.out.println("impl : " + pg); // 9
		return pg;
	}

	// 관리자 게시판 총 후기 목록
	@Override
	public List<Review> selectAdminReviewList(Pagination pg) {
		return dao.selectAdminReviewList(pg);
	}
	
	// 관리자 후기 상세조회
	@Override
	public Review selectAdminReview(int reviewNo) {
		
		Review reviewView = dao.selectAdminReview(reviewNo);
		
		return reviewView;
	}
	
	
	// 후기글 삭제 Service
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteReview(int reviewNo) {
		
		int result = dao.deleteReview(reviewNo);
		
		return result;
	}
	// 전체 게시글 수 조회
		@Override
		public Pagination getPagination(Pagination pg) {
			
			// 1) 전체 목록 수 조회
			int boardListCount = dao.getBoardListCount();
			
			return new Pagination(pg.getCurrentPage(), boardListCount) ;
			
		}

		// 전체 게시글 목록 조회
		@Override
		public List<Place> selectPlaceList(Pagination pagination) {
			return dao.selectPlaceList(pagination);
		}

		@Override
		public int updateStatus(Place place) {
			return dao.updateStatus(place);
		}

		@Override
		public Place placeDetailView(int placeNo) {
			return dao.placeDetailView(placeNo);
		}

		@Override
		public Pagination getPagination2(Pagination pg, int placeNo) {
			// 1) 장소에 대한 전체 후기글 수 조회
			Pagination selectPlacePg = dao.getListCountPlace(placeNo);
			
			// 계산이 완료된 Pagination 객체 생성 후 반환
			return new Pagination(pg.getCurrentPage(), selectPlacePg.getListCount() );
		}
		
		@Override
		public List<Review> selectReviewListPlace(Pagination pagination, int placeNo) {
			return dao.selectReviewListPlace(pagination, placeNo);
		}

		@Override
		public Review addReview(int placeNo) {
			return dao.addReview(placeNo);
		}

		@Override
		public List<Integer> selectunMemberNo() {
			// TODO Auto-generated method stub
			return dao.selectunMemberNo();
		}
		
		
		


}
