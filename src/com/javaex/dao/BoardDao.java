package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;


public class BoardDao {
	
	public BoardVo getOne(int no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		BoardVo vo = null;

		try {
			// JDBC 드라이버(Oracle)로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// sql문 준비 / 바인딩 / 실행
			String query = " select b.no, u.name, b.title, b.content, b.reg_date, b.hit " + 
						   " from board b, users  where b.user_no = (u.no)? ";
						   

			psmt = conn.prepareStatement(query);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();

			// 결과처리
			while (rs.next()) {
				no = rs.getInt("no");
				String title = rs.getString("title");  
				String content = rs.getString("content");  
				vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);

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
	
	public List<BoardVo> getList() {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<BoardVo> boardList = new ArrayList<BoardVo>();

		try {
			// JDBC 드라이버(Oracle)로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// sql문 준비 / 바인딩 / 실행
			String query = " select b.no, u.name, b.title, b.content, b.reg_date, b.hit " + 
						   " from board b, users  where b.user_no = (u.no)? " +
						   " order by b.reg_date desc";

			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();

			// 결과처리
			while (rs.next()) {

				BoardVo dto = new BoardVo();

				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");

				dto.setNo(no);

				dto.setTitle(title);


				dto.setContent(content);

				boardList.add(dto);
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
		return boardList;
	}

	public void insert(BoardVo dto) {
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
			query = "insert into board values (seq_board_no.nextval, ?, ?, default, default, ?)";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());

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

	public void delete(String no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			// JDBC 드라이버(Oracle)로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// sql문 준비 / 바인딩 / 실행
			String query = "delete from board where no = ? ";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, no);

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
	
	public void update(BoardVo vo) {
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			// JDBC 드라이버(Oracle)로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// sql문 준비 / 바인딩 / 실행
			String query = "update board set title = ?, content = ? " + 
						   "where no = ?";
			
			psmt = conn.prepareStatement(query);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setInt(3, vo.getNo());

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
}
