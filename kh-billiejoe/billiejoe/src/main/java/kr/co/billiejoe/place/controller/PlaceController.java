package kr.co.billiejoe.place.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.billiejoe.member.controller.MemberController;
import kr.co.billiejoe.member.model.vo.Member;
import kr.co.billiejoe.place.model.service.PlaceService;
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

@RequestMapping("/place/*")
@SessionAttributes({"loginMember"})
@Controller
public class PlaceController {
	@Autowired
	private PlaceService service;
	
	/**
	 * 장소 목록
	 * @param cp
	 * @param model
	 * @param pg
	 * @return
	 */
	@GetMapping("list")
	public String placeList(@RequestParam(value = "cp", required = false, defaultValue = "1") int cp, Model model, Pagination pg, Search search) {
		Pagination pagination = service.getPagination(pg, search);
		List<Place> placeList = service.selectPlaceList(pagination, search);
		pg.setCurrentPage(cp);
		List<Place> addrList = service.selectPlaceAddrList();
		Set<String> region = new HashSet<>();
		for(Place place : addrList ) {
			String[] splitAddr = place.getPlaceAddr().split(" ");
			region.add(splitAddr[1]);
		}

		model.addAttribute("placeList", placeList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("region", region);
		
		return "place/placeList";
	}
	
	/** 장소 상세보기
	 * @param boardNo
	 * @param model
	 * @param cp
	 * @return
	 */
	@GetMapping("{placeNo}/view")
	public String placeView(@PathVariable("placeNo")int placeNo,Model model, Pagination pg, @RequestParam(value = "cp", required = false, defaultValue = "1")int cp
							){
		Member loginMember = (Member)model.getAttribute("loginMember");
		int like = 0;
		if(loginMember !=null) {
			like = service.likeCheck(loginMember.getMemberNo());
		}
		Place place = service.placeView(placeNo);
		place.setPlaceAddr(place.getPlaceAddr().substring(5));
		model.addAttribute("like",like);
		model.addAttribute("place",place);
		
		
		pg.setCurrentPage(cp);
		
		Pagination pagination = null;
		List<Review> reviewListPlace = null;
		
		
		Review add = null;
		pagination = service.getPagination2(pg, placeNo);
		pagination.setLimit(5);
		reviewListPlace = service.selectReviewListPlace(pagination, placeNo);
		add = service.addReview(placeNo);
		model.addAttribute("reviewListPlace", reviewListPlace);
		model.addAttribute("pagination", pagination);
		model.addAttribute("add", add);
		
		
		return "place/placeView";
				
	}
	
	/**장소 상세보기중 예약가능시간 조회
	 * @param date
	 * @param placeNo
	 * @return
	 */
	@ResponseBody
	@PostMapping("{placeNo}/reservationCheck")
	public Map<String, Object> reservationCheck(String date, @PathVariable("placeNo")int placeNo) {
		
			Reservation reservation = new Reservation();
			reservation.setPlaceNo(placeNo);
			reservation.setUseDate(date);
			List<Integer> time =service.reservationCheck(reservation);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("time", time);
			
		return result;
	}
	
	/**
	 * 좋아요 체크
	 * @param like
	 * @param model
	 * @param placeNo
	 * @return
	 */
	@PostMapping("{placeNo}/likeCheck")
	@ResponseBody
	public int likebCheck(Integer like, Model model, @PathVariable("placeNo")int placeNo) {
		Member loginMember = (Member)model.getAttribute("loginMember");
		Likes likes = new Likes();
		int result=0;
		if(loginMember == null ) {
			result =3; 
		}else {
			likes.setMemberNo(loginMember.getMemberNo());
			likes.setPlaceNo(placeNo);
//		비로그인 상태일때
//		좋아요 안한 회원일때
			if(like==0) {
				result =service.insertLike(likes);
				result = 1;
			}
//		좋아요 한 회원일때
			if(like==1) {
				result = service.deleteLike(likes);
				result = 2;
			}
			
		}
		
		return result;
	
	}
	
	/**
	 * 장소 추가/수정 페이지 
	 * @param model
	 * @return
	 */
	@GetMapping({"addPlace", "addPlace/{placeNo}"})
	public String insertPlace(@PathVariable(name="placeNo", required=false) Integer placeNo, Model model, RedirectAttributes ra) {
		Member loginMember = (Member) model.getAttribute("loginMember");
		if(loginMember == null) {
			return "member/login";
		}
		if(placeNo != null) {
			Place place = service.placeView(placeNo);
			PlaceAvailable pa = service.selectPlaceAvailable(placeNo);
			if(place.getMemberNo() == loginMember.getMemberNo()) {
				model.addAttribute("place", place);
				model.addAttribute("pa", pa);
			} else {
				MemberController.swalSetMessage(ra, "error", "잘못된 접근", "본인이 작성한 글만 수정할 수 있습니다.");
				return "redirect:/";
			}
		} 
		return "place/placeWrite";
	}
	
	/**
	 * 장소 추가
	 * @param place
	 * @param loginMember
	 * @param images
	 * @param request
	 * @param ra
	 * @return
	 */
	@PostMapping({"write", "write/{placeNo}"})
	public String insertPlace(@PathVariable(name="placeNo", required=false) Integer placeNo, Place place, PlaceAvailable pa, @ModelAttribute("loginMember") Member loginMember, @RequestParam("images") List<MultipartFile> images, @RequestParam("tagString") String tagString, HttpServletRequest request, RedirectAttributes ra) {
		place.setMemberNo(loginMember.getMemberNo());
		String webPath = "resources/images/";
		String savePath = request.getSession().getServletContext().getRealPath(webPath);
		if(placeNo != null) {
			place.setPlaceNo(placeNo);
		}
		placeNo = service.insertPlace(place, images, webPath, savePath, tagString, pa);

		String path = null;
		if(placeNo > 0) {
			path = "redirect:" + placeNo + "/view";
		} 
		else {
			// MemberController.swalSetMessage(ra, "error", "실패", null);
			System.out.println("장소 추가 실패");
			path = "redirect:" + request.getHeader("referer");
		}
		return path;
	}
	
	/**
	 * 결제
	 * @param reservation
	 * @param checkBox
	 * @param sumPrice
	 * @param placeNo
	 * @param model
	 * @return
	 */
	@PostMapping("{placeNo}/payMent")
	public String payMent(Reservation reservation,@RequestParam(value = "checkbox") int[] checkBox,int sumPrice, @PathVariable("placeNo")int placeNo, Model model) {
		reservation.setUseStart(checkBox[0]);
		reservation.setUseEnd(checkBox[checkBox.length-1]+1);
		Place place = service.placeView(placeNo);
		model.addAttribute("place", place);
		model.addAttribute("reservation", reservation);
		model.addAttribute("sumPrice", sumPrice);
		return "place/reservation";
	}
	
	/**
	 * 결제 완료
	 * @param placeNo
	 * @param model
	 * @param reservation
	 * @param payment
	 * @param request
	 * @return
	 */
	@PostMapping("{placeNo}/payComplete")
	public String payComplete(@PathVariable("placeNo")int placeNo, Model model, Reservation reservation, Payment payment, HttpServletRequest request) {
		Member loginMember = (Member)model.getAttribute("loginMember");
		reservation.setMemberNo(loginMember.getMemberNo());
		payment.setMemberNo(loginMember.getMemberNo());
		String path = null;
		int result = service.insertReservation(reservation, payment);
		if(result>0) {
			path = "place/payComplete";
			Place place = service.placeView(placeNo);
			model.addAttribute("place",place);
			model.addAttribute("reservation",reservation);
		}else {
			//실패동작 만들어야함
			path = "redirect:" + request.getHeader("referer");
		}
		
		return path;
	}

	// 내가 예약한 장소 목록 조회
	@RequestMapping("myReservation")
	public String myReservationList(@RequestParam(value="cp", required=false, defaultValue="1") int cp,
							Model model, Pagination pg, 
							@ModelAttribute("loginMember")Member loginMember,
							@RequestParam(value="sort", required = false, defaultValue="uploadDate") String sort) {
		// 1) pg에 cp를 세팅
		pg.setCurrentPage(cp);
		
		// 2) 전체 목록 수를 조회하여 Pagination 관련 내용을 계산하고 값을 저장한 객체 반환 받기
		Pagination pagination = service.getPagination(pg, loginMember.getMemberNo());
		
		// 3) 생성된 pagination을 이용하여 현재 목록 페이지에 보여질 게시글 목록 조회
		List<MyReservation> reservationList = service.selectReservationList(pagination,loginMember.getMemberNo(), sort);
		
		
		
		
		// 조회 결과 임시 확인
		System.out.println("sort : " + sort);
		 for(MyReservation r : reservationList) { System.out.println(r); }
		 
		
		model.addAttribute("reservationList", reservationList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("myReservation", "active");
		
		
		return "place/myReservation";
	}

	/**예약한 장소 상세조회
	 * @param placeNo
	 * @param reserveNo
	 * @param model
	 * @return
	 */
	@GetMapping("{placeNo}/reservationView")
	public String reserveNo(@PathVariable("placeNo")int placeNo, int reserveNo, Model model,Pagination pg,@RequestParam(value = "cp", required = false, defaultValue = "1")int cp) {
		Member loginMember = (Member)model.getAttribute("loginMember");
		int like = 0;
		if(loginMember !=null) {
			like = service.likeCheck(loginMember.getMemberNo());
		}
		Place place = service.placeView(placeNo);
		MyReservation reservation = service.getReservation(reserveNo);
		Map<String, Object> map = new HashMap<String, Object>();
		int count = 0;
		for(int i =  reservation.getUseStart(); i<reservation.getUseEnd(); i++) {
			count++;
		}
		int sumPrice = reservation.getPlaceCharge()*count;
		map.put("count", count);
		map.put("sumPrice", sumPrice);
		model.addAttribute("map",map);
		model.addAttribute("reservation", reservation);
		model.addAttribute("like",like);
		place.setPlaceAddr(place.getPlaceAddr().substring(5));
		model.addAttribute("place",place);
		pg.setCurrentPage(cp);
		
		Pagination pagination = null;
		List<Review> reviewListPlace = null;
		
		
		Review add = null;
		pagination = service.getPagination2(pg, placeNo);
		pagination.setLimit(5);
		reviewListPlace = service.selectReviewListPlace(pagination, placeNo);
		add = service.addReview(placeNo);
		model.addAttribute("reviewListPlace", reviewListPlace);
		model.addAttribute("pagination", pagination);
		model.addAttribute("add", add);
		return "place/reservationView";
	}
	
	/**
	 * 예약 취소
	 * @param placeNo
	 * @param reserveNo
	 * @param ra
	 * @return
	 */
	@PostMapping("{placeNo}/cancelRv")
	public String cancelRv(@PathVariable("placeNo") int placeNo, @RequestParam("reserveNo")int reserveNo, RedirectAttributes ra) {
		
		int result = service.cancelRv(reserveNo);
		if (result > 0) {
			MemberController.swalSetMessage(ra, "success", "예약 취소되었습니다", null);
		} else {
			MemberController.swalSetMessage(ra, "error", "예약취소 실패하였습니다", "고객센터로 문의해주세요.");

		}
		return "redirect:reservationView?reserveNo="+reserveNo;
		
	}
	

	/** 장소에 대한 후기 목록 조회 Controller
	 * @param cp
	 * @param model
	 * @param pg
	 * @param loginMember
	 * @return "review/reviewListPlace"
	 */
	@RequestMapping(value = "{placeNo}/reviewListPlace", method = RequestMethod.GET)
	public String reviewListPlace( @RequestParam(value="cp", required=false, defaultValue="1") int cp,
									Model model, Pagination pg, 
									@ModelAttribute("loginMember") Member loginMember,
									@PathVariable("placeNo")int placeNo
									) {
		
		pg.setCurrentPage(cp);
		
		Pagination pagination = null;
		List<Review> reviewListPlace = null;
		Review add = null;
		
		pagination = service.getPagination(pg, placeNo);
		reviewListPlace = service.selectReviewListPlace(pagination, placeNo);
		add = service.addReview(placeNo);
		
		System.out.println(pagination);
		System.out.println(reviewListPlace);
		
		model.addAttribute("reviewListPlace", reviewListPlace);
		model.addAttribute("pagination", pagination);
		model.addAttribute("add", add);
		
		return "review/reviewListPlace";
		
	}	
	
	/** 후기 신고 Controller
	 * @param reviewNo
	 * @param loginMember
	 * @param report
	 * @param ra
	 * @return "redirect:" + request.getHeader("referer")"
	 */
	@RequestMapping(value="{placeNo}/report", method=RequestMethod.POST)
	public String report( HttpServletRequest request, 
						  @ModelAttribute("loginMember") Member loginMember,
						  RedirectAttributes ra, Report report
						) {
		report.setMemberNo( loginMember.getMemberNo() );
		
		int result = service.insertReport( report );
		
		if(result>0) {
			MemberController.swalSetMessage(ra, "success", "신고 완료", null);
		}else {
			MemberController.swalSetMessage(ra, "error", "신고 오류", null);
		}
		
		return  "redirect:" + request.getHeader("referer") ;
	}

	
	// 내가 찜한 장소 목록 조회
			@RequestMapping("myLikePlace")
			public String myLikePlace(@RequestParam(value="cp", required=false, defaultValue="1")int cp,
					Model model, Pagination pg, 
					@ModelAttribute("loginMember")Member loginMember) {
				
				// 1) pg에 cp를 세팅
				pg.setCurrentPage(cp);
				
				// 2) 전체 목록 수를 조회하여 Pagination 관련 내용을 계산하고 값을 저장한 객체 반환 받기
				Pagination pagination = service.getLikePagination(pg, loginMember.getMemberNo());
				
				// 3) 생성된 pagination을 이용하여 현재 목록 페이지에 보여질 게시글 목록 조회
				List<MyReservation> myLikePlaceList = service.selectMyLikePlaceList(pagination,loginMember.getMemberNo());
				
				model.addAttribute("myLikePlaceList", myLikePlaceList);
				model.addAttribute("pagination", pagination);
				model.addAttribute("likePl", "active");
				
				return "place/myLikePlace";
			}
	
}

