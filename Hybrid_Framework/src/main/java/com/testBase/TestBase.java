package com.testBase;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.utility.PropertiesUtils;

public class TestBase {

	public static WebDriver driver;
	public static Logger log = Logger.getLogger(TestBase.class);
	public static ExtentReports report;
	public static ExtentTest test;
	public static ExtentSparkReporter spark;

	public static WebDriver initialization() {
		System.out.println("initializing browser");// console
		log.info("initializing browser");// file+console
		String browser = PropertiesUtils.getProperty("browser");
		log.info(browser + " browser is initialized ");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		}
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:/chromedriver.exe");
			driver = new FirefoxDriver();
		}
		log.info("maximising browser window");
		driver.manage().window().maximize();
		log.info("applying common waits over a browser");
		driver.get(PropertiesUtils.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return driver;
	}

	public void reportInitialization() {
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/target/Reports/ExtentReport.html");
		report = new ExtentReports();
		report.setSystemInfo("Host Name", "Sonal Patil");
		report.setSystemInfo("Platform", "Windows");
		report.setSystemInfo("Environment", "Test Automation");
		report.attachReporter(spark);
	}

	public String takeScreenShot(String name) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + name + ".jpg";
		File destination = new File(path);
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public static void main(String[] args) {
		initialization();
	}
}
