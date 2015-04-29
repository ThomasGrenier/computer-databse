package com.excilys.persistence;

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
import com.excilys.model.ComputerModel;
import com.excilys.model.QCompanyModel;
import com.excilys.model.QComputerModel;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.path.PathBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDAOImpl.
 */
@Repository("computerDAO")
public class ComputerDAOImpl implements ComputerDAO {

	/** The entity manager factory. */
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDAO#listAll()
	 */
	@Override
	public List<ComputerModel> listAll() {
		LOGGER.info("computerDAO listAll");
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QComputerModel computer = QComputerModel.computerModel;
		QCompanyModel company = QCompanyModel.companyModel;

		List<ComputerModel> computers = query.from(computer)
				.leftJoin(computer.company, company)
				.list(computer);
		em.close();
		return computers;
	}

	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDAO#getById(long)
	 */
	@Override
	public ComputerModel getById(long id) {
		
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QComputerModel computer = QComputerModel.computerModel;
		QCompanyModel company = QCompanyModel.companyModel;

		ComputerModel computers = query.from(computer)
				.leftJoin(computer.company, company)
				.where(computer.id.eq(id))
				.uniqueResult(computer);
		LOGGER.info("computerDAO getById");
		em.close();
		return computers;
	}

	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDAO#create(com.excilys.model.ComputerModel)
	 */
	@Override
	public long create(ComputerModel computer) {

		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			em.persist(computer);
			transaction.commit();
			LOGGER.info("computerDAO create succeed");
		} catch(Exception e) {
			transaction.rollback();
			LOGGER.info("computerDAO create failed");
		}
		em.close();

		return 1;
	}

	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDAO#update(com.excilys.model.ComputerModel)
	 */
	@Override
	@Transactional
	public void update(ComputerModel computer) {
		EntityManager em = entityManagerFactory.createEntityManager();
		QComputerModel computerm = QComputerModel.computerModel;

		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();

			JPAUpdateClause jpa = new JPAUpdateClause(em, computerm)
			.where(computerm.id.eq(computer.getId()));
			if ((computer.getName() != null) && (!computer.getName().equals(""))) {
				jpa.set(computerm.name, computer.getName());
			}
			if (computer.getIntroduced() != null) {
				jpa.set(computerm.introduced, computer.getIntroduced());
			}
			if (computer.getDiscontinued() != null) {
				jpa.set(computerm.discontinued, computer.getDiscontinued());
			}
			if (computer.getCompany().getId() > 0) {
				jpa.set(computerm.company, computer.getCompany());
			}
			jpa.execute();
			
			transaction.commit();
			LOGGER.info("computerDAO update succeed");
		} catch (Exception e) {
			transaction.rollback();
			LOGGER.info("computerDAO update failed");
		}
		em.close();
	}

	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDAO#delete(long)
	 */
	@Override
	@Transactional
	public void delete(long id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		QComputerModel computer = QComputerModel.computerModel;

		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();

			JPADeleteClause jpa = new JPADeleteClause(em, computer).where(computer.id.eq(id));
			jpa.execute();
			
			transaction.commit();
			LOGGER.info("computerDAO delete succeed");
		} catch (Exception e) {
			transaction.rollback();
			LOGGER.info("computerDAO delete failed");
		}
		em.close();
	}

	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDAO#deleteByCompanyId(long)
	 */
	@Transactional
	@Override
	public void deleteByCompanyId(long id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		QComputerModel computer = QComputerModel.computerModel;

		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();

			JPADeleteClause jpa = new JPADeleteClause(em, computer).where(computer.company.id.eq(id));
			jpa.execute();
			
			transaction.commit();
			LOGGER.info("computerDAO deleteByCompanyId succeed");
		} catch (Exception e) {
			transaction.rollback();
			LOGGER.info("computerDAO deleteByCompanyId failed");
		}
		em.close();
	}

	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDAO#getComputersByPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<ComputerModel> getComputersByPage(int offset, int limit, String searchBy, String orderBy, String option) {
		if (offset < 0) {
			offset = 0;
		}

		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QComputerModel computer = QComputerModel.computerModel;
		QCompanyModel company = QCompanyModel.companyModel;
		
		query = query.from(computer).leftJoin(computer.company, company)
				.where(computer.name.contains(searchBy).or(company.name.contains(searchBy)))
				.limit(limit)
				.offset(offset);

		if (!orderBy.isEmpty()) {
			PathBuilder orderByExpression = null;
			if (!orderBy.contains("_")) {
				orderByExpression = new PathBuilder(ComputerModel.class, "computerModel");
			} else {
				orderByExpression = new PathBuilder(CompanyModel.class, "companyModel");
				orderBy = orderBy.split("_")[1];
			}

			query = query.orderBy(new OrderSpecifier(option.equals("DESC") ? com.mysema.query.types.Order.DESC
					: com.mysema.query.types.Order.ASC, orderByExpression.get(orderBy)));
		}

		List<ComputerModel> computers = query.list(computer);
		em.close();
		LOGGER.info("computerDAO getComputersByPage succeed");

		return computers;
	}

	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDAO#totalRow(java.lang.String)
	 */
	@Override
	public int totalRow(String searchBy) {
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QComputerModel computer = QComputerModel.computerModel;
		QCompanyModel company = QCompanyModel.companyModel;
		
		long nbRow = query.from(computer).leftJoin(computer.company, company)
				.where(computer.name.contains(searchBy).or(company.name.contains(searchBy))).count();

		em.close();
		return (int) nbRow;
	}

}
