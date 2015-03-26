package com.excilys.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.exception.ConnectionException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum DAOFactory {
	INSTANCE;

	private ComputerDAO computerDao;

	private CompanyDAO companyDao;
	
	private BoneCP connectionPool;

	private DAOFactory() {
		computerDao = new ComputerDAOImpl();
		companyDao = new CompanyDAOImpl();
		connectionPool = initConnection();
	}
	
	private BoneCP initConnection() {
		BoneCP connection = null;
		Properties configProp = new Properties();
		String config = null;
		if ("TEST".equals(System.getProperty("env"))) {
			config = "MysqlProperties-test.properties";
			try {
				Class.forName("org.h2.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			config = "MysqlProperties.properties";
		}
		try {
			//InputStream ips = new FileInputStream(FILE_NAME + config);  
			InputStream ips = DAOFactory.class.getClassLoader().getResourceAsStream(config);
			configProp.load(ips);
			String url = configProp.getProperty("url");
			String user = configProp.getProperty("user");
			String password = configProp.getProperty("password");
			
			BoneCPConfig BoneCPConfig = new BoneCPConfig();

            /* Mise en place de l'URL, du nom et du mot de passe */

			BoneCPConfig.setJdbcUrl(url);

			BoneCPConfig.setUsername(user);

			BoneCPConfig.setPassword(password);

            /* Paramétrage de la taille du pool */

			BoneCPConfig.setMinConnectionsPerPartition( 5 );

			BoneCPConfig.setMaxConnectionsPerPartition( 10 );

			BoneCPConfig.setPartitionCount( 2 );
            
			connection = new BoneCP(BoneCPConfig);
			//connect = DriverManager.getConnection(url, user, password);
		} catch (IOException e) {
			throw new IllegalStateException("fichier introuvable");
		} catch (SQLException e) {
			throw new ConnectionException(e);
		}
		return connection;
		
	}

	public Connection getConnection() throws SQLException {
		/*Properties configProp = new Properties();
		Connection connect = null;
		String config = null;
		if ("TEST".equals(System.getProperty("env"))) {
			config = "MysqlProperties-test.properties";
			try {
				Class.forName("org.h2.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			config = "MysqlProperties.properties";
		}
		try {
			//InputStream ips = new FileInputStream(FILE_NAME + config);  
			InputStream ips = DAOFactory.class.getClassLoader().getResourceAsStream(config);
			configProp.load(ips);
			String url = configProp.getProperty("url");
			String user = configProp.getProperty("user");
			String password = configProp.getProperty("password");
			connect = DriverManager.getConnection(url, user, password);
		} catch (IOException e) {
			throw new IllegalStateException("fichier introuvable");
		} catch (SQLException e) {
			throw new ConnectionException(e);
		}
		return connect;*/
		return connectionPool.getConnection();
	}

	public void CloseConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new ConnectionException(e);
		}
	}

	public ComputerDAO getComputerDAO() {
		return computerDao;
	}

	public CompanyDAO getCompanyDAO() {
		return companyDao;
	}
}
