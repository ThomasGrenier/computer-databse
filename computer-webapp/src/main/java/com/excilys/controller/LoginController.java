package com.excilys.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginController.
 */
@Controller
public class LoginController {

	/**
	 * Login.
	 *
	 * @param log the log
	 * @param err the err
	 * @return the model and view
	 */
	@RequestMapping(value="/loginUser", method = RequestMethod.GET)
	public ModelAndView login (@RequestParam("state") Optional<String> log,
			@RequestParam("error") Optional<String> err) {
		ModelAndView model = new ModelAndView();
		if (log.isPresent() && !log.get().isEmpty()) {
			model.addObject("logout", "out");
		}
		if (err.isPresent() && !err.get().isEmpty()) {
			model.addObject("error", "err");
		}
		model.setViewName("login");
		return model;
	}
}
