package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.excilys.model.CompanyModel;

public class CompanyMapper implements GenericMapper<CompanyModel> {
	
	public List<CompanyModel> mapAll(ResultSet resultSet) {

		List<CompanyModel> companyList = new LinkedList<CompanyModel>();
        try {
            CompanyModel companyModel = null;
			while (resultSet.next()) {
				companyModel = new CompanyModel(resultSet.getLong(1), resultSet.getString(2));
			    /*System.out.printf("%-10d | %-10s \n", //
			            rs.getLong(1), rs.getString(2));*/
				companyList.add(companyModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return companyList;
	}
	
	public CompanyModel mapOne(ResultSet resultSet) {

		CompanyModel companyModel = null;
		try {
			resultSet.next();
	    	companyModel = new CompanyModel(resultSet.getLong(1), resultSet.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companyModel;
	}
}
