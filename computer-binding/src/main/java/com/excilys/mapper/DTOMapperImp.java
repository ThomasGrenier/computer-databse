package com.excilys.mapper;

import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.model.CompanyDTO;
import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerDTO;
import com.excilys.model.ComputerModel;

// TODO: Auto-generated Javadoc
/**
 * The Class DTOMapperImp.
 */
public class DTOMapperImp implements DTOMapper {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DTOMapperImp.class);

	/* (non-Javadoc)
	 * @see com.excilys.mapper.DTOMapper#computerModelToDTO(com.excilys.model.ComputerModel)
	 */
	@Override
	public ComputerDTO computerModelToDTO(ComputerModel computer) {
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId(computer.getId());
		computerDTO.setName(computer.getName());
		computerDTO.setCompany(companyModelToDTO(computer.getCompany()));
		
		DateTimeFormatter formatter = null;

		if (LocaleContextHolder.getLocale().toString().equals("fr")) {
			formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		} else {
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		}
		
		if (computer.getIntroduced() != null) {
			computerDTO.setIntroduced(computer.getIntroduced().format(formatter));
		}
		if (computer.getDiscontinued() != null) {
			computerDTO.setDiscontinued(computer.getDiscontinued().format(formatter));
		}
		LOGGER.info("computerDTO mapper succeed");
		return computerDTO;
	}

	/* (non-Javadoc)
	 * @see com.excilys.mapper.DTOMapper#companyModelToDTO(com.excilys.model.CompanyModel)
	 */
	@Override
	public CompanyDTO companyModelToDTO(CompanyModel company) {
		CompanyDTO companyDTO = new CompanyDTO();
		if (company != null) {
			companyDTO.setId(company.getId());
			companyDTO.setName(company.getName());
		}
		LOGGER.info("companyDTO mapper succeed");
		return companyDTO;
	}

}
