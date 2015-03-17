package com.excilys.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public enum DAOFactory {
	INSTANCE;

	private static final String FILE_NAME = "ressources/MysqlProperties.properties";

	private ComputerDAO computerDao;

	private CompanyDAO companyDao;

	private DAOFactory() {
		computerDao = new ComputerDAOImpl();
		companyDao = new CompanyDAOImpl();
	}

	public Connection getConnection() {
		Properties configProp = new Properties();
		Connection connect = null;
		try {
			InputStream ips = new FileInputStream(FILE_NAME);           
			configProp.load(ips);
			String url = configProp.getProperty("URL");
			String user = configProp.getProperty("USR");
			String password = configProp.getProperty("PWD");
			connect = (Connection) DriverManager.getConnection(url, user, password);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}

	public void CloseConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public ComputerDAO getComputerDAO() {
		return computerDao;
	}

	public CompanyDAO getCompanyDAO() {
		return companyDao;
	}
}
