package kr.co.billiejoe.place.model.vo;

public class Search {
	private String cond;
	private String q;
	private String sort;

	public Search() {}
	public Search(String cond, String q) {
		this.cond = cond;
		this.q = q;
	}
	public String getCond() {
		return cond;
	}
	public void setCond(String cond) {
		this.cond = cond;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public Boolean isNotEmpty() {
		return cond == null && q == null ? false : true; 
	}
	
	@Override
	public String toString() {
		return "Search [cond=" + cond + ", q=" + q + ", sort=" + sort + "]";
	}
}
