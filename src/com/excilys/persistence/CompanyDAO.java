package com.excilys.persistence;

import java.util.List;

import com.excilys.model.CompanyModel;

public interface CompanyDAO {

	List<CompanyModel> listAll();
	
	CompanyModel getById(long id);
}
