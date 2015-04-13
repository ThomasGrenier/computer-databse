package com.excilys.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.model.CompanyDTO;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.utils.Regex;

@Controller
@RequestMapping(value="/addComputer")
public class AddComputer {

	@Autowired
	@Qualifier("computerService")
	ComputerService computerService;

	@Autowired
	@Qualifier("companyService")
	CompanyService companyService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		
		List<CompanyDTO> comp = companyService.listAll();
		model.addAttribute("companies", comp);
		
		return "addComputer";
	}
	

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String addComputer(@RequestParam("name") String nameParam,
			@RequestParam("intro") Optional<String> introParam,
			@RequestParam("disco") Optional<String> discoParam,
			@RequestParam("comp") Optional<Integer> compParam, Model model) {
		
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;
		long idCompany = 0L;

		String name = nameParam;

		boolean error = false;

		if (name == null || name.isEmpty()) {
			model.addAttribute("errorName", "label.requiredName");
			error = true;
		} else {
			model.addAttribute("name", name);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		if (introParam.isPresent()) {
			if (!introParam.get().isEmpty()) {
				if (Pattern.matches(Regex.DATE_FORMAT.getRegex(), introParam.get().trim())) {
					introduced = LocalDateTime.parse(introParam.get(), formatter);
					model.addAttribute("intro", introParam.get());
				} else {
					model.addAttribute("errorIntro", "label.badFormat");
					error = true;
				}
			}
		}

		if (discoParam.isPresent()) {
			if (!discoParam.get().isEmpty()) {
				if (Pattern.matches(Regex.DATE_FORMAT.getRegex(), discoParam.get().trim())) {
					discontinued = LocalDateTime.parse(discoParam.get(), formatter);
					model.addAttribute("disco", discoParam.get());
				} else {
					model.addAttribute("errorDisco", "label.badFormat");
					error = true;
				}
			}
		}

		if (compParam.isPresent()) {
			if ((Pattern.matches(Regex.DIGIT.getRegex(), compParam.get().toString())) || (compParam.get() == -1)) {
				idCompany = compParam.get();
				model.addAttribute("comp", idCompany);
			} else {
				model.addAttribute("errorComp", "label.invalidCompany");
				error = true;
			}
		}

		if (error) {
			List<CompanyDTO> comp = companyService.listAll();
			model.addAttribute("companies", comp);
			return "addComputer";
		}

		computerService.create(name, introduced, discontinued, idCompany);

		return "redirect:dashboard";
	}
}
