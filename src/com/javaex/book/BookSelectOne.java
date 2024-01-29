package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectOne {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			
			String query = "";
			query += " select  b.book_id ";
			query += "	      ,b.title ";
			query += "        ,b.pubs ";
			query += "        ,b.pub_date ";
			query += "        ,b.author_id ";
			query += "        ,a.author_name ";
			query += "        ,a.author_desc ";
			query += " from book b ";
			query += "	 left join author a ";
			query += "          on b.author_id = a.author_id ";
			query += " having book_id = 3 ";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int bookId = rs.getInt("b.book_id");
				String title = rs.getString("b.title");
				String pubs = rs.getString("b.pubs");
				String pubDate = rs.getString("b.pub_date");
				int authorId = rs.getInt("b.author_id");
				String name = rs.getString("a.author_name");
				String desc = rs.getString("a.author_desc");

				System.out.println(bookId +". " + title + " " + pubs + " " + pubDate + " " + authorId + " " + name + " " + desc );
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
