package com.excilys.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;


public class DBUtil {

	public static IDatabaseTester databaseTester;
	public static String jdbcDriver;
	public static String jdbcUrl;
	public static String user;
	public static String password;
	private static final String CONFIG_TEST = "MysqlProperties-test.properties";

	static {

		final Properties properties = new Properties();
		/*try (final InputStream is = DAOFactory.class
				.getClassLoader().getResourceAsStream(CONFIG_TEST)) {
			System.out.println("gg3");
			properties.load(is);
			System.out.println("gg4");
			jdbcDriver = "org.h2.Driver";
			System.out.println("gg5");
			jdbcUrl = properties.getProperty("URL");
			System.out.println("gg6");
			user = properties.getProperty("USR");
			System.out.println("gg7");
			password = properties.getProperty("PWD");
			System.out.println("gg8");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}*/
		InputStream ips;
		try {
			//ips = new FileInputStream(CONFIG_TEST);
			ips = DBUtil.class.getClassLoader().getResourceAsStream(CONFIG_TEST);
			properties.load(ips);
			jdbcUrl = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			jdbcDriver = "org.h2.Driver";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}           
	}

	public static void cleanlyInsert(IDataSet dataSet) throws Exception {
		databaseTester = new JdbcDatabaseTester(
				jdbcDriver, jdbcUrl, user, password);
		databaseTester.getConnection().getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		databaseTester.getConnection().getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	public static void executeSqlFile(String file, Connection connection) throws IOException, SQLException {
		/*final InputStream is = DAOFactory.class
				.getClassLoader().getResourceAsStream(file);
		System.out.println("1");
		final BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		System.out.println("2");*/
		//final InputStream is = new FileInputStream(file);
		final InputStream is = DBUtil.class.getClassLoader().getResourceAsStream(file);
		final BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		final StringBuilder sb = new StringBuilder();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str + "\n ");
		}
		Statement stmt = connection.createStatement();
		stmt.execute(sb.toString());
		stmt.close();
		br.close();
	}


	public static Connection getConnection() throws IOException, SQLException {
		final Properties properties = new Properties();
		/*try (final InputStream is = DAOFactory.class
				.getClassLoader().getResourceAsStream(CONFIG_TEST)) {
			System.out.println("c");
			properties.load(is);
			System.out.println("d");
			final String url = properties.getProperty("URL");
			System.out.println("e");
			return (Connection) DriverManager.getConnection(url, properties);
		}*/
		InputStream ips;
		String url = null;
		try {
			//ips = new FileInputStream(CONFIG_TEST);
			ips = DBUtil.class.getClassLoader().getResourceAsStream(CONFIG_TEST);
			properties.load(ips);
			url = properties.getProperty("url");
			return DriverManager.getConnection(url, properties);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
}
