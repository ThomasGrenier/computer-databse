package com.excilys.mapper;

import com.excilys.model.CompanyDTO;
import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerDTO;
import com.excilys.model.ComputerModel;

public class DTOMapper implements GenericDTOMapper {

	@Override
	public ComputerDTO computerModelToDTO(ComputerModel computer) {
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId(computer.getId());
		computerDTO.setName(computer.getName());
		computerDTO.setCompany(companyModelToDTO(computer.getCompany()));
		if (computer.getIntroduced() != null) {
			computerDTO.setIntroduced(computer.getIntroduced().toString().replaceAll("T", " "));
		}
		if (computer.getDiscontinued() != null) {
			computerDTO.setDiscontinued(computer.getDiscontinued().toString().replaceAll("T", " "));
		}
		return computerDTO;
	}

	@Override
	public CompanyDTO companyModelToDTO(CompanyModel company) {
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(company.getId());
		companyDTO.setName(company.getName());
		return companyDTO;
	}

}
