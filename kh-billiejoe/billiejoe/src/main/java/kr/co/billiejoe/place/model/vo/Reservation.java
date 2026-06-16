package kr.co.billiejoe.place.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Reservation {
	private int reserveNo;
	private String useDate; // 이용 예정일
	private int useStart; // 이용 시작시간
	private int useEnd; // 이용 종료시간
	private Timestamp reserveDate; // 예약일시
	private int placeNo;
	private int memberNo;
	private int stateNo;
	private String stateName;
	
	public Reservation() {}
	
	

	public Reservation(int reserveNo, String useDate, int useStart, int useEnd, Timestamp reserveDate, int placeNo, int memberNo, int stateNo, String stateName) {
		super();
		this.reserveNo = reserveNo;
		this.useDate = useDate;
		this.useStart = useStart;
		this.useEnd = useEnd;
		this.reserveDate = reserveDate;
		this.placeNo = placeNo;
		this.memberNo = memberNo;
		this.stateNo = stateNo;
		this.stateName = stateName;
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



	@Override
	public String toString() {
		return "Reservation [reserveNo=" + reserveNo + ", useDate=" + useDate + ", useStart=" + useStart + ", useEnd="
				+ useEnd + ", reserveDate=" + reserveDate + ", placeNo=" + placeNo + ", memberNo=" + memberNo
				+ ", stateNo=" + stateNo + ", stateName=" + stateName + "]";
	}



	
	
	
}
