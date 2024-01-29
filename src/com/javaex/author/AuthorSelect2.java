package com.javaex.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorSelect2 {

	public static void main(String[] args) {
		
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			
			String query = "";
			query += " select  author_id ";
			query += " 	      ,author_name ";
			query += "        ,author_desc ";
			query += " from author ";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// rs.next()는 bo
			while(rs.next()) {
			
			// 검색결과에서 데이터 꺼내기
		
			int no = rs.getInt("author_id");
			String name = rs.getString("author_name");
			String desc = rs.getString("author_desc");
			
			// Vo로 묶어서 저장
			AuthorVo authorVo = new AuthorVo(no,name,desc);
			
			// List에 추가
			authorList.add(authorVo);
			
			}
			
			// 결과처리
			// 리스트를 이용해서 출력
			
			for(int i=0; i<authorList.size(); i++) {
				int no = authorList.get(i).getAuthorId();
				String name = authorList.get(i).getAuthorName();
				String desc = authorList.get(i).getAuthorDesc();
				
				System.out.println(no + ".\t" + name + "\t" + desc);
			}
			
		}catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		}catch(SQLException e){
			System.out.println("error:" + e);
		}finally {
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
				
				}
			catch(SQLException e) {
					System.out.println("error:" + e);
			}
		}
		
		
		
		
		
		
	}

}
