package com.excilys.service;

import java.util.List;

import com.excilys.model.CompanyModel;
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

}
