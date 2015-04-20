package com.excilys.persistence;

import java.util.List;

import com.excilys.model.CompanyModel;

public interface CompanyDAO {

	List<CompanyModel> listAll();
	
	CompanyModel getById(long id);

	List<CompanyModel> getCompaniesByPage(int offset, int limit, String searchBy, String orderBy, String option);
	
	int totalRow(String searchBy);
	
	void delete(long id);
}
