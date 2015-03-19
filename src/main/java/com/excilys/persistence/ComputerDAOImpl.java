package com.excilys.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import com.excily.exception.DAOException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.ComputerModel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ComputerDAOImpl implements ComputerDAO {

	@Override
	public List<ComputerModel> listAll() {

    	Connection connection = DAOFactory.INSTANCE.getConnection();
	    List<ComputerModel> computerList = new LinkedList<ComputerModel>();
	    try {
	        // create new connection and statement
	        Statement st = (Statement) connection.createStatement();

	        String query = "SELECT * FROM computer as compu left outer join company as compa on compa.id=compu.company_id";
	        ResultSet rs = st.executeQuery(query);
	        
	        computerList = (new ComputerMapper()).mapAll(rs);
	        
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection(connection);
        return computerList;
	}

	@Override
	public ComputerModel getById(long id) {
	    ComputerModel computerModel = null;
    	Connection connection = DAOFactory.INSTANCE.getConnection();
	    try {
	        // create new statement
	    	int i = 1;
	        String query = "SELECT * FROM computer as compu left outer join company as compa on compa.id=compu.company_id where compu.id=?";
	        PreparedStatement st = (PreparedStatement) connection.prepareStatement(query);
	        st.setLong(i++, id);

	        ResultSet rs = st.executeQuery();        	

	        computerModel = (new ComputerMapper()).mapOne(rs);
	            /*System.out.printf("%-5d | %-70s | %-25s | %-25s | %-1s \n", //
	                    rs.getLong(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getLong(5));*/
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection(connection);
	    return computerModel;
	}

	@Override
	public long create(String name) {
	    long id = 0;
    	Connection connection = DAOFactory.INSTANCE.getConnection();
	    try {

	        int i = 1;
	        String query = "insert into computer (name,introduced,discontinued,company_id) values (?, null, null, null);";
	        PreparedStatement sp = (PreparedStatement) connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        sp.setString(i++, name);
	        sp.executeUpdate();
	        
	        ResultSet generatedKeys = sp.getGeneratedKeys();
	        if (generatedKeys.next()) {
	        	id = generatedKeys.getLong(1);
	        }
	            /*System.out.printf("%-5d | %-70s | %-25s | %-25s | %-1s \n", //
	                    rs.getLong(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getLong(5));*/
	        sp.close();
	    } catch (SQLException e) {
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection(connection);
	    return id;
	}

	@Override
	public void update(ComputerModel computer) {
    	Connection connection = DAOFactory.INSTANCE.getConnection();
	    try {
	        // create new statement
	        Statement st = (Statement) connection.createStatement();

	        String question = "SELECT * FROM computer as compu left outer join company as compa on compa.id=compu.company_id where compu.id=" + computer.getId();
	        ResultSet rs = st.executeQuery(question);

	        rs.next();
	        
	        String actualName = rs.getString(2);
	        LocalDateTime actualIntroduced = ((rs.getTimestamp(3) == null) ? null : rs.getTimestamp(3).toLocalDateTime());
	        LocalDateTime actualDiscontinued = ((rs.getTimestamp(4) == null) ? null : rs.getTimestamp(4).toLocalDateTime());
	        long actualCompany = rs.getLong(5);
	        
	        rs.close();
	        
	        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        LocalDateTime formatterIntroduced = LocalDateTime.parse(introduced, formatter);
	        LocalDateTime formatterDiscontinued = LocalDateTime.parse(discontinued, formatter);*/
	        String query = "UPDATE computer " +
	        				"SET name = '" + (((computer.getName() == null) || (computer.getName().equals(""))) ? actualName : computer.getName()) +
	        				"', introduced = '" + ((computer.getIntroduced() == null) ? actualIntroduced : computer.getIntroduced()) + 
	        				"', discontinued = '" + ((computer.getDiscontinued() == null) ? actualDiscontinued : computer.getDiscontinued()) +
	        				"', company_id = '" + ((computer.getCompany().getId() == -1) ? actualCompany : computer.getCompany().getId()) + "' " +
	        				"WHERE id = " + computer.getId() + ";";
	        st.executeUpdate(query);
	            /*System.out.printf("%-5d | %-70s | %-25s | %-25s | %-1s \n", //
	                    rs.getLong(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getLong(5));*/
	        st.close();
	    } catch (MysqlDataTruncation e) {
	    	throw new IllegalArgumentException("invalid date");
	    } catch (SQLException e) {
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection(connection);
	}

	@Override
	public void delete(long id) {
    	Connection connection = DAOFactory.INSTANCE.getConnection();
	    try {
	        // create new statement
	    	int i = 1;
	        String query = "DELETE FROM computer WHERE id = ?";
	        PreparedStatement st = (PreparedStatement) connection.prepareStatement(query);
	        st.setLong(i++, id);
	        st.executeUpdate();
	        st.close();
	    } catch (SQLException e) {
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection(connection);
	}
	
	public List<ComputerModel> getComputersByPage(int offset, int limit) {
		List<ComputerModel> computerList = new LinkedList<ComputerModel>();
    	Connection connection = DAOFactory.INSTANCE.getConnection();
	    try {
	        // create new connection and statement
	        String query = "SELECT * FROM computer as compu left outer join company as compa on compa.id=compu.company_id limit ? offset ?";

	        int i = 1;
	        PreparedStatement st = (PreparedStatement) connection.prepareStatement(query);
	        st.setInt(i++, limit);
	        st.setInt(i++, offset);
	        ResultSet rs = st.executeQuery();
	        
	        computerList = (new ComputerMapper()).mapAll(rs);
	        
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection(connection);
		return computerList;
	}

	@Override
	public int totalRow() {
    	Connection connection = DAOFactory.INSTANCE.getConnection();
    	int nb = 0;
	    try {
	        // create new connection and statement
	        String query = "SELECT count(*) FROM computer";

	        Statement st = (Statement) connection.createStatement();
	        
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
