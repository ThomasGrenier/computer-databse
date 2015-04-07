package com.excilys.service;

import java.util.List;

import com.excilys.model.CompanyDTO;
import com.excilys.model.CompanyModel;
import com.excilys.model.Page;

public interface CompanyService {
	
	List<CompanyDTO> listAll();
	
	CompanyDTO getById(long id);

	List<CompanyDTO> getCompaniesByPage(int offset, int limit, String searchBy, String orderBy, String option);
	
	Page<CompanyDTO> getPage(int currentPage, int limit, String searchBy, String orderBy, String option);
	
	void delete(long id);
}
