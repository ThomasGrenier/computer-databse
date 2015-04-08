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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.exception.DAOException;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.CompanyModel;

@Repository
public class CompanyDAOImpl implements CompanyDAO {
	
	@Autowired
	DAOFactory daoFactory;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImpl.class);

	@Override
	public List<CompanyModel> listAll() {
	    List<CompanyModel> companyList = null;
    	Connection connection = daoFactory.getConnection();
	    try {
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
	    daoFactory.closeConnection();
        LOGGER.info("companyDao listAll succeed");
	    return companyList;
	}

	@Override
	public CompanyModel getById(long id) {
		CompanyModel companyModel = null;
    	Connection connection = daoFactory.getConnection();
	    try {
	    	int i = 1;
	        String query = "SELECT * FROM company WHERE id =?";
	        PreparedStatement st = null;
	        st = connection.prepareStatement(query);
	        st.setLong(i++, id);
	        
	        ResultSet rs = st.executeQuery();
	        
	        companyModel = (new CompanyMapper()).mapOne(rs);
	        
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	        LOGGER.error("companyDao getById failed");
	    	throw new DAOException(e);
	    }
	    daoFactory.closeConnection();
        LOGGER.info("companyDao getById succeed");
	    return companyModel;
	}

	
	public List<CompanyModel> getCompaniesByPage(int offset, int limit, String searchBy, String orderBy, String option) {
		List<CompanyModel> companyList = new LinkedList<CompanyModel>();
    	Connection connection = daoFactory.getConnection();
	    try {
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
	    daoFactory.closeConnection();
        LOGGER.info("companyDao getCompaniesByPage succeed");
		return companyList;
	}

	@Override
	public int totalRow(String searchBy) {
    	Connection connection = daoFactory.getConnection();
    	int nb = 0;
	    try {
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
	    daoFactory.closeConnection();
        LOGGER.info("companyDao totalRow succeed");
	    return nb;
	}

	@Override
	@Transactional
	public void delete(long id) {
    	Connection connection = daoFactory.getConnection();
		try {
			
	        String query = "DELETE FROM computer WHERE computer.company_id=?";

	        PreparedStatement st = connection.prepareStatement(query);
	        st.setLong(1, id);
	        
	        st.executeUpdate();
			
			st.close();
			
	        query = "DELETE FROM company WHERE company.id=?";
	        st = connection.prepareStatement(query);
	        st.setLong(1,  id);
	        st.executeUpdate();
			
	        st.close();
	    } catch (SQLException e) {
	        LOGGER.error("companyDao delete failed");
	    	throw new DAOException(e);
	    }
	    daoFactory.closeConnection();
        LOGGER.info("companyDao delete succeed");
	}
}
