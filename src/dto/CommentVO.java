package dto;

public class CommentVO {
	
	private String userid;
	private String wdate;
	private String comment_content;
	private String keisiban;
	private int bno;
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getKeisiban() {
		return keisiban;
	}
	public void setKeisiban(String keisiban) {
		this.keisiban = keisiban;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}

}
