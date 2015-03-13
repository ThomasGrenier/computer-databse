package persistence;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class JDBCQuery {
	
	private static final String URL = "jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String USER = "root";
	private static final String PWD = "root";
	
	
	public JDBCQuery() {
		
	}
	
	Connection newConnection() throws SQLException {
	    final String url = URL;
	    Connection conn = (Connection) DriverManager.getConnection(url, USER, PWD);
	    return conn;
	}
	
	public void listComputers() throws SQLException {
	    Connection conn = null;
	    try {
	        // create new connection and statement
	        conn = newConnection();
	        Statement st = (Statement) conn.createStatement();

	        String query = "SELECT * FROM computer";
	        ResultSet rs = st.executeQuery(query);

	        while (rs.next()) {
	            System.out.printf("%-5d | %-70s | %-25s | %-25s | %-1s \n", //
	                    rs.getLong(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getLong(5));
	        }
	    } finally {
	        // close result, statement and connection
	        if (conn != null) conn.close();
	    }
	}
	
	public void listCompanies() throws SQLException {
		    Connection conn = null;
		    try {
		        // create new connection and statement
		        conn = newConnection();
		        Statement st = (Statement) conn.createStatement();

		        String query = "SELECT * FROM company";
		        ResultSet rs = st.executeQuery(query);

		        while (rs.next()) {
		            System.out.printf("%-10d | %-10s \n", //
		                    rs.getLong(1), rs.getString(2));
		        }
		    } finally {
		        // close result, statement and connection
		        if (conn != null) conn.close();
		    }
	}
	
	public void showComputer(long id) {
		
	}
	
	public void createComputer() {
		
	}
	
	public void updateComputer(long id, String name, Date introduced, Date discontinued, long idComp) {
		
	}
	
	public void deleteComputer(long id) {
		
	}
	
	public static void main(String[] args) {
		JDBCQuery jdbc = new JDBCQuery();
		try {
			jdbc.listComputers();
		} catch (SQLException e) {
			//System.out.println("SQLException lol lol ");
			e.printStackTrace();
		}
	}
}
