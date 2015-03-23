package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.service.ComputerServiceImpl;

@SuppressWarnings("serial")
public class Dashboard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("computers", new ComputerServiceImpl().listAll());
		getServletContext()
		.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
				request, response);
	}
}
