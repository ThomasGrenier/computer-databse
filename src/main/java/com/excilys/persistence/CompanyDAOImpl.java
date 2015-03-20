package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.excilys.exception.DAOException;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.CompanyModel;

public class CompanyDAOImpl implements CompanyDAO {

	@Override
	public List<CompanyModel> listAll() {
	    List<CompanyModel> companyList = null;
    	Connection connection = DAOFactory.INSTANCE.getConnection();
	    try {
	        // create new statement
	        Statement st = connection.createStatement();

	        String query = "SELECT * FROM company";
	        ResultSet rs = st.executeQuery(query);
	        
	        companyList = (new CompanyMapper()).mapAll(rs);
	        
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection(connection);
	    return companyList;
	}

	@Override
	public CompanyModel getById(long id) {
		CompanyModel companyModel = null;
    	Connection connection = DAOFactory.INSTANCE.getConnection();
	    try {
	        // create new statement
	    	int i = 1;
	        String query = "SELECT * FROM company WHERE id =?";
	        PreparedStatement st = null;
	        st = connection.prepareStatement(query);
	        st.setLong(i++, id);
	        
	        ResultSet rs = st.executeQuery();
	        
	        companyModel = (new CompanyMapper()).mapOne(rs);
	            /*System.out.printf("%-5d | %-70s | %-25s | %-25s | %-1s \n", //
	                    rs.getLong(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getLong(5));*/
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection(connection);
	    return companyModel;
	}

	
	public List<CompanyModel> getCompaniesByPage(int offset, int limit) {
		List<CompanyModel> companyList = new LinkedList<CompanyModel>();
    	Connection connection = DAOFactory.INSTANCE.getConnection();
	    try {
	        // create new connection and statement
	        String query = "SELECT * FROM company limit ? offset ?";

	        int i = 1;
	        PreparedStatement st = connection.prepareStatement(query);
	        st.setInt(i++, limit);
	        st.setInt(i++, offset);
	        ResultSet rs = st.executeQuery();
	        
	        companyList = (new CompanyMapper()).mapAll(rs);
	        
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection(connection);
		return companyList;
	}

	@Override
	public int totalRow() {
    	Connection connection = DAOFactory.INSTANCE.getConnection();
    	int nb = 0;
	    try {
	        // create new connection and statement
	        String query = "SELECT count(*) FROM company";

	        Statement st = connection.createStatement();
	        
	        ResultSet rs = st.executeQuery(query);
	        
	        rs.next();
	        
	        nb = rs.getInt(1);
	        
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection(connection);
	    return nb;
	}
}
