package com.simplilearn.ecom.amazon;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class AmazonNavLinkTest {
	
	// step 1: create a source test url
	String siteURl = "https://www.amazon.in/";
	String driverPath = "drivers/linux/chromedriver";
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		// step 2: set selenium system properties
		System.setProperty("webdriver.chrome.driver", driverPath);
		// step 3: create a web driver instance
		driver = new ChromeDriver();
		// step 4: launch browser
		driver.get(siteURl);
	}

	@AfterMethod
	public void afterMethod() {
		// step 6: close driver
		driver.close();
	}

	@Test
	void testAmazonMobileLink() {
		// find mobile link
		WebElement mobileLink = driver.findElement(By.cssSelector("#nav-xshop > a:nth-child(2)"));
		mobileLink.click();

		String expected = "Mobile Phones: Buy New Mobiles Online at Best Prices in India | Buy Cell Phones Online - Amazon.in";
		String actual = driver.getTitle();
		assertEquals(expected, actual);
	}
	
	@Test
	void testAmazonBestsellerLink() {
		WebElement mobileLink = driver.findElement(By.cssSelector("#nav-xshop > a:nth-child(3)"));
		mobileLink.click();

		String expected = "Amazon.in Bestsellers: The most popular items on Amazon";
		String actual = driver.getTitle();
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testAmazonElectronicLink() {
		WebElement mobileLink = driver.findElement(By.cssSelector("#nav-xshop > a:nth-child(4)"));
		mobileLink.click();
		String expected = "Electronics Store: Buy Electronics products Online at Best Prices in India at Amazon.in";
		String actual = driver.getTitle();
		assertEquals(expected, actual);
	}
}
