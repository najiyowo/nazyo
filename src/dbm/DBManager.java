package dbm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManager {

	
	private DBManager () {};
	private static DBManager instanceDbManager= new DBManager();
	
	public static DBManager getInstance() {
		return instanceDbManager;
	}
	
	public Connection getConnect() {
		Connection conn=null;
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "track6";
		String password = "1234";
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close(Connection conn, PreparedStatement pstmt,ResultSet rs) {
		try {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
