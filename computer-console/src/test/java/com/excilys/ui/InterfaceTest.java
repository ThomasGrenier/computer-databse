package com.excilys.ui;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class InterfaceTest {
	private static WebDriver webDriver;

	@BeforeClass
	public static void init() {
		webDriver = new FirefoxDriver();
		webDriver.get("http://localhost:8080/computer-webapp/dashboard");
		webDriver.findElement(By.id("username"))
		.sendKeys("test2");
		webDriver.findElement(By.id("password"))
		.sendKeys("test2");
		webDriver.findElement(By.tagName("form")).submit();
	}

	@AfterClass
	public static void close() {
		webDriver.close();
	}

	@Before
	public void initFirstPageAtDashboard() {
		webDriver.get("http://localhost:8080/computer-webapp/dashboard");
	}

	@Test
	public void theNumberOfComputerIsIncreaseByOneWhenYouSuccessfullyAddAComputerAndTheLastComputerIsTheAddedComputer() {

		//GIVEN
		String[] result = webDriver.findElement(By.id("homeTitle")).getText().split(" ");
		int oldNumber = Integer.parseInt(result[0]);

		//WHEN

		webDriver.findElement(By.id("addComputer")).click();

		webDriver.findElement(By.id("computerName"))
		.sendKeys("testSelenium");

		webDriver.findElement(By.id("introduced"))
		.sendKeys("2012-12-12 12:12:12");

		webDriver.findElement(By.id("discontinued"))
		.sendKeys("2012-12-12 12:12:12");

		WebElement select = webDriver.findElement(By.id("companyId"));
		List<WebElement> options = select.findElements(By.tagName("option"));
		for(WebElement option : options){
			if(option.getText().equals("RCA")) {
				option.click();
				break;
			}
		}

		webDriver.findElement(By.id("buttonadd")).click();

		result = webDriver.findElement(By.id("homeTitle")).getText().split(" ");
		int newNumber = Integer.parseInt(result[0]);

		webDriver.findElement(By.id("lastPage")).click();

		List<WebElement> table_element = webDriver.findElements(By.id("computerName"));
		String lastName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String lastIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String lastDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerCompId"));
		String lastCompany = table_element.get(table_element.size() - 1).getText();

		//THEN
		Assertions.assertThat("testSelenium").isEqualTo(lastName);
		Assertions.assertThat("2012-12-12 12:12:12").isEqualTo(lastIntroduced);
		Assertions.assertThat("2012-12-12 12:12:12").isEqualTo(lastDiscontinued);
		Assertions.assertThat("RCA").isEqualTo(lastCompany);
		Assertions.assertThat(newNumber).isEqualTo(oldNumber + 1);

	}

	@Test
	public void addComputerShouldShowAnErrorMessageWhenTheNameIsNotPresent() {
		//GIVEN

		//WHEN
		webDriver.findElement(By.id("addComputer")).click();
		webDriver.findElement(By.id("buttonadd")).click();

		String errorMessage = webDriver.findElement(By.id("errorName")).getText();

		//THEN
		Assertions.assertThat(errorMessage).isEqualTo("The name is required");
	}

	@Test
	public void addComputerShouldShowAnErrorMessageWhenTheDateFormatOfIntroducedDateIsInvalid() {

		//GIVEN

		//WHEN
		webDriver.findElement(By.id("addComputer")).click();

		webDriver.findElement(By.id("computerName"))
		.sendKeys("testSelenium");

		webDriver.findElement(By.id("introduced"))
		.sendKeys("bad format");

		webDriver.findElement(By.id("buttonadd")).click();

		String errorMessage = webDriver.findElement(By.id("errorIntro")).getText();

		//THEN
		Assertions.assertThat(errorMessage).isEqualTo("bad Format (yyyy-MM-dd HH:mm:ss)");
	}

	@Test
	public void addComputerShouldShowAnErrorMessageWhenTheDateFormatOfDiscontinuedDateIsInvalid() {

		//GIVEN

		//WHEN
		webDriver.findElement(By.id("addComputer")).click();

		webDriver.findElement(By.id("computerName"))
		.sendKeys("testSelenium");

		webDriver.findElement(By.id("introduced"))
		.sendKeys("2012-12-12 12:12:12");

		webDriver.findElement(By.id("discontinued"))
		.sendKeys("bad format");

		webDriver.findElement(By.id("buttonadd")).click();

		String errorMessage = webDriver.findElement(By.id("errorDisco")).getText();

		//THEN
		Assertions.assertThat(errorMessage).isEqualTo("bad Format (yyyy-MM-dd HH:mm:ss)");
	}

	@Test
	public void addComputerShouldRedirectToDashboardAndTheNumberOfComputerIsTheSameWhenYouCancel() {

		//GIVEN
		String[] result = webDriver.findElement(By.id("homeTitle")).getText().split(" ");
		int oldNumber = Integer.parseInt(result[0]);

		webDriver.findElement(By.id("addComputer")).click();

		//WHEN
		webDriver.findElement(By.id("cancelAdd")).click();

		String page = webDriver.getCurrentUrl();

		result = webDriver.findElement(By.id("homeTitle")).getText().split(" ");
		int newNumber = Integer.parseInt(result[0]);

		//THEN
		Assertions.assertThat(page).isEqualTo("http://localhost:8080/computer-webapp/dashboard");
		Assertions.assertThat(newNumber).isEqualTo(oldNumber); 
	}

	@Test
	public void theNumberOfComputerIsDecreaseByOneWhenYouSuccessfullyDeleteAComputer() {

		//GIVEN
		String[] result = webDriver.findElement(By.id("homeTitle")).getText().split(" ");
		int oldNumber = Integer.parseInt(result[0]);

		//WHEN
		webDriver.findElement(By.id("lastPage")).click();
		webDriver.findElement(By.id("editComputer")).click();

		List<WebElement> table_element = webDriver.findElements(By.id("editCheck"));
		table_element.get(table_element.size() - 1).click();

		webDriver.findElement(By.id("deleteSelected")).click();
		Alert alert = webDriver.switchTo().alert();
		alert.accept();

		result = webDriver.findElement(By.id("homeTitle")).getText().split(" ");
		int newNumber = Integer.parseInt(result[0]);

		//THEN
		Assertions.assertThat(newNumber).isEqualTo(oldNumber - 1);
	}

	@Test
	public void theNumberOfComputerIsTheSameWhenYouCancelDeleteAComputer() {

		//GIVEN
		String[] result = webDriver.findElement(By.id("homeTitle")).getText().split(" ");
		int oldNumber = Integer.parseInt(result[0]);

		//WHEN
		webDriver.findElement(By.id("lastPage")).click();
		webDriver.findElement(By.id("editComputer")).click();

		List<WebElement> table_element = webDriver.findElements(By.id("editCheck"));
		table_element.get(table_element.size() - 1).click();

		webDriver.findElement(By.id("deleteSelected")).click();
		Alert alert = webDriver.switchTo().alert();
		alert.dismiss();

		result = webDriver.findElement(By.id("homeTitle")).getText().split(" ");
		int newNumber = Integer.parseInt(result[0]);

		//THEN
		Assertions.assertThat(newNumber).isEqualTo(oldNumber);
	}

	@Test
	public void theNumberOfComputerIsTheSameWhenYouSuccessfullyDeleteNothing() {

		//GIVEN
		String[] result = webDriver.findElement(By.id("homeTitle")).getText().split(" ");
		int oldNumber = Integer.parseInt(result[0]);

		//WHEN
		webDriver.findElement(By.id("lastPage")).click();
		webDriver.findElement(By.id("editComputer")).click();

		webDriver.findElement(By.id("deleteSelected")).click();
		Alert alert = webDriver.switchTo().alert();
		alert.accept();

		result = webDriver.findElement(By.id("homeTitle")).getText().split(" ");
		int newNumber = Integer.parseInt(result[0]);

		//THEN
		Assertions.assertThat(newNumber).isEqualTo(oldNumber);
	}

	@Test
	public void UpdateAComputerShouldShowTheInformationOfThisComputer() {

		//GIVEN

		webDriver.findElement(By.id("lastPage")).click();

		List<WebElement> table_element = webDriver.findElements(By.id("computerName"));
		String lastName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String lastIntroduced = table_element.get(table_element.size() - 1).getText().replaceAll("T", " ");

		table_element = webDriver.findElements(By.id("computerDisco"));
		String lastDiscontinued = table_element.get(table_element.size() - 1).getText().replaceAll("T", " ");

		table_element = webDriver.findElements(By.id("companyId"));
		String lastCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String lastId = table_element.get(table_element.size() - 1).getAttribute("value");

		//WHEN
		table_element = webDriver.findElements(By.id("computerLink"));
		table_element.get(table_element.size() - 1).click();

		String thisName = webDriver.findElement(By.id("computerName")).getAttribute("value");

		String thisIntroduced = webDriver.findElement(By.id("introduced")).getAttribute("value");

		String thisDiscontinued = webDriver.findElement(By.id("discontinued")).getAttribute("value");

		String thisCompany = webDriver.findElement(By.id("companyId")).getAttribute("value");

		String thisId = webDriver.findElement(By.id("computerId")).getText().split(":")[1].trim();

		//THEN
		Assertions.assertThat(thisId).isEqualTo(lastId);
		Assertions.assertThat(thisName).isEqualTo(lastName);
		Assertions.assertThat(thisIntroduced).isEqualTo(lastIntroduced);
		Assertions.assertThat(thisDiscontinued).isEqualTo(lastDiscontinued);
		Assertions.assertThat(thisCompany).isEqualTo(lastCompany);
	}

	@Test
	public void editComputerShouldRedirectToDashboardAndTheComputerIsTheSameWhenYouCancelEdit() {

		//GIVEN
		webDriver.findElement(By.id("lastPage")).click();

		List<WebElement> table_element = webDriver.findElements(By.id("computerName"));
		String lastName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String lastIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String lastDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String lastCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String lastId = table_element.get(table_element.size() - 1).getAttribute("value");

		//WHEN
		table_element = webDriver.findElements(By.id("computerLink"));
		table_element.get(table_element.size() - 1).click();

		webDriver.findElement(By.id("cancelEdit")).click();
		webDriver.findElement(By.id("lastPage")).click();

		table_element = webDriver.findElements(By.id("computerName"));
		String thisName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String thisIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String thisDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String thisCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String thisId = table_element.get(table_element.size() - 1).getAttribute("value");

		//THEN
		Assertions.assertThat(thisId).isEqualTo(lastId);
		Assertions.assertThat(thisName).isEqualTo(lastName);
		Assertions.assertThat(thisIntroduced).isEqualTo(lastIntroduced);
		Assertions.assertThat(thisDiscontinued).isEqualTo(lastDiscontinued);
		Assertions.assertThat(thisCompany).isEqualTo(lastCompany);
	}

	@Test
	public void editComputerShouldOnlyChangeTheNameOfTheComputerIfISuccessfullyChangeHisName() {

		//GIVEN
		webDriver.findElement(By.id("lastPage")).click();


		List<WebElement> table_element = webDriver.findElements(By.id("computerIntro"));
		String lastIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String lastDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String lastCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String lastId = table_element.get(table_element.size() - 1).getAttribute("value");

		//WHEN
		table_element = webDriver.findElements(By.id("computerLink"));
		table_element.get(table_element.size() - 1).click();



		webDriver.findElement(By.id("computerName")).clear();
		webDriver.findElement(By.id("computerName")).sendKeys("newName");

		webDriver.findElement(By.id("submitEdit")).click();
		webDriver.findElement(By.id("lastPage")).click();

		table_element = webDriver.findElements(By.id("computerName"));
		String thisName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String thisIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String thisDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String thisCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String thisId = table_element.get(table_element.size() - 1).getAttribute("value");

		//THEN
		Assertions.assertThat(thisId).isEqualTo(lastId);
		Assertions.assertThat(thisName).isEqualTo("newName");
		Assertions.assertThat(thisIntroduced).isEqualTo(lastIntroduced);
		Assertions.assertThat(thisDiscontinued).isEqualTo(lastDiscontinued);
		Assertions.assertThat(thisCompany).isEqualTo(lastCompany);
	}

	@Test
	public void editComputerShouldOnlyChangeTheIntroducedOfTheComputerIfISuccessfullyChangeHisIntroduced() {

		//GIVEN
		webDriver.findElement(By.id("lastPage")).click();

		List<WebElement> table_element = webDriver.findElements(By.id("computerName"));
		String lastName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String lastDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String lastCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String lastId = table_element.get(table_element.size() - 1).getAttribute("value");

		//WHEN
		table_element = webDriver.findElements(By.id("computerLink"));
		table_element.get(table_element.size() - 1).click();



		webDriver.findElement(By.id("introduced")).clear();
		webDriver.findElement(By.id("introduced")).sendKeys("2011-11-11 11:11:11");

		webDriver.findElement(By.id("submitEdit")).click();
		webDriver.findElement(By.id("lastPage")).click();

		table_element = webDriver.findElements(By.id("computerName"));
		String thisName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String thisIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String thisDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String thisCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String thisId = table_element.get(table_element.size() - 1).getAttribute("value");

		//THEN
		Assertions.assertThat(thisId).isEqualTo(lastId);
		Assertions.assertThat(thisName).isEqualTo(lastName);
		Assertions.assertThat(thisIntroduced).isEqualTo("2011-11-11 11:11:11");
		Assertions.assertThat(thisDiscontinued).isEqualTo(lastDiscontinued);
		Assertions.assertThat(thisCompany).isEqualTo(lastCompany);
	}

	@Test
	public void editComputerShouldOnlyChangeTheDiscontinuedOfTheComputerIfISuccessfullyChangeHisDiscontinued() {

		//GIVEN
		webDriver.findElement(By.id("lastPage")).click();

		List<WebElement> table_element = webDriver.findElements(By.id("computerName"));
		String lastName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String lastIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String lastCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String lastId = table_element.get(table_element.size() - 1).getAttribute("value");

		//WHEN
		table_element = webDriver.findElements(By.id("computerLink"));
		table_element.get(table_element.size() - 1).click();



		webDriver.findElement(By.id("discontinued")).clear();
		webDriver.findElement(By.id("discontinued")).sendKeys("2013-11-11 11:11:11");

		webDriver.findElement(By.id("submitEdit")).click();
		webDriver.findElement(By.id("lastPage")).click();

		table_element = webDriver.findElements(By.id("computerName"));
		String thisName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String thisIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String thisDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String thisCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String thisId = table_element.get(table_element.size() - 1).getAttribute("value");

		//THEN
		Assertions.assertThat(thisId).isEqualTo(lastId);
		Assertions.assertThat(thisName).isEqualTo(lastName);
		Assertions.assertThat(thisIntroduced).isEqualTo(lastIntroduced);
		Assertions.assertThat(thisDiscontinued).isEqualTo("2013-11-11 11:11:11");
		Assertions.assertThat(thisCompany).isEqualTo(lastCompany);
	}

	@Test
	public void editComputerShouldOnlyChangeTheCompanyOfTheComputerIfISuccessfullyChangeHisCompany() {

		//GIVEN
		webDriver.findElement(By.id("lastPage")).click();

		List<WebElement> table_element = webDriver.findElements(By.id("computerName"));
		String lastName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String lastIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String lastDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("editCheck"));
		String lastId = table_element.get(table_element.size() - 1).getAttribute("value");

		//WHEN
		table_element = webDriver.findElements(By.id("computerLink"));
		table_element.get(table_element.size() - 1).click();



		WebElement select = webDriver.findElement(By.id("companyId"));
		List<WebElement> options = select.findElements(By.tagName("option"));
		for(WebElement option : options){
			if(option.getText().equals("IBM")) {
				option.click();
				break;
			}
		}

		webDriver.findElement(By.id("submitEdit")).click();
		webDriver.findElement(By.id("lastPage")).click();

		table_element = webDriver.findElements(By.id("computerName"));
		String thisName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String thisIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String thisDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String thisCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String thisId = table_element.get(table_element.size() - 1).getAttribute("value");

		//THEN
		Assertions.assertThat(thisId).isEqualTo(lastId);
		Assertions.assertThat(thisName).isEqualTo(lastName);
		Assertions.assertThat(thisIntroduced).isEqualTo(lastIntroduced);
		Assertions.assertThat(thisDiscontinued).isEqualTo(lastDiscontinued);
		Assertions.assertThat(thisCompany).isEqualTo("13");
	}

	@Test
	public void editComputerWontChangeTheNameOfTheComputerIfNoNameIsSend() {

		//GIVEN
		webDriver.findElement(By.id("lastPage")).click();

		List<WebElement> table_element = webDriver.findElements(By.id("computerName"));
		String lastName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String lastIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String lastDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String lastCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String lastId = table_element.get(table_element.size() - 1).getAttribute("value");

		//WHEN
		table_element = webDriver.findElements(By.id("computerLink"));
		table_element.get(table_element.size() - 1).click();


		webDriver.findElement(By.id("computerName")).clear();
		webDriver.findElement(By.id("submitEdit")).click();


		webDriver.findElement(By.id("lastPage")).click();

		table_element = webDriver.findElements(By.id("computerName"));
		String thisName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String thisIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String thisDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String thisCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String thisId = table_element.get(table_element.size() - 1).getAttribute("value");

		//THEN
		Assertions.assertThat(thisId).isEqualTo(lastId);
		Assertions.assertThat(thisName).isEqualTo(lastName);
		Assertions.assertThat(thisIntroduced).isEqualTo(lastIntroduced);
		Assertions.assertThat(thisDiscontinued).isEqualTo(lastDiscontinued);
		Assertions.assertThat(thisCompany).isEqualTo(lastCompany);
	}

	@Test
	public void editComputerWontChangeTheIntroducedOfTheComputerIfNoIntroducedIsSend() {

		//GIVEN
		webDriver.findElement(By.id("lastPage")).click();

		List<WebElement> table_element = webDriver.findElements(By.id("computerName"));
		String lastName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String lastIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String lastDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String lastCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String lastId = table_element.get(table_element.size() - 1).getAttribute("value");

		//WHEN
		table_element = webDriver.findElements(By.id("computerLink"));
		table_element.get(table_element.size() - 1).click();


		webDriver.findElement(By.id("introduced")).clear();
		webDriver.findElement(By.id("submitEdit")).click();


		webDriver.findElement(By.id("lastPage")).click();

		table_element = webDriver.findElements(By.id("computerName"));
		String thisName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String thisIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String thisDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String thisCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String thisId = table_element.get(table_element.size() - 1).getAttribute("value");

		//THEN
		Assertions.assertThat(thisId).isEqualTo(lastId);
		Assertions.assertThat(thisName).isEqualTo(lastName);
		Assertions.assertThat(thisIntroduced).isEqualTo(lastIntroduced);
		Assertions.assertThat(thisDiscontinued).isEqualTo(lastDiscontinued);
		Assertions.assertThat(thisCompany).isEqualTo(lastCompany);
	}

	@Test
	public void editComputerWontChangeTheDiscontinuedOfTheComputerIfNoDiscontinuedIsSend() {

		//GIVEN
		webDriver.findElement(By.id("lastPage")).click();

		List<WebElement> table_element = webDriver.findElements(By.id("computerName"));
		String lastName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String lastIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String lastDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String lastCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String lastId = table_element.get(table_element.size() - 1).getAttribute("value");

		//WHEN
		table_element = webDriver.findElements(By.id("computerLink"));
		table_element.get(table_element.size() - 1).click();


		webDriver.findElement(By.id("discontinued")).clear();
		webDriver.findElement(By.id("submitEdit")).click();


		webDriver.findElement(By.id("lastPage")).click();

		table_element = webDriver.findElements(By.id("computerName"));
		String thisName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String thisIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String thisDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String thisCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String thisId = table_element.get(table_element.size() - 1).getAttribute("value");

		//THEN
		Assertions.assertThat(thisId).isEqualTo(lastId);
		Assertions.assertThat(thisName).isEqualTo(lastName);
		Assertions.assertThat(thisIntroduced).isEqualTo(lastIntroduced);
		Assertions.assertThat(thisDiscontinued).isEqualTo(lastDiscontinued);
		Assertions.assertThat(thisCompany).isEqualTo(lastCompany);
	}

	@Test
	public void editComputerWontChangeTheCompanyOfTheComputerIfNoCompanyIsSend() {

		//GIVEN
		webDriver.findElement(By.id("lastPage")).click();

		List<WebElement> table_element = webDriver.findElements(By.id("computerName"));
		String lastName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String lastIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String lastDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String lastCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String lastId = table_element.get(table_element.size() - 1).getAttribute("value");

		//WHEN
		table_element = webDriver.findElements(By.id("computerLink"));
		table_element.get(table_element.size() - 1).click();


		WebElement select = webDriver.findElement(By.id("companyId"));
		List<WebElement> options = select.findElements(By.tagName("option"));
		for(WebElement option : options){
			if(option.getText().equals("--")) {
				option.click();
				break;
			}
		}
		webDriver.findElement(By.id("submitEdit")).click();

		webDriver.findElement(By.id("lastPage")).click();

		table_element = webDriver.findElements(By.id("computerName"));
		String thisName = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerIntro"));
		String thisIntroduced = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("computerDisco"));
		String thisDiscontinued = table_element.get(table_element.size() - 1).getText();

		table_element = webDriver.findElements(By.id("companyId"));
		String thisCompany = table_element.get(table_element.size() - 1).getAttribute("value");

		table_element = webDriver.findElements(By.id("editCheck"));
		String thisId = table_element.get(table_element.size() - 1).getAttribute("value");

		//THEN
		Assertions.assertThat(thisId).isEqualTo(lastId);
		Assertions.assertThat(thisName).isEqualTo(lastName);
		Assertions.assertThat(thisIntroduced).isEqualTo(lastIntroduced);
		Assertions.assertThat(thisDiscontinued).isEqualTo(lastDiscontinued);
		Assertions.assertThat(thisCompany).isEqualTo(lastCompany);
	}

	@Test
	public void editComputerShouldShowAnErrorMessageWhenTheDateFormatOfIntroducedDateIsInvalid() {

		//GIVEN
		webDriver.findElement(By.id("lastPage")).click();

		List<WebElement> table_element = webDriver.findElements(By.id("computerLink"));
		table_element.get(table_element.size() - 1).click();

		//WHEN
		webDriver.findElement(By.id("introduced")).clear();
		webDriver.findElement(By.id("introduced")).sendKeys("bad format");

		webDriver.findElement(By.id("submitEdit")).click();

		String errorMessage = webDriver.findElement(By.id("errorIntro")).getText();

		//THEN
		Assertions.assertThat(errorMessage).isEqualTo("bad Format (yyyy-MM-dd HH:mm:ss)");
	}

	@Test
	public void editComputerShouldShowAnErrorMessageWhenTheDateFormatOfDiscontinuedDateIsInvalid() {

		//GIVEN
		webDriver.findElement(By.id("lastPage")).click();

		List<WebElement> table_element = webDriver.findElements(By.id("computerLink"));
		table_element.get(table_element.size() - 1).click();

		//WHEN
		webDriver.findElement(By.id("discontinued")).clear();
		webDriver.findElement(By.id("discontinued")).sendKeys("bad format");

		webDriver.findElement(By.id("submitEdit")).click();

		String errorMessage = webDriver.findElement(By.id("errorDisco")).getText();

		//THEN
		Assertions.assertThat(errorMessage).isEqualTo("bad Format (yyyy-MM-dd HH:mm:ss)");
	}

	@Test
	public void ChangeTheLimitOfThePaginationTo10ShouldShow10ComputerMaxByPage() {

		//GIVEN
		webDriver.findElement(By.id("buttonTen")).click();

		//WHEN
		List<WebElement> table_element = webDriver.findElements(By.id("computerLink"));

		//THEN
		Assertions.assertThat(table_element.size()).isLessThanOrEqualTo(10);
	}

	@Test
	public void ChangeTheLimitOfThePaginationTo50ShouldShow50ComputerMaxByPage() {

		//GIVEN
		webDriver.findElement(By.id("buttonFifty")).click();

		//WHEN
		List<WebElement> table_element = webDriver.findElements(By.id("computerLink"));

		//THEN
		Assertions.assertThat(table_element.size()).isLessThanOrEqualTo(50);
	}

	@Test
	public void ChangeTheLimitOfThePaginationTo100ShouldShow100ComputerMaxByPage() {

		//GIVEN
		webDriver.findElement(By.id("buttonHundred")).click();

		//WHEN
		List<WebElement> table_element = webDriver.findElements(By.id("computerLink"));

		//THEN
		Assertions.assertThat(table_element.size()).isLessThanOrEqualTo(100);
	}

	@Test
	public void ifYourAreOnTheLastPageTheButtonLastPageDisappear() {

		//GIVEN
		webDriver.findElement(By.id("lastPage")).click();

		//WHEN
		try {
			webDriver.findElement(By.id("lastPage")).click();
			Assertions.fail("the test should raise an exception");
		} catch (Exception e) {
			//THEN
			Assertions.assertThat(e.getClass().toString()).isEqualTo("class org.openqa.selenium.NoSuchElementException");
		}
	}

	@Test
	public void ifYourAreOnTheLastPageTheButtonNextPageDisappear() {

		//GIVEN
		webDriver.findElement(By.id("lastPage")).click();

		//WHEN
		try {
			webDriver.findElement(By.id("next")).click();
			Assertions.fail("the test should raise an exception");
		} catch (Exception e) {
			//THEN
			Assertions.assertThat(e.getClass().toString()).isEqualTo("class org.openqa.selenium.NoSuchElementException");
		}
	}

	@Test
	public void ifYourAreOnTheFirstPageTheButtonFirstPageDisappear() {

		//GIVEN

		//WHEN
		try {
			webDriver.findElement(By.id("firstPage")).click();
			Assertions.fail("the test should raise an exception");
		} catch (Exception e) {
			//THEN
			Assertions.assertThat(e.getClass().toString()).isEqualTo("class org.openqa.selenium.NoSuchElementException");
		}
	}

	@Test
	public void ifYourAreOnTheFirstPageTheButtonPreviousPageDisappear() {

		//GIVEN

		//WHEN
		try {
			webDriver.findElement(By.id("previous")).click();
			Assertions.fail("the test should raise an exception");
		} catch (Exception e) {
			//THEN
			Assertions.assertThat(e.getClass().toString()).isEqualTo("class org.openqa.selenium.NoSuchElementException");
		}
	}

	@Test
	public void lastPageShouldGoToTheLastPage() {

		//GIVEN
		String[] result = webDriver.findElement(By.id("homeTitle")).getText().split(" ");
		int oldNumber = Integer.parseInt(result[0]);
		int nbPages = oldNumber / 10;
		if ((oldNumber % 10) > 0) {
			nbPages += 1;
		}

		//WHEN
		webDriver.findElement(By.id("lastPage")).click();
		WebElement table_element = webDriver.findElement(By.className("active"));
		int newNumber = Integer.parseInt(table_element.getText());

		//THEN
		Assertions.assertThat(newNumber).isEqualTo(nbPages);
	}

	@Test
	public void firstPageShouldGoToThefirstPage() {

		//GIVEN
		webDriver.findElement(By.id("lastPage")).click();

		//WHEN
		webDriver.findElement(By.id("firstPage")).click();
		WebElement table_element = webDriver.findElement(By.className("active"));
		int newNumber = Integer.parseInt(table_element.getText());

		//THEN
		Assertions.assertThat(newNumber).isEqualTo(1);
	}

	@Test
	public void nextPageShouldIncreaseTheNumberOfThePageByOneIfPossible() {

		//GIVEN
		WebElement table_element = webDriver.findElement(By.className("active"));
		int oldPage = Integer.parseInt(table_element.getText());

		//WHEN
		int newPage = oldPage;
		try {
			webDriver.findElement(By.id("next")).click();
			table_element = webDriver.findElement(By.className("active"));
			newPage = Integer.parseInt(table_element.getText());
			//THEN
			Assertions.assertThat(newPage).isEqualTo(oldPage + 1);
		} catch (Exception e) {
			//THEN
			Assertions.assertThat(newPage).isEqualTo(oldPage);
		}
	}

	@Test
	public void firstPageShouldDecreaseTheNumberOfThePageByOneIfPossible() {

		//GIVEN
		WebElement table_element = webDriver.findElement(By.className("active"));
		int oldPage = Integer.parseInt(table_element.getText());
		int newPage = oldPage;

		//WHEN
		try {
			webDriver.findElement(By.id("next")).click();
			table_element = webDriver.findElement(By.className("active"));
			oldPage = Integer.parseInt(table_element.getText());
			webDriver.findElement(By.id("previous")).click();
			table_element = webDriver.findElement(By.className("active"));
			newPage = Integer.parseInt(table_element.getText());
			//THEN
			Assertions.assertThat(newPage).isEqualTo(oldPage - 1);
		} catch (Exception e) {
			//THEN
			Assertions.assertThat(newPage).isEqualTo(oldPage);
		}
	}
}
