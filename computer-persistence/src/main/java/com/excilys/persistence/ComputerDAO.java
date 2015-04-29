package com.excilys.persistence;

import java.util.List;

import com.excilys.model.ComputerModel;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComputerDAO.
 */
public interface ComputerDAO {

	/**
	 * List all the computers.
	 *
	 * @return the list of all the computers
	 */
	List<ComputerModel> listAll();
	
	/**
	 * Gets the computer by id.
	 *
	 * @param id, the id of the computer
	 * @return the computer by id
	 */
	ComputerModel getById(long id);
	
	/**
	 * Creates the computer.
	 *
	 * @param computer, the computer which will be created
	 * @return the long represents the success of the operation
	 */
	long create(ComputerModel computer);
	
	/**
	 * Update the computer
	 *
	 * @param computer, the computer needed to be updated
	 */
	void update(ComputerModel computer);
	
	/**
	 * Delete the computer
	 *
	 * @param id, the id of the computer
	 */
	void delete(long id);
	
	/**
	 * Delete the computers by the id of the company.
	 *
	 * @param id, the id of the company
	 */
	void deleteByCompanyId(long id);
	
	/**
	 * Gets the computers by page.
	 *
	 * @param offset, the current page
	 * @param limit, the number of computers by page
	 * @param searchBy, search the name by this attribute
	 * @param orderBy, the attribute which will be used to order the page
	 * @param option, the order of the list (ASC or DESC)
	 * @return the computers by page
	 */
	List<ComputerModel> getComputersByPage(int offset, int limit, String searchBy, String orderBy, String option);
	
	/**
	 * Total row of the computer by the research text
	 *
	 * @param searchBy, search the name by this attribute
	 * @return the int represents the number of computers found
	 */
	int totalRow(String searchBy);
}
