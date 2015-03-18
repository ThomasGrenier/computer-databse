package com.excilys.service;

import java.util.List;

import com.excilys.model.CompanyModel;

public interface CompanyService {
	
	List<CompanyModel> listAll();
	
	CompanyModel getById(long id);
}
