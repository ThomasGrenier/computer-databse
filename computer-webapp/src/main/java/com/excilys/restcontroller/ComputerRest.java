package com.excilys.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.model.ComputerDTO;
import com.excilys.service.ComputerService;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerRest.
 */
@RestController
@RequestMapping(value="/rest/computers")
public class ComputerRest {
	
	/** The computer service. */
	@Autowired
	private ComputerService computerService;

	/**
	 * List all the computers.
	 *
	 * @return the list of all the computers
	 */
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public List<ComputerDTO> listAllComputer() {
		return computerService.listAll();
	}
	
	/**
	 * Gets the computer by id.
	 *
	 * @param id the id of the computer
	 * @return the computer by id
	 */
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	public ComputerDTO getById(@RequestParam("id") long id) {
		return computerService.getById(id);
	}
}
