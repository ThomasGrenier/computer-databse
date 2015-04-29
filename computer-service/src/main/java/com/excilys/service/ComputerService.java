package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.ComputerDTO;
import com.excilys.model.Page;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComputerService.
 */
public interface ComputerService {

	/**
	 * List all the computers.
	 *
	 * @return the list of all computer
	 */
	List<ComputerDTO> listAll();
	
	/**
	 * Gets the computer by id.
	 *
	 * @param id the id of the computer
	 * @return the computer by id
	 */
	ComputerDTO getById(long id);
	
	/**
	 * Creates the computer.
	 *
	 * @param name the name
	 * @param introduced the introduced
	 * @param discontinued the discontinued
	 * @param idCompany the id company
	 * @return the long
	 */
	long create(String name, LocalDateTime introduced, LocalDateTime discontinued, long idCompany);
	
	/**
	 * Delete a computer.
	 *
	 * @param id the id of the computer
	 */
	void delete(long id);
	
	/**
	 * Delete the computers by company id.
	 *
	 * @param id the id of the company
	 */
	void deleteByCompanyId(long id);
	
	/**
	 * Update a computer.
	 *
	 * @param id the id
	 * @param name the name
	 * @param introduced the introduced
	 * @param discontinued the discontinued
	 * @param idCompany the id company
	 */
	void update(long id, String name, LocalDateTime introduced, LocalDateTime discontinued, long idCompany);

	/**
	 * Gets the computers by page.
	 *
	 * @param offset the offset
	 * @param limit the limit
	 * @param searchBy the search by
	 * @param orderBy the order by
	 * @param option the option
	 * @return the computers by page
	 */
	List<ComputerDTO> getComputersByPage(int offset, int limit, String searchBy, String orderBy, String option);
	
	/**
	 * Gets the page.
	 *
	 * @param currentPage the current page
	 * @param limit the limit
	 * @param searchBy the search by
	 * @param orderBy the order by
	 * @param option the option
	 * @return the page
	 */
	Page<ComputerDTO> getPage(int currentPage, int limit, String searchBy, String orderBy, String option);
	
	/**
	 * Total row of the computers.
	 *
	 * @param searchBy the search term
	 * @return the number of computers
	 */
	int totalRow(String searchBy);
}
