package com.excilys.service;

import java.util.List;

import com.excilys.model.CompanyModel;
import com.excilys.model.Page;
import com.excilys.persistence.CompanyDAOImpl;
import com.excilys.persistence.DAOFactory;

public class CompanyServiceImpl implements CompanyService {
	
	private CompanyDAOImpl companyDao;
	
	public CompanyServiceImpl() {
		companyDao = (CompanyDAOImpl) DAOFactory.INSTANCE.getCompanyDAO();
	}

	@Override
	public List<CompanyModel> listAll() {
		return companyDao.listAll();
	}

	@Override
	public CompanyModel getById(long id) {
		return companyDao.getById(id);
	}

	@Override
	public List<CompanyModel> getCompaniesByPage(int offset, int limit) {
		return companyDao.getCompaniesByPage(offset, limit);
	}

	@Override
	public Page<CompanyModel> getPage(int currentPage, int limit) {
		int total = companyDao.totalRow() / limit;
		if ((companyDao.totalRow() % limit) > 0) {
			total += 1;
		}
		if (currentPage > total) {
			currentPage = total;
		}
		Page<CompanyModel> page = new Page<CompanyModel>(currentPage, limit);
		page.setTotalPages(total);
		page.setList(getCompaniesByPage(page.getCurrentPage() * page.getNbResult() - limit, limit));
		return page;
	}

}
