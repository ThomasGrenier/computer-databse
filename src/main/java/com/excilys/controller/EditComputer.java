package com.excilys.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;
import com.excilys.service.CompanyServiceImpl;
import com.excilys.service.ComputerServiceImpl;
import com.excilys.utils.Regex;

@SuppressWarnings("serial")
public class EditComputer extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = -1;
		if (request.getParameter("id") != null) {
			if (Pattern.matches(Regex.DIGIT.getRegex(), request.getParameter("id"))) {
				id = Integer.parseInt(request.getParameter("id"));
			}
		}
		List<CompanyModel> comp = new CompanyServiceImpl().listAll();
		ComputerModel m = new ComputerServiceImpl().getById(id);
		request.setAttribute("computer", m);
		request.setAttribute("companies", comp);
		getServletContext()
		.getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(
				request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		long id = 0L;
		String name = "";
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;
		long idCompany = -1L;
		

		if (request.getParameter("id") != null) {
			if (Pattern.matches(Regex.DIGIT.getRegex(), request.getParameter("id"))) {
				id = Integer.parseInt(request.getParameter("id"));
			}
		}
		
		name = request.getParameter("name");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		if (request.getParameter("intro") != null) {
			if (Pattern.matches(Regex.DATE_FORMAT.getRegex(), request.getParameter("intro").trim())) {
				introduced = LocalDateTime.parse(request.getParameter("intro"), formatter);
			}
		}
		
		if (request.getParameter("disco") != null) {
			if (Pattern.matches(Regex.DATE_FORMAT.getRegex(), request.getParameter("disco").trim())) {
				discontinued = LocalDateTime.parse(request.getParameter("disco"), formatter);
			}
		}

		if (request.getParameter("comp") != null) {
			if (Pattern.matches(Regex.DIGIT.getRegex(), request.getParameter("comp"))) {
				idCompany = Integer.parseInt(request.getParameter("comp"));
			}
		}		
		
		new ComputerServiceImpl().update(id, name, introduced, discontinued, idCompany);
		
		request.setAttribute("page", new ComputerServiceImpl().getPage(1, 10, "", "id", ""));
		getServletContext()
		.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
				request, response);
	}

}
