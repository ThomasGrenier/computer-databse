package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.exception.MapperException;
import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;

public class ComputerMapper implements GenericMapper<ComputerModel> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerMapper.class);

	public List<ComputerModel> mapAll(ResultSet resultSet) {
		List<ComputerModel> computerList = new LinkedList<ComputerModel>();
		ComputerModel computerModel = null;
		CompanyModel companyModel = null;
		try {
			while (resultSet.next()) {
				companyModel = new CompanyModel(resultSet.getLong(6), resultSet.getString(7));

				computerModel = new ComputerModel(resultSet.getLong(1), resultSet.getString(2),
						((resultSet.getTimestamp(3) == null) ? null : resultSet.getTimestamp(3).toLocalDateTime())
						, ((resultSet.getTimestamp(4) == null) ? null : resultSet.getTimestamp(4).toLocalDateTime())
						, companyModel);
				computerList.add(computerModel);
				/*System.out.printf("%-5d | %-70s | %-25s | %-25s | %-1s \n", //
			            rs.getLong(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getLong(5));*/
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	        LOGGER.error("computer mapping failed");
			throw new MapperException(e);
		}
        LOGGER.info("computer mapping succeed");
		return computerList;
	}

	public ComputerModel mapOne(ResultSet resultSet) {
		ComputerModel computerModel = null;
		CompanyModel companyModel = null;
		try {
			resultSet.next();
			companyModel = new CompanyModel(resultSet.getLong(6), resultSet.getString(7));

			computerModel = new ComputerModel(resultSet.getLong(1), resultSet.getString(2),
					((resultSet.getTimestamp(3) == null) ? null : resultSet.getTimestamp(3).toLocalDateTime())
					, ((resultSet.getTimestamp(4) == null) ? null : resultSet.getTimestamp(4).toLocalDateTime())
					, companyModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	        LOGGER.error("computer mapping failed");
			throw new MapperException(e);
		}
        LOGGER.info("computer mapping succeed");
		return computerModel;
	}
}
