package kr.co.billiejoe.place.model.vo;

import java.sql.Timestamp;
import java.util.List;

public class Place {
	private int placeNo;
	private String placeName; // 장소명
	private String placeSummary; // 장소 한 줄 설명
	private String placeAddr; // 장소 주소
	private String placeContent; // 장소 소개 본문
	private int placeCharge; // 대여 가격 (1시간)
	private String placeIsAvailable; // 장소 예약 가능 여부
	private String placeStatus; // 삭제 상태
	private Timestamp placeDate; // 작성일
	private int memberNo;
	private String memberName;
	private int likeCount; // 좋아요수
	private List<Attachment> atList;
	private List<Tag> tagList;
	
	private String thumbnail; // 썸네일 파일명
	private int reviewCount; // 작성된 리뷰 개수

	public Place() {
	}

	public Place(int placeNo, String placeName, String placeSummary, String placeAddr, String placeContent,
			int placeCharge, String placeIsAvailable, String placeStatus, Timestamp placeDate, int memberNo,
			int likeCount) {
		super();
		this.placeNo = placeNo;
		this.placeName = placeName;
		this.placeSummary = placeSummary;
		this.placeAddr = placeAddr;
		this.placeContent = placeContent;
		this.placeCharge = placeCharge;
		this.placeIsAvailable = placeIsAvailable;
		this.placeStatus = placeStatus;
		this.placeDate = placeDate;
		this.memberNo = memberNo;
		this.likeCount = likeCount;
	}
	
	

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public List<Attachment> getAtList() {
		return atList;
	}

	public void setAtList(List<Attachment> atList) {
		this.atList = atList;
	}

	public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	public List<Attachment> getatList() {
		return atList;
	}

	

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
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

	public String getPlaceSummary() {
		return placeSummary;
	}

	public void setPlaceSummary(String placeSummary) {
		this.placeSummary = placeSummary;
	}

	public String getPlaceAddr() {
		return placeAddr;
	}

	public void setPlaceAddr(String placeAddr) {
		this.placeAddr = placeAddr;
	}

	public String getPlaceContent() {
		return placeContent;
	}

	public void setPlaceContent(String placeContent) {
		this.placeContent = placeContent;
	}

	public int getPlaceCharge() {
		return placeCharge;
	}

	public void setPlaceCharge(int placeCharge) {
		this.placeCharge = placeCharge;
	}

	public String getPlaceIsAvailable() {
		return placeIsAvailable;
	}

	public void setPlaceIsAvailable(String placeIsAvailable) {
		this.placeIsAvailable = placeIsAvailable;
	}

	public String getPlaceStatus() {
		return placeStatus;
	}

	public void setPlaceStatus(String placeStatus) {
		this.placeStatus = placeStatus;
	}

	public Timestamp getPlaceDate() {
		return placeDate;
	}

	public void setPlaceDate(Timestamp placeDate) {
		this.placeDate = placeDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	@Override
	public String toString() {
		return "Place [placeNo=" + placeNo + ", placeName=" + placeName + ", placeSummary=" + placeSummary
				+ ", placeAddr=" + placeAddr + ", placeContent=" + placeContent + ", placeCharge=" + placeCharge
				+ ", placeIsAvailable=" + placeIsAvailable + ", placeStatus=" + placeStatus + ", placeDate=" + placeDate
				+ ", memberNo=" + memberNo + ", memberName=" + memberName + ", likeCount=" + likeCount + ", atList="
				+ atList + ", tagList=" + tagList + ", thumbnail=" + thumbnail + ", reviewCount=" + reviewCount + "]";
	}



	

}
