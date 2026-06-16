package kr.co.billiejoe.place.model.vo;

public class PlaceAvailable {
	private int availableNo;
	private int placeNo;
	private int start;
	private int end;
	
	public PlaceAvailable() {}

	public PlaceAvailable(int availableNo, int placeNo, int start, int end) {
		this.availableNo = availableNo;
		this.placeNo = placeNo;
		this.start = start;
		this.end = end;
	}

	public int getAvailableNo() {
		return availableNo;
	}

	public void setAvailableNo(int availableNo) {
		this.availableNo = availableNo;
	}

	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "PlaceAvailable [availableNo=" + availableNo + ", placeNo=" + placeNo + ", start=" + start + ", end="
				+ end + "]";
	}
	
	
}
