package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/bbs")
public class BoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("게시판 어서와");
		String actionName = request.getParameter("a");
		request.setCharacterEncoding("UTF-8");

		// 게시판 접속하면 list.jsp로 들어옴
		// 비회원(로그인 여부) 체크해서 insert delete update 가능하게
		if ("list".equals(actionName)) {
			System.out.println("list 들어옴");
			BoardDao dao = new BoardDao();
			List<BoardVo> boardList = dao.getList();

			request.setAttribute("boardList", boardList);
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		}

		else if ("write".equals(actionName)) {
			System.out.println("write 들어옴");

			WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");

		}

		else if ("insert".equals(actionName)) {
			BoardVo vo = new BoardVo();
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			vo.setTitle(title);

			vo.setContent(content);

			System.out.println(vo.toString());

			BoardDao dao = new BoardDao();
			dao.insert(vo);

		} 
//		else if ("getOne".equals(actionName)) {
//			System.out.println("글하나 가져오기");
//			HttpSession session = request.getSession(true);
//			UserVo authUser = (UserVo) session.getAttribute("authUser");
//
//			if (authUser == null) {// 로그인x
//
//			} else {// 로그인
//
//				// (no,title,content) 가져오기
//				// jsp파일에서 no 받는 장치가 없으니까 세션에서 no 가져온다
//				int no = authUser.getNo();
//				String title = request.getParameter("title");
//				String content = request.getParameter("content");
//
//				// 가지고온 데이터 vo에 저장(setter 사용하거나 생성자로 받는것 중 선택)
//				BoardVo vo = new BoardVo();
//				vo.setNo(no);
//				vo.setTitle(title);
//				vo.setContent(content);
//				// dao.update(vo)
//				BoardDao dao = new BoardDao();
//				dao.getOne(no);
//				//조회수 1증가
//				dao.hitCount(no);
//				System.out.println(vo.toString());
//				// view로 보내기
//				WebUtil.redirect(request, response, "/mysite/main");
//			}
//		}
//
//		// else if ("write".equals(actionName)) {
//		// System.out.println("write 들어옴");
//		// WebUtil.forward(request, response, "/WEB-INF/views/guestbook/write.jsp");
//		// }
//		else if ("delete".equals(actionName)) {
//			System.out.println("delete 들어옴");
//			// 번호로 게시글 확인
//			// 로그인해야 접근 가능한 게시판이니까 password는 안받는다
//			String no = request.getParameter("no");
//			BoardDao dao = new BoardDao();
//			dao.delete(no);
//			WebUtil.redirect(request, response, "gc?a=list");
//		}
		
		else if("view".equals(actionName)) {
			System.out.println("view 들어옴");
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo) session.getAttribute("authUser");

			if (authUser == null) {// 로그인x

			} else {// 로그인

				// (no,title,content) 가져오기
				// jsp파일에서 no 받는 장치가 없으니까 세션에서 no 가져온다
				int no = authUser.getNo();
				String title = request.getParameter("title");
				String content = request.getParameter("content");

				// 가지고온 데이터 vo에 저장(setter 사용하거나 생성자로 받는것 중 선택)
				BoardVo boardVo = new BoardVo();
				boardVo.setNo(no);
				boardVo.setTitle(title);
				boardVo.setContent(content);

				BoardDao dao = new BoardDao();
				dao.getOne(no);
				//조회수 1증가
				dao.hitCount(no);
				System.out.println(boardVo.toString());
			WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
		}
		}
		
		else if ("modify".equals(actionName)) {
			//수정폼으로 보내버리기
			System.out.println("수정폼으로 날아가라");
			WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");
		}
		
		else if("update".equals(actionName)) {
			System.out.println("수정되냐?");
			//제목이랑 내용만 수정
			//세션에서 나머지 가져옴?
			HttpSession session = request.getSession();
			BoardVo vo = (BoardVo) session.getAttribute("authUser");
			vo.setNo(vo.getNo());
			vo.setTitle(request.getParameter("password"));
			vo.setContent(request.getParameter("gender"));

			BoardDao dao = new BoardDao();
			dao.update(vo);
			session.setAttribute("authUser", vo);

			WebUtil.redirect(request, response, "/mysite/main");
		}

		else
			System.out.println("바보");
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
