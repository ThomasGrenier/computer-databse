package com.excilys.service;

import java.util.List;

import com.excilys.model.CompanyDTO;
import com.excilys.model.Page;

// TODO: Auto-generated Javadoc
/**
 * The Interface CompanyService.
 */
public interface CompanyService {
	
	/**
	 * List all the companies.
	 *
	 * @return the list
	 */
	List<CompanyDTO> listAll();
	
	/**
	 * Gets the company by id.
	 *
	 * @param id the id of the company
	 * @return the company by id
	 */
	CompanyDTO getById(long id);

	/**
	 * Gets the companies by page.
	 *
	 * @param offset the offset
	 * @param limit the limit
	 * @param searchBy the search by
	 * @param orderBy the order by
	 * @param option the option
	 * @return the companies by page
	 */
	List<CompanyDTO> getCompaniesByPage(int offset, int limit, String searchBy, String orderBy, String option);
	
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
	Page<CompanyDTO> getPage(int currentPage, int limit, String searchBy, String orderBy, String option);
	
	/**
	 * Delete a company.
	 *
	 * @param id the id of the company
	 */
	void delete(long id);
	
	/**
	 * Total row.
	 *
	 * @param searchBy the search term
	 * @return the number of company
	 */
	int totalRow(String searchBy);
}
