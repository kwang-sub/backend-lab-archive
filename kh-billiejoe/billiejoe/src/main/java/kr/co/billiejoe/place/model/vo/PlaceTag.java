package kr.co.billiejoe.place.model.vo;

public class PlaceTag {
	private int placeNo;
	private int tagNo;
	
	public PlaceTag() {}

	public PlaceTag(int placeNo, int tagNo) {
		this.placeNo = placeNo;
		this.tagNo = tagNo;
	}

	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public int getTagNo() {
		return tagNo;
	}

	public void setTagNo(int tagNo) {
		this.tagNo = tagNo;
	}

	@Override
	public String toString() {
		return "PlaceTag [placeNo=" + placeNo + ", tagNo=" + tagNo + "]";
	}
	
	
	
}
