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
	public List<CompanyModel> getCompaniesByPage(int offset, int limit, String searchBy, String orderBy, String option) {
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
		page.setTotalResult(companyDao.totalRow(searchBy));
		page.setTotalPages(total);
		page.setOption(option);
		page.setOrderBy(orderBy);
		page.setList(getCompaniesByPage(page.getCurrentPage() * page.getNbResult() - limit, limit, searchBy, orderBy, option));
		return page;
	}

	@Override
	public void delete(long id) {
		companyDao.delete(id);
	}

}
