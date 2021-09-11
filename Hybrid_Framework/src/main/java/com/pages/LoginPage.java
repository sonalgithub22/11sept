package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.PropertiesUtils;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	private WebElement uname;

	@FindBy(id = "password")
	private WebElement pass;

	@FindBy(xpath = "//button")
	private WebElement loginButton;

	public void validLogin() {
		uname.sendKeys(PropertiesUtils.getProperty("username"));
		pass.sendKeys(PropertiesUtils.getProperty("password"));
		loginButton.click();
	}

}
