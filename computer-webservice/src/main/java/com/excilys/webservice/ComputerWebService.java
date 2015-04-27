package com.excilys.webservice;

import java.time.LocalDateTime;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ComputerWebService {

	@WebMethod
	String getCompanies();

	@WebMethod
	String getCompanyById(long id);

	@WebMethod
	String getCompaniesByPage(int offset, int limit, String searchBy, String orderBy, String option);

	@WebMethod
	String getCompanyPage(int currentPage, int limit, String searchBy, String orderBy, String option);

	@WebMethod
	void deleteCompany(long id);

	@WebMethod
	String getComputers();

	@WebMethod
	String getComputerById(long id);

	@WebMethod
	long createComputer(String name, LocalDateTime introduced, LocalDateTime discontinued, long idCompany);

	@WebMethod
	void deleteComputer(long id);

	@WebMethod
	void updateComputer(long id, String name, String introduced, String discontinued, long idCompany);

	@WebMethod
	String getComputersByPage(int offset, int limit, String searchBy, String orderBy, String option);

	@WebMethod
	String getComputerPage(int currentPage, int limit, String searchBy, String orderBy, String option);
	
	@WebMethod
	int getNbComputer();
	
	@WebMethod
	int getNbCompany();
}
