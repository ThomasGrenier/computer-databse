package com.excilys.ui;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;
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

	@Test
	public void TheNumberOfComputerIsIncreaseByOneWhenYouSuccessfullyAddAComputer() {
		
		//GIVEN
		//webDriver = new FirefoxDriver();

        webDriver.get("http://localhost:8080/computer-database/dashboard");
        
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
        
        //WHEN
        webDriver.findElement(By.tagName("form")).submit();

        
        //THEN

        result = webDriver.findElement(By.id("homeTitle")).getText().split(" ");
        int newNumber = Integer.parseInt(result[0]);
        
		Assertions.assertThat(newNumber).isEqualTo(oldNumber + 1);

        webDriver.close();
	}
}
