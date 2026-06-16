package kr.co.billiejoe.place.model.vo;

import java.sql.Timestamp;
import java.util.List;

public class MyReservation
{
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
	private int likeCount; // 좋아요수
	private int reviewCount; // 좋아요수
	private List<Attachment> atList;
	private List<Tag> tagList;
	
	
	private int reserveNo;
	private String useDate; // 이용 예정일
	private int useStart; // 이용 시작시간
	private int useEnd; // 이용 종료시간
	private Timestamp reserveDate; // 예약일시
	private int stateNo;
	private String stateName;

	private String memberName;
	private String memberPhone;
	
	
	 
	public MyReservation() {
		// TODO Auto-generated constructor stub
	}




	public MyReservation(int placeNo, String placeName, String placeSummary, String placeAddr, String placeContent,
			int placeCharge, String placeIsAvailable, String placeStatus, Timestamp placeDate, int memberNo,
			int likeCount, List<Attachment> atList, List<Tag> tagList, int reserveNo, String useDate, int useStart,
			int useEnd, Timestamp reserveDate, int stateNo, String stateName) {
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
		this.atList = atList;
		this.tagList = tagList;
		this.reserveNo = reserveNo;
		this.useDate = useDate;
		this.useStart = useStart;
		this.useEnd = useEnd;
		this.reserveDate = reserveDate;
		this.stateNo = stateNo;
		this.stateName = stateName;
	}




	public String getMemberName() {
		return memberName;
	}




	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}




	public String getMemberPhone() {
		return memberPhone;
	}




	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
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




	public int getLikeCount() {
		return likeCount;
	}




	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
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




	public int getReserveNo() {
		return reserveNo;
	}




	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
	}




	public String getUseDate() {
		return useDate;
	}




	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}




	public int getUseStart() {
		return useStart;
	}




	public void setUseStart(int useStart) {
		this.useStart = useStart;
	}




	public int getUseEnd() {
		return useEnd;
	}




	public void setUseEnd(int useEnd) {
		this.useEnd = useEnd;
	}




	public Timestamp getReserveDate() {
		return reserveDate;
	}




	public void setReserveDate(Timestamp reserveDate) {
		this.reserveDate = reserveDate;
	}




	public int getStateNo() {
		return stateNo;
	}




	public void setStateNo(int stateNo) {
		this.stateNo = stateNo;
	}




	public String getStateName() {
		return stateName;
	}




	public void setStateName(String stateName) {
		this.stateName = stateName;
	}


	


	public int getReviewCount() {
		return reviewCount;
	}




	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}




	@Override
	public String toString() {
		return "MyReservation [placeNo=" + placeNo + ", placeName=" + placeName + ", placeSummary=" + placeSummary
				+ ", placeAddr=" + placeAddr + ", placeContent=" + placeContent + ", placeCharge=" + placeCharge
				+ ", placeIsAvailable=" + placeIsAvailable + ", placeStatus=" + placeStatus + ", placeDate=" + placeDate
				+ ", memberNo=" + memberNo + ", likeCount=" + likeCount + ", reviewCount=" + reviewCount + ", atList="
				+ atList + ", tagList=" + tagList + ", reserveNo=" + reserveNo + ", useDate=" + useDate + ", useStart="
				+ useStart + ", useEnd=" + useEnd + ", reserveDate=" + reserveDate + ", stateNo=" + stateNo
				+ ", stateName=" + stateName + ", memberName=" + memberName + ", memberPhone=" + memberPhone + "]";
	}




	




	
}