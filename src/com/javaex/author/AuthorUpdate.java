package com.javaex.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorUpdate {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			
			String query = "";
			query += " update  author ";
			query += " set author_name = ? ";
			query += "    ,author_desc = ? ";
			query += " where author_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "김영하");
			pstmt.setString(2, "알쓸신잡");
			pstmt.setInt(3, 6);
			
			
			int count = pstmt.executeUpdate();
			
			System.out.println(count + "건 수정되었습니다.");
			
			
		} catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		
		
	}

}
