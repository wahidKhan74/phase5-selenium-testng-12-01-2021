package com.simplilearn.ecom.group;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BrowserSpecificGroupTest {
	
	String amazonUrl =  "https://www.amazon.in/";
	String facebookUrl = "https://www.facebook.com/";
	
	String chromePath = "drivers/linux/chromedriver";
	String firefoxPath = "drivers/linux/geckodriver";
	
	WebDriver driverOne;
	WebDriver driverTwo;	
	WebDriverWait wait;
	
	// test group for chome
	@Test(groups="ChromeOnly")
	public void lauchChromeTest() {
		System.setProperty("webdriver.chrome.driver", chromePath);
		driverOne = new ChromeDriver();
		driverOne.get(amazonUrl);
	}
	
	@Test(groups="ChromeOnly", dependsOnMethods="lauchChromeTest",priority = 0)
	void testAmazonHomepage() {
		String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actual = driverOne.getTitle();
		assertEquals(expected, actual);
	}
	
	@Test(groups = "ChromeOnly", dependsOnMethods = "lauchChromeTest", priority = 1)
	public void testHompageSourceUrl() {
		assertEquals(amazonUrl, driverOne.getCurrentUrl());
	}

	@Test(groups="ChromeOnly", dependsOnMethods="lauchChromeTest",priority = 2)
	void testAmazonSearch() throws InterruptedException {
		// find search box
		WebElement searchBox = driverOne.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("Iphone 12 max pro");
		searchBox.submit();
		
		String expected = "Amazon.in : Iphone 12 max pro";
		String actual = driverOne.getTitle();
		Thread.sleep(2000);
		assertEquals(expected, actual);
	}
	
	@Test(groups = "ChromeOnly", dependsOnMethods = "lauchChromeTest", priority = 3)
	public void closeChromeBrowser() {
		driverOne.close();
	}

	
	@Test(groups = "FirefoxOnly")
	public void lauchFirefoxTest() {
		System.setProperty("webdriver.gecko.driver", firefoxPath);
		driverTwo = new FirefoxDriver();
		wait = new WebDriverWait(driverTwo, Duration.ofSeconds(40));
		driverTwo.get(facebookUrl);
	}
	
	@Test(groups = "FirefoxOnly", dependsOnMethods="lauchFirefoxTest", priority=0)
	public void testFacebookTitle() {
		String expected = "Facebook - Log In or Sign Up";
		assertEquals(driverTwo.getTitle(), expected);
	}
	
	@Test(groups = "FirefoxOnly", dependsOnMethods="lauchFirefoxTest", priority=1)
	void testFacebookHomepage() {
		WebElement email= driverTwo.findElement(By.cssSelector("#email"));
		WebElement password = driverTwo.findElement(By.cssSelector("#pass"));
		WebElement submit = driverTwo.findElement(By.name("login"));
		
		assertEquals(true, email.isDisplayed());
		assertEquals(true, password.isDisplayed());
		assertEquals(true, submit.isDisplayed());
	}
	

	@Test(groups = "FirefoxOnly", dependsOnMethods="lauchFirefoxTest", priority=2)
	void testFacebookFailureLogin() {
		WebElement email= driverTwo.findElement(By.xpath("//*[@id=\"email\"]"));
		WebElement password = driverTwo.findElement(By.xpath("//*[@id=\"pass\"]"));
		WebElement submitButton = driverTwo.findElement(By.name("login"));
		
		email.sendKeys("abc@gmail.com");
		password.sendKeys("abc@123");
		submitButton.submit();
		
		// takes time to get loaded.
		// WebElement error = driver.findElement(By.cssSelector("#error_box > div.fsl.fwb.fcb"));
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#error_box > div.fsl.fwb.fcb")));
		assertEquals("Wrong Credentials", error.getText());
	}
	
	@Test(groups = "FirefoxOnly", dependsOnMethods="lauchFirefoxTest", priority=3)
	public void closeFirefox() {
		driverTwo.close();
	}

}
