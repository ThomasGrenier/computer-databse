package com.excilys.ui;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class InterfaceTest {

	@Test
	public void test() {
		
		WebDriver webDriver = new FirefoxDriver();
        //WHEN

        webDriver.get("http://localhost:8080/computer-database/dashboard");
        
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

        webDriver.close();
	}
}
