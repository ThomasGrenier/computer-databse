package com.excilys.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.exception.DAOException;
import com.excilys.mapper.DTOMapper;
import com.excilys.model.CompanyDTO;
import com.excilys.model.CompanyModel;
import com.excilys.model.Page;
import com.excilys.persistence.CompanyDAOImpl;
import com.excilys.persistence.DAOFactory;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	CompanyDAOImpl companyDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	public CompanyServiceImpl() {
		
	}

	@Override
	public List<CompanyDTO> listAll() {
		LOGGER.info("CompanyService listAll");
		List<CompanyModel> list = companyDao.listAll();
		List<CompanyDTO> dtos = new LinkedList<CompanyDTO>();
		DTOMapper mapper = new DTOMapper();
		for (CompanyModel c : list) {
			dtos.add(mapper.companyModelToDTO(c));
		}
		return dtos;
	}

	@Override
	public CompanyDTO getById(long id) {
		LOGGER.info("CompanyService getById");
		return (new DTOMapper()).companyModelToDTO(companyDao.getById(id));
	}

	@Override
	public List<CompanyDTO> getCompaniesByPage(int offset, int limit, String searchBy, String orderBy, String option) {
		LOGGER.info("CompanyService getCompaniesByPage");
		List<CompanyModel> list = companyDao.getCompaniesByPage(offset, limit, searchBy, orderBy, option);
		List<CompanyDTO> dtos = new LinkedList<CompanyDTO>();
		DTOMapper mapper = new DTOMapper();
		for (CompanyModel c : list) {
			dtos.add(mapper.companyModelToDTO(c));
		}
		return dtos;
	}

	@Override
	public Page<CompanyDTO> getPage(int currentPage, int limit, String searchBy, String orderBy, String option) {
		int total = companyDao.totalRow(searchBy) / limit;
		if ((companyDao.totalRow(searchBy) % limit) > 0) {
			total += 1;
		}
		if (currentPage > total) {
			currentPage = total;
		}
		Page<CompanyDTO> page = new Page<CompanyDTO>(currentPage, limit, searchBy);
		LOGGER.info("CompanyService getPage");
		page.setTotalResult(companyDao.totalRow(searchBy));
		page.setTotalPages(total);
		page.setOption(option);
		page.setOrderBy(orderBy);
		page.setList(getCompaniesByPage(page.getCurrentPage() * page.getNbResult() - limit, limit, searchBy, orderBy, option));
		return page;
	}

	@Override
	public void delete(long id) {
		LOGGER.info("CompanyService delete");
		companyDao.delete(id);
	}

}
