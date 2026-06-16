package kr.co.billiejoe.place.model.vo;

public class ReservationState {
	private String stateNo;
	private String stateName;
	
	public ReservationState() {}

	public ReservationState(String stateNo, String stateName) {
		this.stateNo = stateNo;
		this.stateName = stateName;
	}

	public String getStateNo() {
		return stateNo;
	}

	public void setStateNo(String stateNo) {
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
		return "ReservationState [stateNo=" + stateNo + ", stateName=" + stateName + "]";
	}
	
}
