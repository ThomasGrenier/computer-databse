package com.excilys.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.service.ComputerServiceImpl;
import com.excilys.utils.Regex;

@SuppressWarnings("serial")
public class Dashboard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int offset = 1;
		int limit = 10;
		if (request.getParameter("offset") != null) {
			if (Pattern.matches(Regex.DIGIT.getRegex(), request.getParameter("offset"))) {
				offset = Integer.parseInt(request.getParameter("offset"));
			}
		}
		if (request.getParameter("limit") != null) {
			if (Pattern.matches(Regex.DIGIT.getRegex(), request.getParameter("limit"))) {
				limit = Integer.parseInt(request.getParameter("limit"));
			}
		}
		request.setAttribute("page", new ComputerServiceImpl().getPage(offset, limit));
		getServletContext()
		.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
				request, response);
	}
}
