package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.ComputerModel;

public interface ComputerService {

	List<ComputerModel> listAll();
	
	ComputerModel getById(long id);
	
	long create(String name);
	
	void delete(long id);
	
	void update(long id, String name, LocalDateTime introduced, LocalDateTime discontinued, long idCompany);
}
