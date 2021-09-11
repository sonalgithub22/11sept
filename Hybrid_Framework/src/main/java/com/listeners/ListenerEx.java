package com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.testBase.TestBase;

public class ListenerEx extends TestBase implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getName());
		log.info("TestCase ready to start with name: " + result.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("TestCase passed successfully with name: " + result.getName());
		test.log(Status.PASS, "Testcase passed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.info("TestCase failed with name: " + result.getName());
		log.info(result.getThrowable());
		test.log(Status.FAIL, "Testcase failed ");
		test.log(Status.FAIL, result.getThrowable());
		String path = takeScreenShot(result.getName());
		test.addScreenCaptureFromPath(path);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.info("TestCase skipped with name: " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		log.info("test suite is started: " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		log.info("test suite finished: " + context.getName());
		report.flush();
	}

}
