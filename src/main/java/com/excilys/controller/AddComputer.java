package com.excilys.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.model.CompanyDTO;
import com.excilys.service.CompanyServiceImpl;
import com.excilys.service.ComputerServiceImpl;
import com.excilys.utils.Regex;

@SuppressWarnings("serial")
@Controller
@WebServlet(urlPatterns = "/addComputer")
public class AddComputer extends HttpServlet {

	@Autowired
	ComputerServiceImpl computerService;

	@Autowired
	CompanyServiceImpl companyService;


	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<CompanyDTO> comp = companyService.listAll();
		request.setAttribute("companies", comp);
		getServletContext()
		.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;
		long idCompany = 0L;

		String name = request.getParameter("name");

		boolean error = false;

		if (name == null || name.isEmpty()) {
			request.setAttribute("errorName", "The name is required");
			error = true;
		} else {
			request.setAttribute("name", name);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		if (request.getParameter("intro") != null) {
			if (!request.getParameter("intro").isEmpty()) {
				if (Pattern.matches(Regex.DATE_FORMAT.getRegex(), request.getParameter("intro").trim())) {
					introduced = LocalDateTime.parse(request.getParameter("intro"), formatter);
					request.setAttribute("intro", request.getParameter("intro"));
				} else {
					request.setAttribute("errorIntro", "bad Format (yyyy-MM-dd HH:mm:ss)");
					error = true;
				}
			}
		}

		if (request.getParameter("disco") != null) {
			if (!request.getParameter("disco").isEmpty()) {
				if (Pattern.matches(Regex.DATE_FORMAT.getRegex(), request.getParameter("disco").trim())) {
					discontinued = LocalDateTime.parse(request.getParameter("disco"), formatter);
					request.setAttribute("disco", request.getParameter("disco"));
				} else {
					request.setAttribute("errorDisco", "bad Format (yyyy-MM-dd HH:mm:ss)");
					error = true;
				}
			}
		}

		if (request.getParameter("comp") != null) {
			if (Pattern.matches(Regex.DIGIT.getRegex(), request.getParameter("comp"))) {
				idCompany = Integer.parseInt(request.getParameter("comp"));
				request.setAttribute("comp", idCompany);
			} else {
				request.setAttribute("errorComp", "company not valid");
				error = true;
			}
		}

		if (error) {
			doGet(request, response);
			return ;
		}

		request.removeAttribute("intro");
		request.removeAttribute("disco");
		request.removeAttribute("comp");

		computerService.create(name, introduced, discontinued, idCompany);

		request.setAttribute("page", computerService.getPage(1, 10, "", "id", ""));
		getServletContext()
		.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
				request, response);
	}
}
