package com.excilys.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

import com.excilys.model.CompanyDTO;
import com.excilys.model.ComputerDTO;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.utils.Regex;

@Controller
@RequestMapping(value="/editComputer")
public class EditComputer {

	@Autowired
	@Qualifier("computerService")
	ComputerService computerService;

	@Autowired
	@Qualifier("companyService")
	CompanyService companyService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(@RequestParam("id") Optional<Integer> idParam, Model model) {

		int id = -1;
		if (idParam.isPresent()) {
			if (Pattern.matches(Regex.DIGIT.getRegex(), idParam.get().toString())) {
				id = idParam.get();
			}
		}
		
		if (id == -1) {
			//grosse erreur --> redirection page d'erreur
		}
		
		List<CompanyDTO> comp = companyService.listAll();
		ComputerDTO m = computerService.getById(id);
		model.addAttribute("parseIntro", m.getIntroduced());
		model.addAttribute("parseDisco", m.getDiscontinued());
		model.addAttribute("computer", m);
		model.addAttribute("companies", comp);
		
		return "editComputer";
	}


	@RequestMapping(value = "", method = RequestMethod.POST)
	public String editComputer(@RequestParam("id") Optional<Integer> idParam,
			@RequestParam("name") String nameParam,
			@RequestParam("intro") Optional<String> introParam,
			@RequestParam("disco") Optional<String> discoParam,
			@RequestParam("comp") Optional<Integer> compParam, Model model) {
		
		long id = 0L;
		String name = "";
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;
		long idCompany = -1L;

		if (idParam.isPresent()) {
			if (Pattern.matches(Regex.DIGIT.getRegex(), idParam.get().toString())) {
				id = idParam.get();
			}
		}
		
		if (id == -1) {
			//grosse erreur --> redirection page d'erreur
		}		

		name = nameParam;

		boolean error = false;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		String result = null;
		if (introParam.isPresent()) {
			if (!introParam.get().isEmpty()) {
				if (Pattern.matches(Regex.DATE_FORMAT.getRegex(), introParam.get().trim())) {
					introduced = LocalDateTime.parse(introParam.get().trim(), formatter);
				} else {
					model.addAttribute("errorIntro", "label.badFormat");
					error = true;
				}
			}
		}

		result = null;
		if (discoParam.isPresent()) {
			if (!discoParam.get().isEmpty()) {
				if (Pattern.matches(Regex.DATE_FORMAT.getRegex(), discoParam.get().trim())) {
					discontinued = LocalDateTime.parse(discoParam.get().trim(), formatter);
				} else {
					model.addAttribute("errorDisco", "label.badFormat");
					error = true;
				}
			}
		}

		result = null;
		if (compParam.isPresent()) {
			if (!compParam.get().toString().isEmpty()) {
				if ((Pattern.matches(Regex.DIGIT.getRegex(), compParam.get().toString())) || (compParam.get().toString().equals("-1"))) {
					idCompany = compParam.get();
				} else {
					model.addAttribute("errorComp", "label.invalidCompany");
					error = true;
				}
			}
		}	

		if (error) {
			List<CompanyDTO> comp = companyService.listAll();
			model.addAttribute("companies", comp);
			ComputerDTO m = computerService.getById(id);
			model.addAttribute("parseIntro", m.getIntroduced());
			model.addAttribute("parseDisco", m.getDiscontinued());
			model.addAttribute("computer", m);
			return "editComputer";
		}

		computerService.update(id, name, introduced, discontinued, idCompany);
		
		return "redirect:dashboard";
	}

	/*@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = -1;
		if (request.getParameter("id") != null) {
			if (Pattern.matches(Regex.DIGIT.getRegex(), request.getParameter("id"))) {
				id = Integer.parseInt(request.getParameter("id"));
			}
		}
		List<CompanyDTO> comp = companyService.listAll();
		ComputerDTO m = computerService.getById(id);
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

		computerService.update(id, name, introduced, discontinued, idCompany);

		request.setAttribute("page", computerService.getPage(1, 10, "", "id", ""));
		getServletContext()
		.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
				request, response);
	}*/

}
