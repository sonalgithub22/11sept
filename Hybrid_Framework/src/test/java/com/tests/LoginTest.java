package com.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.pages.LoginPage;
import com.testBase.TestBase;

public class LoginTest extends TestBase {

	WebDriver driver = null;
	LoginPage lp = null;

	@BeforeSuite
	public void setup() {
		driver = initialization();
		reportInitialization();
		lp = new LoginPage(driver);
	}

	@Test(priority = 1)
	public void verifyTitle() {
		Assert.assertEquals(driver.getTitle(), "JavaByKiran | Log in");
	}

	@Test(priority = 2)
	public void loginTest() {

		lp.validLogin();
		Assert.assertEquals(driver.getTitle(), "JavaByKiran | dashboard");
	}

	@AfterSuite
	public void tearDown() {
		driver.close();
	}

}
