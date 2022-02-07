package dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbm.DBManager;
import dto.MymemberVO;

public class MymemberDAO {
	
	DBManager dbm = DBManager.getInstance();
	
	private MymemberDAO(){};
	private static MymemberDAO mdao = new MymemberDAO();
	
	public static MymemberDAO getinstancememberdao() {
		return mdao;
	}
	
	public int insertintomember(MymemberVO mvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into mymember (id,pw,phone,email) values"
				+ "(?,?,?,?)";
		int result = 0;
		try {
			conn=dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mvo.getId());
			pstmt.setString(2, mvo.getPw());
			pstmt.setString(3, mvo.getPhone());
			pstmt.setString(4, mvo.getEmail());
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
		return result;
	}
	
	public int serchid(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0; 
		
		String sql = "select*from mymember where id = ?";
		
		try {
			conn = dbm.getConnect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1; //use
			}else {
				result = -1; //unuse
			}

			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbm.close(conn, pstmt, rs);
			}
		return result;
		
	}
	
	public String pwkae(String pw) {
		String saisyupw = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(pw.getBytes());
			byte[] encodeData = md.digest();
			for(int i=0; i<encodeData.length; i++) {
				saisyupw += Integer.toHexString(encodeData[i]&0xFF);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return saisyupw;
	}
	
	public int checkidandpw(String id, String pw) {
		int result = 0;
		String realpw = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql="select * from MYMEMBER where ID = ?";
		
		try {
			conn=dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				realpw = rs.getString("pw");
			}
			
			if(realpw!="") {
				if(pw.equals(realpw)) {
					result = 1; //비번이 같음
				}else {
					result = -1; //비번이 다름
				}
			}else {
				result=0;//아이디가 없음
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return result;
	}
	
	public MymemberVO selectid(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MymemberVO mvo = new MymemberVO();
		
		String sql = "select*from mymember where id = ?";
		
		try {
			conn = dbm.getConnect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				mvo.setEmail(rs.getString("email"));
				mvo.setPhone(rs.getString("phone"));
				mvo.setId(rs.getString("id"));
			}

			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbm.close(conn, pstmt, rs);
			}
		return mvo;
	}
	
	public int updatemember(MymemberVO mvo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//id,pw,phone,email
		String sql = "";
		if(mvo.getPw()!="") {
			sql="update mymember set email=?,phone=?,pw=? where id = ?";
		}else {
			sql="update mymember set email=?,phone=? where id = ?";
		}
		try {
			conn= dbm.getConnect();
			int n = 1;
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(n, mvo.getEmail());n++;
			pstmt.setString(n, mvo.getPhone());n++;
			if(mvo.getPw()!="") {
				pstmt.setString(n, mvo.getPw());n++;
			}
			pstmt.setString(n, mvo.getId());
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			
		}finally {
			dbm.close(conn, pstmt);
		}
		
		return result;
	}
	
	public List<MymemberVO> selectid() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MymemberVO> list = new ArrayList<MymemberVO>();
		
		String sql = "select*from mymember";
		
		try {
			conn = dbm.getConnect();
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				MymemberVO mvo = new MymemberVO();
				mvo.setAdmin(Integer.parseInt(rs.getString("admin")));
				mvo.setId(rs.getString("id"));
				mvo.setEmail(rs.getString("email"));
				mvo.setPhone(rs.getString("phone"));
				list.add(mvo);
			}

			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbm.close(conn, pstmt, rs);
			}
		return list;
	}
	
	public void updateadmin(MymemberVO mvo) {
		Connection conn = null;
		PreparedStatement pstmt=null;
		
		String sql="";
		
		if(mvo.getAdmin()==1) {
			sql="update mymember set admin =0 where id ='"+mvo.getId()+"'";
		}else {
			sql="update mymember set admin =1 where id ='"+mvo.getId()+"'";
		}
		
		try {
			conn=dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public int checkadmin(String id) {
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		int admin = 0;
		
		String sql="select admin from mymember where id = ?";
		
		try {
			conn = dbm.getConnect();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				admin=rs.getInt("admin");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return admin;
	}
}
