/*
 * 用于处理添加联系人之后的更新xml数据库和跳转到主页面
 * */
package com.Smileyes.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Smileyes.Exception.ConExitException;
import com.Smileyes.dao.Dao;
import com.Smileyes.dao.impl.DaoImpl;
import com.Smileyes.entity.Contact;
import com.Smileyes.service.Service;
import com.Smileyes.service.impl.ContactService;

public class UpdateAddServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String number = request.getParameter("number");
		String email = request.getParameter("email");
		Contact c = new Contact(id, name, gender, number, email);
		Service service = new ContactService();
		try {
			service.addContact(c);
		} catch (ConExitException e) {
			request.setAttribute("msg", "该联系人已存在");
		}
		request.getRequestDispatcher("/indexServlet").forward(
				request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
