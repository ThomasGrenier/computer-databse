package com.excilys.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.service.ComputerService;
import com.excilys.utils.Regex;

@Controller
@RequestMapping(value="/dashboard")
public class Dashboard {

	@Autowired
	@Qualifier("computerService")
	ComputerService computerService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(@RequestParam("offset") Optional<Integer> offsetParam,
			@RequestParam("limit") Optional<Integer> limitParam,
			@RequestParam("search") Optional<String> searchParam,
			@RequestParam("order") Optional<String> orderParam,
			@RequestParam("option") Optional<String> optionParam,
			Model model) {
		int offset = 1;
		int limit = 10;
		if (offsetParam.isPresent()) {
			if (Pattern.matches(Regex.DIGIT.getRegex(), offsetParam.get().toString())) {
				offset = offsetParam.get();
			}
		}
		
		if (limitParam.isPresent()) {
			if (Pattern.matches(Regex.DIGIT.getRegex(), limitParam.get().toString())) {
				limit = limitParam.get();
			}
		}

		String test = "";
		if (searchParam.isPresent()) {
			test = searchParam.get();
		}

		String order = "id";

		if (orderParam.isPresent()) {
			order = orderParam.get();
		}

		String option = "";

		if (optionParam.isPresent()) {
			option = optionParam.get();
		}

		model.addAttribute("page", computerService.getPage(offset, limit, test, order, option));
		return "dashboard";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String deleteComputer(@RequestParam("selection") Optional<String> selectionParam,
			Model model) {

		if (selectionParam.isPresent()) {
			String selectedComputersId = selectionParam.get();
			if ((selectedComputersId != null) && (!selectedComputersId.isEmpty())) {
				String[] computerId = selectedComputersId.split(",");
				for (String id : computerId) {
					long idCompu = Long.parseLong(id);
					computerService.delete(idCompu);
				}
			}
		}
		
		model.addAttribute("page", computerService.getPage(1, 10, "", "id", ""));
		return "dashboard";
	}


	/*@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

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

		String test = "";
		if (request.getParameter("search") != null) {
			test = request.getParameter("search");
		}

		String order = "id";

		if (request.getParameter("order") != null) {
			order = request.getParameter("order");
		}

		String option = "";

		if (request.getParameter("option") != null) {
			option = request.getParameter("option");
		}


		request.setAttribute("page", computerService.getPage(offset, limit, test, order, option));
		getServletContext()
		.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
				request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String selectedComputersId = request.getParameter("selection");
		if ((selectedComputersId != null) && (!selectedComputersId.isEmpty())) {
			String[] computerId = selectedComputersId.split(",");
			for (String id : computerId) {
				long idCompu = Long.parseLong(id);
				computerService.delete(idCompu);
			}
		}

		request.setAttribute("page", computerService.getPage(1, 10, "", "id", ""));
		getServletContext()
		.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
				request, response);
	}*/
}
