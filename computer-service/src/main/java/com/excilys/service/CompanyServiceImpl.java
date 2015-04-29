package com.excilys.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.mapper.DTOMapperImp;
import com.excilys.model.CompanyDTO;
import com.excilys.model.CompanyModel;
import com.excilys.model.Page;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyServiceImpl.
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
	
	/** The company dao. */
	@Autowired
	@Qualifier("companyDAO")
	CompanyDAO companyDao;
	
	/** The computer dao. */
	@Autowired
	@Qualifier("computerDAO")
	ComputerDAO computerDao;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	/**
	 * Instantiates a new company service impl.
	 */
	public CompanyServiceImpl() {
		
	}

	/* (non-Javadoc)
	 * @see com.excilys.service.CompanyService#listAll()
	 */
	@Override
	public List<CompanyDTO> listAll() {
		LOGGER.info("CompanyService listAll");
		List<CompanyModel> list = companyDao.listAll();
		List<CompanyDTO> dtos = new LinkedList<CompanyDTO>();
		DTOMapperImp mapper = new DTOMapperImp();
		for (CompanyModel c : list) {
			dtos.add(mapper.companyModelToDTO(c));
		}
		return dtos;
	}

	/* (non-Javadoc)
	 * @see com.excilys.service.CompanyService#getById(long)
	 */
	@Override
	public CompanyDTO getById(long id) {
		LOGGER.info("CompanyService getById");
		return (new DTOMapperImp()).companyModelToDTO(companyDao.getById(id));
	}

	/* (non-Javadoc)
	 * @see com.excilys.service.CompanyService#getCompaniesByPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<CompanyDTO> getCompaniesByPage(int offset, int limit, String searchBy, String orderBy, String option) {
		LOGGER.info("CompanyService getCompaniesByPage");
		List<CompanyModel> list = companyDao.getCompaniesByPage(offset, limit, searchBy, orderBy, option);
		List<CompanyDTO> dtos = new LinkedList<CompanyDTO>();
		DTOMapperImp mapper = new DTOMapperImp();
		for (CompanyModel c : list) {
			dtos.add(mapper.companyModelToDTO(c));
		}
		return dtos;
	}

	/* (non-Javadoc)
	 * @see com.excilys.service.CompanyService#getPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see com.excilys.service.CompanyService#delete(long)
	 */
	@Override
	@Transactional
	public void delete(long id) {
		LOGGER.info("CompanyService delete");
		computerDao.deleteByCompanyId(id);
		LOGGER.info("CompanyService deleteByCompanyId succeed");
		companyDao.delete(id);
		LOGGER.info("CompanyService delete succeed");
	}

	/* (non-Javadoc)
	 * @see com.excilys.service.CompanyService#totalRow(java.lang.String)
	 */
	@Override
	public int totalRow(String searchBy) {
		return companyDao.totalRow(searchBy);
	}

}
