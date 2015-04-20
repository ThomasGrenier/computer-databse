package com.excilys.persistence;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.CompanyModel;
import com.excilys.model.QCompanyModel;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.path.PathBuilder;

@Repository("companyDAO")
public class CompanyDAOImpl implements CompanyDAO {
	
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
		em.close();
		return companies;
	}

	@Override
	public CompanyModel getById(long id) {
		LOGGER.info("companyDao getById");
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QCompanyModel company = QCompanyModel.companyModel;
		
		CompanyModel companyModel = query.from(company).where(company.id.eq(id)).uniqueResult(company);
		em.close();
		return companyModel;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CompanyModel> getCompaniesByPage(int offset, int limit, String searchBy, String orderBy, String option) {
		
		LOGGER.info("companyDao getCompaniesByPage");
		
		if (offset < 0) {
			offset = 0;
		}

		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QCompanyModel company = QCompanyModel.companyModel;
		
		query = query.from(company)
				.where(company.name.contains(searchBy))
				.limit(limit)
				.offset(offset);

		if (!orderBy.isEmpty()) {
			PathBuilder orderByExpression = new PathBuilder(CompanyModel.class, "companyModel");

			query = query.orderBy(new OrderSpecifier(option.equals("DESC") ? com.mysema.query.types.Order.DESC
					: com.mysema.query.types.Order.ASC, orderByExpression.get(orderBy)));
		}

		List<CompanyModel> companies = query.list(company);
		em.close();
		LOGGER.info("companyDAO getCompaniesByPage succeed");

		return companies;
	}

	@Override
	public int totalRow(String searchBy) {
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QCompanyModel company = QCompanyModel.companyModel;
		
		long nbRow = query.from(company)
				.where(company.name.contains(searchBy)).count();

		LOGGER.info("companyDao totalRow succeed");
		em.close();
		return (int) nbRow;
	}

	@Override
	@Transactional
	public void delete(long id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		QCompanyModel company = QCompanyModel.companyModel;

		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();

			JPADeleteClause jpa = new JPADeleteClause(em, company).where(company.id.eq(id));
			jpa.execute();
			
			transaction.commit();
			LOGGER.info("companyDao deleteByCompanyId succeed");
		} catch (Exception e) {
			transaction.rollback();
			LOGGER.info("companyDao deleteByCompanyId failed");
		}
		em.close();
	}
}
