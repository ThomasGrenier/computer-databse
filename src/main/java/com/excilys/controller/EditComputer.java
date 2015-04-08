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

import com.excilys.model.CompanyDTO;
import com.excilys.model.ComputerDTO;
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
		List<CompanyDTO> comp = new CompanyServiceImpl().listAll();
		ComputerDTO m = new ComputerServiceImpl().getById(id);
		if (m.getIntroduced() != null) {
			String parseIntro = m.getIntroduced().toString().replaceAll("T", " ");
			request.setAttribute("parseIntro", parseIntro);
		}
		if (m.getDiscontinued() != null) {
			String parseDisco = m.getDiscontinued().toString().replaceAll("T", " ");
			request.setAttribute("parseDisco", parseDisco);
		}
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

		boolean error = false;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		String result = request.getParameter("intro");
		if (result != null) {
			if (!result.isEmpty()) {
				if (Pattern.matches(Regex.DATE_FORMAT.getRegex(), result.trim())) {
					introduced = LocalDateTime.parse(result, formatter);
				} else {
					request.setAttribute("errorIntro", "bad Format (yyyy-MM-dd HH:mm:ss)");
					error = true;
				}
			}
		}

		result = request.getParameter("disco");
		if (result != null) {
			if (!result.isEmpty()) {
				if (Pattern.matches(Regex.DATE_FORMAT.getRegex(), result.trim())) {
					discontinued = LocalDateTime.parse(result, formatter);
				} else {
					request.setAttribute("errorDisco", "bad Format (yyyy-MM-dd HH:mm:ss)");
					error = true;
				}
			}
		}

		result = request.getParameter("comp");
		if (result != null) {
			if (!result.isEmpty()) {
				if ((Pattern.matches(Regex.DIGIT.getRegex(), result)) || (result.equals("-1"))) {
					idCompany = Integer.parseInt(result);
				} else {
					request.setAttribute("errorComp", "company not valid");
					error = true;
				}
			}
		}	

		if (error) {
			doGet(request, response);
			return ;
		}

		new ComputerServiceImpl().update(id, name, introduced, discontinued, idCompany);

		request.setAttribute("page", new ComputerServiceImpl().getPage(1, 10, "", "id", ""));
		getServletContext()
		.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
				request, response);
	}

}
