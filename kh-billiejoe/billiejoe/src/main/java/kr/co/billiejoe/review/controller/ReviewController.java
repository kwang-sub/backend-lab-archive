package kr.co.billiejoe.review.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.billiejoe.member.controller.MemberController;
import kr.co.billiejoe.member.model.vo.Member;
import kr.co.billiejoe.review.model.service.ReviewService;
import kr.co.billiejoe.common.model.vo.Pagination;
import kr.co.billiejoe.review.model.vo.Review;


@Controller
@RequestMapping("/review/*")
@SessionAttributes({"loginMember"})
public class ReviewController {
	
	@Autowired
	private ReviewService service;
	
	/** 내가 작성한 후기 목록 조회 Controller
	 * @param cp
	 * @param model
	 * @param pg
	 * @param loginMember
	 * @return
	 */
	@RequestMapping(value = "reviewList", method = RequestMethod.GET)
	public String reviewList( @RequestParam(value="cp", required=false, defaultValue="1") int cp,
						  	  Model model, Pagination pg, 
						  	  @ModelAttribute("loginMember") Member loginMember
							) {
	
		pg.setCurrentPage(cp);
		
		Pagination pagination = null;
		List<Review> reviewList = null;
		
		pagination = service.getPagination(pg, loginMember);
		pagination.setLimit(5);
		reviewList = service.selectReviewList(pagination,loginMember);
		/*
		 조회 결과 임시 확인 for(Review r : reviewList) { System.out.println(r); }
		 */
		 
		
		 model.addAttribute("reviewList", reviewList);
		 model.addAttribute("pagination", pagination);
		 model.addAttribute("inserted", "active");
		
			/* place = service.selectPlaceName(reviewList.) */
		 
	return "review/reviewList";
	
	}
	
	/** 내가 작성한 후기 상세조회 Controller
	 * @param model
	 * @param ra
	 * @return
	 */
	@RequestMapping(value="{reviewNo}", method = RequestMethod.GET)
	public String reviewView( @PathVariable("reviewNo") int reviewNo,
							  @RequestParam(value="cp", required=false, defaultValue = "1") int cp,
							  @ModelAttribute("loginMember") Member loginMember,
							  Model model, 
							  RedirectAttributes ra) {
		
		// 후기 상세 조회 Service 호출
		Review reviewView = service.selectReview(reviewNo);
		System.out.println("리뷰결과 : " + reviewView);
		if(reviewView != null) {
			model.addAttribute("reviewView", reviewView);
			return "review/reviewView";
			
		}else {
			MemberController.swalSetMessage(ra, "error", "후기 상세 조회 실패", "해당 후기가 존재하지 않습니다.");
			return "redirect:reviewList";
		}
	}
	
	/** 후기 작성 화면 전환 Controller
	 * @param model
	 * @return
	 */
	@RequestMapping(value="insertReview", method=RequestMethod.GET)
	public String insertForm( Model model, int placeNo ) {
		System.out.println(placeNo);
		model.addAttribute("placeNo",placeNo);
		return "review/reviewInsert";
	}
	
	/** 후기 작성 DB 삽입 Controller
	 * @return path
	 */
	@RequestMapping(value="insertReview", method=RequestMethod.POST)
	public String insertReview( @ModelAttribute Review review,
								@ModelAttribute("loginMember") Member loginMember,
								HttpServletRequest request,
								RedirectAttributes ra,
								int placeNo
			) {
		// 요청으로부터 일단 review객체에 작성내용을 담아서 전달받고, 별점은 review객체와 자료형이 일치하지 않으므로
		// 컨트롤러에 일반 파라미터로 전달받아 형변환 시켜준 뒤 review객체 값을 셋팅한다.
		int reviewRate = Integer.parseInt(request.getParameter("rate"));
		review.setReviewRate( reviewRate );
		
		review.setMemberNo( loginMember.getMemberNo() );
		
		System.out.println(review);
		int reviewNo = service.insertReview(review);
		String path = null;
		if(reviewNo>0) {
		
			path = "redirect:/";
			MemberController.swalSetMessage(ra, "success", "후기 작성 성공", null);

		}else {
			
			path = "redirect:" + request.getHeader("referer");
			MemberController.swalSetMessage(ra, "error", "후기 작성 실패", null);
		
		}
		
		return path;
	}
	
	/** 후기 수정 화면 전환 Controller
	 * @param reviewNo
	 * @param model
	 * @return "review/reviewUpdate"
	 */
	@RequestMapping(value="updateForm", method=RequestMethod.POST)
	public String updateForm( int reviewNo, Model model, HttpServletRequest request,@ModelAttribute("loginMember") Member loginMember ) {
		System.out.println("후기 번호 : " + reviewNo);
		// 수정용 상세조회
		Review review = service.selectUpdateReview(reviewNo);
		
		model.addAttribute("review", review);
		
		return "review/reviewUpdate";
		
	}
	
	/** 후기글 수정 Controller
	 * @param review
	 * @param request
	 * @param model
	 * @param ra
	 * @return path
	 */
	@RequestMapping(value="updateReview", method=RequestMethod.POST)
	public String updateReview( @ModelAttribute Review review,
								@ModelAttribute("loginMember") Member loginMember,
								HttpServletRequest request, 
								Model model,
								RedirectAttributes ra
								) {
		
		int reviewRate = Integer.parseInt(request.getParameter("rate"));
		review.setReviewRate( reviewRate );
		
		int result = service.updateReview(review);
		
		String path = null;
		
		if(result > 0) { 
			path = "redirect:" + review.getReviewNo(); 
			MemberController.swalSetMessage(ra, "success", "후기 수정 성공", null);
		
		}else { 
			path = "redirect:" + request.getHeader("referer"); 
			MemberController.swalSetMessage(ra, "error", "후기 수정 실패", null);
		}
		
		return path;
	}
	
	/** 후기 삭제 Controller
	 * @param reviewNo
	 * @param ra
	 * @return "redirect:reviewList"
	 */
	@RequestMapping(value="deleteReview", method=RequestMethod.POST)
	public String deleteReview(@RequestParam int reviewNo, RedirectAttributes ra) {
		
		int result =service.deleteReview(reviewNo);
		
		if(result>0) {
			MemberController.swalSetMessage(ra, "success", "후기 삭제 성공", null);
		}else {
			MemberController.swalSetMessage(ra, "error", "후기 삭제 실패", null);
		}
		
		return"redirect:reviewList";
	}
	

}// class end
