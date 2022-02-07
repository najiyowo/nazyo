package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbm.DBManager;
import dto.CommentVO;

public class CommentDAO {
	private CommentDAO () {}
	private static CommentDAO Comment = new CommentDAO();
	
	public static CommentDAO Commentinstance () {
		return Comment;
	}
	
	DBManager dbm = DBManager.getInstance();
	
	public int insertcomment(CommentVO cvo){
		Connection conn= null;
		PreparedStatement pstmt=null;
		
		int result=0;
		String sql="insert into MYCOMMENTS ("
				+ "userid,"
				+ "comment_content,"
				+ "keisiban,"
				+ "bno)"
				+ "values (?,?,?,?)";
		
		try {
			conn= dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, cvo.getUserid());
			pstmt.setString(2, cvo.getComment_content());
			pstmt.setString(3, cvo.getKeisiban());
			pstmt.setInt(4, cvo.getBno());
			result=pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
		return result;
	}
	
	public List<CommentVO> selectlist(int bno,String doko){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="select * from MYCOMMENTS where bno ="+bno+" and keisiban='"+doko+"' order by wdate desc";
		List<CommentVO> list = new ArrayList<CommentVO>();
		
		
		try {
			conn=dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				CommentVO cvo = new CommentVO();
				cvo.setUserid(rs.getString("Userid"));
				cvo.setWdate(rs.getString("wdate").substring(0,10));
				cvo.setComment_content(rs.getString("comment_content"));
				cvo.setKeisiban(rs.getString("keisiban"));
				cvo.setBno(Integer.parseInt(rs.getString("bno")));
				list.add(cvo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
}
