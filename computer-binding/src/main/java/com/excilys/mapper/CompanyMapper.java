package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.excilys.model.CompanyModel;

public class CompanyMapper implements RowMapper<CompanyModel> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyMapper.class);

	@Override
	public CompanyModel mapRow(ResultSet rs, int row) throws SQLException {
		if (rs == null) {
	        LOGGER.error("company mapping failed resultSet null");
			throw new IllegalArgumentException();
		}
        LOGGER.info("company mapping succeed");
		return new CompanyModel(rs.getLong("id"), rs.getString("name"));
	}
}
