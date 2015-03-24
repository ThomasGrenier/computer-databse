package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;
import com.excilys.model.Page;
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

	@Override
	public List<ComputerModel> getComputersByPage(int offset, int limit) {
		return computerDao.getComputersByPage(offset, limit);
	}

	@Override
	public Page<ComputerModel> getPage(int currentPage, int limit) {
		int total = computerDao.totalRow() / limit;
		if ((computerDao.totalRow() % limit) > 0) {
			total += 1;
		}
		if (currentPage > total) {
			currentPage = total;
		}
		Page<ComputerModel> page = new Page<ComputerModel>(currentPage, limit);
		page.setTotalResult(computerDao.totalRow());
		page.setTotalPages(total);
		page.setList(getComputersByPage(page.getCurrentPage() * page.getNbResult() - limit, limit));
		return page;
	}

}
