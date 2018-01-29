package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");

		if ("joinform".equals(actionName)) {
			System.out.println("joinform");
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");

		} else if ("join".equals(actionName)) {
			System.out.println("join");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			
			
			UserVo vo = new UserVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setEmail(email);
			vo.setGender(gender);
			if(name.equals(vo.getName())) {
				System.out.println("중복");
			}

			UserDao dao = new UserDao();
			dao.insert(vo);

			WebUtil.forward(request, response, "/WEB-INF/views/user/joinsuccess.jsp");

		}

		else if ("loginform".equals(actionName)) {
			System.out.println("로그인폼");
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
		}

		else if ("login".equals(actionName)) {
			System.out.println("로그인");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			UserDao dao = new UserDao();
			UserVo vo = dao.getUser(email, password);

			if (vo == null) {
				System.out.println("로그인 실패");
				WebUtil.redirect(request, response, "/mysite/user?a=loginform&result=fail");
			} else {
				System.out.println("로그인 성공");
				HttpSession session = request.getSession();
				session.setAttribute("authUser", vo);
				WebUtil.redirect(request, response, "/mysite/main");
			}
		} else if ("logout".equals(actionName)) {
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			WebUtil.redirect(request, response, "/mysite/main");
		} else if ("modifyform".equals(actionName)) {
			System.out.println("수정폼");
			// 수업 소스
			// 세션에서 no 가져오기getNo()
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo) session.getAttribute("authUser");

			// no가 없으면 로그인 폼으로 보내버리기 redirect
			if (authUser == null) {// 로그인x

			} else {// 로그인
					// 로그인 회원 no
				String no = authUser.getNo();

				// dao에서 가져오기(no)
				UserDao dao = new UserDao();
				UserVo userVo = dao.getUser(no);

				// 데이터 request에 저장
				request.setAttribute("userVo", userVo);

				// 포워드
				WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
			}
		}
		// 수업소스
		else if ("modify".equals(actionName)) {
			System.out.println("수정되냐?");
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			if (authUser == null) {// 로그인x

			} else {// 로그인
				
				//(no,name,password,gender) 가져오기
				//jsp파일에서 no 받는 장치가 없으니까 세션에서 no 가져온다
				String no = authUser.getNo();
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String gender = request.getParameter("gender");
				
				//가지고온 데이터 vo에 저장(setter 사용하거나 생성자로 받는것 중 선택)
				UserVo vo = new UserVo(no, name, password, "", gender);
				
				//dao.update(vo)
				UserDao dao = new UserDao();
				dao.update(vo);
				System.out.println(vo.toString());
				//세션에서 name 변경
				authUser.setName(name);
				//메인으로 redirect
				WebUtil.redirect(request, response, "/mysite/main");
			}
		}
		
		// else if("update".equals(actionName)) {
		// System.out.println("수정되냐?");
		// HttpSession session = request.getSession();
		// UserVo vo=(UserVo)session.getAttribute("authUser");
		// vo.setNo(vo.getNo());
		// vo.setName(request.getParameter("name"));
		// vo.setPassword(request.getParameter("password"));
		// vo.setGender(request.getParameter("gender"));
		//
		//
		// UserDao dao = new UserDao();
		// dao.update(vo);
		// session.setAttribute("authUser", vo);
		//
		//
		// WebUtil.redirect(request, response, "/mysite/main");
		// }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
