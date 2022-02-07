package util;

public class Criteria {

	private int pagenum;
	private int howmany;
	
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pagenum, int howmany) {
		this.pagenum=pagenum;
		this.howmany=howmany;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pageNum) {
		this.pagenum = pageNum;
	}

	public int getHowmany() {
		return howmany;
	}

	public void setHowmany(int amount) {
		this.howmany = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keword) {
		this.keyword = keword;
	}
}
