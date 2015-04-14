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
}
