package persistence;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import model.ComputerModel;

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
	
	public List<ComputerModel> listComputers() throws SQLException {
	    Connection conn = null;
	    List<ComputerModel> computerList = new LinkedList<ComputerModel>();
	    try {
	        // create new connection and statement
	        conn = newConnection();
	        Statement st = (Statement) conn.createStatement();

	        String query = "SELECT * FROM computer";
	        ResultSet rs = st.executeQuery(query);
	        ComputerModel computerModel = null;
	        while (rs.next()) {
	        	computerModel = new ComputerModel(rs.getLong(1), rs.getString(2),
	        			rs.getTimestamp(3), rs.getTimestamp(4), rs.getLong(5));
	        	computerList.add(computerModel);
	            /*System.out.printf("%-5d | %-70s | %-25s | %-25s | %-1s \n", //
	                    rs.getLong(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getLong(5));*/
	        }
	        rs.close();
	        st.close();
	    } finally {
	        // close result, statement and connection
	        if (conn != null) {
	        	conn.close();
	        }
	    }
        return computerList;
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
		        rs.close();
		        st.close();
		    } finally {
		        // close result, statement and connection
		        if (conn != null) {
		        	conn.close();
		        }
		    }
	}
	
	public void showComputer(long id) throws SQLException {
	    Connection conn = null;
	    try {
	        // create new connection and statement
	        conn = newConnection();
	        Statement st = (Statement) conn.createStatement();

	        String query = "SELECT * FROM computer WHERE id =" + id;
	        ResultSet rs = st.executeQuery(query);

	        rs.next();
	            System.out.printf("%-5d | %-70s | %-25s | %-25s | %-1s \n", //
	                    rs.getLong(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getLong(5));
	        rs.close();
	        st.close();
	    } finally {
	        // close result, statement and connection
	        if (conn != null) {
	        	conn.close();
	        }
	    }
	}
	
	public void createComputer() {
		
	}
	
	public void updateComputer(long id, String name, Timestamp introduced, Timestamp discontinued, long idComp) {
		
	}
	
	public void deleteComputer(long id) {
		
	}
	
	public static void main(String[] args) {
		JDBCQuery jdbc = new JDBCQuery();
		try {
			List<ComputerModel> c = new LinkedList<ComputerModel>();
			c = jdbc.listComputers();
			System.out.println(c.toString());
		} catch (SQLException e) {
			//System.out.println("SQLException lol lol ");
			e.printStackTrace();
		}
	}
}
