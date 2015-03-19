package com.excilys.service;

import java.util.List;

import com.excilys.model.CompanyModel;
import com.excilys.model.Page;

public interface CompanyService {
	
	List<CompanyModel> listAll();
	
	CompanyModel getById(long id);

	List<CompanyModel> getCompaniesByPage(int offset, int limit);
	
	Page<CompanyModel> getPage(int currentPage, int limit);
}
