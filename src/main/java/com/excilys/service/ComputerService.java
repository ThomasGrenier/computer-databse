package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.ComputerModel;
import com.excilys.model.Page;

public interface ComputerService {

	List<ComputerModel> listAll();
	
	ComputerModel getById(long id);
	
	long create(String name, LocalDateTime introduced, LocalDateTime discontinued, long idCompany);
	
	void delete(long id);
	
	void update(long id, String name, LocalDateTime introduced, LocalDateTime discontinued, long idCompany);

	List<ComputerModel> getComputersByPage(int offset, int limit, String searchBy, String orderBy, String option);
	
	Page<ComputerModel> getPage(int currentPage, int limit, String searchBy, String orderBy, String option);
}
