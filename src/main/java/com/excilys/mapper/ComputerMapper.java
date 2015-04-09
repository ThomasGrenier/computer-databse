package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;

public class ComputerMapper implements RowMapper<ComputerModel> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerMapper.class);

	@Override
	public ComputerModel mapRow(ResultSet rs, int row) throws SQLException {
		if (rs == null) {
	        LOGGER.error("computer mapping failed resultSet null");
			throw new IllegalArgumentException();
		}

		ComputerModel computerModel = null;
		CompanyModel companyModel = null;
		companyModel = new CompanyModel(rs.getLong(6), rs.getString(7));

		computerModel = new ComputerModel(rs.getLong(1), rs.getString(2),
				((rs.getTimestamp(3) == null) ? null : rs.getTimestamp(3).toLocalDateTime())
				, ((rs.getTimestamp(4) == null) ? null : rs.getTimestamp(4).toLocalDateTime())
				, companyModel);
		LOGGER.info("computer mapping succeed");
		return computerModel;
	}

}

