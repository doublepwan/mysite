package com.javaex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestBookVO;


public class GuestBookDAO {
	public List<GuestBookVO> getList() {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<GuestBookVO> guestList = new ArrayList<GuestBookVO>();

		try {
			// JDBC 드라이버(Oracle)로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// sql문 준비 / 바인딩 / 실행
			String query = "select no, name, password, reg_date, content from guestbook";

			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();

			// 결과처리
			while (rs.next()) {

				GuestBookVO dto = new GuestBookVO();

				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String date = rs.getString("reg_date");

				dto.setNo(no);

				dto.setName(name);

				dto.setPassword(password);

				dto.setContent(content);
				
				dto.setDate(date);

				guestList.add(dto);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}
		return guestList;
	}

	public void insert(GuestBookVO dto) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			// JDBC 드라이버(Oracle)로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// sql문 준비 / 바인딩 / 실행
			String query = "";
			query = "insert into guestbook values(seq_guest_no.nextval, ?, ?, ?, default)";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getPassword());
			psmt.setString(3, dto.getContent());

			int count = psmt.executeUpdate();

			// 결과처리
			System.out.println(count + "건 저장완료.");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}
	}

	public void delete(String no, String password) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			// JDBC 드라이버(Oracle)로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// sql문 준비 / 바인딩 / 실행
			String query = "delete from guestbook where no = ? and password = ?";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, no);
			psmt.setString(2, password);

			int count = psmt.executeUpdate();

			// 결과처리
			System.out.println(count + "건 삭제완료.");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}

	}
}
