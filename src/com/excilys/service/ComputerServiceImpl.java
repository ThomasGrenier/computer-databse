package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;
import com.excilys.persistence.ComputerDAOImpl;
import com.excilys.persistence.DAOFactory;

public class ComputerServiceImpl implements ComputerService {

	private ComputerDAOImpl computerDao;

	public ComputerServiceImpl() {
		computerDao = (ComputerDAOImpl) DAOFactory.INSTANCE.getComputerDAO();
	}

	@Override
	public List<ComputerModel> listAll() {
		return computerDao.listAll();
	}

	@Override
	public ComputerModel getById(long id) {
		return computerDao.getById(id);
	}

	@Override
	public long create(String name) {
		return computerDao.create(name);
	}

	@Override
	public void delete(long id) {
		computerDao.delete(id);
	}

	@Override
	public void update(long id, String name, LocalDateTime introduced,
			LocalDateTime discontinued, long idCompany) {
		CompanyModel companyModel = new CompanyModel(idCompany, "");
		ComputerModel computerModel = new ComputerModel(id, name, introduced, discontinued, companyModel);
		computerDao.update(computerModel);
	}

}
