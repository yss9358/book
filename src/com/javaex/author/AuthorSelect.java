package com.javaex.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelect {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// JDBC 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url,"book","book");
			
			// SQL문 준비 - select 문
			String query = "";
			query += " select  author_id " ;
			query += "        ,author_name ";
			query += "        ,author_desc ";
			query += " from author ";
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			
			// select 문 실행
			rs = pstmt.executeQuery();
			
			// 결과처리
			
			while(rs.next()) {
				
				/*
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String desc = rs.getString(3);
				*/
				
				int id = rs.getInt("author_id");
				String name = rs.getString("author_name");
				String desc = rs.getString("author_desc");

				System.out.println(id + ", " + name + ", " + desc);
				
			}
			
			
			/*
			rs.next();
			int id = rs.getInt("author_id");
			System.out.println(id);
			
			String name = rs.getString("author_name");
			System.out.println(name);
			
			String desc = rs.getString("author_desc");
			System.out.println(desc);
			*/
			
			
			
		} catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
			System.out.println("error:" + e);
		}
		
		
		
	}

}
