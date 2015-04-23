package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.ComputerDTO;
import com.excilys.model.Page;

public interface ComputerService {

	List<ComputerDTO> listAll();
	
	ComputerDTO getById(long id);
	
	long create(String name, LocalDateTime introduced, LocalDateTime discontinued, long idCompany);
	
	void delete(long id);
	
	void deleteByCompanyId(long id);
	
	void update(long id, String name, LocalDateTime introduced, LocalDateTime discontinued, long idCompany);

	List<ComputerDTO> getComputersByPage(int offset, int limit, String searchBy, String orderBy, String option);
	
	Page<ComputerDTO> getPage(int currentPage, int limit, String searchBy, String orderBy, String option);
}
