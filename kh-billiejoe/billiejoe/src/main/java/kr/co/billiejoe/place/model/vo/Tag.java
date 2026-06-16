package kr.co.billiejoe.place.model.vo;

public class Tag {
	private int tagNo;
	private String tagName;
	private int placeNo;

	public Tag() {}
	
	public Tag(int tagNo, String tagName) {
		this.tagNo = tagNo;
		this.tagName = tagName;
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
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public String toString() {
		return "Tag [tagNo=" + tagNo + ", tagName=" + tagName + ", placeNo=" + placeNo + "]";
	}

	
}
