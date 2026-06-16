package kr.co.billiejoe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.billiejoe.place.model.service.PlaceService;
import kr.co.billiejoe.common.model.vo.Pagination;
import kr.co.billiejoe.place.model.vo.Place;
import kr.co.billiejoe.place.model.vo.Search;
import kr.co.billiejoe.review.model.service.ReviewService;
import kr.co.billiejoe.review.model.vo.RecentReview;

@Controller
public class HomeController {
	@Autowired
	private PlaceService placeService;
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/main")
	public String placeList(Model model, Pagination pg, Search search) {
		pg.setCurrentPage(1);
		pg.setLimit(6);
		
		Pagination pagination = placeService.getPagination(pg, search);
		List<Place> placeList = placeService.selectPlaceList(pagination, search);
		
		List<RecentReview> reviewList = reviewService.selectMainReviewList(6);

		model.addAttribute("placeList", placeList);
		model.addAttribute("placePagination", pagination);
		model.addAttribute("reviewList", reviewList);
		
		return "main";
	}
}