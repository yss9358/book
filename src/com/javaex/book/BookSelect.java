package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelect {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			
			String query = "";
			query += " select  book_id ";
			query += "        ,title ";
			query += "        ,pubs ";
			query += "        ,pub_date ";
			query += "        ,author_id ";
			query += " from book ";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
			
				System.out.println(bookId +". " + title + "\t" + pubs + "\t" +pubDate + "\t" + authorId);
			}
						
			
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
