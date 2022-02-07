package dto;

public class PagingrensyuVO {
	private int page; //현재 페이지
	private String word; // 찾을 단어
	private String searchtype; // 찾는 타입
	
	private int lastpage; // 10번째 페이지
	private int real_lastpage; // 진짜 마지막 페이지
	private int startpage; // 1번째 페이지
	private int real_startpage; //진짜 첫번째 페이지
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getSearchtype() {
		return searchtype;
	}
	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}
	public int getLastpage() {
		return lastpage;
	}
	public void setLastpage(int lastpage) {
		this.lastpage = lastpage;
	}
	public int getReal_lastpage() {
		return real_lastpage;
	}
	public void setReal_lastpage(int real_lastpage) {
		this.real_lastpage = real_lastpage;
	}
	public int getStartpage() {
		return startpage;
	}
	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}
	public int getReal_startpage() {
		return real_startpage;
	}
	public void setReal_startpage(int real_startpage) {
		this.real_startpage = real_startpage;
	}
	

	
	
	
}
