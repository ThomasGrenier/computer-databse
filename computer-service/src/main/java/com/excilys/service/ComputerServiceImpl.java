package com.excilys.service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.excilys.mapper.DTOMapperImp;
import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerDTO;
import com.excilys.model.ComputerModel;
import com.excilys.model.Page;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;

@Service("computerService")
public class ComputerServiceImpl implements ComputerService {

	@Autowired
	@Qualifier("computerDAO")
	ComputerDAO computerDao;

	@Autowired
	@Qualifier("companyDAO")
	CompanyDAO companyDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);

	public ComputerServiceImpl() {
	}

	@Override
	public List<ComputerDTO> listAll() {
		LOGGER.info("ComputerService listAll");
		List<ComputerModel> list = computerDao.listAll();
		List<ComputerDTO> dtos = new LinkedList<ComputerDTO>();
		DTOMapperImp mapper = new DTOMapperImp();
		for (ComputerModel c : list) {
			dtos.add(mapper.computerModelToDTO(c));
		}
		return dtos;
	}

	@Override
	public ComputerDTO getById(long id) {
		LOGGER.info("ComputerService getById");
		return (new DTOMapperImp()).computerModelToDTO(computerDao.getById(id));
	}

	@Override
	public long create(String name, LocalDateTime introduced, LocalDateTime discontinued, long idCompany) {
		LOGGER.info("ComputerService create");
		CompanyModel company = companyDao.getById(idCompany);
		ComputerModel computer = new ComputerModel();
		computer.setName(name);
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		computer.setCompany(company);
		return computerDao.create(computer);
	}

	@Override
	public void delete(long id) {
		LOGGER.info("ComputerService delete");
		computerDao.delete(id);
	}

	@Override
	public void deleteByCompanyId(long id) {
		LOGGER.info("ComputerService deleteByCompanyId");
		computerDao.deleteByCompanyId(id);
	}

	@Override
	public void update(long id, String name, LocalDateTime introduced,
			LocalDateTime discontinued, long idCompany) {
		CompanyModel companyModel = new CompanyModel(idCompany, "");
		ComputerModel computerModel = new ComputerModel(id, name, introduced, discontinued, companyModel);
		LOGGER.info("ComputerService delete");
		computerDao.update(computerModel);
	}

	@Override
	public List<ComputerDTO> getComputersByPage(int offset, int limit, String searchBy, String orderBy, String option) {
		LOGGER.info("ComputerService getComputersByPage");
		List<ComputerModel> list = computerDao.getComputersByPage(offset, limit, searchBy, orderBy, option);
		List<ComputerDTO> dtos = new LinkedList<ComputerDTO>();
		DTOMapperImp mapper = new DTOMapperImp();
		for (ComputerModel c : list) {
			dtos.add(mapper.computerModelToDTO(c));
		}
		return dtos;
	}

	@Override
	public Page<ComputerDTO> getPage(int currentPage, int limit, String searchBy, String orderBy, String option) {
		LOGGER.info("ComputerService getPage");
		int totalRow = computerDao.totalRow(searchBy);
		int total = totalRow / limit;
		if ((totalRow % limit) > 0) {
			total += 1;
		}
		if (currentPage > total) {
			currentPage = total;
		}
		Page<ComputerDTO> page = new Page<ComputerDTO>(currentPage, limit, searchBy);
		page.setTotalResult(totalRow);
		page.setTotalPages(total);
		page.setOption(option);
		page.setOrderBy(orderBy);
		page.setList(getComputersByPage(page.getCurrentPage() * page.getNbResult() - limit, limit, searchBy, orderBy, option));
		return page;
	}

	@Override
	public int totalRow(String searchBy) {
		return computerDao.totalRow(searchBy);
	}

}
