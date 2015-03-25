package com.excilys.persistence;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.ComputerModel;

public interface ComputerDAO {

	List<ComputerModel> listAll();
	
	ComputerModel getById(long id);
	
	long create(String name, LocalDateTime introduced, LocalDateTime discontinued, long idCompany);
	
	void update(ComputerModel computer);
	
	void delete(long id);
	
	List<ComputerModel> getComputersByPage(int offset, int limit, String searchBy, String orderBy, String option);
	
	int totalRow(String searchBy);
}
