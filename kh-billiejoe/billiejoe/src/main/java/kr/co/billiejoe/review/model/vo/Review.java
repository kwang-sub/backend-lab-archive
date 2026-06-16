package kr.co.billiejoe.review.model.vo;

import java.sql.Timestamp;
import java.util.List;

import kr.co.billiejoe.place.model.vo.Attachment;


public class Review {
	private int reviewNo;
	private String reviewContent;
	private int reviewRate;
	private Timestamp reviewDate;
	private int placeNo;
	private int memberNo;
	private String reviewStatus;
	
	// 리뷰 추가 부분(5항목)	
	private String placeName;
	private String memberName;

	private String memberPic;
	private List<Attachment> atList;
	
	private int reviewAvg;
	private int reviewCount;
	
	public Review() {}

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

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public List<Attachment> getAtList() {
		return atList;
	}

	public void setAtList(List<Attachment> atList) {
		this.atList = atList;
	}
	
	public int getReviewAvg() {
		return reviewAvg;
	}

	public void setReviewAvg(int reviewAvg) {
		this.reviewAvg = reviewAvg;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public String getMemberPic() {
		return memberPic;
	}

	public void setMemberPic(String memberPic) {
		this.memberPic = memberPic;
	}
	
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", reviewContent=" + reviewContent + ", reviewRate=" + reviewRate
				+ ", reviewDate=" + reviewDate + ", placeNo=" + placeNo + ", memberNo=" + memberNo + ", reviewStatus="
				+ reviewStatus + ", placeName=" + placeName + ", memberName=" + memberName + ", memberPic=" + memberPic
				+ ", atList=" + atList + ", reviewAvg=" + reviewAvg + ", reviewCount=" + reviewCount + "]";
	}





	
	

	
	
}
