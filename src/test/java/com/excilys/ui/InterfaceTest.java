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
	}
	
	@AfterClass
	public static void close() {
        webDriver.close();
	}
	
	@Before
	public void initFirstPageAtDashboard() {
        webDriver.get("http://localhost:8080/computer-database/dashboard");
	}

	@Test
	public void theNumberOfComputerIsIncreaseByOneWhenYouSuccessfullyAddAComputerAndTheLastComputerIsTheAddedComputer() {
		
		//GIVEN

		
        //WHEN
        
        String[] result = webDriver.findElement(By.id("homeTitle")).getText().split(" ");
        int oldNumber = Integer.parseInt(result[0]);
        
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
        
        webDriver.findElement(By.tagName("form")).submit();
        
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
		Assertions.assertThat("2012-12-12T12:12:12").isEqualTo(lastIntroduced);
		Assertions.assertThat("2012-12-12T12:12:12").isEqualTo(lastDiscontinued);
		Assertions.assertThat("RCA").isEqualTo(lastCompany);
		Assertions.assertThat(newNumber).isEqualTo(oldNumber + 1);

	}
	
	@Test
	public void addComputerShouldShowAnErrorMessageWhenTheNameIsNotPresent() {
		//GIVEN
		
		//WHEN
        webDriver.findElement(By.id("addComputer")).click();
        webDriver.findElement(By.tagName("form")).submit();
        
        String errorMessage = webDriver.findElement(By.id("errorName")).getText();
        
        //THEN
		Assertions.assertThat(errorMessage).isEqualTo("name is required");
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
        
        webDriver.findElement(By.tagName("form")).submit();

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
        
        webDriver.findElement(By.tagName("form")).submit();

        String errorMessage = webDriver.findElement(By.id("errorDisco")).getText();
        
        //THEN
		Assertions.assertThat(errorMessage).isEqualTo("bad Format (yyyy-MM-dd HH:mm:ss)");
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
}
