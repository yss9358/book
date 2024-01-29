package com.javaex.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorInsert {

	public static void main(String[] args) {
		
		// 번호 알아서 / 이름 / 학원강사
		
		// 0 .importjava.sql.*;
		
		Connection conn = null;
		PreparedStatement pstmt = null; // insert / update / delete 문 일 때 사용
		ResultSet rs = null;	// select 문 일 때 사용	
		
		try {
			// 1. JDBC 드라이버 (Oracle)로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db"; // ip주소:port번호/데이터베이스명
			
			conn = DriverManager.getConnection(url, "book", "book"); // (url, id, pw)
			
			// 3. SQL문 준비 / 바인딩 / 실행
			
			// SQL문 준비
			// 앞뒤로 공백처리  /  query "" 안에 ; 는 무조건 제거해야함
			// MySQL workbench 에서 그대로 복사해서 사용
			// 값이 입력될 자리(데이터 부분)에는 ''을 제거하고 ? 를 사용
			String query = "";
			query += " insert into author ";
			query += " values(null, ?, ?) ";
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "황일영");
			pstmt.setString(2, "학원강사");
			
			//실행
			int count = pstmt.executeUpdate();
			
			// 4. 결과처리
			System.out.println(count + "건 등록되었습니다.");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
			
		} finally {
			
			// 5. 자원정리
			
			try {
				if(rs != null) {
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
