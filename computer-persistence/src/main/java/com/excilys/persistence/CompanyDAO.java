package com.excilys.persistence;

import java.util.List;

import com.excilys.model.CompanyModel;

// TODO: Auto-generated Javadoc
/**
 * The Interface CompanyDAO.
 */
public interface CompanyDAO {

	/**
	 * List all the companies.
	 *
	 * @return the list of all Companies
	 */
	List<CompanyModel> listAll();
	
	/**
	 * Gets the company by id.
	 *
	 * @param id, the id of the company
	 * @return the company by id
	 */
	CompanyModel getById(long id);

	/**
	 * Gets the companies by page.
	 *
	 * @param offset, the current page
	 * @param limit, the number of computies by page
	 * @param searchBy, search the name by this attribute
	 * @param orderBy, the attribute which will be used to order the page
	 * @param option, the order of the list (ASC or DESC)
	 * @return the companies by page
	 */
	List<CompanyModel> getCompaniesByPage(int offset, int limit, String searchBy, String orderBy, String option);
	
	/**
	 * Total row of the companies by the text researched
	 *
	 * @param searchBy, search the name by this attribute
	 * @return the int which represents the number of companies
	 */
	int totalRow(String searchBy);
	
	/**
	 * Delete a company and all the computer related to this company
	 *
	 * @param id, the id of the company
	 */
	void delete(long id);
}
