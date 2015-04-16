package com.excilys.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.CompanyModel;
import com.excilys.model.QCompanyModel;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository("companyDAO")
public class CompanyDAOImpl implements CompanyDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImpl.class);

	@Override
	public List<CompanyModel> listAll() {
		LOGGER.info("companyDao listAll");
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QCompanyModel company = QCompanyModel.companyModel;

		List<CompanyModel> companies = query.from(company).list(company);
		return companies;
	}

	@Override
	public CompanyModel getById(long id) {
		LOGGER.info("companyDao getById");
		return jdbcTemplate.queryForObject("SELECT * FROM company WHERE id =?", new Object[] {id}, new CompanyMapper());
	}


	public List<CompanyModel> getCompaniesByPage(int offset, int limit, String searchBy, String orderBy, String option) {
		String query = "SELECT * FROM company";
		if (!searchBy.isEmpty()) {
			query += " WHERE company.name LIKE '%" + searchBy + "%'";
		}
		if (!orderBy.isEmpty()) {
			query += " ORDER BY company." + orderBy;
		}
		if (!option.isEmpty()) {
			query += " " + option;
		}
		query += " limit ? offset ?";

		LOGGER.info("companyDao getCompaniesByPage");
		return jdbcTemplate.query(query,  new Object[] {limit, offset}, new CompanyMapper());
	}

	@Override
	public int totalRow(String searchBy) {
		String query = "SELECT count(*) FROM company";
		if (!searchBy.isEmpty()) {
			query += " WHERE company.name LIKE '%" + searchBy + "%'";
		}

		LOGGER.info("companyDao totalRow");
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

	@Override
	public void delete(long id) {
		jdbcTemplate.update("DELETE FROM company WHERE company.id=?", new Object[] {id});
		LOGGER.info("companyDao delete succeed");
	}
}
