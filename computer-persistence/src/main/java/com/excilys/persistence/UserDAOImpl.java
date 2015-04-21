package com.excilys.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.model.QUserModel;
import com.excilys.model.UserModel;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository("UserDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

	@Override
	public void create(UserModel user) {
		
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			em.persist(user);
			transaction.commit();
			LOGGER.info("userDAO create succeed");
		} catch(Exception e) {
			transaction.rollback();
			LOGGER.info("userDAO create failed");
		}
		em.close();
	}

	@Override
	public UserModel getByLogin(String login) {
		LOGGER.info("UserDAO getByLogin");
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QUserModel user = QUserModel.userModel;
		
		UserModel userModel = query.from(user).where(user.login.eq(login)).uniqueResult(user);
		em.close();
		return userModel;
	}

}
