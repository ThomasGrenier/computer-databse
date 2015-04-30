package com.excilys.webservice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.utils.Regex;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerWebServiceImpl.
 */
@Component
@WebService(endpointInterface = "com.excilys.webservice.ComputerWebService")
public class ComputerWebServiceImpl implements ComputerWebService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerWebServiceImpl.class);

	/** The company service. */
	@Autowired
	private CompanyService companyService;

	/** The computer service. */
	@Autowired
	private ComputerService computerService;

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebService#getCompanies()
	 */
	@Override
	public String getCompanies() {
		LOGGER.info("ComputerWebService getCompanies");
		return companyService.listAll().toString();
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebService#getCompanyById(long)
	 */
	@Override
	public String getCompanyById(long id) {
		LOGGER.info("ComputerWebService getCompanyById " + id);
		return companyService.getById(id).toString();
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebService#getCompaniesByPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getCompaniesByPage(int offset, int limit, String searchBy,
			String orderBy, String option) {
		LOGGER.info("ComputerWebService getCompaniesByPage offset = " + offset + " limit = " + limit);
		return companyService.getCompaniesByPage(offset, limit, searchBy, orderBy, option).toString();
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebService#getCompanyPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getCompanyPage(int currentPage, int limit, String searchBy,
			String orderBy, String option) {
		LOGGER.info("ComputerWebService getCompanyPage offset = " + currentPage + " limit = " + limit);
		return companyService.getPage(currentPage, limit, searchBy, orderBy, option).toString();
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebService#deleteCompany(long)
	 */
	@Override
	public void deleteCompany(long id) {
		LOGGER.info("ComputerWebService deleteCompany proceed");
		computerService.deleteByCompanyId(id);
		LOGGER.info("ComputerWebService deleteComputerByCompanyId succeed");
		companyService.delete(id);		
		LOGGER.info("ComputerWebService deleteCompany succeed");
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebService#getComputers()
	 */
	@Override
	public String getComputers() {
		LOGGER.info("ComputerWebService getComputers");
		return computerService.listAll().toString();
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebService#getComputerById(long)
	 */
	@Override
	public String getComputerById(long id) {
		LOGGER.info("ComputerWebService getComputerById " + id);
		return computerService.getById(id).toString();
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebService#createComputer(java.lang.String, java.time.LocalDateTime, java.time.LocalDateTime, long)
	 */
	@Override
	public long createComputer(String name, LocalDateTime introduced,
			LocalDateTime discontinued, long idCompany) {
		LOGGER.info("ComputerWebService createComputer " + name);
		
		return computerService.create(name, introduced, discontinued, idCompany);
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebService#deleteComputer(long)
	 */
	@Override
	public void deleteComputer(long id) {
		LOGGER.info("ComputerWebService deleteComputer");
		computerService.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebService#updateComputer(long, java.lang.String, java.lang.String, java.lang.String, long)
	 */
	@Override
	public void updateComputer(long id, String name, String introduced,
			String discontinued, long idCompany) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDateTime intro = null;
		if (!introduced.equals("")) {
			if (Pattern.matches(Regex.DATE_FORMAT_EN.getRegex(), introduced.trim())) {
				intro = LocalDateTime.parse(introduced, formatter);
			}
		}
		LocalDateTime disco = null;
		if (!discontinued.equals("")) {
			if (Pattern.matches(Regex.DATE_FORMAT_EN.getRegex(), discontinued.trim())) {
				disco = LocalDateTime.parse(discontinued, formatter);
			}
		}
		LOGGER.info("ComputerWebService updateComputer");
		computerService.update(id, name, intro, disco, idCompany);
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebService#getComputersByPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getComputersByPage(int offset, int limit, String searchBy,
			String orderBy, String option) {
		LOGGER.info("ComputerWebService getComputersByPage");
		return computerService.getComputersByPage(offset, limit, searchBy, orderBy, option).toString();
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebService#getComputerPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getComputerPage(int currentPage, int limit, String searchBy,
			String orderBy, String option) {
		LOGGER.info("ComputerWebService getComputerPage");
		return computerService.getPage(currentPage, limit, searchBy, orderBy, option).toString();
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebService#getNbComputer()
	 */
	@Override
	public int getNbComputer() {
		return computerService.totalRow("");
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebService#getNbCompany()
	 */
	@Override
	public int getNbCompany() {
		return companyService.totalRow("");
	}

}
