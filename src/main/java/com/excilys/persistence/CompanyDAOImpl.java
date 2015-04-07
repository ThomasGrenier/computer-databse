package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.exception.DAOException;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.CompanyModel;

public class CompanyDAOImpl implements CompanyDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImpl.class);

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
	        LOGGER.error("companyDao listAll failed");
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection();
        LOGGER.info("companyDao listAll succeed");
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
	        LOGGER.error("companyDao getById failed");
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection();
        LOGGER.info("companyDao getById succeed");
	    return companyModel;
	}

	
	public List<CompanyModel> getCompaniesByPage(int offset, int limit, String searchBy, String orderBy, String option) {
		List<CompanyModel> companyList = new LinkedList<CompanyModel>();
    	Connection connection = DAOFactory.INSTANCE.getConnection();
	    try {
	        // create new connection and statement
	        String query = "SELECT * FROM company";
	        if (!searchBy.isEmpty()) {
	        	query += " WHERE company.name LIKE '%" + searchBy + "%'";
	        }
	        if (!orderBy.isEmpty()) {
	        	query += " ORDER BY compu." + orderBy;
	        }
	        if (!option.isEmpty()) {
	        	query += " " + option;
	        }
	        query += " limit ? offset ?";

	        int i = 1;
	        PreparedStatement st = connection.prepareStatement(query);
	        st.setInt(i++, limit);
	        st.setInt(i++, offset);
	        ResultSet rs = st.executeQuery();
	        
	        companyList = (new CompanyMapper()).mapAll(rs);
	        
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	        LOGGER.error("companyDao getCompaniesByPage failed");
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection();
        LOGGER.info("companyDao getCompaniesByPage succeed");
		return companyList;
	}

	@Override
	public int totalRow(String searchBy) {
    	Connection connection = DAOFactory.INSTANCE.getConnection();
    	int nb = 0;
	    try {
	        // create new connection and statement
	        String query = "SELECT count(*) FROM company";
	        if (!searchBy.isEmpty()) {
	        	query += " WHERE company.name LIKE '%" + searchBy + "%'";
	        }

	        Statement st = connection.createStatement();
	        
	        ResultSet rs = st.executeQuery(query);
	        
	        rs.next();
	        
	        nb = rs.getInt(1);
	        
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	        LOGGER.error("companyDao totalRow failed");
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection();
        LOGGER.info("companyDao totalRow succeed");
	    return nb;
	}

	@Override
	public void delete(long id) {
    	Connection connection = DAOFactory.INSTANCE.getConnection();
		try {
			
	        // create new connection and statement
	        String query = "DELETE FROM computer WHERE computer.company_id=?";

	        PreparedStatement st = connection.prepareStatement(query);
	        st.setLong(1, id);
	        
	        /*ResultSet rs = st.executeQuery();
	        ComputerDAOImpl computerDaoImpl = new ComputerDAOImpl();
			while (rs.next()) {
				computerDaoImpl.delete(rs.getLong(1));
			}*/
	        st.executeUpdate();
			
			st.close();
	        
	        query = "DELETE FROM company WHERE company.id=?";
	        st = connection.prepareStatement(query);
	        st.setLong(1,  id);
	        st.executeUpdate();
			
	        st.close();
	        //connection.commit();
	    } catch (SQLException e) {
	    	/*try {
				connection.rollback();
			} catch (SQLException e1) {
		        LOGGER.error("companyDao delete rollback failed");
		    	throw new DAOException(e1);
			}*/
	        LOGGER.error("companyDao delete failed");
	    	throw new DAOException(e);
	    }
	    //DAOFactory.INSTANCE.CloseConnection(connection);
        LOGGER.info("companyDao delete succeed");
	}
}
