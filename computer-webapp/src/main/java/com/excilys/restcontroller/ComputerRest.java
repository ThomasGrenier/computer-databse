package com.excilys.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.model.ComputerDTO;
import com.excilys.service.ComputerService;

@RestController
@RequestMapping(value="/rest/computers")
public class ComputerRest {
	
	@Autowired
	private ComputerService computerService;

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public List<ComputerDTO> listAllComputer() {
		return computerService.listAll();
	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	public ComputerDTO getById(@RequestParam("id") long id) {
		return computerService.getById(id);
	}
}
