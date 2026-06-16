package kr.co.billiejoe.place.model.vo;

import java.sql.Timestamp;

public class Review {
	private int reviewNo;
	private String reviewContent;
	private int reviewRate;
	private Timestamp reviewDate;
	private int placeNo;
	private int memberNo;
	private String reviewStatus;
	
	public Review() {}
	
	public Review(int reviewNo, String reviewContent, int reviewRate, Timestamp reviewDate, int placeNo, int memberNo,
			String reviewStatus) {
		this.reviewNo = reviewNo;
		this.reviewContent = reviewContent;
		this.reviewRate = reviewRate;
		this.reviewDate = reviewDate;
		this.placeNo = placeNo;
		this.memberNo = memberNo;
		this.reviewStatus = reviewStatus;
	}

	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public int getReviewRate() {
		return reviewRate;
	}
	public void setReviewRate(int reviewRate) {
		this.reviewRate = reviewRate;
	}
	public Timestamp getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Timestamp reviewDate) {
		this.reviewDate = reviewDate;
	}
	public int getPlaceNo() {
		return placeNo;
	}
	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", reviewContent=" + reviewContent + ", reviewRate=" + reviewRate
				+ ", reviewDate=" + reviewDate + ", placeNo=" + placeNo + ", memberNo=" + memberNo + "]";
	}
	
	
}
