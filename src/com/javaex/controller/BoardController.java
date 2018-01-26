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
import com.javaex.dao.UserDao;
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
		// 최근 글이 맨위로
		// 자신이 작성한 글에만 수정, 삭제버튼
		// 조회수 증가시키기
		// 글 수정 하다가 취소하면 원래글로
		if ("list".equals(actionName)) {
			System.out.println("board list 들어옴");
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
			System.out.println("insert");
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			BoardVo boardVo = new BoardVo();
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String name = authUser.getName();
			int userNo = authUser.getNo();
			boardVo.setName(name);
			boardVo.setTitle(title);
			boardVo.setContent(content);
			boardVo.setUserNo(userNo);
			System.out.println(boardVo.toString());

			BoardDao dao = new BoardDao();
			dao.insert(boardVo);
			WebUtil.redirect(request, response, "/mysite/bbs?a=list");
		}
		// else if ("delete".equals(actionName)) {
		// System.out.println("delete 들어옴");
		// // 번호로 게시글 확인
		// //삭제버튼 클릭하면 바로 삭제후 리스트
		// String no = request.getParameter("no");
		// BoardDao dao = new BoardDao();
		// dao.delete(no);
		// WebUtil.redirect(request, response, "gc?a=list");
		// }

		else if ("view".equals(actionName)) {
			System.out.println("view 들어옴");
			String no = request.getParameter("no");

			BoardDao dao = new BoardDao();
			BoardVo boardVo = dao.getOne(no);

			// view페이지에서 줄바꾸기 해주기
			boardVo.setContent(boardVo.getContent().replace("\r\n", "<br/>"));

			request.setAttribute("boardVo", boardVo);

			// 조회수 1증가
			dao.hitCount(no);
			WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");

		}

		else if ("modify".equals(actionName)) {
			System.out.println("수정폼으로 날아가라");
			String no = request.getParameter("no");

			BoardDao dao = new BoardDao();
			BoardVo boardVo = dao.getOne(no);

			request.setAttribute("boardVo", boardVo);
			WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");
		}

		else if ("update".equals(actionName)) {
			System.out.println("수정되냐?");
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			BoardVo boardVo = new BoardVo();
			boardVo.setTitle(title);
			boardVo.setContent(content);
			
			BoardDao dao = new BoardDao();
			dao.update(boardVo);
			
			System.out.println(boardVo.toString());
			WebUtil.redirect(request, response, "/mysite/bbs?a=list");
		}

		else
			System.out.println("바보");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
