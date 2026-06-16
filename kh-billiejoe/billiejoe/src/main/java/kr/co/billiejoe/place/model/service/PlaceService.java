package kr.co.billiejoe.place.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.billiejoe.place.model.vo.Likes;
import kr.co.billiejoe.place.model.vo.MyReservation;
import kr.co.billiejoe.place.model.vo.Payment;
import kr.co.billiejoe.place.model.vo.Place;
import kr.co.billiejoe.place.model.vo.PlaceAvailable;
import kr.co.billiejoe.place.model.vo.Report;
import kr.co.billiejoe.place.model.vo.Reservation;
import kr.co.billiejoe.place.model.vo.Search;
import kr.co.billiejoe.review.model.vo.Review;

import kr.co.billiejoe.common.model.vo.Pagination;

public interface PlaceService {

	List<Place> selectPlaceAddrList();
	
	Place placeView(int placeNo);

	List<Integer> reservationCheck(Reservation reservation);

	int likeCheck(int memberNo);

	int insertLike(Likes likes);

	int deleteLike(Likes likes);

	Pagination getPagination(Pagination pg, Search search);

	List<Place> selectPlaceList(Pagination pagination, Search search);

	int insertPlace(Place place, List<MultipartFile> images, String webPath, String savePath, String tagString, PlaceAvailable pa);

	/**  전체 목록 수 + 예약한 장소 조회
	 * @param pg
	 * @return
	 */
	Pagination getPagination(Pagination pg, int memberNo);

	/** 내가 예약한 장소 목록 조회
	 * @param pagination
	 * @param memberNo 
	 * @return placeList
	 */
	List<MyReservation> selectReservationList(Pagination pagination, int memberNo);

	int insertReservation(Reservation reservation, Payment payment);

	MyReservation getReservation(int reserveNo);

	int cancelRv(int reserveNo);

	int updateReservation(Map<String, String> map);


	/** 장소에 대한 전체 후기글 수 조회 Service
	 * @param pg
	 * @param loginMember 
	 * @param placeNo
	 * @return pagination
	 */
	Pagination getPagination2(Pagination pg, int placeNo);
	
	/** 장소에 대한 후기 목록 조회
	 * @param pagination
	 * @param loginMember
	 * @param placeNo 
	 * @return reviewListPlace
	 */
	List<Review> selectReviewListPlace(Pagination pagination, int placeNo);
	
	/** 장소에 대한 후기 평점 및 총 개수
	 * @param placeNo
	 * @return add
	 */
	Review addReview(int placeNo);
	
	/** 후기 신고 Service
	 * @param reviewNo
	 * @param memberNo
	 * @param report
	 * @return result
	 */
	int insertReport(Report report);
	/**
	 * 특정 장소 대여 가능 시간 조회 Service
	 * @param placeNo
	 * @return
	 */
	PlaceAvailable selectPlaceAvailable(Integer placeNo);

	/** 내가 찜한 장소 목록 조회
	 * @param pagination
	 * @param memberNo
	 * @return selectMyLikePlaceList
	 */
	List<MyReservation> selectMyLikePlaceList(Pagination pagination, int memberNo);

	/** 전체 목록 수 + 내가 좋아요한 장소 조회
	 * @param pg
	 * @param memberNo
	 * @return
	 */
	Pagination getLikePagination(Pagination pg, int memberNo);

	/** 내가 예약한 목록 목록 조회(정렬 조건 추가)
	 * @param pagination
	 * @param memberNo
	 * @param sort
	 * @return
	 */
	List<MyReservation> selectReservationList(Pagination pagination, int memberNo, String sort);
}
