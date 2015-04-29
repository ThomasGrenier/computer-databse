package com.excilys.webservice;

import java.time.LocalDateTime;

import javax.jws.WebMethod;
import javax.jws.WebService;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComputerWebService.
 */
@WebService
public interface ComputerWebService {

	/**
	 * Gets the companies.
	 *
	 * @return the companies
	 */
	@WebMethod
	String getCompanies();

	/**
	 * Gets the company by id.
	 *
	 * @param id the id of the company
	 * @return the company by id
	 */
	@WebMethod
	String getCompanyById(long id);

	/**
	 * Gets the companies by page.
	 *
	 * @param offset the offset
	 * @param limit the number of companies
	 * @param searchBy the search term
	 * @param orderBy the order attribute
	 * @param option the direction of the order (ASC or DESC)
	 * @return the companies by page
	 */
	@WebMethod
	String getCompaniesByPage(int offset, int limit, String searchBy, String orderBy, String option);

	/**
	 * Gets the company page.
	 *
	 * @param currentPage the current page
	 * @param limit the number of companies
	 * @param searchBy the search term
	 * @param orderBy the order attribute
	 * @param option the direction of the order (ASC or DESC)
	 * @return the company page
	 */
	@WebMethod
	String getCompanyPage(int currentPage, int limit, String searchBy, String orderBy, String option);

	/**
	 * Delete company.
	 *
	 * @param id the id of the company
	 */
	@WebMethod
	void deleteCompany(long id);

	/**
	 * Gets the computers.
	 *
	 * @return the computers
	 */
	@WebMethod
	String getComputers();

	/**
	 * Gets the computer by id.
	 *
	 * @param id the id of the computer
	 * @return the computer by id
	 */
	@WebMethod
	String getComputerById(long id);

	/**
	 * Creates the computer.
	 *
	 * @param name the name of the computer
	 * @param introduced the introduced date
	 * @param discontinued the discontinued date
	 * @param idCompany the id company
	 * @return the long
	 */
	@WebMethod
	long createComputer(String name, LocalDateTime introduced, LocalDateTime discontinued, long idCompany);

	/**
	 * Delete computer.
	 *
	 * @param id the id of the computer
	 */
	@WebMethod
	void deleteComputer(long id);

	/**
	 * Update computer.
	 *
	 * @param id the id of the computer
	 * @param name the name of the computer
	 * @param introduced the introduced date of the computer
	 * @param discontinued the discontinued date of the computer
	 * @param idCompany the id company of the computer
	 */
	@WebMethod
	void updateComputer(long id, String name, String introduced, String discontinued, long idCompany);

	/**
	 * Gets the computers by page.
	 *
	 * @param offset the offset
	 * @param limit the number of computers
	 * @param searchBy the search term
	 * @param orderBy the order attribute
	 * @param option the direction of the order (ASC or DESC)
	 * @return the computers by page
	 */
	@WebMethod
	String getComputersByPage(int offset, int limit, String searchBy, String orderBy, String option);

	/**
	 * Gets the computer page.
	 *
	 * @param currentPage the current page
	 * @param limit the number of computers
	 * @param searchBy the search term
	 * @param orderBy the order attribute
	 * @param option the direction of the order (ASC or DESC)
	 * @return the computer page
	 */
	@WebMethod
	String getComputerPage(int currentPage, int limit, String searchBy, String orderBy, String option);
	
	/**
	 * Gets the number of computer.
	 *
	 * @return the number of computer
	 */
	@WebMethod
	int getNbComputer();
	
	/**
	 * Gets the number of company.
	 *
	 * @return the number of company
	 */
	@WebMethod
	int getNbCompany();
}

