package com.simplilearn.ecom.facebook;


import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FacebookLoginTest {

	// step 1: create a source test url
	String siteURl = "https://www.facebook.com/";
	String driverPath = "drivers/linux/chromedriver";
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void beforeMethod() {
		// step 2: set selenium system properties
		System.setProperty("webdriver.chrome.driver", driverPath);
		// step 3: create a web driver instance
		driver = new ChromeDriver();
		// explicit wait : explicit wait is used to tell the web driver to wait for a
		// certain condition
		// or maximum time to be exceeded before throwing " Element Not Visible
		// exception"
		wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		// step 4: launch browser
		driver.get(siteURl);
	}

	@AfterMethod
	public void afterMethod() {
		// step 6: close driver
		driver.close();
	}
	
	@Test
	void testFacebookHomepage() {
		WebElement email= driver.findElement(By.cssSelector("#email"));
		WebElement password = driver.findElement(By.cssSelector("#pass"));
		WebElement submit = driver.findElement(By.name("login"));
		
		assertEquals(true, email.isDisplayed());
		assertEquals(true, password.isDisplayed());
		assertEquals(true, submit.isDisplayed());
	}
	

	@Test
	void testFacebookFailureLogin() {
		WebElement email= driver.findElement(By.xpath("//*[@id=\"email\"]"));
		WebElement password = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
		WebElement submitButton = driver.findElement(By.name("login"));
		
		email.sendKeys("abc@gmail.com");
		password.sendKeys("abc@123");
		submitButton.submit();
		
		// takes time to get loaded.
		// WebElement error = driver.findElement(By.cssSelector("#error_box > div.fsl.fwb.fcb"));
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#error_box > div.fsl.fwb.fcb")));
		assertEquals("Wrong Credentials", error.getText());
	}
}
