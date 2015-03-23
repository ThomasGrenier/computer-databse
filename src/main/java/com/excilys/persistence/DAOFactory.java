package com.excilys.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.exception.ConnectionException;

public enum DAOFactory {
	INSTANCE;

	private ComputerDAO computerDao;

	private CompanyDAO companyDao;

	private DAOFactory() {
		computerDao = new ComputerDAOImpl();
		companyDao = new CompanyDAOImpl();
	}

	public Connection getConnection() {
		Properties configProp = new Properties();
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
		return connect;
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
