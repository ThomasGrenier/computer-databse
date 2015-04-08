package com.excilys.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.exception.ConnectionException;
import com.excilys.exception.DAOException;

@Component
public class DAOFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DAOFactory.class);

	private static final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>();


	@Autowired
	BasicDataSource dataSource;

	public Connection getConnection() {
		try {
			if ((CONNECTION.get() == null) || (CONNECTION.get().isClosed())) {
				CONNECTION.set(dataSource.getConnection());
			}
		} catch (SQLException e) {
			LOGGER.error("DAOFactory getConnection failed");
			throw new DAOException(e);
		}
		LOGGER.info("DAOFactory getConnection succeed");
		return CONNECTION.get();
	}

	public void closeConnection() {
		Connection connect = CONNECTION.get();
		if (connect != null) {
			try {
				connect.setAutoCommit(true);
				connect.close();
			} catch (SQLException e) {
				LOGGER.error("DAOFactory closeConnection failed");
				throw new ConnectionException(e);
			}
			CONNECTION.remove();
			LOGGER.info("DAOFactory closeConnection succeed");
		}
	}
}
