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

@Component
@WebService(endpointInterface = "com.excilys.webservice.ComputerWebService")
public class ComputerWebServiceImpl implements ComputerWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerWebServiceImpl.class);

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ComputerService computerService;

	@Override
	public String getCompanies() {
		LOGGER.info("ComputerWebService getCompanies");
		return companyService.listAll().toString();
	}

	@Override
	public String getCompanyById(long id) {
		LOGGER.info("ComputerWebService getCompanyById " + id);
		return companyService.getById(id).toString();
	}

	@Override
	public String getCompaniesByPage(int offset, int limit, String searchBy,
			String orderBy, String option) {
		LOGGER.info("ComputerWebService getCompaniesByPage offset = " + offset + " limit = " + limit);
		return companyService.getCompaniesByPage(offset, limit, searchBy, orderBy, option).toString();
	}

	@Override
	public String getCompanyPage(int currentPage, int limit, String searchBy,
			String orderBy, String option) {
		LOGGER.info("ComputerWebService getCompanyPage offset = " + currentPage + " limit = " + limit);
		return companyService.getPage(currentPage, limit, searchBy, orderBy, option).toString();
	}

	@Override
	public void deleteCompany(long id) {
		LOGGER.info("ComputerWebService deleteCompany proceed");
		computerService.deleteByCompanyId(id);
		LOGGER.info("ComputerWebService deleteComputerByCompanyId succeed");
		companyService.delete(id);		
		LOGGER.info("ComputerWebService deleteCompany succeed");
	}

	@Override
	public String getComputers() {
		LOGGER.info("ComputerWebService getComputers");
		return computerService.listAll().toString();
	}

	@Override
	public String getComputerById(long id) {
		LOGGER.info("ComputerWebService getComputerById " + id);
		return computerService.getById(id).toString();
	}

	@Override
	public long createComputer(String name, LocalDateTime introduced,
			LocalDateTime discontinued, long idCompany) {
		LOGGER.info("ComputerWebService createComputer " + name);
		
		return computerService.create(name, introduced, discontinued, idCompany);
	}

	@Override
	public void deleteComputer(long id) {
		LOGGER.info("ComputerWebService deleteComputer");
		computerService.delete(id);
	}

	@Override
	public void updateComputer(long id, String name, LocalDateTime introduced,
			LocalDateTime discontinued, long idCompany) {
		LOGGER.info("ComputerWebService updateComputer");
		computerService.update(id, name, introduced, discontinued, idCompany);
	}

	@Override
	public String getComputersByPage(int offset, int limit, String searchBy,
			String orderBy, String option) {
		LOGGER.info("ComputerWebService getComputersByPage");
		return computerService.getComputersByPage(offset, limit, searchBy, orderBy, option).toString();
	}

	@Override
	public String getComputerPage(int currentPage, int limit, String searchBy,
			String orderBy, String option) {
		LOGGER.info("ComputerWebService getComputerPage");
		return computerService.getPage(currentPage, limit, searchBy, orderBy, option).toString();
	}

}
