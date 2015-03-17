package com.excilys.persistence;

import java.util.List;

import com.excilys.model.ComputerModel;

public interface ComputerDAO {

	List<ComputerModel> listAll();
	
	ComputerModel getById(long id);
	
	long create(String name);
	
	void update(ComputerModel computer);
	
	void delete(long id);
}
