package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.exception.MapperException;
import com.excilys.model.CompanyModel;

public class CompanyMapper implements GenericMapper<CompanyModel> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyMapper.class);
	
	public List<CompanyModel> mapAll(ResultSet resultSet) {

		List<CompanyModel> companyList = new LinkedList<CompanyModel>();
        try {
            CompanyModel companyModel = null;
			while (resultSet.next()) {
				companyModel = new CompanyModel(resultSet.getLong(1), resultSet.getString(2));
				companyList.add(companyModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	        LOGGER.error("company mapping failed");
			throw new MapperException(e);
		}
        LOGGER.info("company mapping succeed");
        return companyList;
	}
	
	public CompanyModel mapOne(ResultSet resultSet) {

		CompanyModel companyModel = null;
		
		try {
			if (resultSet.next() != false) {
				companyModel = new CompanyModel(resultSet.getLong(1), resultSet.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	        LOGGER.error("company mapping failed");
			throw new MapperException(e);
		}
        LOGGER.info("company mapping succeed");
		return companyModel;
	}
}
