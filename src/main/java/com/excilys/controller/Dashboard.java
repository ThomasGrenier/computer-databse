package com.excilys.controller;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
}
