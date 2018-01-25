package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDAO;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestBookVO;


@WebServlet("/gc")
public class GuestController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("방명록 어서와");
		String actionName = request.getParameter("a");
		request.setCharacterEncoding("UTF-8");

		if ("form".equals(actionName)) {
			System.out.println("form 들어옴");
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		}

		else if ("insert".equals(actionName)) {
			System.out.println("insert 들어옴");
			GuestBookVO vo = new GuestBookVO();
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");

			vo.setName(name);
			
			vo.setPassword(password);
			
			vo.setContent(content);
			

			System.out.println(vo.toString());

			GuestBookDAO dao = new GuestBookDAO();
			dao.insert(vo);
			WebUtil.redirect(request, response, "gc?a=list");
//			response.sendRedirect("gc?a=list");

		}

		else if ("list".equals(actionName)) {
			System.out.println("list 들어옴");
			GuestBookDAO dao = new GuestBookDAO();
			List<GuestBookVO> list = dao.getList();

			request.setAttribute("guestList", list);
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");

		}

		else if ("deleteform".equals(actionName)) {
			System.out.println("deleteform 들어옴");
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
		} else if ("delete".equals(actionName)) {
			System.out.println("delete 들어옴");
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			GuestBookDAO dao = new GuestBookDAO();
			dao.delete(no, password);
			WebUtil.redirect(request, response, "gc?a=list");
		}

		else
			System.out.println("바보");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
