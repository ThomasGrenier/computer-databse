package com.excilys.persistence;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.model.CompanyModel;
import com.excilys.util.DBUtil;

public class CompanyDAOTest {
	
	@Autowired
	ComputerDAOImpl computerDao;
	
	@Autowired
	CompanyDAOImpl companyDao;

	@BeforeClass
	public static void setUpDB() {
		System.setProperty("env", "TEST");
		try {
			DBUtil.executeSqlFile("bdTest.sql", DBUtil.getConnection());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@After
	public void tearDown() throws Exception {
		DBUtil.databaseTester.onTearDown();
	}

	@Test
	public void listAllShouldReturnAListOfCompanies() throws Exception {
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/data/listAll.xml")));
		final int expectedSize = 5;

		final List<CompanyModel> companies = companyDao.listAll();
		final CompanyModel expectedCompany1 = new CompanyModel(1L, "Comp1");
		final CompanyModel expectedCompany2 = new CompanyModel(2L, "Comp2");
		final CompanyModel expectedCompany3 = new CompanyModel(3L, "Comp3");
		final CompanyModel expectedCompany4 = new CompanyModel(4L, "Comp4");
		final CompanyModel expectedCompany5 = new CompanyModel(5L, "Comp5");

		Assertions.assertThat(companies).isNotNull();
		Assertions.assertThat(companies.size()).isEqualTo(expectedSize);
		Assertions.assertThat(companies).contains(expectedCompany1, expectedCompany2, expectedCompany3, expectedCompany4, expectedCompany5);
	}

	@Test
	public void listAllShouldReturnEmptyListWhenTheBDIsEmpty() throws Exception {
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/data/emptyBD.xml")));

		final List<CompanyModel> companies = companyDao.listAll();

		Assertions.assertThat(companies).isNotNull();
		Assertions.assertThat(companies).isEmpty();
	}

	@Test 
	public void getByIdShouldReturnTheCompanyModelOfTheId() throws Exception {
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/data/listAll.xml")));
		final long id = 2L;
		final long expectedId = 2L;
		final String expectedName = "Comp2";

		final CompanyModel model = companyDao.getById(id);

		Assertions.assertThat(model).isNotNull();
		Assertions.assertThat(model.getId()).isEqualTo(expectedId);
		Assertions.assertThat(model.getName()).isEqualTo(expectedName);
	}

	@Test
	public void getByIdShouldReturnNullIfTheIdIsNotValid() throws Exception {
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/data/emptyBD.xml")));

		final long id = 400L;

		final CompanyModel model = companyDao.getById(id);
		
		Assertions.assertThat(model).isNull();
	}
}
