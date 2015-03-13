package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import model.CompanyModel;
import model.ComputerModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class JDBCQuery {
	
	private Connection connection;
		
	public JDBCQuery(Connection connection) {
		this.connection = connection;
	}
	
	public List<ComputerModel> listComputers() {
	    List<ComputerModel> computerList = new LinkedList<ComputerModel>();
	    try {
	        // create new connection and statement
	        Statement st = (Statement) connection.createStatement();

	        String query = "SELECT * FROM computer";
	        ResultSet rs = st.executeQuery(query);
	        ComputerModel computerModel = null;
	        while (rs.next()) {
	        	
	        	computerModel = new ComputerModel(rs.getLong(1), rs.getString(2),
            			((rs.getTimestamp(3) == null) ? null : rs.getTimestamp(3).toLocalDateTime())
            			, ((rs.getTimestamp(4) == null) ? null : rs.getTimestamp(4).toLocalDateTime())
            			, rs.getLong(5));
	        	computerList.add(computerModel);
	            /*System.out.printf("%-5d | %-70s | %-25s | %-25s | %-1s \n", //
	                    rs.getLong(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getLong(5));*/
	        }
	        
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
        return computerList;
	}
	
	public List<CompanyModel> listCompanies() {
		    List<CompanyModel> companyList = new LinkedList<CompanyModel>();
		    try {
		        // create new connection and statement
		        Statement st = (Statement) connection.createStatement();

		        String query = "SELECT * FROM company";
		        ResultSet rs = st.executeQuery(query);

		        CompanyModel companyModel = null;
		        while (rs.next()) {
		        	companyModel = new CompanyModel(rs.getLong(1), rs.getString(2));
		            /*System.out.printf("%-10d | %-10s \n", //
		                    rs.getLong(1), rs.getString(2));*/
		        	companyList.add(companyModel);
		        }
		        rs.close();
		        st.close();
		    } catch (SQLException e) {
		    	e.printStackTrace();
		    }
		    return companyList;
	}
	
	public ComputerModel showComputer(long id) {
	    ComputerModel computerModel = null;
	    try {
	        // create new connection and statement
	        Statement st = (Statement) connection.createStatement();

	        String query = "SELECT * FROM computer WHERE id =" + id;
	        ResultSet rs = st.executeQuery(query);

	        rs.next();

        	computerModel = new ComputerModel(rs.getLong(1), rs.getString(2),
        			((rs.getTimestamp(3) == null) ? null : rs.getTimestamp(3).toLocalDateTime())
        			, ((rs.getTimestamp(4) == null) ? null : rs.getTimestamp(4).toLocalDateTime())
        			, rs.getLong(5));
	            /*System.out.printf("%-5d | %-70s | %-25s | %-25s | %-1s \n", //
	                    rs.getLong(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getLong(5));*/
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return computerModel;
	}
	
	public long createComputer(String name) {
	    long max = 0;
	    try {
	        // create new connection and statement
	        Statement st = (Statement) connection.createStatement();

	        String query = "SELECT MAX(id) FROM computer";
	        ResultSet rs = st.executeQuery(query);

	        rs.next();
	        max = rs.getLong(1);
	        max += 1;
	        
	        query = "insert into computer (id,name,introduced,discontinued,company_id) values ( " + max + ",'" + name + "',null,null,null);";
	        st.executeUpdate(query);
	            /*System.out.printf("%-5d | %-70s | %-25s | %-25s | %-1s \n", //
	                    rs.getLong(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getLong(5));*/
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return max;
	}
	
	public int updateComputer(long id, String name, String introduced, String discontinued, long idComp) {
	    int result = 0;
	    try {
	        // create new connection and statement
	        Statement st = (Statement) connection.createStatement();

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        LocalDateTime formatterIntroduced = LocalDateTime.parse(introduced, formatter);
	        LocalDateTime formatterDiscontinued = LocalDateTime.parse(discontinued, formatter);
	        String query = "UPDATE computer " +
	        				"SET name = '" + name +
	        				"', introduced = '" + formatterIntroduced + 
	        				"', discontinued = '" + formatterDiscontinued +
	        				"', company_id = '" + idComp + "' " +
	        				"WHERE id = " + id + ";";
	        result = st.executeUpdate(query);
	            /*System.out.printf("%-5d | %-70s | %-25s | %-25s | %-1s \n", //
	                    rs.getLong(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getLong(5));*/
	        st.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return result;
	}
	
	public int deleteComputer(long id) {
	    int result = 0;
	    try {
	        // create new connection and statement
	        Statement st = (Statement) connection.createStatement();
	        String query = "DELETE FROM computer WHERE id = " + id;
	        result = st.executeUpdate(query);
	        st.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return result;
	}
	
	//public static void main(String[] args) {
		/*JDBCQuery jdbc = new JDBCQuery();
		try {*/
			
			/*List<ComputerModel> c = new LinkedList<ComputerModel>();
			c = jdbc.listComputers();*/
			
			
			/*List<CompanyModel> c = new LinkedList<CompanyModel>();
			c = jdbc.listCompanies();*/
			
			/*jdbc.createComputer("machin");
			ComputerModel c = jdbc.showComputer(576);*/
			
			/*LocalDateTime t = LocalDateTime.now();
			jdbc.updateComputer(575, "go", Timestamp.valueOf(t), Timestamp.valueOf(t), 12);
			ComputerModel c = jdbc.showComputer(575);*/
			
			//jdbc.deleteComputer(577);
			
			//System.out.println(c.toString());
		/*} catch (SQLException e) {
			e.printStackTrace();
		}*/
	//}
}
