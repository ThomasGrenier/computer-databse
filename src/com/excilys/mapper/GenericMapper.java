package com.excilys.mapper;

import java.sql.ResultSet;
import java.util.List;

public interface GenericMapper<T> {

	public List<T> mapAll(ResultSet resultSet);
	
	public T mapOne(ResultSet resultSet);
}
