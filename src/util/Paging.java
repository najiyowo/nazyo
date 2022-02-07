package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbm.DBManager;
import dto.KeisibanVO;

public class Paging {
	DBManager dbm = DBManager.getInstance();
	private Paging () {};
	private static Paging paging = new Paging();
	
	public static Paging pageutil() {
		return paging;
	}
	
	public List<KeisibanVO> paging(String doko, Criteria cri,String query) { //모든 게시판 리스트 출력
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		int pages = cri.getPagenum(); //최소 1페이지
		int howmany = cri.getHowmany(); // 10개씩 비춤 나중에 변경 가능하도록 바꾸게 될경우 바꾸면 댐

		String sql = "";


		if(query!="") {
			sql = "select * from(select /*+ index_desc(my"+doko+" my"+doko+"_pk)*/ "
					+ "rownum rn,my"+doko+".* from my"+doko+" where "
					+ "("+query+") and rownum<="+(pages*howmany)+") "
					+ "where rn>"+(pages-1)*howmany;
					
		}else {
			sql = "select * from(select /*+ index_desc(my"+doko+" my"+doko+"_pk)*/ "
					+ "rownum rn,my"+doko+".* "
					+ "from my"+doko+" where rownum<="+(pages*howmany)+") "
					+ "where rn>"+(pages-1)*howmany;

		}
		List<KeisibanVO> list = new ArrayList<KeisibanVO>();
		try {
			conn=dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				KeisibanVO kvo = new KeisibanVO();
				kvo.setTitle(rs.getString("title"));
				kvo.setContent(rs.getString("content"));
				if(doko!="osirase") {
					kvo.setImgurl(rs.getString("imgurl"));
				}
				kvo.setWrdate(rs.getString("wrdate"));
				kvo.setWriter(rs.getString("writer"));
				kvo.setView_count(Integer.parseInt(rs.getString("VIEW_COUNT")));
				kvo.setBno(Integer.parseInt(rs.getString("bno")));
				list.add(kvo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
}


