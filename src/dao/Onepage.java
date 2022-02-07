package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbm.DBManager;
import dto.KeisibanVO;

public class Onepage {

	private Onepage () {}
	private static Onepage onepage = new Onepage();
	
	public static Onepage onepageinstance () {
		return onepage;
	}
	
	DBManager dbm = DBManager.getInstance();
	
	public void insert (KeisibanVO ovo, String doko){
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="";
		
		if(doko=="osirase") {
			sql ="insert into my"+doko+" (bno,title,content,WRITER)"
					+ "	values ("+doko+"_seq.nextval,?,?,?)";
		}else {
			sql="insert into my"+doko+" (bno,title,CONTENT,IMGURL,WRITER) "
					+ "values ("+doko+"_seq.nextval,?,?,?,?)";
		}
		try {
			conn=dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			int n = 1;
			
			pstmt.setString(n, ovo.getTitle());n++;
			pstmt.setString(n, ovo.getContent());n++;
			if(doko!="osirase") {
				pstmt.setString(n, ovo.getImgurl());n++;
			}
			pstmt.setString(n, ovo.getWriter());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public KeisibanVO selectone(int bno, String doko) {
		Connection conn= null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql="select * from my"+doko+" where bno = ?";
		
		KeisibanVO ovo = new KeisibanVO();
		
		try {
			conn= dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ovo.setBno(rs.getInt("bno"));
				ovo.setContent(rs.getString("content"));
				ovo.setTitle(rs.getString("title"));
				ovo.setView_count(rs.getInt("view_count"));
				ovo.setWrdate(rs.getString("wrdate"));
				ovo.setWriter(rs.getString("writer"));
				if(doko!="osirase") {
					ovo.setImgurl(rs.getString("imgurl"));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return ovo;
	}
	
	public KeisibanVO prev(int bno, String doko){
		Connection conn= null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql="select bno,title from my"+doko+" where bno = (select max(bno) from my"+doko+" where bno<?)";
		KeisibanVO ovo = new KeisibanVO();
		
		try {
			conn= dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ovo.setBno(rs.getInt("bno"));
				ovo.setTitle(rs.getString("title"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return ovo;
	}
	
	public KeisibanVO next(int bno, String doko){
		Connection conn= null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql="select bno,title from my"+doko+" where bno = (select min(bno) from my"+doko+" where bno>?)";
		
		KeisibanVO ovo = new KeisibanVO();
		
		try {
			conn= dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ovo.setBno(rs.getInt("bno"));
				ovo.setTitle(rs.getString("title"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return ovo;
	}
	
	public void update(KeisibanVO ovo, String doko) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql= "update my"+doko+" set title=?, content=?, wrdate=sysdate where bno=?";
		
		try {
			conn=dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,ovo.getTitle());
			pstmt.setString(2, ovo.getContent());
			pstmt.setInt(3, ovo.getBno());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public void delete(int bno, String doko) {
		Connection conn = null;
		PreparedStatement pstmt= null;
		
		String sql="delete from my"+doko+" where bno=?";
		
		try {
			conn=dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public void count(int bno, String doko) {
		Connection conn = null;
		PreparedStatement pstmt= null;
		
		String sql="update my"+doko+" set view_count=view_count+1 where bno=?";
		
		try {
			conn=dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public List<KeisibanVO> selectformain(String doko) {
		Connection conn= null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql="select * from (select * from my"+doko+" order by bno desc)where rownum<=3";
		
		List<KeisibanVO> list = new ArrayList<KeisibanVO>();
		
		try {
			conn= dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				KeisibanVO ovo = new KeisibanVO();
				
				ovo.setBno(rs.getInt("bno"));
				ovo.setContent(rs.getString("content"));
				ovo.setTitle(rs.getString("title"));
				ovo.setView_count(rs.getInt("view_count"));
				ovo.setWrdate(rs.getString("wrdate"));
				ovo.setWriter(rs.getString("writer"));
				list.add(ovo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
	
}
