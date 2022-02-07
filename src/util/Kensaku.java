package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbm.DBManager;

public class Kensaku {
	DBManager dbm = DBManager.getInstance();

	private Kensaku() {
	}

	private static Kensaku kensakuutil = new Kensaku();

	public static Kensaku kensakuutilinstance() {
		return kensakuutil;
	}

	public int noticeAllCount(String query, String doko) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = 0;
		String sql = "";
		try {
			if (query != "") {
				sql = "select count(*) as cnt from my"+doko+" where " + query;
			} else {
				sql = "select count(*) as cnt from my"+doko;
			}
			conn = dbm.getConnect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return count;

	}
}
