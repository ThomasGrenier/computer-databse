package com.excilys.persistence;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.excilys.ebi.spring.dbunit.config.DBOperation;
import com.excilys.ebi.spring.dbunit.test.DataSet;
import com.excilys.ebi.spring.dbunit.test.ExpectedDataSet;
import com.excilys.ebi.spring.dbunit.test.RollbackTransactionalDataSetTestExecutionListener;
import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context-test.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        RollbackTransactionalDataSetTestExecutionListener.class })
public class DaoTest {

	@Autowired
	private ComputerDAO computerDAO;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Test
    @DataSet(locations = "classpath:dataset/company_full.xml", tearDownOperation = DBOperation.DELETE_ALL)
	public void companyDAOListAllShouldReturnAlistOfAllTheCompanies() {
		//GIVEN
		
		//WHEN
		List<CompanyModel> companies = companyDAO.listAll();
		
		//THEN
		Assertions.assertThat(companies).isNotNull();
		Assertions.assertThat(companies).isNotEmpty();
		Assertions.assertThat(companies.size()).isEqualTo(5);
	}
	
	@Test
    @DataSet(locations = "classpath:dataset/company_empty.xml", tearDownOperation = DBOperation.DELETE_ALL)
	public void companyDAOShouldReturnAnEmptyListIfThereAreNoCompanies() {
		//GIVEN
		
		//WHEN
		List<CompanyModel> companies = companyDAO.listAll();
		
		//THEN
		Assertions.assertThat(companies).isNotNull();
		Assertions.assertThat(companies).isEmpty();
		Assertions.assertThat(companies.size()).isEqualTo(0);
	}
	
	@Test
	@DataSet(locations = "classpath:dataset/computer.xml", tearDownOperation = DBOperation.DELETE_ALL)
	public void computerDAOListAllShouldReturnAlistOfAllTheComputers() {
		//GIVEN
		
		//WHEN
		List<ComputerModel> computers = computerDAO.listAll();
		
		//THEN
		Assertions.assertThat(computers).isNotNull();
		Assertions.assertThat(computers).isNotEmpty();
		Assertions.assertThat(computers.size()).isEqualTo(5);
	}
	
	@Test
	@DataSet(locations = "classpath:dataset/computer.xml", tearDownOperation = DBOperation.DELETE_ALL)
	@ExpectedDataSet(locations = "classpath:dataset/computer_add.xml", columnsToIgnore = {"introduced", "discontinued", "company_id"})
	public void createANewComputer() {
		//GIVEN
		ComputerModel computer = new ComputerModel();
		computer.setId(6);
		computer.setName("computer6");
		
		//WHEN
		computerDAO.create(computer);
		
		//THEN
	}
	
	@Test
	@DataSet(locations = "classpath:dataset/computer.xml", tearDownOperation = DBOperation.DELETE_ALL)
	@ExpectedDataSet(locations = "classpath:dataset/computer_delete.xml", columnsToIgnore = {"introduced", "discontinued", "company_id"})
	public void deleteAComputer() {
		//GIVEN
		
		//WHEN
		computerDAO.delete(5);
		
		//THEN
	}
}
