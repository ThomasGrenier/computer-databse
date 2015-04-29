package com.excilys.mapper;

import com.excilys.model.CompanyDTO;
import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerDTO;
import com.excilys.model.ComputerModel;

// TODO: Auto-generated Javadoc
/**
 * The Interface DTOMapper.
 */
public interface DTOMapper {

	/**
	 * Computer model to dto.
	 *
	 * @param computer, the computer
	 * @return the computer dto
	 */
	public ComputerDTO computerModelToDTO(ComputerModel computer);
	
	/**
	 * Company model to dto.
	 *
	 * @param company, the company
	 * @return the company dto
	 */
	public CompanyDTO companyModelToDTO(CompanyModel company);
}
