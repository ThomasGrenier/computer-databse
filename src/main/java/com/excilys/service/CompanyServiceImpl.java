package com.excilys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.exception.DAOException;
import com.excilys.model.CompanyModel;
import com.excilys.model.Page;
import com.excilys.persistence.CompanyDAOImpl;
import com.excilys.persistence.DAOFactory;

public class CompanyServiceImpl implements CompanyService {
	
	private CompanyDAOImpl companyDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	public CompanyServiceImpl() {
		companyDao = (CompanyDAOImpl) DAOFactory.INSTANCE.getCompanyDAO();
	}

	@Override
	public List<CompanyModel> listAll() {
		LOGGER.info("CompanyService listAll");
		return companyDao.listAll();
	}

	@Override
	public CompanyModel getById(long id) {
		LOGGER.info("CompanyService getById");
		return companyDao.getById(id);
	}

	@Override
	public List<CompanyModel> getCompaniesByPage(int offset, int limit, String searchBy, String orderBy, String option) {
		LOGGER.info("CompanyService getCompaniesByPage");
		return companyDao.getCompaniesByPage(offset, limit, searchBy, orderBy, option);
	}

	@Override
	public Page<CompanyModel> getPage(int currentPage, int limit, String searchBy, String orderBy, String option) {
		int total = companyDao.totalRow(searchBy) / limit;
		if ((companyDao.totalRow(searchBy) % limit) > 0) {
			total += 1;
		}
		if (currentPage > total) {
			currentPage = total;
		}
		Page<CompanyModel> page = new Page<CompanyModel>(currentPage, limit, searchBy);
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
		DAOFactory.INSTANCE.startTransaction();
		try {
			companyDao.delete(id);
			DAOFactory.INSTANCE.commit();
		} catch (DAOException e) {
			DAOFactory.INSTANCE.rollback();
		}
		DAOFactory.INSTANCE.endTransaction();
		DAOFactory.INSTANCE.CloseConnection();
	}

}
