package kr.co.billiejoe.reservation.model.vo;

public class BookedReservation {
	private int reserveNo;
	private String useDate;
	private int useStart;
	private int useEnd;
	private String reserveDate;
	private int stateNo;
	private String stateName;
	private int guestNo;
	private String guestName;
	private String guestPhone;
	private int placeNo;
	private String placeName;
	private int hostNo;
	private String hostName;
	
	public BookedReservation() {}

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

	public String getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(String reserveDate) {
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

	public int getGuestNo() {
		return guestNo;
	}

	public void setGuestNo(int guestNo) {
		this.guestNo = guestNo;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestPhone() {
		return guestPhone;
	}

	public void setGuestPhone(String guestPhone) {
		this.guestPhone = guestPhone;
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

	public int getHostNo() {
		return hostNo;
	}

	public void setHostNo(int hostNo) {
		this.hostNo = hostNo;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	@Override
	public String toString() {
		return "BookedReservation [reserveNo=" + reserveNo + ", useDate=" + useDate + ", useStart=" + useStart
				+ ", useEnd=" + useEnd + ", reserveDate=" + reserveDate + ", stateNo=" + stateNo + ", stateName="
				+ stateName + ", guestNo=" + guestNo + ", guestName=" + guestName + ", guestPhone=" + guestPhone
				+ ", placeNo=" + placeNo + ", placeName=" + placeName + ", hostNo=" + hostNo + ", hostName=" + hostName
				+ "]";
	}
}
