package kr.co.billiejoe.place.model.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.billiejoe.place.exception.SaveFileException;
import kr.co.billiejoe.place.model.dao.PlaceDAO;
import kr.co.billiejoe.place.model.vo.Attachment;
import kr.co.billiejoe.place.model.vo.Likes;

import kr.co.billiejoe.place.model.vo.MyReservation;
import kr.co.billiejoe.place.model.vo.Payment;
import kr.co.billiejoe.place.model.vo.Place;
import kr.co.billiejoe.place.model.vo.PlaceAvailable;
import kr.co.billiejoe.place.model.vo.PlaceTag;
import kr.co.billiejoe.place.model.vo.Report;
import kr.co.billiejoe.place.model.vo.Reservation;
import kr.co.billiejoe.place.model.vo.Search;
import kr.co.billiejoe.place.model.vo.Tag;
import kr.co.billiejoe.review.model.vo.Review;

import kr.co.billiejoe.common.model.vo.Pagination;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService{
	@Autowired
	private PlaceDAO dao;

	/**게시글 상세보기
	 *
	 */
	@Override
	public Place placeView(int placeNo) {
		return dao.placeView(placeNo);
	}
	/**로그인한 멤버가 게시물 좋아요 했는지 확인
	 *
	 */
	@Override
	public int likeCheck(int memberNo) {
		
		return dao.likeCheck(memberNo);
	}
	/**상세보기중 예약 가능 시간 구하기
	 *
	 */
	@Override
	public List<Integer> reservationCheck(Reservation reservation) {
		//		예약가능시간 list
		List<Integer> time = new ArrayList<Integer>();
		//		판매자가 지정한 시작시간 마무리시간 구해서 for문으로 삽입
		PlaceAvailable available = dao.getAvailable(reservation.getPlaceNo());
		for (int i = available.getStart(); i<available.getEnd(); i++) {
			time.add(i);
		}
		
		//		조회해온 예약된 리스트 
		List<Reservation> timeList = dao.getTimeList(reservation);
		//		리스트 하나씩 접급하면서 예약 시작시간부터 끝난시간 지우는 for문 진행시 list전부다 출력이안됨....
		//		System.out.println("timeList : " + timeList);
		List<Integer> untime = new ArrayList<Integer>();
		for(Reservation re : timeList) {
			for(int i = re.getUseStart(); i<re.getUseEnd(); i++) {
				untime.add(i);
			}
		}
		
		List<Integer> todayTime = new ArrayList<Integer>();
		Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String today = sdf.format(date);
		if(today.equals(reservation.getUseDate())){
            LocalTime now = LocalTime.now();
            for (int i = 0; i<now.getHour()+1; i++){
                todayTime.add(i);
            }
        }
		time.removeAll(untime);
		time.removeAll(todayTime);
		
		return time;
	}
	
	/**좋아요 추가하기
	 *
	 */
	@Override
	public int insertLike(Likes likes) {
		return dao.insertLike(likes);
	}
	
	// 	좋아요 삭제하기
	@Override
	public int deleteLike(Likes likes) {
		return dao.deleteLike(likes);
	}
	
	// 장소 수 조회 Service
	@Override
	public Pagination getPagination(Pagination pg, Search search) {
		int selectPg = dao.getListCount(search);
		return new Pagination(pg.getCurrentPage(), selectPg);
	}
	
	// 전체 목록 수 + 예약한 장소 조회
	@Override
	public Pagination getPagination(Pagination pg, int memberNo) {
		int listCount = dao.getListCount(memberNo);
		return new Pagination(pg.getCurrentPage(), listCount) ;
	}
	

	// 장소 목록 조회
	@Override
	public List<Place> selectPlaceList(Pagination pagination, Search search) {
		List<Place> placeList = dao.selectPlaceList(pagination, search);
//		for(Place place : placeList) {
//			System.out.println(place);
//			place.setTagList(dao.selectPlaceTagList(place.getPlaceNo()));
//			place.setAtList(dao.selectPlaceThumbnail(place.getPlaceNo()));
//		}
		return placeList;
	}
	
	// 지역 목록 선택 
	@Override
	public List<Place> selectPlaceAddrList() {
		List<Place> arrdList = dao.selectPlaceAddrList();
		return arrdList;
	}
	
	// 장소 추가/수정
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int insertPlace(Place place, List<MultipartFile> images, String webPath, String savePath, String tagString, PlaceAvailable pa) {
		place.setPlaceName(replaceParameter(place.getPlaceName()));
		place.setPlaceSummary(replaceParameter(place.getPlaceSummary()));
		int placeNo = 0;
		boolean isNew = false;
		if(place.getPlaceNo() > 0) {
			placeNo = dao.updatePlace(place);
			pa.setPlaceNo(placeNo);
			dao.updatePlaceAvailable(pa);
		} else {
			placeNo = dao.insertPlace(place);
			pa.setPlaceNo(placeNo);
			dao.insertPlaceAvailable(pa);
			isNew = true;
		}
		
		if(placeNo > 0) {
			List<Attachment> atList = new ArrayList<Attachment>();
			for(int i = 0; i < images.size(); i++) {
				if(!images.get(i).getOriginalFilename().equals("")) {
					String fileName = rename(images.get(i).getOriginalFilename());
					Attachment at = new Attachment();
					at.setFileName(fileName);
					at.setFilePath(webPath);
					at.setPlaceNo(placeNo);
					at.setFileLevel(i);
					
					atList.add(at);
				}
			}
			List<Tag> tagList = new ArrayList<Tag>();
			// tagString 분리 후 삽입
			String[] tagArr = tagString.split(",");
			for(String tagItem : tagArr) {
				Tag tag = new Tag();
				PlaceTag placeTag = new PlaceTag();
				if(!isNew) { // 지금 할 수 있는 가장 빠른 해결방법... (다 지우고 다시 등록)
					dao.deleteAllPlaceTag(placeNo);
				}
				int result = 0;
				tag = dao.isExistTag(tagItem); // DB에 등록되어 있는 태그인지 확인
				if(tag != null) { // 이미 존재하는 태그라면
					placeTag.setPlaceNo(placeNo);
					placeTag.setTagNo(tag.getTagNo());
					dao.insertTagInPlaceTags(placeTag);
				} else { // 등록된 적 없는 태그라면
					result = dao.insertTags(tagItem);
					if(result > 0) {
						tag = dao.isExistTag(tagItem);
						if(tag != null) {
							placeTag.setPlaceNo(placeNo);
							placeTag.setTagNo(tag.getTagNo());
							dao.insertTagInPlaceTags(placeTag);
						}
					}
				}
				tagList.add(tag);
			}
			
			if(!atList.isEmpty()) {
				int result = 0;
				if(!isNew) {
//					dao.deleteAttachmentList(atList);
				} 
				result = dao.insertAttachmentList(atList);
				
				
				if(atList.size() == result) { // 모두 삽입 성공
					// 파일을 서버에 저장(transfer())
					for(int i = 0; i < atList.size(); i++) {
						try {
							images.get(atList.get(i).getFileLevel()).transferTo(new File(savePath + "/" + atList.get(i).getFileName()));
						} catch (Exception err) {
							err.printStackTrace();
							throw new SaveFileException();
						}
					}
				} else {
					throw new SaveFileException();
				}
			}
		}
		return placeNo;
	}

	// 내가 예약한 목록 조회
	@Override
	public List<MyReservation> selectReservationList(Pagination pagination, int memberNo) {
		return dao.selectReservationList(pagination,memberNo);
	}
			
	/**장소예약하기 
	 *
	 */
	@Override
	public int insertReservation(Reservation reservation, Payment payment) {
		//		예약 번호 구하기
		int reserveNo = dao.getReserveNo();
		reservation.setReserveNo(reserveNo);
		int result = dao.insertReservation(reservation);
		if(result>0) {
			payment.setReserveNo(reserveNo);
			result = dao.insertPayment(payment);
		}
		return result;
	}
	
	/**예약내용 조회
	 *
	 */
	@Override
	public MyReservation getReservation(int reserveNo) {
		// TODO Auto-generated method stub
		return dao.getReservation(reserveNo);
	}
	
	/**사용자 예약취소
	 *
	 */
	@Override
	public int cancelRv(int reserveNo) {
		// TODO Auto-generated method stub
		return dao.cancelRv(reserveNo);
	}

	// XSS Proof Method
	public static String replaceParameter(String param) {
		String result = param;
    	if(param != null) {
    		result = result.replaceAll("&", "&amp;");
    		result = result.replaceAll("<", "&lt;");
    		result = result.replaceAll(">", "&gt;");
    		result = result.replaceAll("\"", "&quot;");
    	}
    	return result;
	}

	// 파일명 변경 메소드
	private String rename(String originFileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new java.util.Date(System.currentTimeMillis()));
		int ranNum = (int)(Math.random()*100000);
		String str = "_" + String.format("%05d", ranNum);
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		return date + str + ext;
	}
	
	/**스케줄러 이용 예약상태변경
	 *
	 */
	@Override
	public int updateReservation(Map<String, String> map) {
		int result = dao.startReservation(map);
		result = dao.completReservation(map);
		return result;
	}
	

	// 장소에 대한 후기글 수 조회
	@Override
	public Pagination getPagination2(Pagination pg, int placeNo) {
		
		// 1) 장소에 대한 전체 후기글 수 조회
		Pagination selectPlacePg = dao.getListCountPlace(placeNo);
		
		// 계산이 완료된 Pagination 객체 생성 후 반환
		return new Pagination(pg.getCurrentPage(), selectPlacePg.getListCount() );
	}
	
	// 장소에 대한 후기 목록 조회
	@Override
	public List<Review> selectReviewListPlace(Pagination pagination, int placeNo) {
		return dao.selectReviewListPlace(pagination, placeNo);
	}
	
	// 장소에 대한 후기 평점 및 총 개수Service
	
	@Override
	public Review addReview(int placeNo) {
		return dao.addReview(placeNo);
	}
	
	// 후기 신고 Service
	@Override
	public int insertReport(Report report) {
		
		int result = dao.insertReport(report);
		
		return result;
	}
	
	//특정 장소 대여 가능 시간 조회 Service
	@Override
	public PlaceAvailable selectPlaceAvailable(Integer placeNo) {
		return dao.selectPlaceAvailable(placeNo);
	}
	
	// 내가 찜한 장소 목록
	@Override
	public List<MyReservation> selectMyLikePlaceList(Pagination pagination, int memberNo) {
		return dao.selectMyLikePlaceList(pagination, memberNo);	
	}
	
	// 전체 목록 수 + 내가 좋아요한 장소 조회
	@Override
	public Pagination getLikePagination(Pagination pg, int memberNo) {
		int listCount = dao.getLikeListCount(memberNo);

		// 2) 계산이 완료된 Pagination 객체 생성 후 반환
		return new Pagination(pg.getCurrentPage(), listCount) ;

	}
	
	// 내가 예약한 목록 목록 조회(정렬 조건 추가)
	@Override
	public List<MyReservation> selectReservationList(Pagination pagination, int memberNo, String sort) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberNo", memberNo);
		map.put("sort", sort);
		return dao.selectReservationList(pagination, map);
	}

}
