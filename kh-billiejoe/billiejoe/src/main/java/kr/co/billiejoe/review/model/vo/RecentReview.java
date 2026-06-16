package kr.co.billiejoe.review.model.vo;

import java.util.List;

import kr.co.billiejoe.place.model.vo.Tag;

public class RecentReview {
	private int reviewNo;
	private int placeNo;
	private String placeName;
	private int placeCharge;
	private int reviewRate;
	private String reviewContent;
	private String fileName;
	private List<Tag> tagList;
	
	public RecentReview() {}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public int getPlaceCharge() {
		return placeCharge;
	}

	public void setPlaceCharge(int placeCharge) {
		this.placeCharge = placeCharge;
	}

	public int getReviewRate() {
		return reviewRate;
	}

	public void setReviewRate(int reviewRate) {
		this.reviewRate = reviewRate;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	
}
