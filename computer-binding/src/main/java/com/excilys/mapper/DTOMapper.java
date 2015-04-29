package com.excilys.mapper;

import com.excilys.model.CompanyDTO;
import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerDTO;
import com.excilys.model.ComputerModel;

public interface DTOMapper {

	public ComputerDTO computerModelToDTO(ComputerModel computer);
	
	public CompanyDTO companyModelToDTO(CompanyModel company);
}
