package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookInsert {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			
			String query = "";
			query += " insert into book ";
			query += " values (null, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "과제");
			pstmt.setString(2, "하기싫다");
			pstmt.setString(3, "2024-01-29");
			pstmt.setInt(4, 5);
			
			int count = pstmt.executeUpdate();
			
			System.out.println(count + "건 추가되었습니다.");
			
			
		} catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			 System.out.println("error:" + e);
		} finally {
			 try {
				 if (rs != null) {
					 rs.close();
				 }
				 if (pstmt != null) {
					 pstmt.close();
				 }
				 if (conn != null) {
					 conn.close();
				 }
			 } catch (SQLException e) {
				 System.out.println("error:" + e);
			 }
		}
	}

}
