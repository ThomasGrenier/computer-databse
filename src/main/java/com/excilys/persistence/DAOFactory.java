package com.excilys.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.exception.ConnectionException;
import com.excilys.exception.DAOException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

@Component
public class DAOFactory {
	//	INSTANCE;
	//
	//	private static final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>() {
	//		@Override
	//		protected Connection initialValue() {
	//			try {
	//				return DAOFactory.INSTANCE.getConnectionPool().getConnection();
	//			} catch (SQLException e) {
	//				// TODO Auto-generated catch block
	//				throw new ConnectionException(e);
	//			}
	//		}
	//	};
	//
	//	private ComputerDAO computerDao;
	//
	//	private CompanyDAO companyDao;
	//
	//	private BoneCP connectionPool;
	//
	//	private static final Logger LOGGER = LoggerFactory.getLogger(DAOFactory.class);
	//
	//	private DAOFactory() {
	//		computerDao = new ComputerDAOImpl();
	//		companyDao = new CompanyDAOImpl();
	//		connectionPool = initConnection();
	//	}
	//
	//	private BoneCP initConnection() {
	//		BoneCP connection = null;
	//		Properties configProp = new Properties();
	//		String config = null;
	//		if ("TEST".equals(System.getProperty("env"))) {
	//			config = "MysqlProperties-test.properties";
	//			try {
	//				Class.forName("org.h2.Driver");
	//			} catch (ClassNotFoundException e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			} 
	//		} else {
	//			try {
	//				Class.forName("com.mysql.jdbc.Driver").newInstance();
	//			} catch (InstantiationException e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			} catch (IllegalAccessException e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			} catch (ClassNotFoundException e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
	//			config = "MysqlProperties.properties";
	//		}
	//		try {
	//			//InputStream ips = new FileInputStream(FILE_NAME + config);  
	//			InputStream ips = DAOFactory.class.getClassLoader().getResourceAsStream(config);
	//			configProp.load(ips);
	//			String url = configProp.getProperty("url");
	//			String user = configProp.getProperty("user");
	//			String password = configProp.getProperty("password");
	//
	//			BoneCPConfig BoneCPConfig = new BoneCPConfig();
	//
	//			/* Mise en place de l'URL, du nom et du mot de passe */
	//
	//			BoneCPConfig.setJdbcUrl(url);
	//
	//			BoneCPConfig.setUsername(user);
	//
	//			BoneCPConfig.setPassword(password);
	//
	//			/* Paramétrage de la taille du pool */
	//
	//			BoneCPConfig.setMinConnectionsPerPartition( 5 );
	//
	//			BoneCPConfig.setMaxConnectionsPerPartition( 10 );
	//
	//			BoneCPConfig.setPartitionCount( 2 );
	//
	//			connection = new BoneCP(BoneCPConfig);
	//			//connect = DriverManager.getConnection(url, user, password);
	//		} catch (IOException e) {
	//			throw new IllegalStateException("fichier introuvable");
	//		} catch (SQLException e) {
	//			throw new ConnectionException(e);
	//		}
	//		return connection;
	//
	//	}
	//
	//	public BoneCP getConnectionPool() {
	//		return connectionPool;
	//	}
	//
	//	public Connection getConnection() {
	//		return CONNECTION.get();
	//	}
	//
	//	public void CloseConnection() {
	//		Connection connect = CONNECTION.get();
	//		if (connect != null) {
	//			try {
	//				connect.setAutoCommit(true);
	//				connect.close();
	//			} catch (SQLException e) {
	//				LOGGER.error("DAOFactory closeConnection failed");
	//				throw new ConnectionException(e);
	//			}
	//			CONNECTION.remove();
	//			LOGGER.info("DAOFactory closeConnection succeed");
	//		}
	//	}
	//
	//	public void startTransaction() {
	//		final Connection connection = CONNECTION.get();
	//		if (connection != null) {
	//			try {
	//				connection.setAutoCommit(false);
	//				LOGGER.info("DAOFactory startTransaction succeed");
	//			} catch (SQLException e) {
	//				LOGGER.error("DAOFactory startTransaction succeed");
	//				throw new ConnectionException(e);
	//			}
	//		}
	//	}
	//	
	//	public void endTransaction() {
	//		final Connection connection = CONNECTION.get();
	//		if (connection != null) {
	//			try {
	//				connection.setAutoCommit(true);
	//				LOGGER.info("DAOFactory endTransaction succeed");
	//			} catch (SQLException e) {
	//				LOGGER.error("DAOFactory endTransaction succeed");
	//				throw new ConnectionException(e);
	//			}
	//		}
	//	}
	//
	//	public void commit() {
	//		final Connection connection = CONNECTION.get();
	//		if (connection != null) {
	//			try {
	//				connection.commit();
	//				LOGGER.info("DAOFactory commit succeed");
	//			} catch (SQLException e) {
	//				LOGGER.error("DAOFactory commit succeed");
	//				throw new ConnectionException(e);
	//			}
	//		}
	//	}
	//
	//	public void rollback() {
	//		final Connection connection = CONNECTION.get();
	//		if (connection != null) {
	//			try {
	//				connection.rollback();
	//				LOGGER.info("DAOFactory rollback succeed");
	//			} catch (SQLException e) {
	//				LOGGER.error("DAOFactory rollback succeed");
	//				throw new ConnectionException(e);
	//			}
	//		}
	//	}
	//
	//	public ComputerDAO getComputerDAO() {
	//		return computerDao;
	//	}
	//
	//	public CompanyDAO getCompanyDAO() {
	//		return companyDao;
	//	}
	
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
