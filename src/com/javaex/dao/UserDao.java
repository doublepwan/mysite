package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.UserVo;

public class UserDao {
	
	public void update(UserVo vo) {
			Connection conn = null;
			PreparedStatement psmt = null;
			
			try {
				// JDBC 드라이버(Oracle)로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");

				// Connection 얻어오기
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, "webdb", "webdb");

				// sql문 준비 / 바인딩 / 실행
				String query = "update users set name = ?, password = ?, gender = ? " + 
							   "where no = ?";
				
				psmt = conn.prepareStatement(query);
				psmt.setString(1, vo.getName());
				psmt.setString(2, vo.getPassword());
				psmt.setString(3, vo.getGender());
				psmt.setInt(4, vo.getNo());

				int count = psmt.executeUpdate();

				// 결과처리
				System.out.println(count + "건 수정완료.");

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

	public UserVo getUser(String email, String password) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		UserVo vo = null;

		try {
			// JDBC 드라이버(Oracle)로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// sql문 준비 / 바인딩 / 실행
			String query = "select no, name, email, password from users where email = ? and password = ?";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, email);
			psmt.setString(2, password);
			rs = psmt.executeQuery();

			// 결과처리
			while (rs.next()) {
				vo = new UserVo();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setEmail(rs.getString(3));
				vo.setPassword(rs.getString(4));

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
		return vo;
	}

	public void insert(UserVo vo) {
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
			query = "insert into users values(seq_guest_no.nextval, ?, ?, ?, ?)";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getEmail());
			psmt.setString(3, vo.getPassword());
			psmt.setString(4, vo.getGender());

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

	public UserVo getUser(int no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		UserVo vo = null;

		try {
			// JDBC 드라이버(Oracle)로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// sql문 준비 / 바인딩 / 실행
			String query = "select no, name, email, password, gender from users where no = ?";

			psmt = conn.prepareStatement(query);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();

			// 결과처리
			while (rs.next()) {
				no = rs.getInt("no");
				String name = rs.getString("name");  
				String email = rs.getString("email");  
				String password = rs.getString("password");  
				String gender = rs.getString("gender");  
				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email);
				vo.setPassword(password);
				vo.setGender(gender);

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
		return vo;
	}
}
