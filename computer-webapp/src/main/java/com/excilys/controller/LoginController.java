package com.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(value="/loginUser", method = RequestMethod.GET)
	public ModelAndView login () {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}

	@RequestMapping(value="/loginError", method = RequestMethod.GET)
	public ModelAndView loginError(ModelAndView model) {
		model.addObject("error", "err");
		model.setViewName("login");
		return model;
	}
}
