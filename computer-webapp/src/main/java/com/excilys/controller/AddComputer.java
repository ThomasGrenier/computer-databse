package com.excilys.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.model.CompanyDTO;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.utils.Regex;

// TODO: Auto-generated Javadoc
/**
 * The Class AddComputer.
 */
@Controller
@RequestMapping(value="/addComputer")
public class AddComputer {

	/** The computer service. */
	@Autowired
	@Qualifier("computerService")
	ComputerService computerService;

	/** The company service. */
	@Autowired
	@Qualifier("companyService")
	CompanyService companyService;
	
	/**
	 * Index.
	 *
	 * @param model the model
	 * @return the model and view
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		
		List<CompanyDTO> comp = companyService.listAll();
		model.addObject("companies", comp);
		return model;
	}
	

	/**
	 * Adds a computer.
	 *
	 * @param nameParam the name of the computer
	 * @param introParam the introduced date of the computer
	 * @param discoParam the discontinued date of the computer
	 * @param compParam the company id of the computer
	 * @param model the model
	 * @return the model and view
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView addComputer(@RequestParam("name") String nameParam,
			@RequestParam("intro") Optional<String> introParam,
			@RequestParam("disco") Optional<String> discoParam,
			@RequestParam("comp") Optional<Integer> compParam, ModelAndView model) {
		
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;
		long idCompany = 0L;

		String name = nameParam;

		boolean error = false;

		if (name == null || name.isEmpty()) {
			model.addObject("errorName", "label.requiredName");
			error = true;
		} else {
			model.addObject("name", name);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		if (introParam.isPresent()) {
			if (!introParam.get().isEmpty()) {
				if (Pattern.matches(Regex.DATE_FORMAT.getRegex(), introParam.get().trim())) {
					introduced = LocalDateTime.parse(introParam.get(), formatter);
					model.addObject("intro", introParam.get());
				} else {
					model.addObject("errorIntro", "label.badFormat");
					error = true;
				}
			}
		}

		if (discoParam.isPresent()) {
			if (!discoParam.get().isEmpty()) {
				if (Pattern.matches(Regex.DATE_FORMAT.getRegex(), discoParam.get().trim())) {
					discontinued = LocalDateTime.parse(discoParam.get(), formatter);
					model.addObject("disco", discoParam.get());
				} else {
					model.addObject("errorDisco", "label.badFormat");
					error = true;
				}
			}
		}

		if (compParam.isPresent()) {
			if ((Pattern.matches(Regex.DIGIT.getRegex(), compParam.get().toString())) || (compParam.get() == -1)) {
				idCompany = compParam.get();
				model.addObject("comp", idCompany);
			} else {
				model.addObject("errorComp", "label.invalidCompany");
				error = true;
			}
		}

		if (error) {
			List<CompanyDTO> comp = companyService.listAll();
			model.addObject("companies", comp);
			model.setViewName("addComputer");
			return model;
		}

		computerService.create(name, introduced, discontinued, idCompany);

		model.setViewName("redirect:dashboard");
		return model;
	}
}
