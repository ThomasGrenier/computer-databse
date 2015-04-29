package com.excilys.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.model.CompanyDTO;
import com.excilys.service.CompanyService;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyRest.
 */
@RestController
@RequestMapping(value="/rest/companies")
public class CompanyRest {
	
	/** The company service. */
	@Autowired
	private CompanyService companyService;

	/**
	 * List all company.
	 *
	 * @return the list of the companies
	 */
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public List<CompanyDTO> listAllComputer() {
		return companyService.listAll();
	}
	
	/**
	 * Gets the company by id.
	 *
	 * @param id the id of the company
	 * @return the company by id
	 */
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	public CompanyDTO getById(@RequestParam("id") long id) {
		return companyService.getById(id);
	}
}
