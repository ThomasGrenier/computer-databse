package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import com.excilys.exception.DAOException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.ComputerModel;
import com.mysql.jdbc.MysqlDataTruncation;

public class ComputerDAOImpl implements ComputerDAO {

	@Override
	public List<ComputerModel> listAll() {

    	Connection connection = null;
		try {
			connection = DAOFactory.INSTANCE.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	    	throw new DAOException(e);
		}
	    List<ComputerModel> computerList = new LinkedList<ComputerModel>();
	    try {
	        // create new connection and statement
	        Statement st = connection.createStatement();

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
    	Connection connection = null;
		try {
			connection = DAOFactory.INSTANCE.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	    	throw new DAOException(e);
		}
	    try {
	        // create new statement
	    	int i = 1;
	        String query = "SELECT * FROM computer as compu left outer join company as compa on compa.id=compu.company_id where compu.id=?";
	        PreparedStatement st = connection.prepareStatement(query);
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
	public long create(String name, LocalDateTime introduced, LocalDateTime discontinued, long idCompany) {
	    long id = 0;
    	Connection connection = null;
		try {
			connection = DAOFactory.INSTANCE.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	    	throw new DAOException(e);
		}
	    try {

	        int i = 1;
	        String query = "insert into computer (name,introduced,discontinued,company_id) values (?, ?, ?, ?);";
	        PreparedStatement sp = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        sp.setString(i++, name);
	        if (introduced != null) {
	        	sp.setString(i++, introduced.toString());
	        } else {
	        	sp.setString(i++,  null);
	        }
	        if (discontinued != null) {
	        	sp.setString(i++, discontinued.toString());
	        } else {
	        	sp.setString(i++,  null);
	        }
	        sp.setLong(i++,  idCompany);
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
    	Connection connection = null;
		try {
			connection = DAOFactory.INSTANCE.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	    	throw new DAOException(e);
		}
	    try {
	        // create new statement
	        Statement st = connection.createStatement();

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
    	Connection connection = null;
		try {
			connection = DAOFactory.INSTANCE.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	    	throw new DAOException(e);
		}
	    try {
	        // create new statement
	    	int i = 1;
	        String query = "DELETE FROM computer WHERE id = ?";
	        PreparedStatement st = connection.prepareStatement(query);
	        st.setLong(i++, id);
	        st.executeUpdate();
	        st.close();
	    } catch (SQLException e) {
	    	throw new DAOException(e);
	    }
	    DAOFactory.INSTANCE.CloseConnection(connection);
	}
	
	public List<ComputerModel> getComputersByPage(int offset, int limit, String searchBy, String orderBy, String option) {
		List<ComputerModel> computerList = new LinkedList<ComputerModel>();
    	Connection connection = null;
		try {
			connection = DAOFactory.INSTANCE.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	    	throw new DAOException(e);
		}
    	if (offset < 0) {
    		offset = 0;
    	}
	    try {
	        // create new connection and statement
	        String query = "SELECT * FROM computer as compu left outer join company as compa on compa.id=compu.company_id";
	        if (!searchBy.isEmpty()) {
	        	query += " WHERE compu.name LIKE ? OR compa.name LIKE ?";
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
	        if (!searchBy.isEmpty()) {
		        st.setString(i++, "%" + searchBy + "%");
		        st.setString(i++, "%" + searchBy + "%");
		    }
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
	public int totalRow(String searchBy) {
    	Connection connection = null;
		try {
			connection = DAOFactory.INSTANCE.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	    	throw new DAOException(e);
		}
    	int nb = 0;
	    try {
	        // create new connection and statement
	        String query = "SELECT count(*) FROM computer as compu left outer join company as compa on compa.id=compu.company_id";
	        
	        if (!searchBy.isEmpty()) {
	        	query += " WHERE compu.name LIKE '%" + searchBy + "%' OR compa.name LIKE '%" + searchBy + "%'";
	        }

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
