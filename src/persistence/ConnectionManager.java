package persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public enum ConnectionManager implements AutoCloseable{
	INSTANCE;

	private static final String FILE_NAME = "ressources/MysqlProperties.properties";
	private final Properties configProp = new Properties();
	private final Connection connection;

	ConnectionManager() {
		connection = createConnection();
	}
	
	private Connection createConnection() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}

	public final Connection getConnection() {
		return connection;
	}
	
	@Override
	public void close() throws Exception {
		connection.close();
	}

}
